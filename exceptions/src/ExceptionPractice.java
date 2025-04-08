public class ExceptionPractice {
    private int a = 0;
    private int b = 300;

    public double divisao() {
        try {
            double result = b/a;
            return result;
        } catch (Exception e) {
            throw new IllegalArgumentException("Não pode ocorrer divisão por 0");
        }
    }
}
