package edu.isel.leic.poo;

import java.util.ArrayList;

public class Parser {

    private static String consumeSpaces(String expression) {
        int idx = 0;
        while (idx < expression.length() && expression.charAt(idx) == ' ')
            idx += 1;

        return expression.substring(idx);
    }

    private static String nextToken(String expression) {
        for(int i = 0; i < expression.length(); ++i) {
            if(expression.charAt(i) == ' ') {
                return expression.substring(0, i);
            }
        }
        return expression;
    }

    private static ArrayList<String> tokenize(String expression) {
        final ArrayList<String> tokens = new ArrayList<>();
        String remainingExpression = consumeSpaces(expression);
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

    private static boolean isNumber(String token) {
        for (int idx = 0; idx < token.length(); idx++) {
            if (!Character.isDigit(token.charAt(idx)))
                return false;
        }
        return true;
    }

    private static Expression parse(ArrayList<String> tokens) {
        final String token = tokens.remove(0);

        if (isNumber(token))
            return new Constant(Integer.parseInt(token));

        return new Operation(token.charAt(0), parse(tokens), parse(tokens));
    }

    public static Expression parse(String expression) {
        final ArrayList<String> tokens = tokenize(expression);
        return parse(tokens);
    }
}
