package main.lambda;

public class MethodReferencesExample1 {

    public static void main(String[] args) {
//THIS LAMBDA IS A METHOD EXECUTION
     //   Thread t = new Thread(() -> printMessage() );
        /**
         *
         * METHOD REFERENCE's REPLACE SIMPLE LAMBDAS
         *
         * **/
        var t = new Thread(MethodReferencesExample1::printMessage);

        t.start();
    }

    public static void printMessage(){
        System.out.println("Hello");
    }


}
