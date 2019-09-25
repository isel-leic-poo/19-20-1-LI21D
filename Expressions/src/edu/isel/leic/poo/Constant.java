package edu.isel.leic.poo;

/**
 * Class whose instances represent constants
 */
public class Constant extends Expression {
    public final int number;

    public Constant(int number) {
        this.number = number;
    }

    @Override
    public int evaluate() {
        return number;
    }
}
