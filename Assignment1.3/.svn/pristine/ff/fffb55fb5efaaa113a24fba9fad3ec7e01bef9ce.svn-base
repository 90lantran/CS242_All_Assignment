package gui;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import network.MyClient;
import network.MyServer;

import chessGame.ConditionCheck;
import chessGame.MoveExecute;
import chessGame.MoveValidCheck;
import chessGame.MyGame;

public class Action implements MouseInputListener, MouseMotionListener {
	/*
	 * Mouse actions on the main chess board
	 */
	private static Color oldColor;
	private static boolean validChoose;
	private static int enteredIndex;
	private static int chosenPieceIndex;
	private static ImageIcon chosenImage;
	private static JButton oldLocation;
	private static int moveLocationIndex;
	
	public static String myClientSend;
	
	public static void Action() {}

	public void mousePressed(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {}

	/*
	 * A valid move will show green An invalid move will show red
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e) {
		JButton enteredButton = (JButton) e.getSource();
		oldColor = enteredButton.getBackground();
		if (validChoose) {
			enteredIndex = (int) enteredButton.getClientProperty("index");
			if (MoveValidCheck.isValidMove(chosenPieceIndex, enteredIndex)) {
				enteredButton.setBackground(Color.green);
			} else {
				enteredButton.setBackground(Color.red);
			}
		}
	}

	/*
	 * Square return to its original color when mouse leaves it.
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent e) {
		JButton exitedButton = (JButton) e.getSource();
		exitedButton.setBackground(oldColor);
	}

	/*
	 * Left click to choose a piece. Valid choose, square turns green; invalid
	 * choose turns red Right click to move a piece. Valid move, square turns
	 * green; invalid move turns red
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e) {
		// left click
		if (SwingUtilities.isLeftMouseButton(e)) {
			JButton leftClick = (JButton) e.getSource();
			chosenPieceIndex = (int) leftClick.getClientProperty("index");
			if (MyGame.currentState == MyGame.PLAYING) {

				if (MyGame.board[chosenPieceIndex] / 10 == MyGame.currentPlayer) {
					leftClick.setBackground(Color.green);
					chosenImage = (ImageIcon) leftClick.getIcon();
					oldLocation = leftClick;
					validChoose = true;
				} else {
					leftClick.setBackground(Color.red);
					validChoose = false;
				}
			} else if (MyGame.currentState == MyGame.NOPLAYING) {

				JOptionPane.showMessageDialog(null,
						"Please Start a game first.", null,
						JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null,
						"Please ReStart a game first.", null,
						JOptionPane.WARNING_MESSAGE);
			}

		}
		// right click
		else {
			JButton rightClick = (JButton) e.getSource();
			moveLocationIndex = (int) rightClick.getClientProperty("index");

			if (validChoose) {
				if (MoveValidCheck.isValidMove(chosenPieceIndex,
						moveLocationIndex)) {

					unDoSetting(rightClick); // set undo
					MyGame.undoAvailable = true;
					
					MoveExecute
							.executeMove(chosenPieceIndex, moveLocationIndex);

					checkIfEnd();	//check if there is a winner

					if(MyGame.currentPlayer == MyGame.WHITE) {
						
						String message = "" + chosenPieceIndex + "," + moveLocationIndex;
						
						MyServer.sendMessage(message);
						
						
					//	MyServer.sendMessage(myServerSend);
					}
					if(MyGame.currentPlayer == MyGame.BLACK) {
						myClientSend = "" + chosenPieceIndex + "," + moveLocationIndex;
						MyClient.sendMessage(myClientSend);
					}
					
					// move piece image
					rightClick.setIcon(chosenImage);
					oldLocation.setIcon(null);
					MyGame.switchPlayer();
					validChoose = false; // set for next pick
					
					//Check condition
					ifKingChecked();
				
				}
			}
		}
	}



	
	
	
	private void ifKingChecked() {
		if (MyGame.currentState == MyGame.PLAYING
				&& ConditionCheck.isChecked()) {
			if (MyGame.currentPlayer == MyGame.WHITE) {
				JOptionPane
						.showMessageDialog(
								null,
								"Oops, White Player! Your King is Checked!!",
								null, JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane
						.showMessageDialog(
								null,
								"Oops, Black Player! Your King is Checked!!",
								null, JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private void checkIfEnd() {
		if (MyGame.currentState != MyGame.PLAYING) {
			if (MyGame.currentState == MyGame.WHITE_WIN) {
				JOptionPane.showMessageDialog(null,
						"Congratulations!! White Player Won!!.",
						null, JOptionPane.WARNING_MESSAGE);
				MyGame.whitePlayerScore++;
				MyGame.undoAvailable = false;
				Graphic.whiteScoreLabel
						.setText("White Player Score "
								+ MyGame.whitePlayerScore);
			} else {
				JOptionPane.showMessageDialog(null,
						"Congratulations!! Black Player Won!!.",
						null, JOptionPane.WARNING_MESSAGE);
				MyGame.blackPlayerScore++;
				MyGame.undoAvailable = false;
				Graphic.blackScoreLabel
						.setText("Black Player Score "
								+ MyGame.blackPlayerScore);
			}
		}
	}

	/*
	 * Undo setting function
	 */
	private void unDoSetting(JButton rightClick) {
	//	if (MyGame.currentPlayer == MyGame.WHITE) {
			MyGame.LastPickLocation = chosenPieceIndex;
			MyGame.LastPickPiece = MyGame.board[chosenPieceIndex];
			MyGame.LastPickButton = oldLocation;
			MyGame.LastPickIcon = chosenImage;

			MyGame.LastMoveLocation = moveLocationIndex;
			MyGame.LastMovePiece = MyGame.board[moveLocationIndex];
			MyGame.LastMoveButton = rightClick;
			MyGame.LastMoveIcon = (ImageIcon) rightClick.getIcon();
	}
	


}
