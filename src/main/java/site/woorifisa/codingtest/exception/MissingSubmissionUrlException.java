package site.woorifisa.codingtest.exception;

public class MissingSubmissionUrlException extends RuntimeException {
    public MissingSubmissionUrlException(String message) {
        super(message);
    }
}
