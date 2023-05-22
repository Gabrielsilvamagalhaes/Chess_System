package entities.chessLayer;


import entities.boardLayer.Position;
import exceptions.ChessException;

public class ChessPosition {
    private char column;
    private int row;

    public ChessPosition() {

    }

    public ChessPosition(int row, char column) {
        if (column < 'a' || column > 'h' || row > 8 || row < 1) {

            throw new ChessException("ERRO OF POSITION: the incorrect position of collumn!");
        }
        this.column = column;
        this.row = row;

    }

    // Metodo que traduz a posicao da peca
    private char translatePosition(int column) {
        switch (column) {
            case 0:
                return 'a';

            case 1:
                return 'b';

            case 2:
                return 'c';

            case 3:
                return 'd';

            case 4:
                return 'e';

            case 5:
                return 'f';

            case 6:
                return 'g';

            case 7:
                return 'h';

            default:
                throw new ChessException("ERRO OF POSITION: the incorrect position of collumn!");

        }
    }

    // Metodo que traduz a posicao da peca
    private int translatePosition(char column) {
        switch (column) {
            case 'a':
                return 0;

            case 'b':
                return 1;

            case 'c':
                return 2;

            case 'd':
                return 3;

            case 'e':
                return 4;

            case 'f':
                return 5;

            case 'g':
                return 6;

            case 'h':
                return 7;

            default:
                throw new ChessException("ERRO OF POSITION: the incorrect position of collumn!");

        }
    }

    // Metodo que converte a posicao em xadrez para computacional
    protected Position toPosition() {
        return new Position(8 - row, translatePosition(column));

    }

    // Metodo que converte a posicao em matriz para xadrez
    protected ChessPosition fromPosition(Position position) {
        return new ChessPosition(8 - position.getRow(), translatePosition(position.getColumn()));
    }

    public String toString() {
        return "" + column + row;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

}
