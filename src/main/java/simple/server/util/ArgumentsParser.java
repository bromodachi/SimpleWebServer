package simple.server.util;

public final class ArgumentsParser {
    private final static int DEFAULT_THREADS = 20;
    private final static int DEFAULT_PORT = 8080;

    private final static String NUMBER_OF_THREADS = "number-of-threads";
    private final static String PORT_TO_SET = "port";

    private final int port;
    private final int threads;

    private ArgumentsParser(int port, int threads) {
        this.port = port;
        this.threads = threads;
    }

    private static Integer getInteger(String s, int defaultValue) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return defaultValue;
        }
    }
    public static ArgumentsParser valueOf(String [] args) {
        int threadsAmount = DEFAULT_THREADS;
        int port = DEFAULT_PORT;

        if (args.length > 0 && args.length % 2 == 0) {
            for (int i = 0; i < args.length; i += 2) {
                if (args[i].equals(NUMBER_OF_THREADS)) {
                    threadsAmount = getInteger(args[i + 1], DEFAULT_THREADS);
                }
                else if (args[i].equals(PORT_TO_SET)) {
                    port = getInteger(args[i + 1], DEFAULT_PORT);
                }
            }
        }
        return new ArgumentsParser(port, threadsAmount);
    }

    public int getPort() {
        return port;
    }

    public int getThreads() {
        return threads;
    }
}
