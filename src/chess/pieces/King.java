package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "K";
	}

	//Metodo que verifica se e possivel mover a peca para tal posicao
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);

		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);
	
		//Movendo para cima
		p.setValues(position.getRow()-1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p))	{
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Movendo para baixo
		p.setValues(position.getRow()+1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p))	{
			mat[p.getRow()][p.getColumn()] = true;
		}


		//Movendo para esquerda
		p.setValues(position.getRow(), position.getColumn()-1);
		if(getBoard().positionExists(p) && canMove(p))	{
			mat[p.getRow()][p.getColumn()] = true;
		}

		//Movendo para direita
		p.setValues(position.getRow(), position.getColumn()+1);
		if(getBoard().positionExists(p) && canMove(p))	{
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Movendo para diagonal da esquerda inferior
		p.setValues(position.getRow()+1, position.getColumn()-1);;
		if(getBoard().positionExists(p) && canMove(p))	{
			mat[p.getRow()][p.getColumn()] = true;
		}

		//Movendo para diagonal da direita inferior
		p.setValues(position.getRow()+1, position.getColumn()+1);;
		if(getBoard().positionExists(p) && canMove(p))	{
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Movendo para diagonal da direita superior
		p.setValues(position.getRow()-1, position.getColumn()+1);;
		if(getBoard().positionExists(p) && canMove(p))	{
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Movendo para diagonal da esquerda superior
		p.setValues(position.getRow()-1, position.getColumn()-1);;
		if(getBoard().positionExists(p) && canMove(p))	{
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}
}
