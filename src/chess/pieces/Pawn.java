package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "p";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        
        Position p = new Position(0, 0);
        
        if (Color.WHITE == getColor()) {
            //Movendo peoes brancos
            //Movendo a peca uma casa para frente
            p.setValues(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
                
            }
            //Movendo a peca 2 casas para frente
            p.setValues(position.getRow() - 2, position.getColumn());
            Position p2 = new Position(position.getRow() - 1, position.getColumn());
            //Condicional que verifica se a posicao existe, se é uma peca, se pode andar 2 casas
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0&& getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) ) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            //Movendo a peca para diagonal superior esquerda
            p.setValues(position.getRow() - 1, position.getColumn()-1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
                
            }
            //Movendo a peca para diagonal superior direita
            p.setValues(position.getRow() - 1, position.getColumn()+1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
                
            }
            
            
        } else {
            //Movendo peoes pretos
            //Movendo a peca uma casa para frente
            p.setValues(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
                
            }
            //Movendo a peca 2 casas para frente
            p.setValues(position.getRow() + 2, position.getColumn());
            Position p2 = new Position(position.getRow() - 1, position.getColumn());
            
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0&& getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) ) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            //Movendo a peca para diagonal inferior direita
            p.setValues(position.getRow() + 1, position.getColumn()+1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
                
            }
            //Movendo a peca para diagonal inferior esquerda
            p.setValues(position.getRow() +1, position.getColumn()-1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
                
            }
            
            
        }
        return mat;
        
    }

}
