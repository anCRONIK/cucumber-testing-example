package net.ancronik.samples.cucumber.application.exception;

/**
 * Exceptions which should be thrown when some entry does not exist in database.
 * <p>
 * Should be thrown/propagated by services to controller and processed by global handler.
 *
 * @author Nikola Presecki
 */
public class DataDoesNotExistException extends Exception {

    /**
     * Constructor with message.
     *
     * @param message message
     */
    public DataDoesNotExistException(String message) {
        super(message);
    }

}
