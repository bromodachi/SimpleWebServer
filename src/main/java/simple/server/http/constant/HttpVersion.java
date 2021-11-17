package simple.server.http.constant;

import simple.server.exception.server.InternalServerException;
import simple.server.exception.server.UnsupportedRequestException;

public enum HttpVersion {
    HTTP_VERSION_1_0,
    HTTP_VERSION_1_1;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HTTP/");
        switch (this) {
            case HTTP_VERSION_1_0:
                sb.append("1.0");
                break;
            case HTTP_VERSION_1_1:
                sb.append("1.1");
                break;
            default:
                throw new InternalServerException("Invalid http version");
        }
        return sb.toString();
    }

    public static HttpVersion value(final String info) {
        final int indexOfSlash = info.indexOf("/");
        if (indexOfSlash == -1) {
            throw new InternalServerException("Invalid http version string");
        }
        final String majorAndMinor = info.substring(indexOfSlash + 1);
        if (majorAndMinor.equals("1.0")) {
            return HTTP_VERSION_1_0;
        }
        else if (majorAndMinor.equals("1.1")) {
            return HTTP_VERSION_1_1;
        }
        throw new UnsupportedRequestException("Invalid http version. We only support 1.0 or 1.1");
    }
}
