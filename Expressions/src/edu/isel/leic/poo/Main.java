package edu.isel.leic.poo;

import java.util.Scanner;

import static edu.isel.leic.poo.Parser.parse;

public class Main {

    private static String prettyPrint(Expression expression) {

        if (expression instanceof Constant) {
            Constant constant = (Constant) expression;
            return Integer.toString(constant.number);
        }

        Operation operation = (Operation) expression;
        return prettyPrint(operation.left) + ' '
                + operation.operation + ' '
                + prettyPrint(operation.right);
    }

    public static void main(String[] args) {

        final Scanner in = new Scanner(System.in);
        final Expression expression = parse(in.nextLine());
        System.out.println(
                prettyPrint(expression) + " = " + expression.evaluate()
        );
    }
}
