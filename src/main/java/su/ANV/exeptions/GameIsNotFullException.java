package su.ANV.exeptions;

public class GameIsNotFullException extends Exception {
    public GameIsNotFullException(String message) {
        super(message);
    }
}
