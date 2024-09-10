package fr.rvander.computorV1;

import fr.rvander.computorV1.polynomial.*;
import fr.rvander.computorV1.exceptions.*;


public class ComputorV1 {

    public static void main(String args[]) {
        try {
            ComputorV1.checkArgs(args);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return;
        }
        PolynomialFactory pnmFactory = PolynomialFactory.getInstance();
        String eqSplit[] = args[0].split("=");
        Polynomial pnmLeft = null;
        Polynomial pnmRight = null;
        Polynomial reduced = null;
        try {
            pnmLeft = pnmFactory.newPolynomial(eqSplit[0]);
            System.out.println(pnmLeft.toString() + "  degree: " + pnmLeft.degree);
            if (eqSplit.length == 2) {
                pnmRight = pnmFactory.newPolynomial(eqSplit[1]);
                System.out.println(pnmRight.toString() + "  degree: " + pnmRight.degree);
            }
            reduced = pnmLeft;
            if (pnmRight != null) {
                reduced = pnmLeft.substractPolynomial(pnmRight);
                System.out.println("reduced form:\n" + reduced.toString() + "  degree: " + reduced.degree);
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    private static void checkArgs(String args[]) throws ComputorV1Exception {
        if (args.length < 1)
            throw new ComputorV1UsageException("Missing polynomial equation as argument.");
        if (args.length > 1)
            throw new ComputorV1UsageException("Too many arguments. Only one polynomial equation required.");
        int l = args[0].split("=").length;
        if (l != 1 && l != 2) {
            throw new ComputorV1PolynomialException(
                    "Wrong format for polynomial equation. Required formats: polynomial OR polynomial1 = polynomial2");
        }
    }
}