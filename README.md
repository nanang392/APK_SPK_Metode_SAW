# Sistem Pendukung Keputusan Pemilihan Siswa Berprestasi dengan Metode SAW 🏆

<p align="center">
  <img style="margin-right: 8px;" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java Badge">
  <img style="margin-right: 8px;" src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL Badge">
  <img style="margin-right: 8px;" src="https://img.shields.io/badge/NetBeans-1B6AC6?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white" alt="NetBeans Badge">
  <img style="margin-right: 8px;" src="https://img.shields.io/badge/JasperReports-007399?style=for-the-badge&logo=jasperreports&logoColor=white" alt="JasperReports Badge">
</p>

📝 Aplikasi ini merupakan sistem pendukung keputusan (SPK) yang dirancang untuk membantu proses pemilihan siswa berprestasi menggunakan metode Simple Additive Weighting (SAW). Aplikasi ini dibangun dengan Java dan memanfaatkan database MySQL untuk menyimpan data siswa dan kriteria penilaian. Tujuannya adalah untuk menyediakan alat yang efisien dan objektif dalam menentukan siswa yang paling layak menerima penghargaan.

✨ Aplikasi ini membantu sekolah atau institusi pendidikan dalam proses seleksi siswa berprestasi dengan metode yang terstruktur dan transparan.

### **Fitur Utama ✨**

*   ✅ **Implementasi Metode SAW:** Menerapkan metode Simple Additive Weighting untuk memberikan bobot pada setiap kriteria dan menghasilkan skor akhir untuk setiap siswa.
*   📊 **Manajemen Data Siswa:** Memungkinkan penambahan, pengeditan, dan penghapusan data siswa beserta nilai-nilai mereka pada setiap kriteria.
*   🗄️ **Laporan dan Visualisasi:** Menghasilkan laporan hasil seleksi dalam format yang mudah dibaca dan dipahami, serta visualisasi data yang relevan.
*   ⚙️ **Konfigurasi Kriteria:** Memungkinkan penyesuaian kriteria penilaian dan bobot masing-masing kriteria sesuai dengan kebutuhan institusi.

### **Tech Stack 🛠️**

*   ☕ **Bahasa Pemrograman:** Java
*   🗄️ **Database:** MySQL
*   🖥️ **IDE:** NetBeans
*   📊 **Reporting:** JasperReports

### **Instalasi & Menjalankan 🚀**

1.  Clone repositori:
    ```bash
    git clone https://github.com/nanang392/APK_SPK_Metode_SAW
    ```

2.  Masuk ke direktori:
    ```bash
    cd APK_SPK_Metode_SAW
    ```

3.  Import project ke NetBeans:
    *   Buka NetBeans.
    *   Pilih "File" -> "Open Project..."
    *   Arahkan ke direktori `APK_SPK_Metode_SAW` dan pilih project.

4.  Konfigurasi koneksi database:
    *   Buka file konfigurasi database (biasanya di dalam kode Java).
    *   Sesuaikan pengaturan koneksi (host, port, nama database, username, password) agar sesuai dengan konfigurasi MySQL Anda.

5.  Build project:
    *   Klik kanan pada nama project di panel "Projects" NetBeans.
    *   Pilih "Clean and Build Project".

6.  Jalankan proyek:
    *   Klik kanan pada nama project di panel "Projects" NetBeans.
    *   Pilih "Run".

### **Cara Berkontribusi 🤝**

1.  Fork repositori ini.
2.  Buat branch baru dengan nama yang deskriptif: `git checkout -b fitur/nama-fitur`
3.  Lakukan perubahan dan commit dengan pesan yang jelas: `git commit -m "Menambahkan fitur baru"`
4.  Push ke branch Anda: `git push origin fitur/nama-fitur`
5.  Buat Pull Request ke branch `main` pada repositori ini.
