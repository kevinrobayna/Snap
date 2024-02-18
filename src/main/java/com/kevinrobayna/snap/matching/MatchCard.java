package com.kevinrobayna.snap.matching;

import com.kevinrobayna.snap.Card;

public class MatchCard implements Matcher {
    @Override
    public boolean match(Card a, Card b) {
        return a.value().equals(b.value());
    }
}
