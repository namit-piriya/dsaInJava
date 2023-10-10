import java.util.Arrays;

public class StringProblems {
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
        var last = strs[strs.length - 1];
        int minInd = Math.min(first.length(), last.length());

        StringBuffer s = new StringBuffer();

        for (int i = 0; i < minInd; i++) {
            if (first.charAt(i) == last.charAt(i)) {
                s.append(first.charAt(i));
            } else {
                break;
            }
        }
        return s.toString();
    }

//    https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
    public int strStr(String haystack, String needle) {

        if (haystack.length() < needle.length()) {
            return 0;
        }

        for (int i = 0; i < haystack.length(); i++) {
            int startInd;
            //        "sadbutsad"   "sad"
            if (haystack.charAt(i) == needle.charAt(0)) {
                i++;
                int j = 1;
                startInd = i;
                while (i < haystack.length() && j < needle.length()
                        && haystack.charAt(i++) == needle.charAt(j++)) ;
                if (j == needle.length()) {
                    return startInd;
                }
            }
        }
        return 0;


    }

//    https://leetcode.com/problems/valid-palindrome
    public boolean isPalindrome(String s) {
        if(s.trim().isEmpty()){
            return true;
        }
        int start = 0;
        int end = s.length()-1;

        while(start <= end){

            if(!Character.isLetterOrDigit(s.charAt(start))){
                start++;
            }
            else if(!Character.isLetterOrDigit(s.charAt(end))){
                end--;
            }
            else {
                if(Character.toLowerCase(s.charAt(start)) == Character.toLowerCase(s.charAt(end))){
                    start++;
                    end--;
                }
                else {
                    return false;
                }
            }

        }
        return true;
    }

}
