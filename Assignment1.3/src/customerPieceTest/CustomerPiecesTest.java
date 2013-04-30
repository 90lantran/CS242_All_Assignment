package customerPieceTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chessGame.MoveValidCheck;
import chessGame.MyGame;

public class CustomerPiecesTest {

	@Before
	public void testSetup() {
		MyGame newGame = new MyGame();
		newGame.initBoard();
	}
	
	/*
	 * -------------------------------------------------
	 * Move test for ArchBishop
	 * put ArchBishop at Board Index 54
	 */
	@Test
	public void archBishopMoveLikeKnight1() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(54, 42) );
	}
	@Test
	public void archBishopMoveLikeKnight2() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(54, 62) );
	}
	@Test
	public void archBishopMoveLikeKnight3() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(54, 35) );
	}
	@Test
	public void archBishopMoveLikeBishop1() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(54, 63) );
	}
	@Test
	public void archBishopMoveLikeBishop2() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(54, 36) );
	}
	@Test
	public void archBishopMoveLikeBishop3() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(54, 65) );
	}
	@Test
	public void archBishopMoveLikeBishop4() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(54, 76) );
	}
	
	/*
	 * Move test for Chancellor
	 * put Chancellor at Board Index 55
	 */
	@Test
	public void chancellorMoveLikeRook1() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(55, 51) );
	}
	@Test
	public void chancellorMoveLikeRook2() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(55, 58) );
	}
	@Test
	public void chancellorMoveLikeRook3() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(55, 25) );
	}
	@Test
	public void chancellorMoveLikeKnight1() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(55, 47) );
	}
	@Test
	public void chancellorMoveLikeKnight2() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(55, 34) );
	}
	@Test
	public void chancellorMoveLikeKnight3() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(55, 63) );
	}
	@Test
	public void chancellorMoveLikeKnight4() throws Exception {
		assertFalse( MoveValidCheck.isValidMove(55, 76) );
	}
	


}
