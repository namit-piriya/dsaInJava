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
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        var first = strs[0];
        var last = strs[strs.length-1];
        int minInd = Math.min(first.length(), last.length());

        StringBuffer s = new StringBuffer();

        for(int i =0; i < minInd; i++){
            if(first.charAt(i) == last.charAt(i)){
                s.append(first.charAt(i));
            }
            else{
                break;
            }
        }
        return s.toString();
    }
}
