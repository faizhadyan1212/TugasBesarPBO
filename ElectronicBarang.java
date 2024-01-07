import java.util.Date;
public class ElectronicBarang extends Barang {
    private String brand;
    private double powerConsumption;

    public ElectronicBarang(int id, String name, double price, Date date, String brand, double powerConsumption) {
        super(id, name, price, date);
        this.brand = brand;
        this.powerConsumption = powerConsumption;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Brand: " + getBrand());
        System.out.println("Konsumsi daya: " + getPowerConsumption());
    }
}