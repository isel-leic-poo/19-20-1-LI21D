package edu.isel.leic.poo;

public class ASTNode {
    public final int number;
    public final char operation;
    public final ASTNode left;
    public final ASTNode right;

    private ASTNode(int number, char operation, ASTNode left, ASTNode right) {
        this.number = number;
        this.operation = operation;
        this.left = left;
        this.right = right;
    }

    public ASTNode(int number) {
        this(number, ' ', null, null);
    }

    public ASTNode(char operation, ASTNode left, ASTNode right) {
        this(0, operation, left, right);
    }

    public boolean isNumber() {
        return operation == ' ';
    }
}
