package pieces;

public class Queen {

	public static boolean queenMoveValid(int piecePosition, int movePosition) {
		//combine the valid move of rook and bishop
		if( Bishop.bishopMoveValid(piecePosition, movePosition) || Rook.rookMoveValid(piecePosition, movePosition) )
				return true;
		else return false;
	}
}
