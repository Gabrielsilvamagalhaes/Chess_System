package entities.chessLayer;

import entities.boardLayer.Board;
import entities.boardLayer.Piece;
import entities.boardLayer.Position;
import entities.chessLayer.PiecesOfChess.Bishop;
import entities.chessLayer.PiecesOfChess.King;
import entities.chessLayer.PiecesOfChess.Knight;
import entities.chessLayer.PiecesOfChess.Pawn;
import entities.chessLayer.PiecesOfChess.Queen;
import entities.chessLayer.PiecesOfChess.Rook;
import entities.chessLayer.enums.Color;
import exceptions.ChessException;

public class ChessMatch {
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMatch;
    private ChessPiece promoted;
    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
        initialSetup();

    }

    // Metodo para colocar as pecas no tabuleiro
    public ChessPiece[][] getPieces() {
        ChessPiece[][] chessPieces = new ChessPiece[board.getRows()][board.getColumns()];

        for (int i = 0; i < chessPieces.length; i++) {
            for (int j = 0; j < chessPieces.length; j++) {
                chessPieces[i][j] = (ChessPiece) board.piece(i, j);

            }

        }
        return chessPieces;

    }

    private void placeNewPiece(int row, char column, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(row, column).toPosition());
        
    }
    
    private void initialSetup() {
        //Instanciando as pecas brancas
        placeNewPiece(1, 'e', new King(board, Color.WHITE));
        placeNewPiece(1, 'd', new Queen(board, Color.WHITE));
        placeNewPiece(1, 'a', new Rook(board, Color.WHITE));
        placeNewPiece(1, 'h', new Rook(board, Color.WHITE));
        placeNewPiece(1, 'b', new Knight(board, Color.WHITE));
        placeNewPiece(1, 'g', new Knight(board, Color.WHITE));
        placeNewPiece(1, 'c', new Bishop(board, Color.WHITE));
        placeNewPiece(1, 'f', new Bishop(board, Color.WHITE));
        placeNewPiece(2, 'a', new Pawn(board, Color.WHITE));
        placeNewPiece(2, 'b', new Pawn(board, Color.WHITE));
        placeNewPiece(2, 'c', new Pawn(board, Color.WHITE));
        placeNewPiece(2, 'd', new Pawn(board, Color.WHITE));
        placeNewPiece(2, 'e', new Pawn(board, Color.WHITE));
        placeNewPiece(2, 'f', new Pawn(board, Color.WHITE));
        placeNewPiece(2, 'g', new Pawn(board, Color.WHITE));
        placeNewPiece(2, 'h', new Pawn(board, Color.WHITE));


        //Instanciando as pecas pretas
        placeNewPiece(8, 'e', new King(board, Color.BLACK));
        placeNewPiece(8, 'd', new Queen(board, Color.BLACK));
        placeNewPiece(8, 'a', new Rook(board, Color.BLACK));
        placeNewPiece(8, 'h', new Rook(board, Color.BLACK));
        placeNewPiece(8, 'b', new Knight(board, Color.BLACK));
        placeNewPiece(8, 'g', new Knight(board, Color.BLACK));
        placeNewPiece(8, 'c', new Bishop(board, Color.BLACK));
        placeNewPiece(8, 'f', new Bishop(board, Color.BLACK));
        placeNewPiece(7, 'a', new Pawn(board, Color.BLACK));
        placeNewPiece(7, 'b', new Pawn(board, Color.BLACK));
        placeNewPiece(7, 'c', new Pawn(board, Color.BLACK));
        placeNewPiece(7, 'd', new Pawn(board, Color.BLACK));
        placeNewPiece(7, 'e', new Pawn(board, Color.BLACK));
        placeNewPiece(7, 'f', new Pawn(board, Color.BLACK));
        placeNewPiece(7, 'g', new Pawn(board, Color.BLACK));
        placeNewPiece(7, 'h', new Pawn(board, Color.BLACK));
        
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        Piece capturedPiece = makeMouve(source, target);
        return (ChessPiece) capturedPiece;

        
    }
    //Metodo que valida a posicao da peca que deseja mover, caso de erro, gera execao
    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new ChessException( "There is no piece on position");

        }

    }
    //Metodo que move a peca para o local designado e remove a peca do local de origem
    private Piece makeMouve(Position source, Position target){
        Piece p = board.removePiece(source);
        Piece capturedPiece =board.removePiece(target);
        board.placePiece(p, target);
        return capturedPiece;
    }

}
