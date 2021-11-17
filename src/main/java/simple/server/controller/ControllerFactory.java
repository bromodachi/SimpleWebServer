package simple.server.controller;

import simple.server.exception.server.UnsupportedRequestException;
import simple.server.http.message.request.HttpRequest;

public class ControllerFactory {
    public static HandleRequest getController(HttpRequest httpRequest) {
        switch (httpRequest.getMethod()) {
            case GET:
                return new GetController(httpRequest);
            case HEAD:
                return new HeadController(httpRequest);
            default:
                // Use this or NotFoundController
                throw new UnsupportedRequestException(httpRequest.getMethod().toString() +" is not supported at this time.");
        }
    }
}
