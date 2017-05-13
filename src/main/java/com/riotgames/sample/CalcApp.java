package com.riotgames.sample;

import java.util.Arrays;
import java.util.Stack;

/**
 * Calculator application
 */
public class CalcApp {

	private String[] postfix;
	private Stack<String> operatorStack;
	private Stack<Double> valueStack;

	public static void main(String[] args) {
		final CalcApp app = new CalcApp();
		final StringBuilder outputs = new StringBuilder();
		Arrays.asList(args).forEach(value -> outputs.append(value + " "));
		System.out.print("Addition of values: " + outputs + " = ");
		System.out.println(app.calc(args));
	}

	public double calc(String[] tokens) {

		this.token2Postfix(tokens);

		return this.evalPostfix();
	}

	public double evalPostfix() {
		int p = 0;
		String curToken;
		Double popToken1, popToken2, result;
		this.valueStack = new Stack<Double>();

		while (p < this.postfix.length && this.postfix[p] != null) {
			curToken = this.postfix[p++];
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

	public boolean token2Postfix(String[] args) {
		int i = 0;
		int p = 0;
		String[] tokens = args;

		String curToken, poppedToken, topToken;
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
							poppedToken = (String) this.operatorStack.pop();
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
