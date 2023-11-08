import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        if (s.trim().isEmpty()) {
            return true;
        }
        int start = 0;
        int end = s.length() - 1;

        while (start <= end) {

            if (!Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            } else if (!Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            } else {
                if (Character.toLowerCase(s.charAt(start)) == Character.toLowerCase(s.charAt(end))) {
                    start++;
                    end--;
                } else {
                    return false;
                }
            }

        }
        return true;
    }

    //    https://leetcode.com/problems/is-subsequence/
//    Input: s = "abc", t = "ahbgdc"
//    Output: true
    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }
        if (s.isEmpty()) return true;
        int sPtr = 0;
        int i = 0;
        while (i < t.length()) {
            if (s.charAt(sPtr) == t.charAt(i)) {
                sPtr++;
            }
            if (sPtr == s.length()) return true;
            i++;
        }
        return false;
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        int len = s.length();
        HashMap<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            hm.put(words[i], hm.getOrDefault(words[i], 0) + 1);
        }
        int i = 0;
        int subStrLen = words.length * words[0].length();
        int oneWordLen = words[0].length();
        while (i <= len - subStrLen) {
            int j = i;
            boolean flag = true;
            HashMap<String, Integer> checkHm = new HashMap<>();
            for (int k = 0; k < subStrLen; k += oneWordLen) {
                String sub = s.substring(j + k, j + k + oneWordLen);
                // System.out.println("substring " + sub + ", " + j + k + " " + j + k + oneWordLen);
                int maxCount = hm.getOrDefault(sub, 0);
                if (maxCount == 0 || checkHm.getOrDefault(sub, 0) + 1 > maxCount) {
                    i++;
                    flag = false;
                    break;
                } else
                    checkHm.put(sub, checkHm.getOrDefault(sub, 0) + 1);
            }
            if (flag) {
                i += oneWordLen;
                ans.add(j);
            }
        }
        return ans;


    }

    public static void main(String[] args) {
        findSubstring("adfaswordgoodgoodwordgoodbestword", new String[]{"word", "good", "best", "word"});
    }

}

