package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import entities.chessLayer.ChessMatch;
import entities.chessLayer.ChessPiece;
import entities.chessLayer.ChessPosition;
import exceptions.ChessException;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChessMatch match = new ChessMatch();

        while (true) {
            try {
                UI.clearScreen();

                // Inicializando do tabuleiro
                UI.printBoard(match.getPieces());

                System.out.println();
                System.out.print("Source: ");
                // Leitura da posicao de origem da peca
                ChessPosition source = UI.readChessPosition(sc);
                System.out.println();

                System.out.print("Target: ");
                // Leitura da posicao que deseja mover a peca
                ChessPosition target = UI.readChessPosition(sc);
                ChessPiece capturedPiece = match.performChessMove(source, target);
            } 
            catch (ChessException e) {
                System.out.println(e.getMessage());
                sc.next().charAt(0);
                
            } 
            catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.next();
            }
        }
    }

}
