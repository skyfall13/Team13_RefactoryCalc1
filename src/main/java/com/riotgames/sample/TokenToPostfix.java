package com.riotgames.sample;

import java.util.Stack;

/**
 * Created by gobug on 2017-05-14.
 */
public class TokenToPostfix {

    private Stack<String> operatorStack;
    private String[] postfix;

    public String[] getPostfix() {
        return this.postfix;
    }

    public boolean tokenToPostfix(String[] args) {
        int i = 0;
        int p = 0;

        String[] tokens = args;

        String curToken, poppedToken, topToken;

        CalcApp app = new CalcApp();

        this.operatorStack = new Stack<String>();
        this.postfix = new String[tokens.length];

        while (i < tokens.length) {
            curToken = tokens[i++];
            if (new IsDigit().isDigit(curToken))
                this.postfix[p++] = curToken;
            else {
                if (curToken.charAt(0) == ')') {
                    if (!this.operatorStack.isEmpty())
                        poppedToken = (String) this.operatorStack.pop();
                    else
                        return false;

                    while (poppedToken.charAt(0) != '(') {
                        this.postfix[p++] = poppedToken;
                        if (!this.operatorStack.isEmpty())
                            poppedToken = (String) operatorStack.pop();
                        else
                            return false;
                    }
                }
                else {
                    int inComingP = inComingPrecedence(curToken);
                    if (!this.operatorStack.isEmpty()) {
                        topToken = (String) this.operatorStack.peek();
                        while (inStackPrecedence(topToken) >= inComingP) {
                            poppedToken = (String) this.operatorStack.pop();
                            this.postfix[p++] = poppedToken;
                            if (!this.operatorStack.isEmpty())
                                topToken = (String) this.operatorStack.peek();
                            else
                                break;
                        }
                    }
                    this.operatorStack.push(curToken);
                }
            }
        }
        while (!this.operatorStack.isEmpty()) {
            poppedToken = (String) this.operatorStack.pop();
            this.postfix[p++] = poppedToken;
        }
        return true;
    }

    private int inComingPrecedence(String givenToken) {

        switch (givenToken.charAt(0)) {
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

        switch (givenToken.charAt(0)) {
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
}