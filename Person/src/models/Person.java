package models;

public class Person {
    private String nome;
    private int idade;
    private String id;
    private float peso;
    private float altura;

    public Person() {}

    public Person(String nome, int idade, String id) {
        this.nome = nome;
        this.idade = idade;
        this.id = id;
    }

    public Person(String nome, int idade, String id, float peso, float altura) {
        this.nome = nome;
        this.idade = idade;
        this.id = id;
        this.peso = peso; // em kgs
        this.altura = altura; //deve estar em metros
    }

    public void calcularIMC() {
        float imc = this.peso / (this.altura * this.altura);
        if (imc < 20){
            System.out.println("-1");
        }
        else if (imc >= 20 && imc <= 25) {
            System.out.printf("0");
        } else {
            System.out.printf("1");
        }
        System.out.println();
    }

    public boolean isOldOfAge(){ return this.idade >= 18; }
}
