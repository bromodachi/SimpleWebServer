import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    public static void main(String[] args) {
        Fibonacci invoker = new Fibonacci();
        System.out.println(invoker.calculateFibonacciLogN(50));
        System.out.println(invoker.calculateFibonacciMemo(50, new HashMap<>()));
        System.out.println(invoker.calculateFibonacciIterative(50));
        System.out.println(invoker.calculateFibonacciWorstRunTime(20));
    }
    private long calculateFibonacciWorstRunTime(long n) {
        if (n <= -1) {
            throw new IllegalArgumentException("Invalid");
        }
        if (n <= 1){
            return n;
        }
        return calculateFibonacciWorstRunTime(n - 1) + calculateFibonacciWorstRunTime(n - 2);
    }
    private long calculateFibonacciMemo(long n, Map<Long, Long> map) {
        if (n <= -1) {
            throw new IllegalArgumentException("Invalid");
        }
        if (map.containsKey(n)){
            return map.get(n);
        }
        if (n <= 1){
            return n;
        }
        long value = calculateFibonacciMemo(n - 1, map) + calculateFibonacciMemo(n - 2, map);
        map.put(n, value);
        return value;
    }

    private long calculateFibonacciIterative(long n) {
        if (n <= -1) {
            throw new IllegalArgumentException("Invalid");
        }
        if (n <= 1){
            return n;
        }
        long ptr = 0;
        long minusOne = 1;
        long minusTwo = 0;
        for (int i = 2; i <= n; i ++) {
            ptr = minusOne + minusTwo;
            minusTwo = minusOne;
            minusOne = ptr;
        }
        return ptr;
    }

    private long calculateFibonacciLogN(long n) {
        double squareRootOfFive = Math.sqrt(5);
        double goldenRatio = (1 + squareRootOfFive ) /2;

        return (long) ((Math.pow(goldenRatio, n) - Math.pow(-goldenRatio, -n)) /squareRootOfFive);
    }
}
