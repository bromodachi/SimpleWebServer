package simple.server.constant;

import simple.server.http.constant.ContentType;
import simple.server.util.Constants;

import java.util.Map;

public final class CommonHeaders {
    private CommonHeaders() {}
    public static Map<String, String> getContentTypeHeader(ContentType type) {
        return Map.of(Constants.CONTENT_TYPE, type.getType());
    }
}
