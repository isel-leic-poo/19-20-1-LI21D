package edu.isel.leic.poo;

import java.util.Scanner;

import static edu.isel.leic.poo.Evaluator.evaluate;
import static edu.isel.leic.poo.Parser.parse;

public class Main {

    private static String prettyPrint(ASTNode expression) {

        if (expression.isNumber())
            return Integer.toString(expression.number);

        return prettyPrint(expression.left) + ' '
                + expression.operation + ' '
                + prettyPrint(expression.right);
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final ASTNode expression = parse(in.nextLine());
        System.out.println(
                prettyPrint(expression) + " = " + evaluate(expression)
        );
    }
}
