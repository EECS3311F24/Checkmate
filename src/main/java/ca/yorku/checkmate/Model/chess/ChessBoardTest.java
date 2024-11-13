package ca.yorku.checkmate.Model.chess;

import ca.yorku.checkmate.Model.chess.chesspieces.ChessPiece;
import ca.yorku.checkmate.Model.chess.chesspieces.Pawn;

import java.util.ArrayList;
import java.util.List;

// #NOTE you'll need to enable asserts: https://stackoverflow.com/questions/11415160/how-to-enable-the-java-keyword-assert-in-eclipse-program-wise
public class ChessBoardTest extends ChessBoard {
	
	// to satisfy the move() method
	static Player playerW = new Player('W');
    static Player playerB = new Player('B');
	
	ChessBoardTest() {
		board = new Placeholder[dimensions][dimensions];
        this.blackPieces = new ArrayList<>();
        this.whitePieces = new ArrayList<>();
        this.capturedPieces = new ArrayList<>();
        //place blank spaces
        for(int i = 0; i < dimensions; i++){
            for(int j = 0; j < dimensions; j++){
                board[i][j] = new Placeholder();
            }
        }
	}
	
	void addPiece(ChessPiece p, Move position) {
		List<ChessPiece> piecesList;
		switch (p.getColor()) {
			case white: {
				piecesList = this.whitePieces;
				break;
			}
			case black: {
				piecesList = this.blackPieces;
				break;
			}
			default: {
				//#TODO throw exception
				System.err.println("invalid piece char");
				return;
			}
		}
		
		piecesList.add(p);
		board[position.row()][position.col()] = new Placeholder(p);
        p.addMove(position);
	}
	
	// Moves the source piece to the destination. No checks are made.
	void movePiece(Move start, Move end) {
		Placeholder startSquare = board[start.row()][start.col()];
		board[end.row()][end.col()].piece = startSquare.piece;
		board[end.row()][end.col()].chessPiece = startSquare.chessPiece;
		startSquare.emptyPlaceholder();
	}
	
	// Returns the Placeholder at position
	Placeholder getPlace(Move position) {
		return getPlace(position.row(), position.col());
	}
	
	// Returns the Placeholder at (r, c)
	Placeholder getPlace(int r, int c) {
		return board[r][c];
	}
	
	void assertMoveSuccess(ChessPiece p, Move start, Move end) {
		// Start place is cleared
		Placeholder startPlace = getPlace(start);
		assert startPlace.chessPiece == null && startPlace.piece == ' ';
		// End place contains piece
		Placeholder endPlace = getPlace(end);
		assert endPlace.chessPiece == p && endPlace.piece == p.symbol;
	}
	
	void assertMoveReject(ChessPiece p, Move start, Move end) {
		// Board is unchanged
		Placeholder startPlace = getPlace(start);
		assert startPlace.chessPiece == p && startPlace.piece == p.symbol;
		Placeholder endPlace = getPlace(end);
		assert endPlace.chessPiece == null && endPlace.piece == ' ';
		System.out.println("move ");
	}
	
	void assertMoveCapture(ChessPiece p, ChessPiece capturedPiece, Move start, Move end) {
		
		// Gather the captured sides list of pieces
		List<ChessPiece> piecesList;
		int listSizeBefore;
		
		switch (p.getColor()) {
			case white: {
				piecesList = this.whitePieces;
				listSizeBefore = this.whitePieces.size();
				break;
			}
			case black: {
				piecesList = this.blackPieces;
				listSizeBefore = this.blackPieces.size();
				break;
			}
			default: {
				//#TODO throw exception
				System.err.println("invaild piece char");
				return;
			}
		}
		
		// Start place is cleared
		Placeholder startPlace = getPlace(start);
		assert startPlace.chessPiece == null && startPlace.piece == ' ';
		
		// End place contains capturing piece
		Placeholder endPlace = getPlace(end);
		assert endPlace.chessPiece == p && endPlace.piece == p.symbol;
		
		// Lists have been updated correctly
		assert piecesList.size() == listSizeBefore - 1;
		assert capturedPieces.contains(capturedPiece);
	}
	
	static void testWhitePawn() {
		// Setup 
		ChessBoardTest c;
		ChessPiece pawn;
		ChessPiece opponent;
		Move start;
		Move end;
		
		// Move 1 square
		c = new ChessBoardTest();
		pawn = new Pawn('W');
		start = new Move(6, 3);
		end   = new Move(5, 3);
		c.addPiece(pawn, start);
		c.move(pawn, end, playerW.playerColor(), false);
		
		c.assertMoveSuccess(pawn, start, end);
		
		// Try to move 2 squares on non-first move
		start = new Move(5, 3);
		end   = new Move(3, 3);
		c.move(pawn, end, playerW.playerColor(), false);
		
		c.assertMoveReject(pawn, start, end);
		
		// Move 2 squares on first move
		c = new ChessBoardTest();
		pawn = new Pawn('W');
		start = new Move(6, 3);
		end   = new Move(4, 3);
		c.addPiece(pawn, start);
		c.move(pawn, end, playerW.playerColor(), false);
		
		c.assertMoveSuccess(pawn, start, end);
		
		// Try to move 3 squares
		c = new ChessBoardTest();
		pawn = new Pawn('W');
		start = new Move(6, 3);
		end   = new Move(3, 3);
		c.addPiece(pawn, start);
		c.move(pawn, end, playerW.playerColor(), false);
		
		c.assertMoveReject(pawn, start, end);
		
		// Try to move through a piece on the same column
		c = new ChessBoardTest();
		
		pawn = new Pawn('W');
		start = new Move(6, 3);
		end   = new Move(4, 3);
		c.addPiece(pawn, start);
		
		opponent = new Pawn('B');
		c.addPiece(opponent, new Move(5, 3));
		
		c.move(pawn, end, playerW.playerColor(), false);
		
		c.assertMoveReject(pawn, start, end);
		
		// Capture an opposing piece
		c = new ChessBoardTest();
		
		pawn = new Pawn('W');
		start = new Move(6, 3);
		end   = new Move(5, 2);
		c.addPiece(pawn, start);

		opponent = new Pawn('B');
		c.addPiece(opponent, end);
		
		c.move(pawn, end, playerW.playerColor(), false);
		
		c.assertMoveCapture(pawn, opponent, start, end);
		
		// #TODO Try to capture a friendly piece
		
		// Try to move to random invalid squares
		// #NOTE need to have a list of valid moves for other pieces, but simple enough for a pawn
		c = new ChessBoardTest();
		pawn = new Pawn('W');
		start = new Move(3, 3);
		c.addPiece(pawn, start);
		int i = 0;
		while (i < 20) {
			int row = (int)(Math.random() * ChessBoard.dimensions);
			int col = (int)(Math.random() * ChessBoard.dimensions);
			
			// Exclude valid move(s)
			if (row == 2 && col == 3) {
				continue;
			}
			
			end = new Move(row, col);
			
			c.move(pawn, end, playerW.playerColor(), false);
			c.assertMoveReject(pawn, start, end);
			i++;
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("Start tests");
		testWhitePawn();
		System.out.println("End tests");
	}
}
