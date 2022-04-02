package net.ancronik.samples.cucumber.application.exception;

/**
 * Exception that should be thrown in case when some user tries to do unauthorized operation.
 *
 * @author Nikola Presecki
 */
public class UnauthorizedActionException extends RuntimeException {

    /**
     * Constructor with message.
     *
     * @param message message
     */
    public UnauthorizedActionException(String message) {
        super(message);
    }

}
