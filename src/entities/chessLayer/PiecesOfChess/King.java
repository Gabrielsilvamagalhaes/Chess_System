package entities.chessLayer.PiecesOfChess;

import entities.boardLayer.Board;
import entities.chessLayer.ChessPiece;
import entities.chessLayer.enums.Color;

public class King extends ChessPiece{

    public King(){
        super();
    }

    public King(Board board, Color color){
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves(){
        boolean [][]promt= new boolean [getBoard().getRows()][getBoard().getColumns()];
        return promt;
    }
   
    @Override
    public String toString(){
        return "K";
    }

    
}
