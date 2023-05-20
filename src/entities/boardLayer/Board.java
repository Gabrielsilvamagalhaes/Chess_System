package entities.boardLayer;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(){
        
    }
    public Board(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows] [columns];
    }

    public Piece piece(int row, int column){
        return pieces[row][column];
    }

    

    /**
     * @return int return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * @return int return the columns
     */
    public int getColumns() {
        return columns;
    }

    

}
