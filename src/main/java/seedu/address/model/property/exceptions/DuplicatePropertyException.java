package seedu.address.model.property.exceptions;

/**
 * Signals that the operation will result in duplicate Properties.
 */
public class DuplicatePropertyException extends RuntimeException {
    public DuplicatePropertyException() {
        super("Operation would result in duplicate properties");
    }
}
