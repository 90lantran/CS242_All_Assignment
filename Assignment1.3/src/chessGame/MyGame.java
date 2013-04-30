package chessGame;

import gui.Graphic;

import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * This class is used to setup a new chess board, 
 * and communicate the chess board with user
 */

public class MyGame extends java.awt.Canvas {
	
	public static int NOPLAYER = 0;
	public static int WHITE = 1; 
	public static int BLACK = 2;
	
	public static int EDGE = 99;
	public static int SQUARE = 0;

	public static final int NOPLAYING = -1;
	public static final int PLAYING = 0;
	public static final int WHITE_WIN = 1;
	public static final int BLACK_WIN = 2;

	public static int currentState; 
	public static int currentPlayer; 
	public static int currentPosition;
	
	public static boolean myBlock1;
	public static boolean myBlock2;
	public static Scanner in = new Scanner(System.in);
	
	public static boolean undoAvailable = false;
	
	public static int LastPickLocation;
	public static int LastPickPiece;
	public static int LastMoveLocation;
	public static int LastMovePiece;
	public static JButton LastPickButton;
	public static JButton LastMoveButton;
	public static ImageIcon LastPickIcon;
	public static ImageIcon LastMoveIcon;
	
	public static int whitePlayerScore;
	public static int blackPlayerScore;
	/*
	 * 12*10 board. The 1st digit is color: 2 for black, 1 for white. 
	 * The 2nd digit is the type of piece: 1 for pawn, 2 for knight, 3
	 * for bishop, 4 for rook, 5 for queen, 6 for king, 7 for Archbishop, 
	 * 8 for Chancellor
	 */
	public static int[] orignalPosition = { 
			99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
			99, 24, 22, 23, 25, 26, 23, 22, 24, 99, 
			99, 21, 21, 21, 21, 21, 21, 21, 21, 99, 
			99, 00, 00, 00, 00, 00, 00, 00, 00, 99, 
			99, 00, 00, 00, 00, 00, 00, 00, 00, 99, 
			99, 00, 00, 00, 00, 00, 00, 00, 00, 99, 
			99, 00, 00, 00, 00, 00, 00, 00, 00, 99, 
			99, 11, 11, 11, 11, 11, 11, 11, 11, 99, 
			99, 14, 12, 13, 15, 16, 13, 12, 14, 99, 
			99, 99, 99, 99, 99, 99, 99, 99, 99, 99,};

	public static int[] board = new int[100];

	public static void initBoard() {
		for (int i = 0; i < 100; i++) {
			board[i] = orignalPosition[i];
		}
		currentState = NOPLAYING; 
		currentPlayer = NOPLAYER; 
	}

	/*
	 * Switch player
	 */
	public static void switchPlayer(){
		if( currentPlayer == WHITE )
			currentPlayer = BLACK;
		else
			currentPlayer = WHITE;
	}

}
