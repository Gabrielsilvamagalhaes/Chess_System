package entities.chessLayer;

import entities.boardLayer.Board;
import entities.boardLayer.Piece;

public class ChessMatch {
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMatch;
    private ChessPiece promoted;
    private Board board;
    
    public ChessMatch(){
        board = new Board(8, 8);
        
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
    
    
}
