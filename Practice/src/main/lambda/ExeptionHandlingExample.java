package main.lambda;

import java.util.function.BiConsumer;

public class ExeptionHandlingExample {
    public static void main(String[] args) {
        int[] someNumbers = {1, 2, 3, 4};
        int key = 2;

        //   process(someNumbers, key, (v,k) -> System.out.println(v / k)  );

     /*   process(someNumbers,key,(v,k) -> {
            try{
                System.out.println(v/k);
            } catch (ArithmeticException e ){
                System.out.println("An Arithmetic Exception happened");
            }
        }); */

        process(someNumbers, key, wrapperLambda((v, k) -> System.out.println(v / k)));
    }


    private static void process(int[] someNumbers, int key, BiConsumer<Integer, Integer> consumer){

        for(int i: someNumbers){
            consumer.accept(i, key);
        //   try {
        //  }catch (ArithmeticException e ){
        //      e.printStackTrace();
        //    }
        }
    }
    private static BiConsumer<Integer, Integer> wrapperLambda(BiConsumer<Integer,Integer> consumer){
        return (v,k) -> {
            try {
                consumer.accept(v, k);
            }catch (ArithmeticException e){
                e.printStackTrace();
                System.out.println("Arithmatic eXeption caught in wrapper lambda");
            }
        };
    }
}
