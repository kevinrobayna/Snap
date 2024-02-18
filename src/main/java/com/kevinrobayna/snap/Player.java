package com.kevinrobayna.snap;

import java.util.Stack;

public class Player {
    private Stack<Card> stack;
    private int id;

    public Player(int inx) {
        this.stack = new Stack<>();
        this.id = inx;
    }

    public void addToStack(Card card) {
        stack.push(card);
    }

    public Stack<Card> getStack() {
        return stack;
    }

    public String getId() {
        return String.valueOf(id + 1);
    }
}
