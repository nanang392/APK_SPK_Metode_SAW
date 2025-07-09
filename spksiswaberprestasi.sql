-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 12 Jun 2025 pada 12.13
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spksiswaberprestasi`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_alternatif`
--

CREATE TABLE `data_alternatif` (
  `id_siswa` int(11) NOT NULL,
  `kode` varchar(20) NOT NULL,
  `alternatif` varchar(100) NOT NULL,
  `nisn` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_alternatif`
--

INSERT INTO `data_alternatif` (`id_siswa`, `kode`, `alternatif`, `nisn`) VALUES
(1, 'A1', 'Muhammad Adzkiya', '3082663680'),
(2, 'A2', 'Muhammad Rosyid', '0087893171'),
(3, 'A3', 'Fauzul Hafis', '3093952359'),
(4, 'A4', 'Ahmad Ali', '3080877878'),
(5, 'A5', 'M Dafa Khoirulloh', '0081335174'),
(6, 'A6', 'M Dafi Hibatulloh', '0081515132'),
(7, 'A7', 'Imaduddin Dai', '0077556121'),
(8, 'A8', 'M Farhan Azhary', '0085362308'),
(9, 'A9', 'M Daffa Fahreza', '0059406657'),
(10, 'A10', 'Kheisan Fatih Achmad', '0074444443'),
(11, 'A11', 'A Rafiz', '0069504519'),
(13, 'A12', 'Nanang M', '908765213'),
(14, 'A13', 'Hamzah', '098765678');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_kriteria`
--

CREATE TABLE `data_kriteria` (
  `id_kriteria` int(11) NOT NULL,
  `kode` varchar(10) NOT NULL,
  `kriteria` varchar(100) NOT NULL,
  `wj` varchar(20) NOT NULL,
  `bobot` decimal(3,2) UNSIGNED NOT NULL CHECK (`bobot` > 0 and `bobot` <= 1),
  `atribut` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_kriteria`
--

INSERT INTO `data_kriteria` (`id_kriteria`, `kode`, `kriteria`, `wj`, `bobot`, `atribut`) VALUES
(1, 'C1', 'Nilai rata-rata raport', 'W1', 0.30, 'Benefit'),
(2, 'C2', 'Nilai Presensi', 'W2', 0.25, 'Benefit'),
(3, 'C3', 'Nilai Sikap', 'W3', 0.20, 'Benefit'),
(4, 'C4', 'Nilai Rata-rata Ujian Praktik & Lisan', 'W4', 0.25, 'Benefit');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_nilai`
--

CREATE TABLE `data_nilai` (
  `id_nilai` int(11) NOT NULL,
  `kode` varchar(10) DEFAULT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `tahun_pelajaran` varchar(10) DEFAULT NULL,
  `nilai_raport` decimal(5,2) DEFAULT NULL,
  `bobot_raport` int(11) DEFAULT NULL,
  `presensi` int(11) DEFAULT NULL,
  `bobot_presensi` int(11) DEFAULT NULL,
  `sikap` varchar(20) DEFAULT NULL,
  `bobot_sikap` int(11) DEFAULT NULL,
  `nilai_ujian` decimal(5,2) DEFAULT NULL,
  `bobot_ujian` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_nilai`
--

INSERT INTO `data_nilai` (`id_nilai`, `kode`, `nama`, `tahun_pelajaran`, `nilai_raport`, `bobot_raport`, `presensi`, `bobot_presensi`, `sikap`, `bobot_sikap`, `nilai_ujian`, `bobot_ujian`) VALUES
(19, 'A1', 'Muhammad Adzkiya', '2024/2025', 80.00, 3, 90, 3, 'Sangat Baik', 4, 80.00, 3),
(20, 'A2', 'Muhammad Rosyid', '2024/2025', 81.00, 3, 90, 3, 'Sangat Baik', 4, 83.00, 3),
(21, 'A3', 'Fauzul Hafis', '2024/2025', 85.00, 4, 100, 4, 'Sangat Baik', 4, 85.00, 4),
(22, 'A4', 'Ahmad Ali', '2024/2025', 81.00, 3, 100, 4, 'Sangat Baik', 4, 84.00, 3),
(23, 'A5', 'M Dafa Khoirulloh', '2024/2025', 80.00, 3, 90, 3, 'Baik', 3, 80.00, 3),
(24, 'A6', 'M Dafi Hibatulloh', '2024/2025', 79.00, 3, 90, 3, 'Baik', 3, 80.00, 3),
(25, 'A7', 'Imaduddin Dai', '2024/2025', 78.00, 3, 80, 2, 'Cukup', 2, 63.00, 1),
(26, 'A8', 'M Farhan Azhary', '2024/2025', 80.00, 3, 80, 2, 'Baik', 3, 85.00, 4),
(27, 'A9', 'M Daffa Fahreza', '2024/2025', 79.00, 3, 80, 2, 'Cukup', 2, 82.00, 3),
(30, 'A10', 'Kheisan Fatih Achmad', '2024/2025', 83.00, 3, 100, 4, 'Sangat Baik', 4, 83.00, 3),
(31, 'A11', 'A Rafiz', '2024/2025', 83.00, 3, 90, 3, 'Sangat Baik', 4, 87.00, 4);

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_ranking`
--

CREATE TABLE `data_ranking` (
  `id_ranking` int(11) NOT NULL,
  `kode` varchar(10) DEFAULT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `tahun` varchar(20) DEFAULT NULL,
  `nilai_preferensi` decimal(10,4) DEFAULT NULL,
  `ranking` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_ranking`
--

INSERT INTO `data_ranking` (`id_ranking`, `kode`, `nama`, `tahun`, `nilai_preferensi`, `ranking`) VALUES
(34, 'A3', 'Fauzul Hafis', '2024/2025', 1.0000, 1),
(35, 'A4', 'Ahmad Ali', '2024/2025', 0.8625, 2),
(36, 'A10', 'Kheisan Fatih Achmad', '2024/2025', 0.8625, 3),
(37, 'A11', 'A Rafiz', '2024/2025', 0.8625, 4),
(38, 'A1', 'Muhammad Adzkiya', '2024/2025', 0.8000, 5),
(39, 'A2', 'Muhammad Rosyid', '2024/2025', 0.8000, 6),
(40, 'A5', 'M Dafa Khoirulloh', '2024/2025', 0.7500, 7),
(41, 'A6', 'M Dafi Hibatulloh', '2024/2025', 0.7500, 8),
(42, 'A8', 'M Farhan Azhary', '2024/2025', 0.7500, 9),
(43, 'A9', 'M Daffa Fahreza', '2024/2025', 0.6375, 10),
(44, 'A7', 'Imaduddin Dai', '2024/2025', 0.5125, 11);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `level` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `level`) VALUES
(1, 'admin', '123', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `data_alternatif`
--
ALTER TABLE `data_alternatif`
  ADD PRIMARY KEY (`id_siswa`);

--
-- Indeks untuk tabel `data_kriteria`
--
ALTER TABLE `data_kriteria`
  ADD PRIMARY KEY (`id_kriteria`),
  ADD UNIQUE KEY `kode` (`kode`);

--
-- Indeks untuk tabel `data_nilai`
--
ALTER TABLE `data_nilai`
  ADD PRIMARY KEY (`id_nilai`);

--
-- Indeks untuk tabel `data_ranking`
--
ALTER TABLE `data_ranking`
  ADD PRIMARY KEY (`id_ranking`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `data_alternatif`
--
ALTER TABLE `data_alternatif`
  MODIFY `id_siswa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT untuk tabel `data_kriteria`
--
ALTER TABLE `data_kriteria`
  MODIFY `id_kriteria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `data_nilai`
--
ALTER TABLE `data_nilai`
  MODIFY `id_nilai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT untuk tabel `data_ranking`
--
ALTER TABLE `data_ranking`
  MODIFY `id_ranking` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
