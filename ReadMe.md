# Simple Web Server and Fibonacci

## Simple Web Server

A straightforward web server using Java's ServerSocket. 
You can clone and run the program on an IDE or use the `build_and_run.sh` shell script attached.
If you're using the shell script, the minimum requirements to get it running is `chmod +x build_and_run.sh && ./build_and_run.sh -b -r`. 
A more detail configuration can be found in the help command.

### Background

This simple server will only handle get request. 
For anything else, we will read the requests but not process the request itself.
You may be asking why and the reason is to keep things simple.
I wanted to avoid parsing a byte to a JSON since there are many robust libraries that do this for you.
To avoid using third party libraries, I opted to skip those implementations.

### APIs you can call.

| path       |   METHOD   | Description |
|------------|------------|-------------|
|     /      |     GET    |Brings up an html page that describes this project.|
|/index.html |     GET    |Gives you an html page that says hello world.|
|/hello-world|     GET    |Gives you a response in text/plain that simply says hello world.|
|/nested-json|     GET    |Gives you a response in application/json|
|/return-queries|     GET |Return the queries you gave|

And that's basically it. 
The rest will give you an error(404 or not implemented, depending on what you request).
The main goal is just to keep things simple and **not** do anything fancy.

## Fibonacci

We're given a task to implement a fast Fibonacci.

Fibonacci is just the sum of the two preceding numbers. The straight forward approach is just:
```java
private int calculateFibonacci(int n) {
	if (n <= 1){
		return n;
	}
	int value = calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
	return value;
}
```

As you can tell, this will be really bad as it's basically O(2^n). We can improve this by using dynamic programming.

```java
private int calculateFibonacci(int n, Map<Integer, Integer> map) {
	if (map.containsKey(n)){
		return map.get(n);
	}
	if (n <= 1){
		return n;
	}
	int value = calculateFibonacci(n - 1, map) + calculateFibonacci(n - 2, map);
	map.put(n, value);
	return value;
}
```
This will give you an O(N) solution but the space will be O(N). We can still do better.

A better approach would be..just using a simple for loop. This will give you O(N) but space will be O(1).
```java
private int calculateFibonacci(int n) {
	if (n <= -1) {
		throw new IllegalArgumentException("Invalid");
	}
	if (n <= 1){
		return n;
	}
	int ptr = 0;
	int minusOne = 1;
	int minusTwo = 0;
	for (int i = 2; i <= n; i ++) {
		ptr = minusOne + minusTwo;
		minusTwo = minusOne
		minusOne = ptr;
	}
	return value;
}
```
This will give you an O(n) run time and use constant space thus O(1).

However, we can still do better. We can use math to get the fibonacci number. However, please note that it doesn't work well for large numbers(you would have to use BigDecimal).
```java
private long calculateFibonacciLogN(long n) {
	double squareRootOfFive = Math.sqrt(5);
	double goldenRatio = (1 + squareRootOfFive ) /2;
	
	return (long) ((Math.pow(goldenRatio, n) - Math.pow(-goldenRatio, -n)) /squareRootOfFive);
}
```

The code can be found in java/Fibonacci.java. 