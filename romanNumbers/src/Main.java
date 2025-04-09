import service.NumberConversor;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        var n = new NumberConversor();


        var number = n.toDecimal("XM");
        System.out.println(number);
    }
}