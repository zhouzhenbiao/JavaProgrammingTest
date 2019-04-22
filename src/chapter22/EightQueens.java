package chapter22;

import org.junit.Test;

import java.util.Arrays;

/**
 * 八皇后问题的所有解
 */
public class EightQueens {

    public static final int SIZE = 8;
    private int[] queens = {-1, -1, -1, -1, -1, -1, -1, -1};

    @Test
    public void testSearch() {
        if (search()) {
            System.out.println(Arrays.toString(queens));
        }
    }

    private boolean search() {
        int row = 0;//从第k行开始
        while (row >= 0 && row < EightQueens.SIZE) {
            int column = findColumn(row);
            if (column < 0) {
                queens[row] = -1;
                row--;
            } else {
                queens[row] = column;
                row++;
            }
        }
        if (row == -1)
            return false;
        else
            return true;
    }

    private int findColumn(int row) {
        int start = queens[row] + 1;
        for (int column = start; column < EightQueens.SIZE; column++) {
            if (isValid(row, column)) {
                return column;
            }
        }
        return -1;
    }

    private boolean isValid(int row, int column) {
        for (int i = 1; i <= row; i++) {
            if (queens[row - i] == column
                    || queens[row - i] == column - i
                    || queens[row - i] == column - i) {
                return false;
            }
        }
        return true;
    }
}
