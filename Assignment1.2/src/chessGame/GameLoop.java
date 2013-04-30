package chessGame;

import gui.Graphic;

/*
 * Start a new game.
 */
public class GameLoop {

	public static void main(String[] args){
	
		MyGame newGame = new MyGame();
		
		MyGame.initBoard();
		
		MyGame.whitePlayerScore = 0;
		MyGame.blackPlayerScore = 0;
		
		Graphic myChessGame = new Graphic();
		
	}
}
