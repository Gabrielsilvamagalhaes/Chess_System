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
    public Piece piece(Position position){
        return pieces[position.getRow()] [position.getColumn()];
    }
    
    public void removePiece(Position position){

        Piece[][] newPieces = new Piece[pieces.length - 1][pieces[0].length - 1];

        int newRow = 0;
        for (int i = 0; i < pieces.length; i++) {
            if (i == position.getRow()) {
                continue; 
            }

            int newColumn = 0;
            for (int j = 0; j < pieces[0].length; j++) {
                if (j == position.getColumn()) {
                    continue; 
                }

                newPieces[newRow][newColumn] = pieces[i][j];
                newColumn++;
            }

            newRow++;
        }


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
