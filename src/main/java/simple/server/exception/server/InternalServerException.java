package simple.server.exception.server;

public class InternalServerException extends ServerException implements InternalHttpStatusException {

    public InternalServerException(String message) {
        super(message);
    }
}
