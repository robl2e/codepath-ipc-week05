public class SolutionSumFibonacciNumbers {
    public int fibsum(int N) {
        if (N == 0) return 0;
        if (N <= 3) return 1;

        // Generate fib nums and store in queue
        Deque<Integer> fibNums = new LinkedList<>();
        int a = 1, b = 1;
        while (b <= N) {
            fibNums.offer(b);
            int c = a + b;
            a = b;
            b = c;
        }

        //System.out.println(" fibNums = " + fibNums);
        int count = 1;
        int target = N;
        while(!fibNums.isEmpty()) {
            int fib = fibNums.pollLast();
            //System.out.println("fib = " + fib + " target = " + target);
            if (fib > target) {
                continue;
            }

            int remain = target - fib;
            if (remain == 0) {
                return count;
            } else {
                count++;
                target = remain;
            }
        }
        return count;
    }
}
