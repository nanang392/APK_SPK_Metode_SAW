# Sistem Pendukung Keputusan Pemilihan Siswa Berprestasi dengan Metode SAW ✨

<p align="center">
  <img style="margin-right: 8px;" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java">
  <img style="margin-right: 8px;" src="https://img.shields.io/badge/NetBeans-1B6AC6?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white" alt="NetBeans">
  <img style="margin-right: 8px;" src="https://img.shields.io/badge/OOP-4F5052?style=for-the-badge&logo=gnu-objectmodel&logoColor=white" alt="Object-Oriented Programming">
</p>

📝 Aplikasi ini dirancang untuk membantu proses pengambilan keputusan dalam pemilihan siswa berprestasi menggunakan metode Simple Additive Weighting (SAW). Dibuat dengan Java dan memanfaatkan NetBeans IDE, aplikasi ini menyediakan antarmuka yang mudah digunakan untuk mengelola data siswa, kriteria penilaian, dan melakukan perhitungan SAW untuk menghasilkan rekomendasi siswa berprestasi.

**Fitur Utama ✨**

*   🏆 **Perhitungan SAW Otomatis:** Menghitung skor siswa berdasarkan kriteria yang telah ditentukan menggunakan metode SAW.
*   📊 **Laporan dan Visualisasi:** Menghasilkan laporan yang mudah dibaca dan divisualisasikan untuk membantu dalam proses pengambilan keputusan.
*   ⚙️ **Konfigurasi Kriteria:** Fleksibilitas untuk menyesuaikan kriteria penilaian dan bobot sesuai dengan kebutuhan.
*   🗄️ **Manajemen Data Siswa:** Mengelola data siswa, termasuk menambah, mengedit, dan menghapus data.
*   🎨 **Antarmuka Pengguna yang Intuitif:** Antarmuka pengguna yang mudah digunakan untuk memudahkan navigasi dan penggunaan aplikasi.

**Tech Stack 🛠️**

*   ☕ Java: Bahasa pemrograman utama.
*   👨‍💻 NetBeans IDE: Lingkungan pengembangan terintegrasi.
*   🧮 Metode SAW: Metode pengambilan keputusan.
*   📊 JasperReports: Library untuk menghasilkan laporan (berdasarkan file `.jasper` dan `.jrxml`).
*   📚 OOP: Pemrograman Berorientasi Objek.

**Instalasi & Menjalankan 🚀**

1.  Clone repositori:
    ```bash
    git clone https://github.com/nanang392/APK_SPK_Metode_SAW
    ```

2.  Masuk ke direktori:
    ```bash
    cd APK_SPK_Metode_SAW
    ```

3.  Compile kode Java: Karena proyek ini menggunakan NetBeans, Anda dapat membuka proyek di NetBeans dan meng-compile proyek. Atau, gunakan `javac`:
    ```bash
    javac -d build/classes src/Main/*.java src/Report/*.java
    ```
    (Sesuaikan path jika struktur direktori berbeda)

4.  Jalankan proyek:
    ```bash
    java -cp build/classes Main.MenuUtama
    ```
    (Pastikan `build/classes` berada di classpath)

**Cara Berkontribusi 🤝**

1.  Fork repositori ini.
2.  Buat branch baru dengan nama yang deskriptif (`git checkout -b fitur-baru`).
3.  Lakukan perubahan dan commit dengan pesan yang jelas (`git commit -m "Menambahkan fitur baru"`).
4.  Push ke branch Anda (`git push origin fitur-baru`).
5.  Buat Pull Request ke repositori ini.
