package net.awesomepwoered.valhallaslack.Utilities;

/**
 * Created by John on 12/2/2014.
 */
public class SlackException extends RuntimeException {

    public SlackException() {
        super();
    }

    public SlackException(String message) {
        super(message);
    }

    public SlackException(Throwable cause) {
        super(cause);
    }

    public SlackException(String message, Throwable cause) {
        super(message, cause);
    }

}
