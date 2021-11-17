package simple.server.controller;

import simple.server.constant.CommonHeaders;
import simple.server.http.constant.ContentType;
import simple.server.http.message.response.FileResponse;
import simple.server.http.message.request.HttpRequest;
import simple.server.http.message.response.Response;
import simple.server.http.constant.StatusCode;
import simple.server.util.LoadResource;
import simple.server.util.MapToJson;

import java.util.Map;

public class GetController implements HandleRequest{

    final HttpRequest httpRequest;

    public GetController(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    private FileResponse getFileResponse(String filename) {
        return new FileResponse(
                CommonHeaders.getContentTypeHeader(ContentType.TEXT_HTML),
                httpRequest.getVersion(),
                StatusCode.OK,
                null,
                LoadResource.getInputStream(filename)
        );
    }

    @Override
    public Response processRequest() {
        if (httpRequest.getUri() == null || httpRequest.getPath() == null) {
            throw new RuntimeException("Error");
        }
        switch (httpRequest.getPath()) {
            case "/":
                return getFileResponse("simple_web_server_page.html");
            case "/index.html":
                return getFileResponse("simple.html");
            case "/hello-world":
                return new Response.Builder()
                        .setStatusCode(StatusCode.OK)
                        .setVersion(httpRequest.getVersion())
                        .setHeaders(CommonHeaders.getContentTypeHeader(ContentType.TEXT_PLAIN))
                        .setContent("Hello world")
                        .build();
            case "/nested-json":
                return new Response.Builder()
                        .setStatusCode(StatusCode.OK)
                        .setVersion(httpRequest.getVersion())
                        .setHeaders(CommonHeaders.getContentTypeHeader(ContentType.APPLICATION_JSON))
                        .setContent(MapToJson.mapToJson(Map.of("key", "value", "innerKey", Map.of("foo", "bar", "number", 2))))
                        .build();
        }
        return handleUnknownRequest();
    }
}
