package simple.server.exception.server;

import simple.server.constant.CommonHeaders;
import simple.server.http.constant.ContentType;
import simple.server.http.constant.HttpVersion;
import simple.server.http.message.response.Response;
import simple.server.http.constant.StatusCode;

public class ServerException extends RuntimeException implements InternalHttpStatusException {

    private final HttpVersion httpVersion;
    private final StatusCode statusCode;

    public ServerException(String message) {
        super(message);
        httpVersion = HttpVersion.HTTP_VERSION_1_1;
        statusCode = StatusCode.INTERNAL_SERVER_ERROR;
    }

    public ServerException(String message, HttpVersion httpVersion) {
        super(message);
        this.httpVersion = httpVersion;
        this.statusCode = StatusCode.INTERNAL_SERVER_ERROR;
    }

    public ServerException(String message, HttpVersion httpVersion, StatusCode statusCode) {
        super(message);
        this.httpVersion = httpVersion;
        this.statusCode = statusCode;
    }

    @Override
    public Response toResponse() {
        Response.Builder builder = new Response.Builder()
                .setVersion(httpVersion)
                .setStatusCode(statusCode);
        if (getMessage() != null) {
            builder.setContent(getMessage())
                    .setHeaders(CommonHeaders.getContentTypeHeader(ContentType.TEXT_PLAIN));
        }
        return builder.build();
    }
}
