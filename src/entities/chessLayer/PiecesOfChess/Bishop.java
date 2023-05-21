package entities.chessLayer.PiecesOfChess;

import entities.boardLayer.Board;
import entities.chessLayer.ChessPiece;
import entities.chessLayer.enums.Color;

public class Bishop extends ChessPiece {

    public Bishop() {
        super();
    }

    public Bishop(Board board, Color color){
        super(board, color);
    }

    public String toString(){
        return "B";
    }

}
