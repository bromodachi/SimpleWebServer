package simple.server.worker;

import simple.server.constant.CommonResponse;
import simple.server.controller.ControllerFactory;
import simple.server.controller.HandleRequest;
import simple.server.exception.server.ServerException;
import simple.server.http.parser.HttpParser;
import simple.server.http.message.request.HttpRequest;
import simple.server.http.message.response.Response;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class RequestWorker implements Runnable{
    private final Socket socket;
    public RequestWorker(Socket socket) {
        this.socket = socket;
    }
    private void writeResponse(Socket client, Response response) throws IOException {
        try (OutputStream outputStream = client.getOutputStream()) {
            outputStream.write(response.toByte());
            outputStream.flush();
        }
    }
    @Override
    public void run() {
        try {
            try {
                HttpRequest httpRequest = new HttpParser(socket).parseRequest();
                HandleRequest controller = ControllerFactory.getController(httpRequest);
                Response response = controller.processRequest();
                writeResponse(socket, response);
            } catch (SocketException e) {
                writeResponse(socket, CommonResponse.internalServerErrorWithMessage("Internal error"));
            } catch (ServerException e) {
                writeResponse(socket, e.toResponse());
            } catch (RuntimeException e) {
                writeResponse(socket, CommonResponse.INTERNAL_SERVER_ERROR);
            }
            socket.close();
        } catch (IOException e) {
            System.out.println("unknown error");
            e.printStackTrace();
        }
    }
}
