package simple.server.constant;

import simple.server.http.constant.ContentType;
import simple.server.http.constant.HttpVersion;
import simple.server.http.message.response.Response;
import simple.server.http.constant.StatusCode;

public final class CommonResponse {
    private CommonResponse() {}
    public static Response INTERNAL_SERVER_ERROR = new Response.Builder()
            .setVersion(HttpVersion.HTTP_VERSION_1_1)
            .setStatusCode(StatusCode.INTERNAL_SERVER_ERROR)
            .build();

    public static Response internalServerErrorWithMessage(String message) {
        return new Response.Builder()
                .setVersion(HttpVersion.HTTP_VERSION_1_1)
                .setStatusCode(StatusCode.INTERNAL_SERVER_ERROR)
                .setHeaders(CommonHeaders.getContentTypeHeader(ContentType.TEXT_PLAIN))
                .setContent(message)
                .build();
    }
}
