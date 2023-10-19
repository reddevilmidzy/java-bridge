package bridge.controller;

import bridge.validation.Validator;
import bridge.view.OutputView;

public class ValidationController {
    OutputView outputView;
    Validator validator;

    public ValidationController(OutputView outputView, Validator validator) {
        this.outputView = outputView;
        this.validator = validator;
    }

    public void validate(String value) {
        try {
            validator.validate(value);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            throw e;
        }
    }
}
