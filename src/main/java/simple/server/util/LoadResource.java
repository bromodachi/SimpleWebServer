package simple.server.util;

import simple.server.exception.server.InternalServerException;

import java.io.IOException;
import java.io.InputStream;

public final class LoadResource {
    private LoadResource() {}
    public static byte[] getInputStream(final String fileName){
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try(InputStream inputStream = classloader.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new InternalServerException("Input stream is null.");
            }
            return inputStream.readAllBytes();
        } catch (IOException e) {
            throw new InternalServerException("Couldn't process file.");
        }
    }
}
