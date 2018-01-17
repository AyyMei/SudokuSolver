import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        if (inputCheck(args[0])) { //If input is 81 digits long
            Board board = new Board(inputParse(args[0]));

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board.getValueAt(i, j).size() > 1) {
                        HashSet<Integer> singleList = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
                        singleList.removeAll(board.getPeers(i, j));
                        board.setValueAt(singleList, i, j);
                    }
                }
            }

            System.out.print(board.solution());
        } else {
            System.out.print("Invalid input. Please enter 81 digits.");
        }
    }

    // Regex test to see if input is a 81-digit string of numbers
    private static boolean inputCheck(String s) {
        return Pattern.compile("\\d{81}").matcher(s).find();
    }

    // Generate an initial list of possible numbers for each position
    private static ArrayList<HashSet<Integer>> inputParse(String input) {
        ArrayList<HashSet<Integer>> list = new ArrayList<>();

        for (char c : input.toCharArray()) {
            if (c == '0') {
                HashSet<Integer> singleList = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
                list.add(singleList);
            } else {
                HashSet<Integer> singleList = new HashSet<>();
                singleList.add(Character.getNumericValue(c));
                list.add(singleList);
            }
        }

        return list;
    }
}
