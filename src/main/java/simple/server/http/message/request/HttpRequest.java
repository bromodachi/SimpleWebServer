package simple.server.http.message.request;

import simple.server.http.message.HttpUri;
import simple.server.http.constant.HttpMethod;
import simple.server.http.constant.HttpVersion;
import simple.server.http.message.HttpMessage;

import java.util.Map;

public class HttpRequest extends HttpMessage {

    private final HttpUri uri;
    private final HttpMethod method;
    private final byte [] requestBody;

    public HttpRequest(HttpVersion version, HttpUri uri, Map<String, String> headers, HttpMethod method, byte [] requestBody) {
        super(headers, version);
        this.uri = uri;
        this.method = method;
        this.requestBody = requestBody;
    }

    public static class Builder {
        private HttpVersion version;
        private HttpUri uri;
        private Map<String, String> headers;
        private HttpMethod method;
        private byte [] requestBody;

        public Builder setVersion(HttpVersion version) {
            this.version = version;
            return this;
        }

        public Builder setHeaders(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder setMethod(HttpMethod method) {
            this.method = method;
            return this;
        }

        public Builder setUri(HttpUri uri) {
            this.uri = uri;
            return this;
        }

        public Builder setBody(byte [] body) {
            this.requestBody = body;
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(version, uri, headers, method, requestBody);
        }
    }

    public HttpUri getUri() {
        return uri;
    }

    public String getPath(){
        return getUri().getPath();
    }

    public Map<String, String> getQueries(){
        return getUri().getQueries();
    }

    public HttpMethod getMethod() {
        return method;
    }

    public byte[] getRequestBody() {
        return requestBody;
    }
}
