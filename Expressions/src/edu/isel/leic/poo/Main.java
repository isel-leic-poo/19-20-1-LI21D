package edu.isel.leic.poo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static String consumeSpaces(String expression) {
        int idx = 0;
        while (idx < expression.length() && expression.charAt(idx) == ' ')
            idx += 1;

        return expression.substring(idx);
    }

    public static String nextToken(String expression) {
        for(int i = 0; i < expression.length(); ++i) {
            if(expression.charAt(i) == ' ') {
                return expression.substring(0, i);
            }
        }
        return expression;
    }

    public static ArrayList<String> tokenize(String expression) {
        final ArrayList<String> tokens = new ArrayList<>();
        String remainingExpression = expression;
        int tokenIdx = 0;
        while (!remainingExpression.isEmpty()) {
            int originalSize = remainingExpression.length();
            remainingExpression = consumeSpaces(remainingExpression);
            int removedSpaces = originalSize - remainingExpression.length();
            final String token = nextToken(remainingExpression);
            tokens.add(token);
            remainingExpression = remainingExpression.substring(token.length() + removedSpaces);
        }
        return tokens;
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final String expression = in.nextLine();

        final ArrayList<String> tokens = tokenize(expression);


        for (int i = 0; i < tokens.size(); i++) {
            System.out.println(tokens.get(i));
        }
    }
}
