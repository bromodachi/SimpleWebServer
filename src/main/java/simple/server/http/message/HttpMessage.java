package simple.server.http.message;

import simple.server.http.constant.HttpVersion;

import java.util.Collections;
import java.util.Map;

public abstract class HttpMessage {
    protected final Map<String, String> headers;
    protected final HttpVersion version;

    public HttpMessage(Map<String, String> headers, HttpVersion version) {
        this.headers = headers == null ? Collections.emptyMap() : headers;
        this.version = version;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public HttpVersion getVersion() {
        return version;
    }
}
