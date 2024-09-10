package fr.rvander.computorV1.exceptions;

import fr.rvander.computorV1.exceptions.ComputorV1Exception;


public class ComputorV1PolynomialException extends ComputorV1Exception {


    public ComputorV1PolynomialException() {
        super();
    }


    public ComputorV1PolynomialException(String p_message) {
        super(p_message);
    }


    public ComputorV1PolynomialException(String p_message, Throwable p_cause) {
        super(p_message, p_cause);
    }


    public ComputorV1PolynomialException(Throwable p_cause) {
        super(p_cause);
    }
}