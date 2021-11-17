package simple.server.http.message.response;

import simple.server.http.constant.HttpVersion;
import simple.server.http.constant.StatusCode;

import java.nio.ByteBuffer;
import java.util.Map;

public class FileResponse extends Response {

    final byte [] fileBytes;
    public FileResponse(Map<String, String> headers, HttpVersion version, StatusCode statusCode, String content, byte [] fileBytes) {
        super(headers, version, statusCode, content);
        this.fileBytes = fileBytes;
    }

    public byte [] toByte() {
        byte[] initial = super.toByte();
        byte[] returnArray = new byte[initial.length + fileBytes.length];
        ByteBuffer byteBuffer = ByteBuffer.wrap(returnArray);
        byteBuffer.put(initial);
        byteBuffer.put(fileBytes);
        return byteBuffer.array();
    }
}
