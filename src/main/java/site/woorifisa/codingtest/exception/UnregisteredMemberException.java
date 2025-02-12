package site.woorifisa.codingtest.exception;

public class UnregisteredMemberException extends RuntimeException {
    public UnregisteredMemberException(String message) {
        super(message);
    }
}
