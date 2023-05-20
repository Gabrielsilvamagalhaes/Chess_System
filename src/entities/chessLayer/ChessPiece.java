package entities.chessLayer;

import entities.boardLayer.Board;
import entities.boardLayer.Piece;

public abstract class ChessPiece extends Piece {
    private Color color;
    private int moveCount;

    public ChessPiece(){
        super();
    }
    public ChessPiece(Board board, Color color, int moveCount){
        super(board);
        this.color = color;
        this.moveCount = moveCount;
    }
    
    

    /**
     * @return Color return the color
     */
    public Color getColor() {
        return color;
    }

   

    

}
