package entities.boardLayer;

public class Piece {
    protected Position position;
    private Board board;

    public Piece(){
    
    }

    public Piece(Board board){
        this.board = board;
    }

    
    public boolean possibleMove(Position position){
      return true;
    }


    


    /**
     * @return Board return the board
     */
    protected Board getBoard() {
        return board;
    }

    
    

}
