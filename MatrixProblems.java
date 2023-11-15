import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MatrixProblems {

    /*
    Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
    Each row must contain the digits 1-9 without repetition.
    Each column must contain the digits 1-9 without repetition.
    Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
    Note:
    A Sudoku board (partially filled) could be valid but is not necessarily solvable.
    Only the filled cells need to be validated according to the mentioned rules.
    * */

    public static boolean isValidSudoku(char[][] board) {

        Map<Integer, ArrayList<int[]>> map = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                char currChar = board[i][j];
                if (currChar != '.') {
                    int curr = Character.digit(currChar, 10);
                    var curValue = map.getOrDefault(curr, new ArrayList<>());
                    if (checkForDupe(curValue, i, j)) {
                        return false;
                    }
                    int[] temp = new int[]{i, j};
                    curValue.add(temp);
                    map.put(curr, curValue);
                }
            }
        }
        return true;


    }

    private static boolean checkForDupe(ArrayList<int[]> curValue, int i, int j) {
        int currRow = (i / 3) * 3;
        int maxBoxRIndex = currRow + 2;
        int minBoxCIndex = (j / 3) * 3;
        int maxBoxCIndex = minBoxCIndex + 2;
        for (int[] currPair : curValue) {
            if (currPair[0] == i || currPair[1] == j) {
                // row or column are same as the given row column
                System.out.println("row column");
                return true;
            } else if (currRow <= currPair[0] && maxBoxRIndex >= currPair[0] &&
                    minBoxCIndex <= currPair[1] && maxBoxCIndex >= currPair[1]) {
                System.out.println("Box");
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        char[][] board = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '5', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        System.out.println(isValidSudoku(board));
    }

}
