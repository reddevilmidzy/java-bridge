package bridge.validation;

import bridge.error.Error;
import bridge.model.Status;

public class MovingValidation implements Validator {
    @Override
    public void validate(String value) {
        validType(value);
    }

    private void validType(String value) {
        try {
            Status.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(Error.MOVING_FORMAT_EXCEPTION.toString());
        }
    }
}
