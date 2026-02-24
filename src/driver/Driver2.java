package driver;

import java.util.Scanner;

public class Driver2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Membaca jumlah total data (N)
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();

            // 2. Membaca deret nilai
            int[] nilai = new int[n];
            for (int i = 0; i < n; i++) {
                nilai[i] = scanner.nextInt();
            }

            // 3. Membaca kode kelompok siswa yang ingin dihitung total nilainya
            // Asumsi: 1 untuk kelompok posisi ganjil (1, 3, 5...), 2 untuk kelompok posisi genap (2, 4, 6...)
            int kodeKelompok = scanner.nextInt();

            int totalNilai = 0;

            // Menghitung total nilai sesuai kelompok
            for (int i = 0; i < n; i++) {
                int posisiSiswa = i + 1; // Menggunakan 1-based index (posisi 1, 2, 3...)
                
                // Jika posisi ganjil dan kode yang diinput adalah 1
                if (posisiSiswa % 2 != 0 && kodeKelompok == 1) {
                    totalNilai += nilai[i];
                } 
                // Jika posisi genap dan kode yang diinput adalah 2
                else if (posisiSiswa % 2 == 0 && kodeKelompok == 2) {
                    totalNilai += nilai[i];
                }
            }

            // Menampilkan hasil akhir
            System.out.println(totalNilai);
        }

        scanner.close();
    }
}
// davina
