package entities.boardLayer;

public class Position {
    private int row;
    private int column;

    public Position(){
        
    }
    
    public Position(int row, int column){
        this.row = row;
        this.column = column;
    }

    
    

    public String toString(){
        return row+", "+ column;
    }
    /**
     * @return int return the row
     */
    public int getRow() {
        return row;
    }

    

    /**
     * @return int return the column
     */
    public int getColumn() {
        return column;
    }


   


    /**
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }

}
