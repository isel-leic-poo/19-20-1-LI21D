package edu.isel.leic.poo;

public class Evaluator {

    public static int evaluate(ASTNode expression) {
        if (expression.isNumber())
            return expression.number;

        int res = 0;
        switch (expression.operation) {
            case '+': res = evaluate(expression.left) + evaluate(expression.right); break;
            case '-': res = evaluate(expression.left) - evaluate(expression.right); break;
            case '*': res = evaluate(expression.left) * evaluate(expression.right); break;
            case '/': res = evaluate(expression.left) / evaluate(expression.right); break;
        }
        return res;
    }
}
