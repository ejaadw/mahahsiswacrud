import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MahasiswaDAO dao = new MahasiswaDAO();
        try (Scanner sc = new Scanner(System.in)) {
            int pilihan;

            do {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Tambah Mahasiswa");
                System.out.println("2. Lihat Mahasiswa");
                System.out.println("3. Update Mahasiswa");
                System.out.println("4. Hapus Mahasiswa");
                System.out.println("0. Keluar");
                System.out.print("Pilihan: ");
                pilihan = sc.nextInt();
                sc.nextLine(); // hapus newline

                switch (pilihan) {
                    case 1 -> {
                        System.out.print("Nama: ");
                        String nama = sc.nextLine();
                        System.out.print("NIM: ");
                        String nim = sc.nextLine();
                        System.out.print("Jurusan: ");
                        String jurusan = sc.nextLine();
                        dao.tambahMahasiswa(new Mahasiswa(0, nama, nim, jurusan));
                    }
                    case 2 -> dao.tampilkanMahasiswa();
                    case 3 -> {
                        System.out.print("ID Mahasiswa: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nama Baru: ");
                        String nama = sc.nextLine();
                        System.out.print("NIM Baru: ");
                        String nim = sc.nextLine();
                        System.out.print("Jurusan Baru: ");
                        String jurusan = sc.nextLine();
                        dao.updateMahasiswa(new Mahasiswa(id, nama, nim, jurusan));
                    }
                    case 4 -> {
                        System.out.print("ID yang akan dihapus: ");
                        int id = sc.nextInt();
                        dao.hapusMahasiswa(id);
                    }
                    case 0 -> System.out.println("Keluar...");
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } while (pilihan != 0);
        }
    }
}