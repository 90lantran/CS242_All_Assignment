package testSuit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chessGame.MoveExecute;
import chessGame.MoveValidCheck;
import chessGame.MyGame;

public class KingTest {


	
	/*
	 * -------------------------------------------------------
	 * Move test for king
	 */
	@Test
	public void validKingMove1() throws Exception {
		MoveExecute.executeMove(75, 65);
		assertFalse( MoveValidCheck.isValidMove(85, 75) );
	}
	
	@Test
	public void validKingMove2() throws Exception {
		MoveExecute.executeMove(74, 64);
		assertFalse( MoveValidCheck.isValidMove(85, 74) );
	}
	
	@Test
	public void validKingMove3() throws Exception {
		MoveExecute.executeMove(76, 66);
		assertFalse( MoveValidCheck.isValidMove(85, 76) );
	}
	
	@Test
	public void invalidKingMove1() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(85, 75) );
	}
	
	@Test
	public void invalidKingMove2() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(85, 84) );
	}
	
	@Test
	public void invalidKingMove3() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(85, 86) );
	}
	

}
