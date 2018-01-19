import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Board {
    private ArrayList<HashSet<Integer>> board;

    public Board(ArrayList<HashSet<Integer>> board) {
        this.board = board;
    }

    // Returns solved content of the given row.
    public HashSet<Integer> getRow(int r) {
        HashSet<Integer> row = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (board.get(r * 9 + i).size() == 1) {
                row.addAll(board.get(r * 9 + i));
            }
        }
        return row;
    }

    // Returns solved content of the given column.
    public HashSet<Integer> getCol(int c) {
        HashSet<Integer> col = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (board.get(c + (9 * i)).size() == 1) {
                col.addAll(board.get(c + (9 * i)));
            }
        }
        return col;
    }

    // Returns solved content of the 3x3 square that contains the given row and column.
    public HashSet<Integer> getSquare(int row, int col) {
        int s = ((row / 3) * 3 + (col / 3)); // Convert row, col into index of the box
        HashSet<Integer> square = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.get(i + (j * 9) + ((s % 3) * 3 + (s / 3) * 27)).size() == 1) {
                    square.addAll(board.get(i + (j * 9) + ((s % 3) * 3 + (s / 3) * 27)));
                }
            }
        }
        return square;
    }

    // Returns unsolved content of the given row excluding the given column.
    public HashSet<Integer> getUnsolvedRow(int r, int c) {
        HashSet<Integer> row = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (board.get(r * 9 + i).size() > 1 && i != c) {
                row.addAll(board.get(r * 9 + i));
            }
        }
        return row;
    }

    // Returns unsolved content of the given column excluding the given row.
    public HashSet<Integer> getUnsolvedCol(int r, int c) {
        HashSet<Integer> col = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (board.get(c + (9 * i)).size() > 1 && i != r) {
                col.addAll(board.get(c + (9 * i)));
            }
        }
        return col;
    }

    // Returns unsolved possibilities of the 3x3 square that contains the given box, excluding said box
    public HashSet<Integer> getUnsolvedSquare(int row, int col) {
        int s = ((row / 3) * 3 + (col / 3)); // Convert row, col into index of the box
        HashSet<Integer> square = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.get(i + (j * 9) + ((s % 3) * 3 + (s / 3) * 27)).size() > 1 && (col + row * 9) != ((i + (j * 9)) + ((s % 3) * 3 + (s / 3) * 27))) { // as long as it works
                    square.addAll(board.get(i + (j * 9) + ((s % 3) * 3 + (s / 3) * 27)));
                }
            }
        }
        return square;
    }

    public HashSet<Integer> getPeers(int row, int col) {
        HashSet<Integer> peers = getSquare(row, col);
        peers.addAll(getRow(row));
        peers.addAll(getCol(col));
        return peers;
    }

    public HashSet<Integer> getValueAt(int row, int col) {
        return board.get(row * 9 + col);
    }

    public void setValueAt(HashSet<Integer> s, int row, int col) {
        board.get(row * 9 + col).clear();
        board.get(row * 9 + col).addAll(s);
    }

    public void simplePossibilities() {
        boolean valuesChanged = false;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (getValueAt(i, j).size() > 1) {
                    HashSet<Integer> singleList = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
                    singleList.removeAll(getPeers(i, j));
                    if (!singleList.equals(getValueAt(i, j))) {
                        valuesChanged = true;
                        setValueAt(singleList, i, j);
                    }
                }
            }
        }
        if (valuesChanged) {
            simplePossibilities();
        }
    }

    public void hiddenSingles() { //hacky af but it works lmao
        boolean valuesChanged = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(board.get(i * 9 + j).size() > 1) {
                    HashSet<Integer> possibles = new HashSet<>(board.get(i * 9 + j));
                    possibles.removeAll(getUnsolvedSquare(i, j));
                    if (possibles.size() == 1) {
                        setValueAt(possibles, i, j);
                        valuesChanged = true;
                        simplePossibilities();
                    }
                    possibles = new HashSet<>(board.get(i * 9 + j));
                    possibles.removeAll(getUnsolvedRow(i, j));
                    if (possibles.size() == 1) {
                        setValueAt(possibles, i, j);
                        valuesChanged = true;
                        simplePossibilities();
                    }
                    possibles = new HashSet<>(board.get(i * 9 + j));
                    possibles.removeAll(getUnsolvedCol(i, j));
                    if (possibles.size() == 1) {
                        setValueAt(possibles, i, j);
                        valuesChanged = true;
                        simplePossibilities();
                    }
                }
            }
        }
        if (valuesChanged) {
            hiddenSingles();
        }
    }

    public String toString() {
        String out = "";
        for (int i = 0; i < 81; i++) {
            out = out + board.get(i).toString();
            if (i % 9 == 8) {
                out = out + "\n";
            }
        }
        return out;
    }
}
