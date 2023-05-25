package entities.boardLayer;

public abstract class Piece {
    protected Position position;
    private Board board;

    public Piece(){
    
    }

    public Piece(Board board){
        this.board = board;
    }

    
    public abstract boolean[][] possibleMoves();


    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];

    }

    public boolean isThereAnyPossibleMove(){
        boolean[][] promt = possibleMoves();
        for(int i =0;i<promt.length; i++){
            for(int j =0; j<promt.length; j++){
                if(promt[i][j]){
                    return true;
                }
            }
        }
        return false;
    }
     
    


    


    /**
     * @return Board return the board
     */
    protected Board getBoard() {
        return board;
    }

    
    

}
