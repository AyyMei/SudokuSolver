import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
	    if (inputCheck(args[0])) { //If input is 81 digits long
	        Board board = new Board(inputParse(args[0]));
	        board.solve();
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
    private static ArrayList<ArrayList<Integer>> inputParse(String input) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (char c : input.toCharArray()) {
            if (c == '0') {
                ArrayList<Integer> singleList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
                list.add(singleList);
            } else {
                ArrayList<Integer> singleList = new ArrayList<>();
                singleList.add(Character.getNumericValue(c));
                list.add(singleList);
            }
        }

        return list;
    }
}
