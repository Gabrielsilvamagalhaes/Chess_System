package entities.chessLayer.PiecesOfChess;

import entities.boardLayer.Board;
import entities.chessLayer.ChessPiece;
import entities.chessLayer.enums.Color;

public class Queen extends ChessPiece {

    public Queen() {
        super();

    }
    
    public Queen(Board board, Color color) {
        super(board, color);

    }

    @Override
    public String toString(){
        return "Q";
    }
}
