package entities.chessLayer;

import entities.boardLayer.Board;
import entities.boardLayer.Position;
import entities.chessLayer.PiecesOfChess.Bishop;
import entities.chessLayer.PiecesOfChess.King;
import entities.chessLayer.PiecesOfChess.Knight;
import entities.chessLayer.PiecesOfChess.Pawn;
import entities.chessLayer.PiecesOfChess.Queen;
import entities.chessLayer.PiecesOfChess.Rook;
import entities.chessLayer.enums.Color;

public class ChessMatch {
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMatch;
    private ChessPiece promoted;
    private Board board;
    
    public ChessMatch(){
        board = new Board(8, 8);
        initialSetup();
        
    }
    
    public ChessPiece [][] getPieces(){
         ChessPiece[][] chessPieces= new ChessPiece[board.getRows()][board.getColumns()];

         for(int i=0; i<chessPieces.length; i++){
            for(int j=0; j<chessPieces.length; j++){
                chessPieces[i][j] =(ChessPiece) board.piece(i, j);

            }

         }
         return chessPieces;

        
    }

    private void initialSetup(){
        //Pecas brancas
        board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
        board.placePiece(new Queen(board, Color.WHITE), new Position(7, 3));
        board.placePiece(new Rook(board, Color.WHITE), new Position(7, 7));
        board.placePiece(new Rook(board, Color.WHITE), new Position(7, 0));
        board.placePiece(new Knight(board, Color.WHITE), new Position(7, 1));
        board.placePiece(new Knight(board, Color.WHITE), new Position(7, 6));
        board.placePiece(new Bishop(board, Color.WHITE), new Position(7, 2));
        board.placePiece(new Bishop(board, Color.WHITE), new Position(7, 5));
        board.placePiece(new Pawn(board, Color.WHITE), new Position(6, 0));
        board.placePiece(new Pawn(board, Color.WHITE), new Position(6, 1));
        board.placePiece(new Pawn(board, Color.WHITE), new Position(6, 2));
        board.placePiece(new Pawn(board, Color.WHITE), new Position(6, 3));
        board.placePiece(new Pawn(board, Color.WHITE), new Position(6, 4));
        board.placePiece(new Pawn(board, Color.WHITE), new Position(6, 5));
        board.placePiece(new Pawn(board, Color.WHITE), new Position(6, 6));
        board.placePiece(new Pawn(board, Color.WHITE), new Position(6, 7));
        
        
        //Pecas pretas
        board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
        board.placePiece(new Queen(board, Color.BLACK), new Position(0, 3));
        board.placePiece(new Rook(board, Color.BLACK), new Position(0, 7));
        board.placePiece(new Rook(board, Color.BLACK), new Position(0, 0));
        board.placePiece(new Knight(board, Color.BLACK), new Position(0, 1));
        board.placePiece(new Knight(board, Color.BLACK), new Position(0, 6));
        board.placePiece(new Bishop(board, Color.BLACK), new Position(0, 2));
        board.placePiece(new Bishop(board, Color.BLACK), new Position(0, 5));
        board.placePiece(new Pawn(board, Color.BLACK), new Position(1, 0));
        board.placePiece(new Pawn(board, Color.BLACK), new Position(1, 1));
        board.placePiece(new Pawn(board, Color.BLACK), new Position(1, 2));
        board.placePiece(new Pawn(board, Color.BLACK), new Position(1, 3));
        board.placePiece(new Pawn(board, Color.BLACK), new Position(1, 4));
        board.placePiece(new Pawn(board, Color.BLACK), new Position(1, 5));
        board.placePiece(new Pawn(board, Color.BLACK), new Position(1, 6));
        board.placePiece(new Pawn(board, Color.BLACK), new Position(1, 7));
    }
    
    
}
