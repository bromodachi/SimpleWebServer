package simple.server.controller;

import simple.server.constant.CommonHeaders;
import simple.server.http.constant.ContentType;
import simple.server.http.message.request.HttpRequest;
import simple.server.http.message.response.Response;
import simple.server.http.constant.StatusCode;

public class HeadController implements HandleRequest {
    final HttpRequest httpRequest;

    public HeadController(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    @Override
    public Response processRequest() {
        return new Response.Builder()
                .setStatusCode(StatusCode.OK)
                .setVersion(httpRequest.getVersion())
                .setHeaders(CommonHeaders.getContentTypeHeader(ContentType.TEXT_PLAIN))
                .build();
    }
}
