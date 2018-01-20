public class SolutionSteppingNumbers {
    public ArrayList<Integer> stepnum(int N, int M) {
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = N; i <= M; i++) {
            if (isStepping(i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public boolean isStepping(int num) {
        if (num < 10) return true;

        List<Integer> digits = findDigitsInDecimal(num);
        if (digits.isEmpty()) return false;
        if (digits.size() == 1) return true;

        int cur = digits.get(0);
        for (int i = 1; i < digits.size(); i++) {
            int next = digits.get(i);
            // Difference greater than 1
            int diff = Math.abs(cur-next);
            if (diff > 1 || diff == 0) {
                return false;
            }
            cur = next;
        }
        return true;
    }

    public List<Integer> findDigitsInDecimal(int n) {
        List<Integer> digits = new ArrayList<>();
        while (n > 0) {
            int digit = n % 10;
            digits.add(digit);
            n = n/10;
        }
        Collections.reverse(digits);
        return digits;
    }
}
