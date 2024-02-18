package com.kevinrobayna.snap.matching;

import com.kevinrobayna.snap.Card;

public interface Matcher {
    boolean match(Card a, Card b);
}
