package com.kevinrobayna;

import com.kevinrobayna.snap.Deck;
import com.kevinrobayna.snap.Game;
import com.kevinrobayna.snap.Player;
import com.kevinrobayna.snap.matching.MatchAll;
import com.kevinrobayna.snap.matching.MatchCard;
import com.kevinrobayna.snap.matching.MatchSuit;
import com.kevinrobayna.snap.matching.Matcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Player> players = new ArrayList<>();
        System.out.print("Enter number of playing card decks: ");
        Deck deck = new Deck(scanner.nextInt());

        System.out.print("Enter matching criteria (1 for suit, 2 for value, 3 for both): ");
        Matcher matcher = switch (scanner.nextInt()) {
            case 1 -> new MatchSuit();
            case 2 -> new MatchCard();
            case 3 -> new MatchAll();
            default -> {
                System.out.println("You introduced an invalid value for the matching criteria");
                throw new IllegalArgumentException();
            }
        };

        System.out.print("Enter number of players: ");
        int numPlayers = scanner.nextInt();
        if (numPlayers < 2) {
            System.out.println("Defaulting to 2 players as you need at least a friend to play!");
            numPlayers = 2;
        }
        for (int inx = 0; inx < numPlayers; inx++) {
            players.add(new Player(inx));
        }
        new Game(deck, players, matcher).play();
    }
}
