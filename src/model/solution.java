import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ==========================================
// KELAS BANTUAN UNTUK PROGRAM 1 (PESANAN)
// ==========================================
class Pesanan {
    String nama;
    int porsi;
    int harga;
    long total;

    Pesanan(String nama, int porsi, int harga) {
        this.nama = nama;
        this.porsi = porsi;
        this.harga = harga;
        this.total = (long) porsi * harga;
    }
}

// ==========================================
// KELAS BANTUAN UNTUK PROGRAM 3 (LAUNDRY)
// ==========================================
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

// ==========================================
// KELAS UTAMA (MAIN CLASS)
// ==========================================
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== KUMPULAN PROGRAM DAVINA ===");
        System.out.println("1. Program Pesanan Makanan (Driver 1)");
        System.out.println("2. Program Kelompok Nilai Siswa (Driver 2)");
        System.out.println("3. Program Laundry (Driver 3)");
        System.out.print("Pilih program yang ingin dijalankan (1/2/3): ");
        
        if (sc.hasNextInt()) {
            int pilihan = sc.nextInt();
            
            switch (pilihan) {
                case 1:
                    jalankanProgramPesanan(sc);
                    break;
                case 2:
                    jalankanProgramSiswa(sc);
                    break;
                case 3:
                    jalankanProgramLaundry(sc);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        sc.close();
    }

    // --- LOGIKA PROGRAM 1 ---
    private static void jalankanProgramPesanan(Scanner sc) {
        System.out.println("\n--- Menjalankan Program Pesanan Makanan ---");
        System.out.println("Masukkan kode pesanan dan porsi (Ketik END untuk selesai):");
        ArrayList<Pesanan> daftarPesanan = new ArrayList<>();
        
        String[] kodeMenu = {"NGS", "AP", "SA", "BU", "MAP", "GG", "SAM", "RD", "IB", "NUK"};
        String[] namaMenu = {
            "Nasi Goreng Spesial", "Ayam Penyet", "Sate Ayam (10 Tusuk)", 
            "Bakso Urat", "Mie Ayam Pangsit", "Gado-Gado", 
            "Soto Ayam", "Rendang Daging", "Ikan Bakar", "Nasi Uduk Komplit"
        };
        int[] hargaMenu = {15000, 20000, 25000, 18000, 15000, 15000, 17000, 25000, 35000, 20000};

        while (sc.hasNext()) {
            String inputKode = sc.next().toUpperCase();
            if (inputKode.equals("END")) break;

            int index = -1;
            for (int i = 0; i < kodeMenu.length; i++) {
                if (kodeMenu[i].equals(inputKode)) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                if (sc.hasNextInt()) {
                    int porsiButet = sc.nextInt();
                    int totalPorsi = porsiButet * 3;
                    daftarPesanan.add(new Pesanan(namaMenu[index], totalPorsi, hargaMenu[index]));
                }
            }
        }

        long totalSebelumDiskon = 0;
        for (Pesanan p : daftarPesanan) {
            totalSebelumDiskon += p.total;
        }

        double diskon = 0;
        if (totalSebelumDiskon >= 500000) diskon = 0.25;
        else if (totalSebelumDiskon >= 400000) diskon = 0.20;
        else if (totalSebelumDiskon >= 300000) diskon = 0.15;
        else if (totalSebelumDiskon >= 200000) diskon = 0.10;
        else if (totalSebelumDiskon >= 100000) diskon = 0.05;

        long nilaiDiskon = (long) (totalSebelumDiskon * diskon);
        long totalAkhir = totalSebelumDiskon - nilaiDiskon;

        System.out.println("\nMenu                 Porsi   Harga    Total");
        System.out.println("=============================================");
        for (Pesanan p : daftarPesanan) {
            System.out.printf("%-20s %-7d %-8d %-10d\n", p.nama, p.porsi, p.harga, p.total);
        }
        System.out.println("=============================================");
        
        if (diskon > 0) {
            System.out.printf("%-35s %d\n", "Total Belanja", totalSebelumDiskon);
            System.out.printf("%-35s %d\n", "Diskon (" + (int)(diskon*100) + "%)", nilaiDiskon);
            System.out.println("---------------------------------------------");
        }
        
        System.out.printf("%-35s %d\n", "Total Pembayaran", totalAkhir);
        System.out.println("=============================================");
    }

    // --- LOGIKA PROGRAM 2 ---
    private static void jalankanProgramSiswa(Scanner sc) {
        System.out.println("\n--- Menjalankan Program Nilai Siswa ---");
        System.out.print("Masukkan jumlah data (N): ");
        if (sc.hasNextInt()) {
            int n = sc.nextInt();

            System.out.print("Masukkan deret nilai: ");
            int[] nilai = new int[n];
            for (int i = 0; i < n; i++) {
                nilai[i] = sc.nextInt();
            }

            System.out.print("Masukkan kode kelompok (1 untuk ganjil, 2 untuk genap): ");
            int kodeKelompok = sc.nextInt();
            int totalNilai = 0;

            for (int i = 0; i < n; i++) {
                int posisiSiswa = i + 1;
                if (posisiSiswa % 2 != 0 && kodeKelompok == 1) {
                    totalNilai += nilai[i];
                } else if (posisiSiswa % 2 == 0 && kodeKelompok == 2) {
                    totalNilai += nilai[i];
                }
            }
            System.out.println("Total nilai: " + totalNilai);
        }
    }

    // --- LOGIKA PROGRAM 3 ---
    private static void jalankanProgramLaundry(Scanner sc) {
        sc.nextLine(); // Clear buffer dari input menu sebelumnya
        System.out.println("\n--- Form Input Mahasiswa ---");
        System.out.print("Nama Anda: ");
        String nama = sc.nextLine();
        System.out.print("Nama Asrama: ");
        String asrama = sc.nextLine();

        LaundryBag bag = new LaundryBag(nama, asrama);

        System.out.print("Berapa jenis pakaian yang dimasukkan? ");
        int jenis = sc.nextInt();
        sc.nextLine(); // clear buffer

        for (int i = 0; i < jenis; i++) {
            System.out.println("Item ke-" + (i + 1));
            System.out.print("  Nama Pakaian: ");
            String pkn = sc.nextLine();
            System.out.print("  Jumlah: ");
            int jml = sc.nextInt();
            sc.nextLine(); // clear buffer
            System.out.print("  Keterangan (misal: warna): ");
            String ket = sc.nextLine();
            bag.tambahPakaian(pkn, jml, ket);
        }

        System.out.print("Tambahkan catatan untuk laundry: ");
        bag.setCatatanMahasiswa(sc.nextLine());

        bag.tampilkanStruk();

        System.out.println("\n--- Bagian Verifikasi Pihak Laundry ---");
        for (LaundryItem item : bag.getDatarPakaian()) {
            System.out.print("Apakah fisik '" + item.namaPakaian + "' sesuai? (y/n): ");
            String jawab = sc.next();
            if (jawab.equalsIgnoreCase("y")) {
                item.terverifikasi = true;
            }
        }
        sc.nextLine(); // clear buffer
        System.out.print("Catatan tambahan dari pihak laundry: ");
        bag.setCatatanLaundry(sc.nextLine());

        System.out.println("\nPROSES SELESAI. Berikut adalah list laundry bag Anda:");
        bag.tampilkanStruk();
    }
}
