package simple.server.http.message.response;

import simple.server.http.message.HttpMessage;
import simple.server.http.constant.HttpVersion;
import simple.server.http.constant.StatusCode;
import simple.server.util.Constants;

import java.util.Map;

public class Response extends HttpMessage {

    protected final StatusCode statusCode;
    protected final Object content;

    public Response(Map<String, String> headers, HttpVersion version, StatusCode statusCode, String content) {
        super(headers, version);
        this.statusCode = statusCode;
        this.content = content == null ? "" : content;
    }
    public byte [] toByte() {
        return (getFirstLine() + handleHeaders() + Constants.CRLF + content).getBytes();
    }

    private String getFirstLine() {
        return version.toString() + " " + statusCode.getStatus() + Constants.CRLF;
    }

    protected String handleHeaders() {
        if (headers.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String key : headers.keySet()) {
            sb.append(key.trim()).append(":").append(headers.get(key).trim()).append(Constants.CRLF);
        }
        return sb.toString();
    }

    public static class Builder {
        private HttpVersion version;
        private Map<String, String> headers;
        private StatusCode statusCode;
        private String content;

        public Response.Builder setVersion(HttpVersion version) {
            this.version = version;
            return this;
        }

        public Response.Builder setHeaders(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Response.Builder setStatusCode(StatusCode code) {
            this.statusCode = code;
            return this;
        }

        public Response.Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Response build() {
            return new Response(headers, version, statusCode, content);
        }
    }
}
