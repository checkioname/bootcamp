package models;

public class Empreiteiro extends Funcionario{
    private float valorEmpreita;

    public float getValorEmpreita() {
        return valorEmpreita;
    }

    public void setValorEmpreita(float valorEmpreita) {
        this.valorEmpreita = valorEmpreita;
    }

    @Override
    public float calcularSalario() {
        return 0;
    }
}
