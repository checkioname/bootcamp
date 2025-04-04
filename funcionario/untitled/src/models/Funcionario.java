package models;

public abstract class Funcionario {
    String nome;
    int numeroRegistro;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(int numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public abstract float calcularSalario();


    public String toString() {
        return String.format("%5d - %-20s - %-15s R$ %.2f %", numeroRegistro, nome, this.getClass().getName(), calcularSalario());
    }
}
