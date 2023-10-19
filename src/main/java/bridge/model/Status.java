package bridge.model;

public enum Status {
    D("D"),
    U("U");

    private String status;

    // TODO: bridge 말고 이쁜 이름은 없나
    Status(String status) {
        this.status = status;
    }
}
