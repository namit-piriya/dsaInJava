import java.util.Arrays;

public class stringProblems {
    public int lengthOfLastWord(String s) {
//        "   fly me   to   the moon  "
        int len = s.length() - 1;
        int counter = len;
        int ans = 0;
        while (s.charAt(counter) == ' ' && counter >= 0) {
            counter--;
        }

        while (counter >= 0) {
            if (s.charAt(counter) == ' ') {
                break;
            }
            ans++;
            counter--;
        }
        return ans;

    }
}
