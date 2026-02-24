package driver;

import java.util.ArrayList;
import java.util.Scanner;

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

public class Driver1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Pesanan> daftarPesanan = new ArrayList<>();
        
        // Database Menu
        String[] kodeMenu = {"NGS", "AP", "SA", "BU", "MAP", "GG", "SAM", "RD", "IB", "NUK"};
        String[] namaMenu = {
            "Nasi Goreng Spesial", "Ayam Penyet", "Sate Ayam (10 Tusuk)", 
            "Bakso Urat", "Mie Ayam Pangsit", "Gado-Gado", 
            "Soto Ayam", "Rendang Daging", "Ikan Bakar", "Nasi Uduk Komplit"
        };
        int[] hargaMenu = {15000, 20000, 25000, 18000, 15000, 15000, 17000, 25000, 35000, 20000};

        // LOOP INPUT (Sesuai format: Kode -> Enter -> Porsi Butet)
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
                    // Logika: Ucok (100kg) makan 2x lipat Butet (50kg). Total = 3x porsi Butet
                    int totalPorsi = porsiButet * 3;
                    daftarPesanan.add(new Pesanan(namaMenu[index], totalPorsi, hargaMenu[index]));
                }
            }
        }

        // PERHITUNGAN TOTAL DAN DISKON
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

        // OUTPUT FORMAT STRUK (Sesuai Gambar Tugas)
        System.out.println("\nMenu                 Porsi   Harga    Total");
        System.out.println("=============================================");
        for (Pesanan p : daftarPesanan) {
            System.out.printf("%-20s %-7d %-8d %-10d\n", p.nama, p.porsi, p.harga, p.total);
        }
        System.out.println("=============================================");
        
        // Jika ada diskon, tampilkan rinciannya
        if (diskon > 0) {
            System.out.printf("%-35s %d\n", "Total Belanja", totalSebelumDiskon);
            System.out.printf("%-35s %d\n", "Diskon (" + (int)(diskon*100) + "%)", nilaiDiskon);
            System.out.println("---------------------------------------------");
        }
        
        System.out.printf("%-35s %d\n", "Total Pembayaran", totalAkhir);
        System.out.println("=============================================");
        
        sc.close();
    }
}

//davinaa
