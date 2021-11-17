package simple.server.http.message;

import java.util.Collections;
import java.util.Map;

public class HttpUri {
    public String getPath() {
        return path;
    }

    public Map<String, String> getQueries() {
        return queries;
    }

    final String path;
    final Map<String, String> queries;

    public HttpUri(String path, Map<String, String> map) {
        this.path = path;
        this.queries = Collections.unmodifiableMap(map);
    }
}
