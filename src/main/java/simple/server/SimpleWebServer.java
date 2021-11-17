package simple.server;

import simple.server.exception.InternalErrorException;
import simple.server.worker.RequestWorker;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SimpleWebServer implements AutoCloseable {

    private final ServerSocket serverSocket;

    private final ThreadPoolExecutor executor;

    // store the variable in ram, make reads thread safe
    private volatile boolean isRunning = false;

    public SimpleWebServer(final int port, int threads) {
        try {
            serverSocket = new ServerSocket(port);
            executor =  (ThreadPoolExecutor) Executors.newFixedThreadPool(threads);
        } catch (IOException e) {
            throw new InternalErrorException("Couldn't start server. \nPort:" + port + " threads: "+ threads, e);
        }
    }

    public void start() {
        isRunning = true;
        System.out.println("server is starting on port: " +serverSocket.getLocalPort());
        // if you want to not have this running on the main thread indefinitely, you can use a single thread executor instead.
        while (isRunning) {
            // setup to receive messages
            try{
                executor.submit(new RequestWorker(serverSocket.accept()));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IO exception was thrown. Normally you log this type of error but since this is a demo, we're going to skip it");
            }
        }
    }

    @Override
    public void close() throws Exception {
        isRunning = false;
        executor.shutdown();
        serverSocket.close();
    }
}
