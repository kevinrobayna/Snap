package com.kevinrobayna.snap;

import com.kevinrobayna.snap.matching.Matcher;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private final Deck deck;
    private final List<Player> players;
    private final Matcher matcher;

    public Game(Deck deck, List<Player> players, Matcher matcher) {
        this.deck = deck;
        this.players = players;
        this.matcher = matcher;
    }

    public void play() {
        int currentPlayerIndex = 0;
        deck.shuffle();

        while (!deck.isEmpty()) {
            Card card = deck.dealCard();

            Player currentPlayer = players.get(currentPlayerIndex);
            currentPlayer.addToStack(card);
            System.out.println("Player " + currentPlayer.getId() + " plays: " + card);


            // We do this here to check all the playing players at once and randomly pick one
            var matchingPlayers = new java.util.ArrayList<>(players.
                    stream().
                    filter(player -> checkSnap(currentPlayer, player)).
                    toList());
            // We could probably do this ourselves as it would be more performant but this is simpler.
            Collections.shuffle(matchingPlayers);
            if (matchingPlayers.size() != 1) {
                var winner = matchingPlayers.remove(0);
                var losingIds = matchingPlayers.
                        stream().
                        map(Player::getId).
                        collect(Collectors.joining(", "));
                System.out.println("Player " + winner.getId() + " shouts SNAP and takes cards from Player(s) " + losingIds);
                matchingPlayers.forEach(loser -> {
                    while (!loser.getStack().isEmpty()) {
                        winner.addToStack(loser.getStack().pop());
                    }
                });
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }

    private boolean checkSnap(Player player1, Player player2) {
        if (player1.getStack().isEmpty()) {
            return false;
        }
        if (player2.getStack().isEmpty()) {
            return false;
        }
        Card topCard1 = player1.getStack().peek();
        Card topCard2 = player2.getStack().peek();
        return matcher.match(topCard1, topCard2);
    }
}
