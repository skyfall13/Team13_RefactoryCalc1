package com.riotgames.sample;

import java.util.Arrays;
import java.util.Stack;

/**
 * Calculator application
 */
public class CalcApp {
	public static void main(String[] args) {

		final Evaluate app = new Evaluate();
		final StringBuilder outputs = new StringBuilder();
		Arrays.asList(args).forEach(value -> outputs.append(value + " "));
		System.out.print("Addition of values: " + outputs + " = ");
		System.out.println(app.calc(args));

	}
}
