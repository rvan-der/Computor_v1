package fr.rvander.computorV1.polynomial;

import fr.rvander.computorV1.polynomial.Polynomial;
import fr.rvander.computorV1.exceptions.ComputorV1PolynomialException;
import java.text.DecimalFormat;


public final class PolynomialSolver {

    private static DecimalFormat decimalFormat = new DecimalFormat("#.######");
    

    private PolynomialSolver() {};


    public static void solve(Polynomial p_polynomial) throws ComputorV1PolynomialException {
        if (p_polynomial == null)
            throw new ComputorV1PolynomialException("Recieved a null pointer in solver.");
        switch (p_polynomial.degree) {
            case 0:
                PolynomialSolver.solveDegree0(p_polynomial);
                break;
            case 1:
                PolynomialSolver.solveDegree1(p_polynomial);
                break;
            case 2:
                PolynomialSolver.solveDegree2(p_polynomial);
                break;
            default:
                System.out.println("Sorry, I'm a noob and can't solve polynomial equations above the 2nd degree.");
        }
    }


    private static void solveDegree0(Polynomial p_polynomial) {
        if (p_polynomial.coefs.get(0) == 0)
            System.out.println("This polynomial has infinite solutions in the real numbers.");
        else
            System.out.print("This polynomial is undefined and has no solutions.");
    }


    private static void solveDegree1(Polynomial p_polynomial) {
        Double a = p_polynomial.coefs.get(1);
        Double b = p_polynomial.coefs.get(0);
        Double root = -b / a;
        DecimalFormat df = PolynomialSolver.decimalFormat;
        System.out.println(String.format("This polynomial has a unique real solution:\n",
                df.format(root)));
    }

    private static void solveDegree2(Polynomial p_polynomial) {
        Double a = p_polynomial.coefs.get(2);
        Double b = p_polynomial.coefs.get(1);
        Double c = p_polynomial.coefs.get(0);
        Double delta = (b * b) - (4 * a * c);
        Double root1;
        Double root2;
        DecimalFormat df = PolynomialSolver.decimalFormat;

        if (delta == 0) {
            root1 = -b / (2 * a);
            System.out.println(String.format("This polynomial has a unique real solution:\n%s",
                    root1 != 0 ? df.format(root1) : "0"));
        }
        else if (delta > 0) {
            root1 = (-b - Math.sqrt(delta)) / (2 * a);
            root2 = (-b + Math.sqrt(delta)) / (2 * a);
            System.out.println(String.format("This polynomial has two real solutions:\n%s and %s",
                    root1 != 0 ? df.format(root1) : "0", root2 != 0 ? df.format(root2) : "0"));
        }
        else {
            String fmtB = df.format(-b);
            String fmtDelta = df.format(Math.sqrt(Math.abs(delta)));
            String fmt2A = df.format(2 * a);
            if (b != 0) {
                System.out.println(String.format("This polynomial has two complex solutions:\n" +
                        "(%s - i * %s) / %s and (%s + i * %s) / %s",
                        fmtB, fmtDelta, fmt2A, fmtB, fmtDelta, fmt2A));
            }
            else {
                System.out.println(String.format("This polynomial has two complex solutions:\n" +
                        "(i * %s) / %s and (i * %s) / %s",
                        fmtDelta, fmt2A, fmtDelta, fmt2A));
            }
        }
    }
}