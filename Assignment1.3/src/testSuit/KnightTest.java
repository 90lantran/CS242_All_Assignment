package testSuit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chessGame.MoveValidCheck;
import chessGame.MyGame;

public class KnightTest {


	/*
	 * ---------------------------------------------------
	 * Move test for Knight
	 */
	@Test
	public void validKnightMove1() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(87, 66) );
	}
	
	@Test
	public void validKnightMove2() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(87, 68) );
	}
	
	@Test
	public void validKnightMove3() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(87, 66) );
	}
	
	@Test
	public void invalidKnightMove1() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(87, 95) );
	}
	
	@Test
	public void invalidKnightMove2() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(87, 99) );
	}
	
	@Test
	public void invalidKnightMove3() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(87, 75) );
	}
	
	@Test
	public void invalidKnightMove4() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(87, 79) );
	}
}
