package pieces;
import chessGame.MyGame;


public class Knight {
	
	/*
	 * Check if the move position is valid for the knight
	 */
	public static boolean knightMoveValid(int piecePosition, int movePosition) {

		//leftback
		if (piecePosition == (movePosition - 12)) {
			return isKnightMoveValid(movePosition);
		}
		//rightForward
		else if (piecePosition == (movePosition + 12)) {
			return isKnightMoveValid(movePosition);
		}
		//backLeft
		else if (piecePosition == (movePosition - 21)) {	
			return isKnightMoveValid(movePosition);
		}
		//forwardRight
		else if (piecePosition == (movePosition + 21)) {
			return isKnightMoveValid(movePosition);
		}
		//backRight
		else if (piecePosition == (movePosition - 19)) {
			return isKnightMoveValid(movePosition);
		}
		//forwardLeft
		else if (piecePosition == (movePosition + 19)) {
			return isKnightMoveValid(movePosition);
		}
		//rightBack
		else if (piecePosition == (movePosition - 8)) {
			return isKnightMoveValid(movePosition);
		}
		//leftForward
		else if (piecePosition == (movePosition + 8)) {
			return isKnightMoveValid(movePosition);
		}
		else
			return false;
	}

	private static boolean isKnightMoveValid(int movePosition) {
		if (MyGame.board[movePosition] / 10 == MyGame.currentPlayer)
			return false;
		else
			return true;
	}
}
