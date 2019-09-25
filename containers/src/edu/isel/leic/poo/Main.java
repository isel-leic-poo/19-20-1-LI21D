package edu.isel.leic.poo;

public class Main {

    public static void main(String[] args) {

        DynamicArray array = new DynamicArray();

        array.add("SLB");
        array.add("GLORIOSO");
        array.add("37");

        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i));
        }

        int[] arr = new int[] { 1, 2 , 3};
        for (int elem : arr) {
            System.out.println(elem);
        }

    }
}
