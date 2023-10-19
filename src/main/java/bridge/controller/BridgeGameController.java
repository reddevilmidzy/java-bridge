package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.model.AttemptCount;
import bridge.model.Command;
import bridge.model.Status;
import bridge.service.BridgeGame;
import bridge.validation.BridgeSizeValidation;
import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BridgeGameController {

    // TODO: 이 부분 DI 적용
    private final BridgeGame bridgeGame = new BridgeGame();
    private ValidationController validationController;
    private final InputView inputView;
    private final OutputView outputView;

    public BridgeGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();

        Integer size = readBridgeSize();
        // TODO: 검증 로직
        // TODO: 동강동강열매 먹은 듯이 컷팅
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        List<Status> bridge = convertStatus(bridgeMaker.makeBridge(size));
        System.out.println("bridge = " + bridge);
        start(size, bridge);
    }

    private void start(Integer size, List<Status> bridge) {
        int current = 0;
        while (current < size) {
            //TODO: 여기도 Status 로 바꾸는 메서드 만들기
            String moving = inputView.readMoving();
            printMap(bridge, current, bridgeGame.move(bridge.get(current), Status.valueOf(moving)));
            if (bridgeGame.move(bridge.get(current), Status.valueOf(moving))) {
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
        outputView.printResult(getResult(size, current), AttemptCount.getCount());
    }

    private void printMap(List<Status> bridge, Integer current, Boolean isSuccess) {
        List<String> upperBridge = new ArrayList<>();
        List<String> lowerBridge = new ArrayList<>();
        for (int i = 0; i < current; i++) {
            upperBridge.add(getSuccessStatus(bridge.get(i), true));
            lowerBridge.add(getSuccessStatus(bridge.get(i), false));
        }

        // TODO: 여기 코드 변경하기
        if (isSuccess) {
            upperBridge.add(getSuccessStatus(bridge.get(current), true));
            lowerBridge.add(getSuccessStatus(bridge.get(current), false));
        } else {
            upperBridge.add(getFailureStatus(bridge.get(current), true));
            lowerBridge.add(getFailureStatus(bridge.get(current), false));
        }
        if (current.equals(bridge.size())) {
            outputView.printFinal();
        }
        outputView.printMap(upperBridge);
        outputView.printMap(lowerBridge);
    }

    private Integer readBridgeSize() {
        String value = inputView.readBridgeSize();
        this.validationController = new ValidationController(outputView, new BridgeSizeValidation());
        try {
            this.validationController.validate(value);
        } catch (IllegalArgumentException e) {
            return readBridgeSize();
        }
        return Integer.parseInt(value);
    }

    private String getSuccessStatus(Status bridge, Boolean isUpper) {
        if (bridge.equals(Status.U) && isUpper) {
            return "O";
        }
        if (bridge.equals(Status.D) && !isUpper) {
            return "O";
        }
        return " ";
    }

    private String getFailureStatus(Status bridge, Boolean isUpper) {
        if (bridge.equals(Status.U) && isUpper) {
            return " ";
        }
        if (bridge.equals(Status.D) && !isUpper) {
            return " ";
        }
        return "X";
    }


    private List<Status> convertStatus(List<String> bridge) {
        List<Status> result = new ArrayList<>();
        for (String current : bridge) {
            result.add(Status.valueOf(current));
        }
        return result;
    }

    private String getResult(Integer size, Integer current) {
        if (isSuccessful(size, current)) {
            return "성공";
        }
        return "실패";
    }

    private Boolean isSuccessful(Integer size, Integer current) {
        return size.equals(current);
    }
}
