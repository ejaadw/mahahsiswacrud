import java.sql.*;

public class MahasiswaDAO {
    private Connection conn;

    public MahasiswaDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:mahasiswa.db");
            buatTabelJikaBelumAda();
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
    }

    private void buatTabelJikaBelumAda() throws SQLException {
        String sql = """
                    CREATE TABLE IF NOT EXISTS mahasiswa (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nama TEXT,
                        nim TEXT,
                        jurusan TEXT
                    );
                """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void tambahMahasiswa(Mahasiswa mhs) {
        try {
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, mhs.nama);
            stmt.setString(2, mhs.nim);
            stmt.setString(3, mhs.jurusan);
            stmt.executeUpdate();
            System.out.println("✅ Data berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void tampilkanMahasiswa() {
        try {
            String sql = "SELECT * FROM mahasiswa";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " +
                        rs.getString("nama") + " - " +
                        rs.getString("nim") + " - " +
                        rs.getString("jurusan"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMahasiswa(Mahasiswa mhs) {
        try {
            String sql = "UPDATE mahasiswa SET nama=?, nim=?, jurusan=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, mhs.nama);
            stmt.setString(2, mhs.nim);
            stmt.setString(3, mhs.jurusan);
            stmt.setInt(4, mhs.id);
            stmt.executeUpdate();
            System.out.println("✅ Data berhasil diupdate.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hapusMahasiswa(int id) {
        try {
            String sql = "DELETE FROM mahasiswa WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Data berhasil dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
