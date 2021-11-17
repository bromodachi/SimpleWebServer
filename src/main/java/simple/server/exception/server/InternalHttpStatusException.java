package simple.server.exception.server;

import simple.server.http.message.response.Response;

public interface InternalHttpStatusException {
    public Response toResponse();
}
