package model;

public class PcoException extends Exception {

    private static final long serialVersionUID = -7669751088704144947L;

    public PcoException(String message) {
        super(message);
    }

    public PcoException(String message, Throwable cause) {
        super(message, cause);
    }
}
