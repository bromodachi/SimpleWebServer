package simple.server.http.constant;

import simple.server.exception.server.UnsupportedRequestException;

public enum HttpMethod {
    // GET AND HEAD must be supported according to the RFC
    GET,
    // that the server MUST NOT return a message-body in the response
    HEAD,

    // WILL NOT SUPPORT
    POST,
    PUT,
    PATCH,
    DELETE;

    public static HttpMethod value(final String method) {

        try {
            return HttpMethod.valueOf(method.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnsupportedRequestException(method + " is not supported at this time");
        }
    }
}
