package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.model.AttemptCount;
import bridge.model.Command;
import bridge.model.Status;
import bridge.service.BridgeGame;
import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.List;

public class BridgeGameController {

    // TODO: 이 부분 DI 적용
    private final BridgeGame bridgeGame = new BridgeGame();
    private final InputView inputView;
    private final OutputView outputView;

    public BridgeGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();

        String bridgeSize = inputView.readBridgeSize();
        // TODO: 검증 로직
        // TODO: 동강동강열매 먹은 듯이 컷팅
        int size = Integer.parseInt(bridgeSize);
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        List<String> bridge = bridgeMaker.makeBridge(Integer.parseInt(bridgeSize));

        System.out.println("bridge = " + bridge);
        start(size, bridge);
    }

    private void start(Integer size, List<String> bridge) {
        int current = 0;
        while (current < size) {
            String moving = inputView.readMoving();
            if (bridgeGame.move(Status.valueOf(bridge.get(current)), Status.valueOf(moving))) {
                outputView.printMap();
                current++;
                continue;
            }
            String gameCommand = inputView.readGameCommand();
            if (Command.valueOf(gameCommand).equals(Command.R)) {
                bridgeGame.retry();
                start(size, bridge);
                return;
            }
            break;
        }
        outputView.printResult(getSuccessStatus(size, current), AttemptCount.getCount());
    }

    private String getSuccessStatus(Integer size, Integer current) {
        if (isSuccessful(size, current)) {
            return "성공";
        }
        return "실패";
    }

    private Boolean isSuccessful(Integer size, Integer current) {
        return size.equals(current);
    }
}
