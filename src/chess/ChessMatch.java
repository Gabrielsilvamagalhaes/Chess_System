package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	private int turn;
	private boolean check;
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

		nextTurn();
		return (ChessPiece) capturedPiece;
	}

	// Metodo que move a peca para o local designado e remove a peca do local de
	// origem
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
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
		Piece p = board.removePiece(target);
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


	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
