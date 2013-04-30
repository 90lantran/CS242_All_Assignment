package testSuit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chessGame.MoveExecute;
import chessGame.MoveValidCheck;
import chessGame.MyGame;

public class QueenTest {



	/*
	 * ------------------------------------------------------------
	 * Move test for queen
	 */
	@Test
	public void validQueenMove1() throws Exception {
		MoveExecute.executeMove(73, 63);
		assertFalse( MoveValidCheck.isValidMove(84, 73) );
	}
	
	@Test
	public void validQueenMove2() throws Exception {
		MoveExecute.executeMove(73, 63);
		assertFalse( MoveValidCheck.isValidMove(84, 51) );
	}
	
	@Test
	public void validQueenMove3() throws Exception {
		MoveExecute.executeMove(75, 65);
		assertFalse( MoveValidCheck.isValidMove(84, 75) );
	}
	
	@Test
	public void validQueenMove4() throws Exception {
		MoveExecute.executeMove(75, 65);
		assertFalse( MoveValidCheck.isValidMove(84, 48) );
	}
	
	@Test
	public void invalidQueenMove1() throws Exception {
		MoveExecute.executeMove(75, 65);
		assertFalse( MoveValidCheck.isValidMove(84, 39) );
	}
	
	@Test
	public void invalidQueenMove2() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(84, 29) );
	}
	
	@Test
	public void invalidQueenMove3() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(84, 73) );
	}

}
