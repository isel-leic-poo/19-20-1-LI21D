package edu.isel.leic.poo;

/**
 * Class whose instances represent operations
 */
public class Operation extends Expression {

    public final char operation;
    public final Expression left;
    public final Expression right;

    public Operation(char operation, Expression left, Expression right) {
        this.operation = operation;
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate() {
        int res = 0;
        switch (operation) {
            case '+': res = left.evaluate() + right.evaluate(); break;
            case '-': res = left.evaluate() - right.evaluate(); break;
            case '*': res = left.evaluate() * right.evaluate(); break;
            case '/': res = left.evaluate() / right.evaluate(); break;
        }
        return res;
    }
}
