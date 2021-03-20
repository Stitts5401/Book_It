package main.lambda;

public class TypeInferenceExample {
    public static void main(String[] args) {
        StringLengthLambda myLambda = String::length;
        System.out.println(   myLambda.getLength("HelloLambda"));

        printLambda(String::length);
    }

    public static void printLambda(StringLengthLambda l){
        System.out.println(l.getLength("Hello Lambda"));
    }

    interface StringLengthLambda {
        int getLength(String s);
    }
}
