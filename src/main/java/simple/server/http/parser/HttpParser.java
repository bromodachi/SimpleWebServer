package simple.server.http.parser;

import simple.server.exception.server.InternalServerException;
import simple.server.http.constant.HttpMethod;
import simple.server.http.message.request.HttpRequest;
import simple.server.http.constant.HttpVersion;
import simple.server.util.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpParser {

    private final Socket socket;

    public HttpParser(Socket socket) {
        this.socket = socket;
    }

    public HttpRequest parseRequest() throws IOException {
        final InputStream inputStream = socket.getInputStream();
        final String firstLine = readLine(inputStream);
        if (firstLine.isBlank()) {
            // most likely just checking the server is up.
            return new HttpRequest.Builder()
                    .setMethod(HttpMethod.HEAD)
                    .setVersion(HttpVersion.HTTP_VERSION_1_1)
                    .build();
        }
        // read the rest right away.
        Map<String, String> headers = new HashMap<>();
        String line;
        while (!(line = readLine(inputStream)).isBlank()) {
            final String [] headerInfo = line.split(":");
            headers.put(headerInfo[0].trim(), headerInfo[1].trim());
        }

        final byte [] body;
        // you need to ready everything or else you will get an error....(curl: (56) Recv failure: Connection reset by peer)
        if (headers.containsKey(Constants.CONTENT_LENGTH)) {
            // OUT OF SCOPE OF PROJECT - NOT GOING TO PARSE A JSON STRING. There are many libraries(e.g GSON or jackson) that do this for you.
            body = readBody(Integer.parseInt(headers.get(Constants.CONTENT_LENGTH)), inputStream);
        }
        else {
            body = null;
        }
        // now parse the first line (again, we have to read everything before we start throwing exceptions)

        final RequestURIParser requestURIParser = new RequestURIParser(firstLine);
        return new HttpRequest.Builder()
                .setUri(requestURIParser.getHttpUri())
                .setMethod(requestURIParser.getHttpMethod())
                .setVersion(requestURIParser.getVersion())
                .setBody(body)
                .setHeaders(headers)
                .build();
    }

    private byte[] readBody(int size, InputStream inputStream) throws IOException {
        byte[] body = new byte[size];
        int input;
        for (int i = 0; i < size && (input = inputStream.read()) != -1; i++)
        {
            body[i] = (byte) input;
        }
        return body;
    }

    private String readLine(InputStream inputStream) {
        boolean carriageReturnRead = false;
        // stringbuffer is thread safe. Though it's probably okay to use stringbuilder since it's a local variable
        StringBuffer sb = new StringBuffer();
        try {
            for (int input = inputStream.read(); input  != -1; input = inputStream.read()) {
                if (input == Constants.CARRIAGE_RETURN) {
                    carriageReturnRead = true;
                }
                else if (input == Constants.NEWLINE && carriageReturnRead) {
                    return sb.toString();
                }
                else {
                    sb.append((char)input);
                }
            }
        }catch (Exception e) {
            throw new InternalServerException("InputStream caused an error");
        }

        return sb.toString();
    }
}
