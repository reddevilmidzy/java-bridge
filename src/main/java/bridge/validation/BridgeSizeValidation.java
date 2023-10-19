package bridge.validation;

import bridge.error.Error;

public class BridgeSizeValidation implements Validator {
    private static final int MAX_BRIDGE_SIZE = 20;
    private static final int MIN_BRIDGE_SIZE = 3;

    @Override
    public void validate(String value) {
        validType(value);
        validRange(value);
    }

    private void validType(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Error.NUMBER_FORMAT_EXCEPTION.toString());
        }
    }

    private void validRange(String value) {
        int size = Integer.parseInt(value);
        if (MIN_BRIDGE_SIZE > size || MAX_BRIDGE_SIZE < size) {
            throw new IllegalArgumentException(Error.SIZE_OUT_OF_RANGE.toString());
        }
    }
}
