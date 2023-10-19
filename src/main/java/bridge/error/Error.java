package bridge.error;

public enum Error {
    PREFIX("[ERROR]"),
    NUMBER_FORMAT_EXCEPTION("다리 길이는 3부터 20 사이의 숫자여야 합니다."),
    MOVING_FORMAT_EXCEPTION("이동할 칸은 U 혹은 D 여야 합니다."),
    GAME_COMMAND_EXCEPTION("게임 명령은 R 혹은 Q 여야 합니다."),
    SIZE_OUT_OF_RANGE("다리 길이는 3부터 20 사이의 숫자여야 합니다.");

    final String message;

    Error(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
