package simple.server.http.constant;

public enum StatusCode {
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NO_CONTENT(204),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500),
    NOT_IMPLEMENTED(501);
    public final int statusCode;

    StatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        switch (this) {
            case OK:
                return "200 OK";
            case CREATED:
                return "201 CREATED";
            case ACCEPTED:
                return "202 ACCEPTED";
            case NO_CONTENT:
                return "204 NO CONTENT";
            case BAD_REQUEST:
                return "400 BAD REQUEST";
            case NOT_FOUND:
                return "404 NOT FOUND";
            case INTERNAL_SERVER_ERROR:
                return "500 INTERNAL SERVER ERROR";
            case NOT_IMPLEMENTED:
                return "501 NOT IMPLEMENTED";
            default:
                return null;
        }
    }
}
