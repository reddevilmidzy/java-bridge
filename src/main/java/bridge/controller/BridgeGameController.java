package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.model.Status;
import bridge.service.BridgeGame;
import bridge.view.InputView;

import java.util.List;

public class BridgeGameController {

    // TODO: 이 부분 DI 적용
    private BridgeGame bridgeGame = new BridgeGame();
    private final InputView inputView;

    public BridgeGameController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        String bridgeSize = inputView.readBridgeSize();
        // TODO: 검증 로직
        int size = Integer.parseInt(bridgeSize);
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        List<String> bridge = bridgeMaker.makeBridge(Integer.parseInt(bridgeSize));

        System.out.println("bridge = " + bridge);

        for (int i = 0; i < size; i++) {
            String moving = inputView.readMoving();
            if (bridgeGame.move(Status.valueOf(bridge.get(i)), Status.valueOf(moving))) {
                System.out.println("상태 출력");
                continue;
            }
            break;
        }
    }
}
