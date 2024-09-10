package fr.rvander.computorV1.exceptions;

import fr.rvander.computorV1.exceptions.ComputorV1Exception;


public class ComputorV1UsageException extends ComputorV1Exception {


    public ComputorV1UsageException() {
        super();
    }


    public ComputorV1UsageException(String p_message) {
        super(p_message);
    }


    public ComputorV1UsageException(String p_message, Throwable p_cause) {
        super(p_message, p_cause);
    }


    public ComputorV1UsageException(Throwable p_cause) {
        super(p_cause);
    }
}