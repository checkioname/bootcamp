package models;

public class Perishable extends Product{
    int dayPerCaducar;

    public Perishable(String name, double price, int dayPerCaducar) {
        super(name, price);
        this.dayPerCaducar = dayPerCaducar;
    }

    public int getDayPerCaducar() {
        return dayPerCaducar;
    }

    public void setDayPerCaducar(int dayPerCaducar) {
        this.dayPerCaducar = dayPerCaducar;
    }

    @Override
    public String toString() {
        return "Perishable{" +
                "dayPerCaducar=" + dayPerCaducar +
                '}';
    }

    @Override
    public double calculate(int quantityOfProducts) {
        double result = this.getPrice() * quantityOfProducts;
        double resultDiscount;
        switch (this.dayPerCaducar) {
            case 1:
                resultDiscount = result / 4;
            case 2:
                resultDiscount = result / 3;
            case 3:
                resultDiscount = result / 2;
            default:
                resultDiscount = result;
        }
        return result;
    }
}
