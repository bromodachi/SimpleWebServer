package simple.server.http.parser;

import simple.server.http.message.HttpUri;

import java.util.HashMap;
import java.util.Map;

public class HttpUriParser {
    public static HttpUri parse(String uri) {
        final int indexOfQuestionMark = uri.indexOf("?");
        // then it's just the path.
        if (indexOfQuestionMark == -1) {
            return new HttpUri(uri, new HashMap<>());
        }
        final String queries = uri.substring(indexOfQuestionMark + 1);
        final String [] queryArray = queries.split("&");
        Map<String, String> map = new HashMap<>();
        for (String query : queryArray) {
            final String [] keyAndValue = query.split("=");
            map.put(keyAndValue[0].trim(), keyAndValue[1].trim());
        }
        return new HttpUri(uri.substring(0, indexOfQuestionMark), map);
    }
}
