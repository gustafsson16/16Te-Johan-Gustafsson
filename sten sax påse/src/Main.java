package Main;

import java.util.Random;
import java.util.Scanner;
 
public class Main {
    private User user;
    private Computer computer;
    private int userScore;
    private int computerScore;
    private int numberOfGames;
 
    private enum Move {
        Sten, Sax, Påse;
 
        public int compareMoves(Move otherMove) {
            if (this == otherMove)
                return 0;
 
            switch (this) {
            case Sten:
                return (otherMove == Påse ? 1 : -1);
            case Påse:
                return (otherMove == Sten ? 1 : -1);
            case Sax:
                return (otherMove == Sax ? 1 : -1);
            }
 
            return 0;
        }
	 
    }
}

private class User {
    private Scanner inputScanner;

    public User() {
        inputScanner = new Scanner(System.in);
    }

    public Move getMove() {
        System.out.print("Sten, Sax, eller Påse? ");

        String userInput = inputScanner.nextLine();
        userInput = userInput.toUpperCase();
        char firstLetter = userInput.charAt(0);
        if (firstLetter == 'R' || firstLetter == 'P' || firstLetter == 'S') {
            switch (firstLetter) {
            case 'R':
                return Move.Sten;
            case 'P':
                return Move.Påse;
            case 'S':
                return Move.Sax;
            }
        }

        return getMove();
    }

    public boolean playAgain() {
        System.out.print("Vill du spela igen? ");
        String userInput = inputScanner.nextLine();
        userInput = userInput.toUpperCase();
        return userInput.charAt(0) == 'Y';
    }
}

private class Computer {
    public Move getMove() {
        Move[] moves = Move.values();
        Random random = new Random();
        int index = random.nextInt(moves.length);
        return moves[index];
    }
}

public Main() {
    user = new User();
    computer = new Computer();
    userScore = 0;
    computerScore = 0;
    numberOfGames = 0;
}

public void startGame() {
    System.out.println("Sten, Sax, Påse!");

    Move userMove = user.getMove();
    Move computerMove = computer.getMove();
    System.out.println("\nDu spelade " + userMove + ".");
    System.out.println("Datorn spelade " + computerMove + ".\n");

    int compareMoves = userMove.compareMoves(computerMove);
    switch (compareMoves) {
    case 0:
    	
    	System.out.println("Lika!");
        break;
    case 1:
        System.out.println(userMove + " slår " + computerMove + ". Du vann!");
        userScore++;
        break;
    case -1: 
        System.out.println(computerMove + " slår " + userMove + ". Du förlorade.");
        computerScore++;
        break;
    }
    numberOfGames++;

    if (user.playAgain()) {
        System.out.println();
        startGame();
    } else {
        printGameStats();
    }
}

private void printGameStats() {
    int wins = userScore;
    int losses = computerScore;
    int ties = numberOfGames - userScore - computerScore;
double percentageWon = (wins + ((double) ties) / 2) / numberOfGames;

    System.out.print("+");
    printDashes(68);
    System.out.println("+");

    System.out.printf("|  %6s  |  %6s  |  %6s  |  %12s  |  %14s  |\n",
            "Vinst", "Förlust", "Lika", "Spel Spelade", "Procent vinst");

    System.out.print("|");
    printDashes(10);
    System.out.print("+");
    printDashes(10);
    System.out.print("+");
    printDashes(10);
    System.out.print("+");
    printDashes(16);
    System.out.print("+");
    printDashes(18);
    System.out.println("|");

    System.out.printf("|  %6d  |  %6d  |  %6d  |  %12d  |  %13.2f%%  |\n",
            wins, losses, ties, numberOfGames, percentageWon * 100);

    System.out.print("+");
    printDashes(68);
    System.out.println("+");
}

private void printDashes(int numberOfDashes) {
    for (int i = 0; i < numberOfDashes; i++) {
        System.out.print("-");
    }
}

public static void main(String[] args) {
    Main game = new Main();
    game.startGame();
}
}  	