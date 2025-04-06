package online.cybeer.simplecalendarapi.exception;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */
public abstract class BaseException extends RuntimeException {
    private final String errorCode;

    protected BaseException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
