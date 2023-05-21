package entities.chessLayer;

import entities.boardLayer.Board;
import entities.boardLayer.Piece;
import entities.chessLayer.enums.Color;

public abstract class ChessPiece extends Piece {
    private Color color;

    public ChessPiece(){
        super();
    }
    public ChessPiece(Board board, Color color){
        super(board);
        this.color = color;
    }
    
    

    /**
     * @return Color return the color
     */
    public Color getColor() {
        return color;
    }

   

    

}
