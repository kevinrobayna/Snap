package com.kevinrobayna.snap.matching;

import com.kevinrobayna.snap.Card;

public class MatchAll implements Matcher {
    @Override
    public boolean match(Card a, Card b) {
        return a.suit().equals(b.suit()) && a.value().equals(b.value());
    }
}
