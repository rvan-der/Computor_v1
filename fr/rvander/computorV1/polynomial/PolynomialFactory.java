package fr.rvander.computorV1.polynomial;

import fr.rvander.computorV1.polynomial.Polynomial;
import fr.rvander.computorV1.exceptions.ComputorV1PolynomialException;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;
import java.util.stream.*;
import java.util.ArrayList;
import java.util.Collections;


public class PolynomialFactory {

    private static PolynomialFactory instance = null;


    private PolynomialFactory() {}


    public static PolynomialFactory getInstance () {
        if (instance == null)
            instance = new PolynomialFactory();
        return instance;
    }


    public Polynomial newPolynomial(String p_string) throws ComputorV1PolynomialException {
        p_string = p_string.trim();
        if (!p_string.matches("^-?\\d+(?:\\.\\d+)? \\* X\\^\\d+(?: [-+] \\d+(?:\\.\\d+)? \\* X\\^\\d+)*$")) {
            throw new ComputorV1PolynomialException(
                    "Wrong format for polynomial. Required format: \"a * X^p + b * X^p - c * X^p ...\"");
        }
        ArrayList<Double> coefs = null;
        
        Stream<String> degreesStrings = Pattern.compile("(?<=\\^)\\d+")
                .matcher(p_string)
                .results()
                .map(MatchResult::group);
        
        int maxDegree;
        try {
            maxDegree = degreesStrings.mapToInt(Integer::parseInt)
                    .max()
                    .getAsInt();
        }
        catch (Exception e) {
            throw new ComputorV1PolynomialException(
                "Invalid exponent found in polynomial.", e);
        }

        coefs = new ArrayList<Double>(Collections.nCopies(maxDegree + 1, 0.0));
        String data[] = p_string.split(" (?:\\* X\\^)?");

        Double coefficient;
        try {
            coefficient = Double.parseDouble(data[0]);
        }
        catch (Exception e) {
            throw new ComputorV1PolynomialException(
                "Invalid coefficient found in polynomial.", e);
        }

        int exponent;
        try {
            exponent = Integer.parseInt(data[1]);
        }
        catch (Exception e) {
            throw new ComputorV1PolynomialException(
                "Invalid exponent found in polynomial.", e);
        }

        int sign = 1;
        coefs.set(exponent, coefficient);
        for (int i = 2; i < data.length; i += 3) {
            sign = data[i].equals("+") ? 1 : -1;
            try {
                coefficient = Double.parseDouble(data[i + 1]);
            }
            catch (Exception e) {
                throw new ComputorV1PolynomialException(
                    "Invalid coefficient found in polynomial.", e);
            }
            try {
                exponent = Integer.parseInt(data[i + 2]);
            }
            catch (Exception e) {
                throw new ComputorV1PolynomialException(
                    "Invalid exponent found in polynomial.", e);
            }
            Double prevCoef = coefs.get(exponent);
            coefs.set(exponent, prevCoef + coefficient * sign);
        }
        return new Polynomial(coefs);
    }
}