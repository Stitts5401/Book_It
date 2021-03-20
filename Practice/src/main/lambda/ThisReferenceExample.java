package main.lambda;
/**
 * IN AN ANONYMOUS INNER CLASS WHEN USING THIS REFERENCE YOU OVERWRITE THE Anonymous inner class reference
 * However when in a lambda it still refers to the instance that it points to outside of the lambda **/
public class ThisReferenceExample {
    public void doProcess(int i, Process process){
        process.process(i);
    }
    public void execute(){

        doProcess(10, i -> {
            System.out.println("Value"+ i);
            System.out.println(this);
        });
    }
    public static void main(String[] args) {
        ThisReferenceExample thisReferenceExample = new ThisReferenceExample();
       /* thisReferenceExample.doProcess(10, i -> {
            System.out.println("value "+i);
          //  System.out.println(this); will not work points to instance as if it was outside lambda
        });*/
        thisReferenceExample.execute();
    }

    public String toString(){
        return "THIS IS THE MAIN THIS REFERENCE EXAMPLE CLASS INSTANCE";
    }
}
