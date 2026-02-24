package driver; 

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 1. Kelas untuk merepresentasikan setiap item pakaian
class LaundryItem {
    String namaPakaian;
    int jumlah;
    String keterangan;
    boolean terverifikasi;

    public LaundryItem(String namaPakaian, int jumlah, String keterangan) {
        this.namaPakaian = namaPakaian;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
        this.terverifikasi = false;
    }
}

// 2. Kelas untuk mengelola laundry bag
class LaundryBag {
    private String namaMahasiswa;
    private String namaAsrama;
    private List<LaundryItem> daftarPakaian;
    private String catatanMahasiswa;
    private String catatanLaundry;

    public LaundryBag(String namaMahasiswa, String namaAsrama) {
        this.namaMahasiswa = namaMahasiswa;
        this.namaAsrama = namaAsrama;
        this.daftarPakaian = new ArrayList<>();
    }

    public void tambahPakaian(String nama, int qty, String ket) {
        daftarPakaian.add(new LaundryItem(nama, qty, ket));
    }

    public void setCatatanMahasiswa(String catatan) {
        this.catatanMahasiswa = catatan;
    }

    public void setCatatanLaundry(String catatan) {
        this.catatanLaundry = catatan;
    }

    public List<LaundryItem> getDatarPakaian() {
        return daftarPakaian;
    }

    public void tampilkanStruk() {
        System.out.println("\n========= LAUNDRY DEL =========");
        System.out.println("Mahasiswa : " + namaMahasiswa);
        System.out.println("Asrama    : " + namaAsrama);
        System.out.println("-------------------------------");
        for (int i = 0; i < daftarPakaian.size(); i++) {
            LaundryItem item = daftarPakaian.get(i);
            String status = item.terverifikasi ? "[SESUAI]" : "[BELUM DICEK]";
            System.out.printf("%d. %-12s (%d pcs) %s\n", (i + 1), item.namaPakaian, item.jumlah, status);
        }
        System.out.println("-------------------------------");
        System.out.println("Note Mhs  : " + (catatanMahasiswa != null ? catatanMahasiswa : "-"));
        System.out.println("Note Admin: " + (catatanLaundry != null ? catatanLaundry : "-"));
        System.out.println("===============================");
    }
}

// 3. Driver utama
public class Driver3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // ALUR 1: Input dari Mahasiswa
        System.out.println("--- Form Input Mahasiswa ---");
        System.out.print("Nama Anda: ");
        String nama = input.nextLine();
        System.out.print("Nama Asrama: ");
        String asrama = input.nextLine();

        LaundryBag bag = new LaundryBag(nama, asrama);

        System.out.print("Berapa jenis pakaian yang dimasukkan? ");
        int jenis = input.nextInt();
        input.nextLine(); // clear buffer

        for (int i = 0; i < jenis; i++) {
            System.out.println("Item ke-" + (i + 1));
            System.out.print("  Nama Pakaian: ");
            String pkn = input.nextLine();
            System.out.print("  Jumlah: ");
            int jml = input.nextInt();
            input.nextLine(); // clear buffer
            System.out.print("  Keterangan (misal: warna): ");
            String ket = input.nextLine();
            bag.tambahPakaian(pkn, jml, ket);
        }

        System.out.print("Tambahkan catatan untuk laundry: ");
        bag.setCatatanMahasiswa(input.nextLine());

        // Tampilan sebelum dicek
        bag.tampilkanStruk();

        // ALUR 2 & 3: Verifikasi oleh Pihak Laundry
        System.out.println("\n--- Bagian Verifikasi Pihak Laundry ---");
        for (LaundryItem item : bag.getDatarPakaian()) {
            System.out.print("Apakah fisik '" + item.namaPakaian + "' sesuai? (y/n): ");
            String jawab = input.next();
            if (jawab.equalsIgnoreCase("y")) {
                item.terverifikasi = true;
            }
        }
        input.nextLine(); // clear buffer
        System.out.print("Catatan tambahan dari pihak laundry: ");
        bag.setCatatanLaundry(input.nextLine());

        // Hasil Akhir
        System.out.println("\nPROSES SELESAI. Berikut adalah list laundry bag Anda:");
        bag.tampilkanStruk();

        input.close();
    }
}
//davinaa
