package simple.server.http.parser;

import simple.server.exception.server.InternalServerException;
import simple.server.http.constant.HttpMethod;
import simple.server.http.message.HttpUri;
import simple.server.http.constant.HttpVersion;

public class RequestURIParser {
    private final HttpMethod httpMethod;
    private final HttpVersion version;

    private final HttpUri httpUri;

    public RequestURIParser(String line) {
        final String [] requestUriInfo = line.split(" ");
        if (requestUriInfo.length != 3) {
            throw new InternalServerException("Invalid request uri");
        }
        this.httpMethod = HttpMethod.value(requestUriInfo[0]);
        this.httpUri = HttpUriParser.parse(requestUriInfo[1]);
        this.version = HttpVersion.value(requestUriInfo[2]);
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public HttpVersion getVersion() {
        return version;
    }

    public HttpUri getHttpUri() {
        return httpUri;
    }

}
