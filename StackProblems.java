import java.util.*;
import java.util.LinkedList;

public class StackProblems {

    public String simplifyPath(String path) {
        var directories = path.split("/");
        Stack<String> stack = new Stack<>();
        for (var s : directories) {
            if (s.equals("..") && !stack.isEmpty()) {
                stack.pop();
            }
            else if (!s.equals(".") && !s.equals("..") && !s.isEmpty()) {
                stack.push(s);
            }
        }
        var strBuild = new StringBuilder();
        for (var str : stack) {
            strBuild.append("/");
            strBuild.append(str);
        }
        return strBuild.length() >  1 ?  strBuild.toString() : "/";
    }

}
