package simple.server.http.constant;

public enum ContentType {
    TEXT_HTML("text/html"),
    TEXT_PLAIN("text/plain"),
    APPLICATION_JSON("application/json");

    private final String type;

    ContentType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return getType();
    }

    public String getType() {
        return type;
    }
}
