package models;

public class NonPerishable extends Product{
    private String type;

    public NonPerishable(String name, double price) {
        super(name, price);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NonPerishable{" +
                "type='" + type + '\'' +
                '}';
    }
}
