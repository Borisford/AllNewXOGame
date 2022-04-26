package su.ANV.exeptions;

public class PlayerAlreadyInGameException extends Exception {
    public PlayerAlreadyInGameException(String message) {
        super(message);
    }
}
