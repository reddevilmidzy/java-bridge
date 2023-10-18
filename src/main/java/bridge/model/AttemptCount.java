package bridge.model;

public class AttemptCount {

    private static int count = 1;

    private AttemptCount() {
    }

    public static void add() {
        ++count;
    }

    // TODO: get 안쓰는 방법은 없을까
    public static Integer getCount() {
        return count;
    }
}
