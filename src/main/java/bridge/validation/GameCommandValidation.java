package bridge.validation;

import bridge.error.Error;
import bridge.model.Command;

public class GameCommandValidation implements Validator {
    @Override
    public void validate(String value) {
        validType(value);
    }

    private void validType(String value) {
        try {
            Command.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(Error.GAME_COMMAND_EXCEPTION.toString());
        }
    }
}
