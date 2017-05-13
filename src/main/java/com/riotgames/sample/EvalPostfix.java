package com.riotgames.sample;

import java.util.Stack;

/**
 * Created by gobug on 2017-05-14.
 */
public class EvalPostfix {

    TokenToPostfix ttp = new TokenToPostfix();
    private Stack<Double> valueStack;

    public double calc(String[] tokens) {
        ttp.tokenToPostfix(tokens);
        return this.evalPostfix();
    }

    public double evalPostfix() {
        int p = 0;
        String curToken;
        Double popToken1, popToken2, result;
        this.valueStack = new Stack<Double>();

        while (p < ttp.getPostfix().length && ttp.getPostfix()[p] != null) {
            curToken = ttp.getPostfix()[p++];
            if (new IsDigit().isDigit(curToken)) {
                this.valueStack.push(Double.parseDouble(String.valueOf(curToken)));
            } else {
                popToken1 = this.valueStack.pop();
                popToken2 = this.valueStack.pop();
                Operator operator = Operator.findOperator(curToken);
                this.valueStack.push(operator.evaluate(popToken2, popToken1));
            }
        }
        result = this.valueStack.pop();
        return result;
    }
}
