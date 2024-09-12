package fr.rvander.computorV1.polynomial;

import java.util.ArrayList;
import java.text.DecimalFormat;
import fr.rvander.computorV1.exceptions.ComputorV1PolynomialException;


public class Polynomial {

    public static final int maxDegreeSolving = 1;
    public ArrayList<Double> coefs = null;
    public int degree = 0;


    protected Polynomial(ArrayList<Double> p_coefs) throws ComputorV1PolynomialException {
        if (p_coefs == null)
            throw new ComputorV1PolynomialException("null pointer received in Polynomial constructor");
        this.coefs = this.trimZeroes(p_coefs);
        this.degree = this.coefs.size() - 1;
    }


    private ArrayList<Double> trimZeroes(ArrayList<Double> p_array) {
        ArrayList<Double> resArray = new ArrayList<Double>(p_array);
        for (int i = p_array.size() - 1; p_array.get(i) == 0 && i > 0; i--)
            resArray.remove(i);
        return resArray;
    }


    public Polynomial addPolynomial(Polynomial polynomialB) throws ComputorV1PolynomialException {
        ArrayList<Double> resCoefs = new ArrayList<Double>();
        Double a, b;
        a = b = 0.0;
        for (int exp = 0; exp <= this.degree || exp <= polynomialB.degree; exp++) {
            a = b = 0.0;
            if (exp <= this.degree)
                a = this.coefs.get(exp);
            if (exp <= polynomialB.degree)
                b = polynomialB.coefs.get(exp);
            resCoefs.add(a + b);
        }
        return new Polynomial(resCoefs);
    }


    public Polynomial substractPolynomial(Polynomial polynomialB) throws ComputorV1PolynomialException {
        ArrayList<Double> resCoefs = new ArrayList<Double>();
        Double a, b;
        a = b = 0.0;
        for (int exp = 0; exp <= this.degree || exp <= polynomialB.degree; exp++) {
            a = b = 0.0;
            if (exp <= this.degree)
                a = this.coefs.get(exp);
            if (exp <= polynomialB.degree)
                b = polynomialB.coefs.get(exp);
            resCoefs.add(a - b);
        }
        return new Polynomial(resCoefs);
    }


    @Override
    public String toString() {
        String res = "";
        Double coef;
        DecimalFormat decFmt = new DecimalFormat("#.######");
        for (int exp = 0; exp <= this.degree; exp++) {
            coef = this.coefs.get(exp);
            if (coef != 0) {
                if (res.equals(""))
                    res += String.format("%s * X^%d", decFmt.format(coef), exp);
                else
                    res += String.format(" %s %s * X^%d", coef < 0 ? "-" : "+", decFmt.format(Math.abs(coef)), exp);
            }
        }
        if (res.equals(""))
            res = "0 * X^0";
        return res;
    }
}