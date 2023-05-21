package entities.chessLayer.PiecesOfChess;

import entities.boardLayer.Board;
import entities.chessLayer.ChessPiece;
import entities.chessLayer.enums.Color;

public class Knight extends ChessPiece {

    public Knight(){
        super();
    }

    public Knight(Board board, Color color){
        super(board, color);
    }

    public String toString(){
        return "N";
    }

    
}
