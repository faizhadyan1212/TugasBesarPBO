import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

interface Displayable {
    void display();
}

class BarangNotFoundException extends Exception {
    public BarangNotFoundException(String message) {
        super(message);
    }
}

public class BarangManagement {
    private static Map<Integer, Barang> dataBarang = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline not consumed by nextInt()

            try {
                switch (choice) {
                    case 1:
                        createBarang(scanner);
                        break;
                    case 2:
                        deleteBarang(scanner);
                        break;
                    case 3:
                        readBarang(scanner);
                        break;
                    case 4:
                        updateBarang(scanner);
                        break;
                    case 5:
                        System.out.println("Keluar");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                        break;
                }
            } catch (BarangNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void displayMenu() {
        System.out.println("1. Tambah barang");
        System.out.println("2. Hapus barang");
        System.out.println("3. Baca barang");
        System.out.println("4. Update barang");
        System.out.println("5. Keluar");
        System.out.print("Pilih menu: ");
    }

    private static void createBarang(Scanner scanner) {
        System.out.println("Pilih jenis barang:");
        System.out.println("1. Barang biasa");
        System.out.println("2. Barang elektronik");
        System.out.print("Masukkan pilihan: ");
        
        int choice;
        
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Masukkan pilihan yang valid.");
            return;
        }
    
        switch (choice) {
            case 1:
                createRegularBarang(scanner);
                break;
            case 2:
                createElectronicBarang(scanner);
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }
    }
    
    
    private static void createRegularBarang(Scanner scanner) {
        System.out.print("Masukkan nama barang: ");
        String name = scanner.nextLine();

        System.out.print("Masukkan harga barang: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        int id = dataBarang.size() + 1;
        Barang barang = new Barang(id, name, price, new Date());
        dataBarang.put(id, barang);
        System.out.println("Barang biasa berhasil ditambahkan.");
    }

    private static void createElectronicBarang(Scanner scanner) {
        System.out.print("Masukkan nama barang elektronik: ");
        String name = scanner.nextLine();
    
        System.out.print("Masukkan harga barang elektronik: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Membersihkan newline
    
        System.out.print("Masukkan brand barang elektronik: ");
        String brand = scanner.nextLine();
    
        System.out.print("Masukkan konsumsi daya barang elektronik: ");
        double powerConsumption = scanner.nextDouble();
        scanner.nextLine(); // Membersihkan newline
    
        int id = dataBarang.size() + 1;
        ElectronicBarang electronicBarang = new ElectronicBarang(id, name, price, new Date(), brand, powerConsumption);
        dataBarang.put(id, electronicBarang);
        System.out.println("Barang elektronik berhasil ditambahkan.");
    }

    private static void deleteBarang(Scanner scanner) throws BarangNotFoundException {
        System.out.print("Masukkan ID barang yang ingin dihapus: ");
        int id = scanner.nextInt();

        if (dataBarang.containsKey(id)) {
            dataBarang.remove(id);
            System.out.println("Barang berhasil dihapus.");
        } else {
            throw new BarangNotFoundException("ID barang tidak ditemukan.");
        }
    }

    private static void readBarang(Scanner scanner) throws BarangNotFoundException {
        System.out.print("Masukkan ID barang yang ingin dibaca: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Membersihkan newline
    
        if (dataBarang.containsKey(id)) {
            Barang barang = dataBarang.get(id);
            barang.display();
        } else {
            throw new BarangNotFoundException("ID barang tidak ditemukan.");
        }
    }

    private static void updateBarang(Scanner scanner) throws BarangNotFoundException {
        System.out.print("Masukkan ID barang yang ingin diperbarui: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Membersihkan newline
    
        if (dataBarang.containsKey(id)) {
            Barang barang = dataBarang.get(id);
    
            if (barang instanceof ElectronicBarang) {
                updateElectronicBarang(scanner, (ElectronicBarang) barang);
            } else {
                updateRegularBarang(scanner, barang);
            }
    
            System.out.println("Barang berhasil diperbarui.");
        } else {
            throw new BarangNotFoundException("ID barang tidak ditemukan.");
        }
    }

    private static void updateRegularBarang(Scanner scanner, Barang barang) {
        System.out.print("Masukkan nama barang baru: ");
        String name = scanner.nextLine();
        barang.setName(name);

        System.out.print("Masukkan harga barang baru: ");
        double price = scanner.nextDouble();
        barang.setPrice(price);
    }

    private static void updateElectronicBarang(Scanner scanner, ElectronicBarang electronicBarang) {
        updateRegularBarang(scanner, electronicBarang); // Panggil metode update kelas dasar
    
        System.out.print("Masukkan brand barang elektronik baru: ");
        String brand = scanner.nextLine();
        electronicBarang.setBrand(brand);
    
        // Membersihkan newline yang tidak terbaca oleh nextDouble()
        scanner.nextLine();
    
        while (true) {
            try {
                System.out.print("Masukkan konsumsi daya barang elektronik baru: ");
                double powerConsumption = Double.parseDouble(scanner.nextLine());
                electronicBarang.setPowerConsumption(powerConsumption);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Masukkan konsumsi daya yang valid.");
            }
        }
    }
}
