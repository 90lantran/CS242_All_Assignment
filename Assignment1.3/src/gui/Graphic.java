package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pieces.Pieces;
import chessGame.MyGame;

public class Graphic {

	private static JFrame window;
	private static BoardPanel myBoard;	
	public static JLabel whiteScoreLabel;
	public static JLabel blackScoreLabel;
	public static Action myAction;


	public Graphic() {
		MyGame newGame = new MyGame();
		MyGame.initBoard();
		MyGame.whitePlayerScore = 0;
		MyGame.blackPlayerScore = 0;
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
		myAction = new Action();
		
	}

	private static class BoardPanel extends JPanel {
		private static final int N = 8;
		private static final int SIZE = 70;

		public BoardPanel() {
			super(new GridLayout(N, N));
			this.setPreferredSize(new Dimension(N * SIZE, N * SIZE));
			this.setVisible(true);
			//int m = 0;
			for (int i = 11; i < 89; i++) {

				final JButton gridSquare = new JButton();
				
				gridSquare.setVisible(true);
				gridSquare.setIcon(pieceImage(i));			

				int x = i % 10;
				int y = i / 10;
				if ((x + y) % 2 == 0) {
					gridSquare.setBackground(Color.gray);
					
					this.add(gridSquare);
					//System.out.println(mylist.size());
				} else {
					gridSquare.setBackground(Color.white);
					this.add(gridSquare);
				}

				gridSquare.putClientProperty("index", i);
				
				
				
				Action mouseHandler = new Action();
				gridSquare.addMouseListener(mouseHandler);
				gridSquare.addMouseMotionListener(mouseHandler);
				
			//	myList.add(gridSquare);
			//	m++;

				if (i % 10 == 8)
					i += 2;
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
					MyGame.undoAvailable = false;
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
					MyGame.undoAvailable = false;

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
			if (MyGame.undoAvailable) {

				if (MyGame.currentState == MyGame.PLAYING) {
					int n = JOptionPane.showConfirmDialog(null,
							"Would you like to undo last step?", null,
							JOptionPane.YES_NO_OPTION);

					if (n == 0) {
						MyGame.board[MyGame.LastMoveLocation] = MyGame.LastMovePiece;
						MyGame.board[MyGame.LastPickLocation] = MyGame.LastPickPiece;
						MyGame.LastMoveButton
								.setIcon(MyGame.LastMoveIcon);
						MyGame.LastPickButton
								.setIcon(MyGame.LastPickIcon);
						MyGame.switchPlayer();
						MyGame.undoAvailable = false;
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
		String pieceColor = null;

		if (corPiece / 10 == MyGame.BLACK) {
			pieceColor = "black";
		}
		if (corPiece / 10 == MyGame.WHITE){
				pieceColor = "white";
			}
		ImageIcon myPieceImage = loadImage(pieceColor, corPiece);
		return myPieceImage;
	}
	
	private static ImageIcon loadImage(String pieceColor, int corPiece){
		String piecePath = null;
		ImageIcon image = null;
		switch(corPiece % 10) {
		case Pieces.ROOK:
			piecePath = "image/" + pieceColor + "_rook.gif";
			break;
		case Pieces.PAWN:
			piecePath = "image/" + pieceColor + "_pawn.gif";
			break;
		case Pieces.BISHOP:
			piecePath = "image/" + pieceColor + "_bishop.gif";
			break;
		case Pieces.KNIGHT:
			piecePath = "image/" + pieceColor + "_knight.gif";
			break;
		case Pieces.QUEEN:
			piecePath = "image/" + pieceColor + "_queen.gif";
			break;
		case Pieces.KING:
			piecePath = "image/" + pieceColor + "_king.gif";
			break;
		default:
				return null;
		}
		image = new ImageIcon(piecePath);
		return image;
	}	

}
