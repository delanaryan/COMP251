import java.util.*;

public class A2_Q1 {
	
	public static int[] game(String[][] board){
		int rowCount = board.length;
		int colCount = board[0].length;
		int ballCount = 0;

		for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (board[i][j].equals("o")) {
                    ballCount++;
                }
            }
        }

		int[] result = {ballCount, Integer.MAX_VALUE};
		solve(board, 0, ballCount, result);

		return result;
	}

	public static void solve(String[][] board, int moves, int balls, int[] result){
		boolean canMove = false;
		int rowCount = board.length;
		int colCount = board[0].length;

		for (int i = 0; i < rowCount; i++){
			for (int j = 0; j < colCount; j++){
				if (board[i][j].equals("o")){


					if (j + 2 < colCount && board[i][j+1].equals("o") && board[i][j+2].equals(".")){
						canMove = true;
						move(board, i, j, i, j + 1, i, j + 2);
						solve(board, moves + 1, balls - 1, result);
                        undo(board, i, j, i, j + 1, i, j + 2);
					}
					
					if (j - 2 >= 0 && board[i][j - 1].equals("o") && board[i][j - 2].equals(".")) {
                        canMove = true;
                        move(board, i, j, i, j - 1, i, j - 2);
                        solve(board, moves + 1, balls - 1, result);
                        undo(board, i, j, i, j - 1, i, j - 2);
					}

					if (i + 2 < rowCount && board[i + 1][j].equals("o") && board[i + 2][j].equals(".")) {
                        canMove = true;
                        move(board, i, j, i + 1, j, i + 2, j);
                        solve(board, moves + 1, balls - 1, result);
                        undo(board, i, j, i + 1, j, i + 2, j);
                    }

					if (i - 2 >= 0 && board[i - 1][j].equals("o") && board[i - 2][j].equals(".")) {
                        canMove = true;
                        move(board, i, j, i - 1, j, i - 2, j);
                        solve(board, moves + 1, balls - 1, result);
                        undo(board, i, j, i - 1, j, i - 2, j);
                    }
				}
			}
		}
		if (!canMove){
			if (balls < result[0] || (balls == result[0] && moves < result[1])){
				result[0] = balls;
				result[1] = moves;
			}
		}
	}

	private static void move(String[][] board, int fromX, int fromY, int jumpX, int jumpY, int toX, int toY) {
        board[fromX][fromY] = "."; 
        board[jumpX][jumpY] = "."; 
        board[toX][toY] = "o"; 
    }

    private static void undo(String[][] board, int fromX, int fromY, int jumpX, int jumpY, int toX, int toY) {
        board[fromX][fromY] = "o"; 
        board[jumpX][jumpY] = "o"; 
        board[toX][toY] = "."; 
    }

}
