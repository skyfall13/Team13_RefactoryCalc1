package com.riotgames.sample;

import java.util.Arrays;

/**
 * Calculator application
 */
public class CalcApp {

	public static void main(String[] args) {

	    final EvalPostfix ep = new EvalPostfix();
		final StringBuilder outputs = new StringBuilder();

		Arrays.asList(args).forEach(value -> outputs.append(value + " "));
		System.out.print("Addition of values: " + outputs + " = ");
		System.out.println(ep.calc(args));

	}

}
