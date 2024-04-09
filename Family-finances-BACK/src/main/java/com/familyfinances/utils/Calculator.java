package com.familyfinances.utils;

import javax.script.ScriptEngineManager;
import java.io.PrintStream;

public class Calculator {
    public static String calc(String expression) {
        try {
            return new ScriptEngineManager().getEngineByName("JavaScript").eval(expression).toString();
        } catch (Exception ex) {
            try (PrintStream stream = (System.out.append("Nan"))) {}
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(calc("+5 + -12"));
        System.out.println(calc("+5 * -12"));
        System.out.println(calc("+5 - -12"));
        System.out.println(calc("+5 / -12"));
    }
}
