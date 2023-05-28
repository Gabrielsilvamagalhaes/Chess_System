package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	private int turn;
	private boolean check;
	private boolean checkMate;
	private Color currentPlayer;

	private List<ChessPiece> piecesOnBoard = new ArrayList<>();
	private List<ChessPiece> piecesCaptured = new ArrayList<>();

	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean getCheckMate(){
		return checkMate;
	}

	public boolean getCheck(){
		return check;
	}

	private void nextTurn() {
		turn++;

		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	// Metodo para colocar as pecas no tabuleiro
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);


		if(testCheck(currentPlayer)){
			undoMove(source, target,(ChessPiece) capturedPiece);
			throw new ChessException("You cant put yourself in check!");
		}

		//Expressao lambda que diz, se o oponente estiver ficar em check a partida estara em check
		check = testCheck(opponnet(currentPlayer))? true : false;

		//Se a partida estiver em xeque mate ela acaba
		if(testCheckMate(opponnet(currentPlayer))){
			checkMate = true;
		}else{

			nextTurn();
		}
		return (ChessPiece) capturedPiece;
	}

	// Metodo que move a peca para o local designado e remove a peca do local de
	// origem
	private Piece makeMove(Position source, Position target) {
		ChessPiece p =(ChessPiece) board.removePiece(source);
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		if(capturedPiece!=null){
			piecesCaptured.add((ChessPiece)capturedPiece);
			piecesOnBoard.remove(capturedPiece);
		}
		return capturedPiece;
	}

	//Metodo que retrocede o movimento caso o jogador se coloque em posicao de xeque
	//Metodo contrario ao makeMove
	private void undoMove(Position source, Position target, ChessPiece capturedPiece){
		//Remove a peca da posicao de destino
		ChessPiece p = (ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();
		//Readiciona ela na posicao de origem
		board.placePiece(p, source);
		//Caso uma peca tenha sido capturada, remove ela da lista de capturadas e aloca-a na lista de pecas no tabuleiro
		if(capturedPiece!=null){
			board.placePiece(capturedPiece, target);
			piecesCaptured.remove(capturedPiece);
			piecesOnBoard.add(capturedPiece);
		}
	}

	// Metodo que valida a posicao da peca que deseja mover, caso de erro, gera
	// execao
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if(currentPlayer!=((ChessPiece)board.piece(position)).getColor()){
			throw new ChessException("The chosen piece not yours");

		}

		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position");
		}
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnBoard.add(piece);
	}
	//Retorna a cor do oponente
	private Color opponnet(Color color){
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private ChessPiece king(Color color){
		List<ChessPiece> lst = piecesOnBoard.stream().filter(x-> x.getColor() == color).collect(Collectors.toList());
		for(ChessPiece p : lst){
			if( p instanceof King){
				return p;
			}
		}
		throw new IllegalStateException("There is no " + color + "king on the board");
	}

	private boolean testCheck(Color color){
		//Pegando a posicao da peca do rei a ser testada para verificar se esta em xeque 
		Position kingPosition = king(color).getChessPosition().toPosition();
		//Pegando as pecas do oponente
		List<Piece> opponnetPieces = piecesOnBoard.stream().filter(x-> x.getColor() == opponnet(color)).collect(Collectors.toList());
		//Lendo as pecas do oponnete para verificar se alguma peca tem um possivel movimento para deixar o rei em xeque, caso sim o xeque acontece
		for(Piece p : opponnetPieces){
			boolean [][] promt = p.possibleMoves();
			if(promt [kingPosition.getRow()][kingPosition.getColumn()]){
				return true;
			}
		}
		return false;
	}
	
	//Metodo que verfica se esta em xeque mate
	private boolean testCheckMate(Color color){
		if(!testCheck(color)){
			return false;
		}
		
		List<ChessPiece> lst = piecesOnBoard.stream().filter(x-> x.getColor() == color).collect(Collectors.toList());
		for(ChessPiece p : lst){
			boolean [][] promt =p.possibleMoves();
			for(int i =0; i<board.getRows(); i++){
				for(int j =0; j<board.getColumns(); j++){
					if(promt[i][j]){
						//Pega a posicao de origem da peca p da lista lst
						Position source = p.getChessPosition().toPosition();
						//Pega a posicao a possivel posicao de destino da peca p da lista lst
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target,(ChessPiece) capturedPiece);
						if(!testCheck){
							return false;
						}

					}

				}
			}

		}
		return true;

	}


	private void initialSetup() {
		//Instanciando as pecas brancas
		placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('h', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(board, Color.WHITE));
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
		
		//Instanciando as pecas pretas
		placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new King(board, Color.BLACK));
	}
}
