import java.util.Date;
 
public class Barang implements Displayable {
    private int id;
    private String name;
    private double price;
    private Date date;

    public Barang(int id, String name, double price, Date date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public void display() {
        System.out.println("ID barang: " + getId());
        System.out.println("Nama barang: " + getName());
        System.out.println("Harga barang: " + getPrice());
        System.out.println("Tanggal pembelian: " + getDate());
    }
}

