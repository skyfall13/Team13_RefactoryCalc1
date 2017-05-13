package com.riotgames.sample;

import java.util.Stack;

/**
 * Created by noble on 2017-05-14.
 */
public class Token {
    private String[] postfix;
    private Stack<String> oStack;

    private class MyString { String poppedToken = "";}
    public String[] getPostfix() {
        return this.postfix;
    }

    public boolean isDigit(String token) {
        if (token.charAt(0) >= '0' && token.charAt(0) <= '9')
            return true;
        else
            return false;
    }

    public boolean token2Postfix(String[] args) {
        int i = 0;
        int p = 0;
        String[] tokens = args;

        String curToken, topToken;
        MyString str = new MyString();

        this.oStack = new Stack<String>();
        this.postfix = new String[tokens.length];

        while (i < tokens.length) {
            curToken = tokens[i++];
            if (this.isDigit(curToken))
                this.postfix[p++] = curToken;
            else {
                if (curToken.charAt(0) == ')') {
                    if (!this.q1(this.oStack, str))
                        return false;
                    while (str.poppedToken.charAt(0) != '(') {
                        this.postfix[p++] = str.poppedToken;
                        if (!this.q1(this.oStack, str))
                            return false;
                    }
                } else {
                    int inComingP = inComingPrecedence(curToken);
                    if (!this.oStack.isEmpty()) {
                        topToken = (String) this.oStack.peek();
                        while (inStackPrecedence(topToken) >= inComingP) {
                            str.poppedToken = (String) this.oStack.pop();
                            this.postfix[p++] = str.poppedToken;
                            if (!this.oStack.isEmpty())
                                topToken = (String) this.oStack.peek();
                            else
                                break;
                        }
                    }
                    this.oStack.push(curToken);
                }
            }
        }
        while (!this.oStack.isEmpty()) {
            str.poppedToken = (String) this.oStack.pop();
            this.postfix[p++] = str.poppedToken;
        }
        return true;
    }

    private int inComingPrecedence(String givenToken) {
        char tempChar = givenToken.charAt(0);

        switch (tempChar) {
            case '+':
            case '-':
                return 12;
            case '*':
            case '/':
                return 13;
            case '(':
                return 20;
            case ')':
                return 19;
            default:
                return -1;
        }

    }

    private int inStackPrecedence(String givenToken) {

        char tempChar = givenToken.charAt(0);

        switch (tempChar) {
            case '+':
            case '-':
                return 12;
            case '*':
            case '/':
                return 13;
            case '(':
                return 0;
            case ')':
                return 19;
            default:
                return -1;
        }

    }
    private boolean q1(Stack stack, MyString str) {
        if (!stack.isEmpty()) {
            str.poppedToken = (String) stack.pop();
            return true;
        } else {
            return false;
        }
    }
}
