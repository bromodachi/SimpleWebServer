package http.constant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import simple.server.exception.server.InternalServerException;
import simple.server.exception.server.UnsupportedRequestException;
import simple.server.http.constant.HttpVersion;

public class HttpVersionTest {

    @Test
    public void invalidStringPassed(){
        Assertions.assertThrows(InternalServerException.class, ()->HttpVersion.value("unknown"));
    }

    @Test
    public void invalidVersion(){
        Assertions.assertThrows(UnsupportedRequestException.class, ()->HttpVersion.value("http/2.0"));
    }

    @Test
    public void validVersion1_0(){
        Assertions.assertEquals(HttpVersion.HTTP_VERSION_1_0, HttpVersion.value("http/1.0"));
    }

    @Test
    public void validVersion1_1(){
        Assertions.assertEquals(HttpVersion.HTTP_VERSION_1_1, HttpVersion.value("http/1.1"));
    }
}
