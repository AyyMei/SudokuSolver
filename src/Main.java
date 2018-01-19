import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.*;

public class Main {
/*
  unsolveable: 000000907000420180000705026100904000050000040000507709990108000034559000557000000
  1 = 043080250600000000000001094900004070000608000010200003820500000000000005034090710 <-- hard
  2 = 200080300060070084030500209000105408000000000402706000301007040720040060004010003
  3 = 003020600900305001001806400008102900700000008006708200002609500800203009005010300
  4 = 100920000524010000000000070050008102000000000402700090060000000000030945000071006 <-- hard
  5 = 850000401000000670002105003008500007000982000300001500500004300037000000209000058
  6 = 010000020030090016560070003307008000000000089000060000006025400905001007003000002 <-- hard
*/
    public static void main(String[] args) {
        if (inputCheck(args[0])) { //If input is 81 digits long
            Board board = new Board(inputParse(args[0]));

            board.simplePossibilities();
            board.hiddenSingles();

            System.out.print(board.toString());
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
