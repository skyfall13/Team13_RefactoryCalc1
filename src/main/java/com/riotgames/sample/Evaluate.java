package com.riotgames.sample;

import java.util.Stack;

/**
 * Created by noble on 2017-05-14.
 */
public class Evaluate {

    private Token token = new Token();
    private Stack<Double> vStack;

    public double calc(String[] tokens) {

        token.token2Postfix(tokens);

        return this.evalPostfix();
    }

    public double evalPostfix() {

        int p = 0;
        String curToken;
        Double popToken1, popToken2, result;
        this.vStack = new Stack<Double>();

        while (p < this.token.getPostfix().length && this.token.getPostfix()[p] != null) {
            curToken = this.token.getPostfix()[p++];
            if (this.token.isDigit(curToken)) {
                this.vStack.push(Double.parseDouble(String.valueOf(curToken)));
            } else {
                popToken1 = this.vStack.pop();
                popToken2 = this.vStack.pop();
                Operator operator = Operator.findOperator(curToken);
                this.vStack.push(operator.evaluate(popToken2, popToken1));
            }
        }
        result = this.vStack.pop();
        return result;
    }
}

