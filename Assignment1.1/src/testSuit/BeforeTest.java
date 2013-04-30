package testSuit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chessGame.MyGame;

public class BeforeTest {

	@Before
	public void testSetup() {
		MyGame newGame = new MyGame();
		newGame.initBoard();
	}
	
}
