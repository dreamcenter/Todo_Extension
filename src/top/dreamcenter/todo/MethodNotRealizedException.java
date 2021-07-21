package top.dreamcenter.todo;

public class MethodNotRealizedException extends RuntimeException{
    private String detailMessage;
    private boolean throwException;

    public MethodNotRealizedException(String message) {
        detailMessage = message;
    }
}
