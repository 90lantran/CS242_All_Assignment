package testSuit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chessGame.MoveValidCheck;
import chessGame.MyGame;

public class PawnTest {

	public class ChessTest {

		
		/*
		 * -------------------------------------------------
		 * Move test for pawn
		 */
		@Test
		public void validPawnMove1() throws Exception {
			assertTrue( MoveValidCheck.isValidMove(71, 61) );
		}

		@Test
		public void validPawnMove2() throws Exception {
			assertTrue( MoveValidCheck.isValidMove(72, 62) );
		}

		@Test
		public void validPawnMove3() throws Exception {
			assertTrue( MoveValidCheck.isValidMove(73, 63) );
		}

		@Test
		public void validPawnMove4() throws Exception {
			assertTrue( MoveValidCheck.isValidMove(74, 64) );
		}

		@Test
		public void validPawnMove5() throws Exception {
			assertTrue( MoveValidCheck.isValidMove(75, 65) );
		}

		@Test
		public void validPawnMove6() throws Exception {
			assertTrue( MoveValidCheck.isValidMove(76, 66) );
		}
		
		@Test
		/*
		 * Out of edge
		 */
		public void invalidPawnMove1() throws Exception {
			assertFalse( MoveValidCheck.isValidMove(71, 60) );
		}

		@Test
		/*
		 * invalid move to diagonal
		 */
		public void invalidPawnMove2() throws Exception {
			assertFalse( MoveValidCheck.isValidMove(71, 62) );
		}
		
	}

}
