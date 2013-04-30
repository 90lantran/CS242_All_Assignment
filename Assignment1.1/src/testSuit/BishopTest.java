package testSuit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chessGame.MoveExecute;
import chessGame.MoveValidCheck;
import chessGame.MyGame;

public class BishopTest {

	
	/*
	 * ---------------------------------------------------
	 * Move test for bishop
	 */
	@Test
	public void validBishopMove1() throws Exception {
		MoveExecute.executeMove(72, 62);
		assertFalse( MoveValidCheck.isValidMove(83, 72) );
	}
	
	@Test
	public void validBishopMove2() throws Exception {
		MoveExecute.executeMove(72, 62);
		assertFalse( MoveValidCheck.isValidMove(83, 61) );
	}
	
	@Test
	public void validBishopMove3() throws Exception {
		MoveExecute.executeMove(74, 64);
		assertFalse( MoveValidCheck.isValidMove(83, 74) );
	}
	
	@Test
	public void validBishopMove4() throws Exception {
		MoveExecute.executeMove(74, 64);
		assertFalse( MoveValidCheck.isValidMove(83, 47) );
	}
	
	@Test
	public void invalidBishopMove1() throws Exception {
		MoveExecute.executeMove(74, 64);
		assertFalse( MoveValidCheck.isValidMove(83, 29) );
	}
	
	@Test
	public void invalidBishopMove2() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(83, 29) );
	}
	
	@Test
	public void invalidBishopMove3() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(83, 73) );
	}
	

}
