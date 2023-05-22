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

    //Metodo para acessar as pecas a partir das linhas e colunas
    public Piece piece(int row, int column) {
        if (!positionExsits(row, column)) {
            throw new BoardException("ERROR ACCESSING THE PIECE: the wrong column or row!");
        }
        return pieces[row][column];
    }
    
    //Metodo para acessar as pecas a partir da posicao
    public Piece piece(Position position) {
        if (!positionExsits(position)) {
            throw new BoardException("ERROR ACCESSING THE PIECE: the wrong column or row!");
        }
        return pieces[position.getRow()][position.getColumn()];
    }
    
    public Piece removePiece(Position position) {
        if(!positionExsits(position)){
            throw new BoardException("ERROR ACCESSING THE PIECE: the wrong column or row!");
        }

        if(piece(position)==null){
            return null;
        }else{
            Piece temp = piece(position);
            temp.position =null;
            pieces[position.getRow()][position.getColumn()] = null;
            return temp;

        }

        
        

    }
    
    //Metodo para alocar a peca em tal posicao
    public void placePiece(Piece piece, Position position) {
        if(thereIsAPiece(position)){
            throw new BoardException("ERROR ACCESSING THE PIECE: the position already exists!");

        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;

    }
    

    //Metodo para saber se a posicao existi a partir de linhas e colunas
    private boolean positionExsits(int row, int column) {
        return column >= 0 && row >= 0 && column < 8 && row < 8;
        
    }
    
    //Metodo para saber se a posicao existi a partir da posicao
    public boolean positionExsits(Position position) {
        return positionExsits(position.getRow(), position.getColumn());
    }

    //Metoo para saber se ja ha uma peca nessa posicao
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
