package fr.rvander.computorV1.exceptions;


public class ComputorV1Exception extends Exception {


    public ComputorV1Exception() {
        super();
    }


    public ComputorV1Exception(String p_message) {
        super(p_message);
    }


    public ComputorV1Exception(String p_message, Throwable p_cause) {
        super(p_message, p_cause);
    }


    public ComputorV1Exception(Throwable p_cause) {
        super("an exception was caught", p_cause);
    }

    @Override
    public String toString() {
        String _str = "[ERROR]\n";
        _str += super.toString();
        if (this.getCause() != null)
            _str += "\n" + this.getCause().toString();
        return _str;
    }
}