package entities.chessLayer.PiecesOfChess;

import entities.boardLayer.Board;
import entities.boardLayer.Position;
import entities.chessLayer.ChessPiece;
import entities.chessLayer.enums.Color;

public class Rook extends ChessPiece{
    public Rook(){
        super();
    }
    
    public Rook(Board board, Color color){
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves(){
        boolean [][]promt= new boolean [getBoard().getRows()][getBoard().getColumns()];
        
        
        Position p=  new Position(0, 0);
        //Movendo a peca para cima
        p.setValues(position.getRow()-1, position.getColumn());
        while(getBoard().positionExsits(p) && !getBoard().thereIsAPiece(p)){
            promt [p.getRow()][p.getColumn()] = true;
            p.setRow(position.getRow()-1);
            
        }
        if(getBoard().positionExsits(p) && isThereOpponentPiece(p)){
            promt [p.getRow()][p.getColumn()] = true;

        }
        //Movendo a peca para esquerda
        p.setValues(position.getRow(), position.getColumn()-1);
        while(getBoard().positionExsits(p) && !getBoard().thereIsAPiece(p)){
            promt [p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn()-1);
            
        }
        if(getBoard().positionExsits(p) && isThereOpponentPiece(p)){
            promt [p.getRow()][p.getColumn()] = true;
            
        }
        //Movendo a peca para direita
        p.setValues(position.getRow(), position.getColumn()+1);
        while(getBoard().positionExsits(p) && !getBoard().thereIsAPiece(p)){
            promt [p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn()+1);
            
        }
        if(getBoard().positionExsits(p) && isThereOpponentPiece(p)){
            promt [p.getRow()][p.getColumn()] = true;
            
        }
        
        //Movendo a peca para baixo
        p.setValues(position.getRow()+1, position.getColumn());
        while(getBoard().positionExsits(p) && !getBoard().thereIsAPiece(p)){
            promt [p.getRow()][p.getColumn()] = true;
            p.setRow(position.getRow()+1);
            
        }
        if(getBoard().positionExsits(p) && isThereOpponentPiece(p)){
            promt [p.getRow()][p.getColumn()] = true;

        }
        return promt;
    }



    @Override
    public String toString(){
        return "R";
    }

    
}
