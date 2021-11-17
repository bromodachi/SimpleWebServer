package simple.server.exception.server;

import simple.server.http.constant.HttpVersion;
import simple.server.http.constant.StatusCode;

public class UnsupportedRequestException extends ServerException {
    public UnsupportedRequestException(String message) {
        super(message, HttpVersion.HTTP_VERSION_1_1, StatusCode.NOT_IMPLEMENTED);
    }
}
