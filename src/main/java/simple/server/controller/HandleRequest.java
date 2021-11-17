package simple.server.controller;

import simple.server.http.constant.HttpVersion;
import simple.server.http.message.response.Response;
import simple.server.http.constant.StatusCode;

public interface HandleRequest {
    Response processRequest();

    default Response handleUnknownRequest() {
        return new Response.Builder()
                .setVersion(HttpVersion.HTTP_VERSION_1_1)
                .setStatusCode(StatusCode.NOT_FOUND)
                .setContent("The content you're looking for is not found")
                .build();
    }
}
