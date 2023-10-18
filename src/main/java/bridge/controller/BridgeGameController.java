package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.view.InputView;

import java.util.List;

public class BridgeGameController {

    private final InputView inputView;

    public BridgeGameController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        String bridgeSize = inputView.readBridgeSize();
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        List<String> bridge = bridgeMaker.makeBridge(Integer.parseInt(bridgeSize));

        System.out.println("bridge = " + bridge);
    }
}
