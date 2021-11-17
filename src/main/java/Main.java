import simple.server.util.ArgumentsParser;
import simple.server.SimpleWebServer;
import simple.server.exception.InternalErrorException;

public class Main {
    public static void main(String[] args) {
        final ArgumentsParser argumentsParser = ArgumentsParser.valueOf(args);
        try(final SimpleWebServer serverSocket = new SimpleWebServer(argumentsParser.getPort(), argumentsParser.getThreads())) {
            serverSocket.start();
        } catch (InternalErrorException e) {
            System.out.println("Couldn't start server. Please try a different port.\nPort: "+ argumentsParser.getPort());
        } catch (Exception e) {
            System.out.println("Unknown error. Will shutdown...");
        }
    }
}
