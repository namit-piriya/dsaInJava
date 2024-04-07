import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class StringProblems {
//    https://leetcode.com/problems/length-of-last-word/description/

    /**
     * Given a string s consisting of words and spaces, return the length of the last word in the string.
     * A word is a maximal
     * substring
     * consisting of non-space characters only.
     */
//        "   fly me   to   the moon  "
    public int lengthOfLastWord(String s) {
        int len = s.length() - 1;
        int counter = len;
        int ans = 0;
//        Go to the last char from back
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
// skip the letter or digits
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


    /*
        Given a pattern and a string s, find if s follows the same pattern.
        Here follow means a full match, such that there is a bijection between a letter in
        pattern and a non-empty word in s.
        Example 1:
        Input: pattern = "abba", s = "dog cat cat dog"
        Output: true
    * */
    public boolean wordPattern(String pattern, String s) {
        Map<String, Character> charMap = new HashMap<>();
        Map<Character, String> revCharMap = new HashMap<>();
        String[] allStrings = s.split(" ");
        if (pattern.length() != allStrings.length) {
            return false;
        }
        AtomicInteger i = new AtomicInteger();
        AtomicBoolean ans = new AtomicBoolean(true);
        Arrays.stream(allStrings).forEach(currStr -> {
            Character curr = charMap.get(currStr);
            char currChar = pattern.charAt(i.getAndIncrement());
            String currString = revCharMap.get(currChar);
            if (curr == null && revCharMap.get(currChar) == null) {
                charMap.put(currStr, currChar);
                revCharMap.put(currChar, currStr);
            } else if (!currStr.equals(currString) || !revCharMap.get(currChar).equals(currStr)) {
                ans.set(false);
            }
        });
        return ans.get();
    }


    public static void main(String[] args) {
        findSubstring("adfaswordgoodgoodwordgoodbestword", new String[]{"word", "good", "best", "word"});
    }

//    https://leetcode.com/problems/word-break/
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] flags = new boolean[s.length() + 1];
        flags[0] = true;
        for (int i = 0; i < s.length(); i++) {
            for (String a : wordDict) {
                int len = a.length();
                if(i + len <= s.length()){
                    String sub = s.substring(i,i+len);
                    // System.out.println("Sub is "+sub);
                    if(sub.equals(a)) {
                        flags[i + len] = flags[i + len] ||  flags[i];
                    }
                }

            }
//            printArray(flags);
        }
        return flags[s.length()];
    }


}

