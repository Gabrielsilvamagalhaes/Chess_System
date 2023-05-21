package entities.chessLayer.PiecesOfChess;

import entities.boardLayer.Board;
import entities.chessLayer.ChessPiece;
import entities.chessLayer.enums.Color;

public class Pawn extends ChessPiece {

    public Pawn(){
        super();
    }

    public Pawn(Board board, Color color){
        super(board, color);
    }

    public String toString(){
        return "p";
    }
    
}
