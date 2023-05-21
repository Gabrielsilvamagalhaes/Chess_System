package entities.chessLayer.PiecesOfChess;

import entities.boardLayer.Board;
import entities.chessLayer.ChessPiece;
import entities.chessLayer.enums.Color;

public class Rook extends ChessPiece{
    public Rook(){
        super();
    }
    
    public Rook(Board board, Color color){
        super(board, color);
    }

    @Override
    public String toString(){
        return "R";
    }

    
}
