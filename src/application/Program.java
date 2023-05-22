package application;

import java.util.Scanner;

import entities.chessLayer.ChessMatch;
import entities.chessLayer.ChessPiece;
import entities.chessLayer.ChessPosition;

public class Program {
    public static void main(String[] args) {
        ChessMatch match = new ChessMatch();
        Scanner sc;

        while (true) {
            //Inicializando do tabuleiro
            UI.printBoard(match.getPieces());

            System.out.println();
            System.out.print("Source:");
            //Leitura da posicao de origem da peca
            ChessPosition source = UI.readChessPosition(sc = new Scanner(System.in));
            System.out.println();
            
            System.out.print("Target: ");
            //Leitura da posicao que deseja mover a peca
            ChessPosition target = UI.readChessPosition(sc = new Scanner(System.in));
            ChessPiece capturedPiece = match.performChessMove(source, target);
        }
    }

}
