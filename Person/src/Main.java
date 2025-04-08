import models.Person;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> imcMessage = Map.of(
                0, "Peso saudável",
                -1, "Abaixo do peso",
                1, "Sobrepeso"
        );

        var lucas = new Person("Lucas", 21, "12312", 80.1f, 1.81f);

        var imcResult = lucas.calcularIMC();
        System.out.printf("Calculado o IMC de lucas e o resultado é: %s\n",imcMessage.get(imcResult));
        System.out.printf("Lucas é maior de idade: %s", lucas.isOldOfAge());
    }
}