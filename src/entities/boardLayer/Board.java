package entities.boardLayer;

import exceptions.BoardException;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board() {

    }

    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new BoardException("ERROR CREATING OF BOARD: the board need at least 1 row or 1 column!");
        }

        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public Piece piece(int row, int column) {
        if (!positionExsits(row, column)) {
            throw new BoardException("ERROR ACCESSING THE PIECE: the wrong column or row!");
        }
        return pieces[row][column];
    }
    
    public Piece piece(Position position) {
        if (!positionExsits(position)) {
            throw new BoardException("ERROR ACCESSING THE PIECE: the wrong column or row!");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    public void removePiece(Position position) {

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

    public void placePiece(Piece piece, Position position) {
        if(thereIsAPiece(position)){
            throw new BoardException("ERROR ACCESSING THE PIECE: the position already exists!");

        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;

    }

    private boolean positionExsits(int row, int column) {
        return column >= 0 && row >= 0 && column < 8 && row < 8;

    }

    public boolean positionExsits(Position position) {
        return positionExsits(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position) {
        if(!positionExsits(position)){
            throw new BoardException("ERROR ACCESSING THE PIECE: the wrong column or row!");
        }

        return piece(position) != null;
    }

    /**
     * @return int return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * @return int return the columns
     */
    public int getColumns() {
        return columns;
    }

}
