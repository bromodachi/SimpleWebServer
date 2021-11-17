package simple.server.controller;

import simple.server.http.message.response.Response;

public class NotFoundController implements HandleRequest{
    @Override
    public Response processRequest() {
        return handleUnknownRequest();
    }
}
