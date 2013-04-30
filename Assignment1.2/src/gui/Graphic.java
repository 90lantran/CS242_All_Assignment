package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import pieces.Pieces;
import chessGame.*;

public class Graphic {

	private static JFrame window;
	private static BoardPanel myBoard;
	private static ControlPanel myControl;
	private static ScorePanel myScore;

	public static int chosenPieceIndex;
	public static ImageIcon chosenImage;
	public static JButton oldLocation;
	public static int moveLocationIndex;
	public static int enteredIndex;
	public static int draggedIndex;
	public static boolean validChoose;
	public static boolean validMove;
	public static Color oldColor;

	public static JLabel whiteScoreLabel;
	public static JLabel blackScoreLabel;

	public Graphic() {
		window = new JFrame("Chess Game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		myBoard = new BoardPanel();
		ControlPanel myControl = new ControlPanel();
		ScorePanel myScore = new ScorePanel();
		window.add(myBoard, BorderLayout.WEST);
		window.add(myControl, BorderLayout.SOUTH);
		window.add(myScore, BorderLayout.EAST);
		window.pack();
	}

	private static class BoardPanel extends JPanel {
		private static final int N = 8;
		private static final int SIZE = 70;

		public BoardPanel() {
			super(new GridLayout(N, N));
			this.setPreferredSize(new Dimension(N * SIZE, N * SIZE));

			for (int i = 11; i < 89; i++) {

				final JButton gridSquare = new JButton();

				// gridSquare.add(new PieceImage(i));
				gridSquare.setVisible(true);
				gridSquare.setIcon(pieceImage(i));

				int x = i % 10;
				int y = i / 10;
				if ((x + y) % 2 == 0) {
					gridSquare.setBackground(Color.gray);
					this.add(gridSquare);
				} else {
					gridSquare.setBackground(Color.white);
					this.add(gridSquare);
				}

				gridSquare.putClientProperty("index", i);
				
				Action mouseHandler = new Action();
				gridSquare.addMouseListener(mouseHandler);
				gridSquare.addMouseMotionListener(mouseHandler);

				if (i % 10 == 8)
					i += 2;
			}
		}
	}

	/*
	 * Mouse actions on the main chess board
	 */
	private static class Action implements MouseInputListener,
			MouseMotionListener {
		public void mousePressed(MouseEvent e) {}
		public void mouseDragged(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {}
		
		/*
		 * A valid move will show green
		 * An invalid move will show red
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
		 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
		 */
		public void mouseExited(MouseEvent e) {
			JButton exitedButton = (JButton) e.getSource();
			exitedButton.setBackground(oldColor);
		}

		/*
		 * Left click to choose a piece. Valid choose, square turns green; invalid choose turns red
		 * Right click to move a piece. Valid move, square turns green; invalid move turns red
		 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		public void mouseClicked(MouseEvent e) {
			//left click
			if (SwingUtilities.isLeftMouseButton(e)) {
				JButton leftClick = (JButton) e.getSource();
				chosenPieceIndex = (int) leftClick.getClientProperty("index");
				System.out.println("button index is " + chosenPieceIndex);

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

					JOptionPane.showMessageDialog(null,"Please Start a game first.", null,JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,"Please ReStart a game first.", null,JOptionPane.WARNING_MESSAGE);
				}

			} 
			//right click
			else {
				JButton rightClick = (JButton) e.getSource();
				moveLocationIndex = (int) rightClick.getClientProperty("index");
				System.out.println("move to " + moveLocationIndex);

				if (validChoose) {
					if (MoveValidCheck.isValidMove(chosenPieceIndex, moveLocationIndex)) {
						
						unDoSetting(rightClick);	//set undo

						MoveExecute.executeMove(chosenPieceIndex, moveLocationIndex);

						MyGame.undoAvailable++;

						if (MyGame.currentState != MyGame.PLAYING) {
							if (MyGame.currentState == MyGame.WHITE_WIN) {
								JOptionPane.showMessageDialog(null,"Congratulations!! White Player Won!!.",null,JOptionPane.WARNING_MESSAGE);
								MyGame.whitePlayerScore++;
								MyGame.undoAvailable = 0;
								whiteScoreLabel.setText("White Player Score "
										+ MyGame.whitePlayerScore);
							} else {
								JOptionPane.showMessageDialog(null,"Congratulations!! Black Player Won!!.",null,JOptionPane.WARNING_MESSAGE);
								MyGame.blackPlayerScore++;
								MyGame.undoAvailable = 0;
								blackScoreLabel.setText("Black Player Score "
										+ MyGame.blackPlayerScore);
							}
						}

						//move piece image
						rightClick.setIcon(chosenImage);
						oldLocation.setIcon(null);
						MyGame.switchPlayer();
						validChoose = false;	//for next pick

						/*
						 * Check condition
						 */
						if (MyGame.currentState == MyGame.PLAYING
								&& ConditionCheck.isChecked()) {
							if (MyGame.currentPlayer == MyGame.WHITE) {
								JOptionPane.showMessageDialog(null,"Oops, White Player! Your King is Checked!!",null,JOptionPane.WARNING_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(null,"Oops, Black Player! Your King is Checked!!",null,JOptionPane.WARNING_MESSAGE);
							}
						}
					}
				}
			}
		}
		
		/*
		 * Undo setting function
		 */
		private void unDoSetting(JButton rightClick) {
			if (MyGame.currentPlayer == MyGame.WHITE) {
				MyGame.whiteLastPickLocation = chosenPieceIndex;
				MyGame.whiteLastPickPiece = MyGame.board[chosenPieceIndex];
				MyGame.whiteLastPickButton = oldLocation;
				MyGame.whiteLastPickIcon = chosenImage;

				MyGame.whiteLastMoveLocation = moveLocationIndex;
				MyGame.whiteLastMovePiece = MyGame.board[moveLocationIndex];
				MyGame.whiteLastMoveButton = rightClick;
				MyGame.whiteLastMoveIcon = (ImageIcon) rightClick
						.getIcon();

			} else {
				MyGame.blackLastPickLocation = chosenPieceIndex;
				MyGame.blackLastPickPiece = MyGame.board[chosenPieceIndex];
				MyGame.blackLastPickButton = oldLocation;
				MyGame.blackLastPickIcon = chosenImage;

				MyGame.blackLastMoveLocation = moveLocationIndex;
				MyGame.blackLastMovePiece = MyGame.board[moveLocationIndex];
				MyGame.blackLastMoveButton = rightClick;
				MyGame.blackLastMoveIcon = (ImageIcon) rightClick
						.getIcon();
			}
		}

	}

	/*
	 * ControlPanel Setting
	 */
	private static class ControlPanel extends JPanel {
		public ControlPanel() {
			super(new GridLayout(1, 4));

			JButton startButton = new JButton("Start");
			JButton restartButton = new JButton("Restart");
			JButton forfeitButton = new JButton("ForFeit");
			JButton undoButton = new JButton("Undo");

			startButton.addActionListener(new StartAction());
			restartButton.addActionListener(new RestartAction());
			forfeitButton.addActionListener(new ForfeitAction());
			undoButton.addActionListener(new UndoAction());

			this.add(startButton);
			this.add(restartButton);
			this.add(forfeitButton);
			this.add(undoButton);
		}

	}

	/*
	 * The game between the same two players can only be started once.
	 * After one battle finished, click Restart to begin a new battle.
	 */
	private static class StartAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (MyGame.currentState == MyGame.NOPLAYING) {
				int n = JOptionPane.showConfirmDialog(null,
						"Would you like to start a game?", null,
						JOptionPane.YES_NO_OPTION);

				if (n == 0) {
					MyGame.currentState = MyGame.PLAYING;
					MyGame.currentPlayer = MyGame.WHITE;
					MyGame.undoAvailable = 0;
					whiteScoreLabel.setText("White Player Score "
							+ MyGame.whitePlayerScore);
					blackScoreLabel.setText("Black Player Score "
							+ MyGame.blackPlayerScore);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Game is already started.",
						null, JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/*
	 * Restart Action can only happen after the game is started.
	 */
	private static class RestartAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (MyGame.currentState != MyGame.NOPLAYING) {

				int n = JOptionPane.showConfirmDialog(null,
						"White Player, Would you like to RESTART a Battle?", null,
						JOptionPane.YES_NO_OPTION);
				
				int m = JOptionPane.showConfirmDialog(null,
						"Black Player, Would you like to RESTART a Battle?", null,
						JOptionPane.YES_NO_OPTION);

				if (n == 0 && m == 0) {
					MyGame.initBoard();
					MyGame.currentState = MyGame.PLAYING;
					MyGame.currentPlayer = MyGame.WHITE;
					MyGame.undoAvailable = 0;

					myBoard.removeAll();
					window.remove(myBoard);

					myBoard = new BoardPanel();
					window.add(myBoard, BorderLayout.WEST);

					whiteScoreLabel.setText("White Player Score "
							+ MyGame.whitePlayerScore);
					blackScoreLabel.setText("Black Plyaer Score "
							+ MyGame.blackPlayerScore);

					window.revalidate();
					window.repaint();

				}
				else {
					JOptionPane.showMessageDialog(null,
							"Restart fail. Please keep playing this Battle.", null,
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Please Start a Game first.", null,
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/*
	 * Forfeit Action could only happen during a battle.
	 */
	private static class ForfeitAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (MyGame.currentState == MyGame.PLAYING) {

				int n = JOptionPane.showConfirmDialog(null,
						"Would you like to forfeit this game?", null,
						JOptionPane.YES_NO_OPTION);

				if (n == 0) {
					if (MyGame.currentPlayer == MyGame.WHITE) {
						JOptionPane.showMessageDialog(null,
								"Congratulations!! Black Player Won!!.", null,
								JOptionPane.WARNING_MESSAGE);
						MyGame.blackPlayerScore++;
						MyGame.currentState = MyGame.BLACK_WIN;
					} else {
						JOptionPane.showMessageDialog(null,
								"Congratulations!! White Player Won!!.", null,
								JOptionPane.WARNING_MESSAGE);
						MyGame.whitePlayerScore++;
						MyGame.currentState = MyGame.WHITE_WIN;
					}

					whiteScoreLabel.setText("White Player Score "
							+ MyGame.whitePlayerScore);
					blackScoreLabel.setText("Black Plyaer Score "
							+ MyGame.blackPlayerScore);
				}
			}

			else {
				JOptionPane.showMessageDialog(null,
						"Please Start a Game first.", null,
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/*
	 * Player allow to undo only their last move.
	 * Undo Action can only happen during a battle.
	 */
	private static class UndoAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (MyGame.undoAvailable >= 2) {

				if (MyGame.currentState == MyGame.PLAYING) {
					int n = JOptionPane.showConfirmDialog(null,
							"Would you like to undo last step?", null,
							JOptionPane.YES_NO_OPTION);

					if (n == 0) {
						MyGame.board[MyGame.whiteLastMoveLocation] = MyGame.whiteLastMovePiece;
						MyGame.board[MyGame.whiteLastPickLocation] = MyGame.whiteLastPickPiece;
						MyGame.whiteLastMoveButton
								.setIcon(MyGame.whiteLastMoveIcon);
						MyGame.whiteLastPickButton
								.setIcon(MyGame.whiteLastPickIcon);

						MyGame.board[MyGame.blackLastMoveLocation] = MyGame.blackLastMovePiece;
						MyGame.board[MyGame.blackLastPickLocation] = MyGame.blackLastPickPiece;
						MyGame.blackLastMoveButton
								.setIcon(MyGame.blackLastMoveIcon);
						MyGame.blackLastPickButton
								.setIcon(MyGame.blackLastPickIcon);

						MyGame.undoAvailable = 0;
					}
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Sorry Undo is not Available.", null,
						JOptionPane.WARNING_MESSAGE);
			}

		}
	}

	/*
	 * ScorePanel will record and update both plays' scores during a Game.
	 */
	private static class ScorePanel extends JPanel {
		public ScorePanel() {
			super(new GridLayout(2, 1));

			JButton whiteScoreButton = new JButton();
			JButton blackScoreButton = new JButton();

			whiteScoreLabel = new JLabel();
			blackScoreLabel = new JLabel();

			whiteScoreLabel.setText("White Player Score "
					+ MyGame.whitePlayerScore);
			blackScoreLabel.setText("Black Player Score "
					+ MyGame.blackPlayerScore);

			whiteScoreButton.add(whiteScoreLabel);
			blackScoreButton.add(blackScoreLabel);

			this.add(blackScoreButton);
			this.add(whiteScoreButton);

		}
	}
	
	/*
	 * Set piece image
	 */
	private static ImageIcon pieceImage(int i) {
		int corPiece = MyGame.board[i];
		ImageIcon piece = null;
		String piecePath = null;

		if (corPiece / 10 == MyGame.BLACK) {
			if (corPiece % 10 == Pieces.ROOK) {
				piecePath = "image/black_rook.gif";
				piece = new ImageIcon(piecePath);
			} else if (corPiece % 10 == Pieces.PAWN) {
				piecePath = "image/black_pawn.gif";
				piece = new ImageIcon(piecePath);
			} else if (corPiece % 10 == Pieces.BISHOP) {
				piecePath = "image/black_bishop.gif";
				piece = new ImageIcon(piecePath);
			} else if (corPiece % 10 == Pieces.KNIGHT) {
				piecePath = "image/black_knight.gif";
				piece = new ImageIcon(piecePath);
			} else if (corPiece % 10 == Pieces.QUEEN) {
				piecePath = "image/black_queen.gif";
				piece = new ImageIcon(piecePath);
			} else if (corPiece % 10 == Pieces.KING) {
				piecePath = "image/black_king.gif";
				piece = new ImageIcon(piecePath);
			}
		} else if (corPiece / 10 == MyGame.WHITE) {
			if (corPiece % 10 == Pieces.ROOK) {
				piecePath = "image/white_rook.gif";
				piece = new ImageIcon(piecePath);
			} else if (corPiece % 10 == Pieces.PAWN) {
				piecePath = "image/white_pawn.gif";
				piece = new ImageIcon(piecePath);
			} else if (corPiece % 10 == Pieces.BISHOP) {
				piecePath = "image/white_bishop.gif";
				piece = new ImageIcon(piecePath);
			} else if (corPiece % 10 == Pieces.KNIGHT) {
				piecePath = "image/white_knight.gif";
				piece = new ImageIcon(piecePath);
			} else if (corPiece % 10 == Pieces.QUEEN) {
				piecePath = "image/white_queen.gif";
				piece = new ImageIcon(piecePath);
			} else if (corPiece % 10 == Pieces.KING) {
				piecePath = "image/white_king.gif";
				piece = new ImageIcon(piecePath);
			}
		}
		return piece;

	}

}
