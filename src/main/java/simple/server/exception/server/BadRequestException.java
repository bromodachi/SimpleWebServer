package simple.server.exception.server;

import simple.server.http.constant.HttpVersion;
import simple.server.http.constant.StatusCode;

public class BadRequestException extends ServerException{
    public BadRequestException(String message) {
        super(message, HttpVersion.HTTP_VERSION_1_1, StatusCode.BAD_REQUEST);
    }
}
