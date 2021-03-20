package main.lambda;
import main.lambda.Greeting;
import main.lambda.HelloWorldGreeting;

public class Greeter {

    public void greet(Greeting greeting){
        //System.out.println("Hello World");
        greeting.perform();
    }


    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        //class implements greeting interface provided logic in class

        //instance of specific implementation that prints helloworld
        //
        HelloWorldGreeting helloWorldGreeting = new HelloWorldGreeting();
        //^Instance of a class, that implements greeting interface
        Greeting lambdaFunction = () -> System.out.println("Helloworld");
        //^lambdaExpression of type greeting interface
        //Lambda expression implementing function of interface

        helloWorldGreeting.perform();

        lambdaFunction.perform();

        Greeting innerClassGreeting = new Greeting() {
            public void perform() {
                System.out.println("PRINT");
            }
        };


        innerClassGreeting.perform();
        greeter.greet(lambdaFunction);
        greeter.greet(innerClassGreeting);
  }
}


interface MyLambda{
    void foo();

}
