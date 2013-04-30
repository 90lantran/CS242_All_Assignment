package testSuit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chessGame.MoveExecute;
import chessGame.MoveValidCheck;
import chessGame.MyGame;

public class RookTest {

	/*
	 * -------------------------------------------------------
	 * Move test for rook
	 */
	@Test
	public void validRookMove1() throws Exception {
		MoveExecute.executeMove(71, 31);
		assertFalse( MoveValidCheck.isValidMove(81, 41) );
	}
	
	@Test
	public void validRookMove2() throws Exception {
		MoveExecute.executeMove(71, 31);
		assertFalse( MoveValidCheck.isValidMove(81, 51) );
	}
	
	@Test
	public void validRookMove3() throws Exception {
		MoveExecute.executeMove(71, 31);
		assertFalse( MoveValidCheck.isValidMove(81, 61) );
	}
	
	@Test
	public void validRookMove4() throws Exception {
		MoveExecute.executeMove(71, 31);
		assertFalse(MoveValidCheck.isValidMove(81, 71) );
	}
	
	@Test
	public void invalidRookMove1() throws Exception {
		MoveExecute.executeMove(71, 31);
		assertFalse( MoveValidCheck.isValidMove(81, 31) );
	}
	
	@Test
	public void invalidRookMove2() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(81, 71) );
	}
	
	@Test
	public void invalidRookMove3() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(81, 80) );
	}

}
