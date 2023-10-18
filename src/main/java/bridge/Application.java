package bridge;

import bridge.controller.BridgeGameController;
import bridge.view.InputView;

public class Application {

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        BridgeGameController br = new BridgeGameController(new InputView());
        br.run();
    }
}
