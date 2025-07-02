package View;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;


public class MenuPenilaian_Kriteria extends javax.swing.JPanel {
    private int totalPages;
    
    private final Connection conn;

    private Map<String, Object> parameter;
    
    public MenuPenilaian_Kriteria() {
        initComponents();
        
        conn = koneksi.getConnection();
        setTabelModel();
        loadDataAlternatifToComboBox();
        cbsikap.addItem("Pilih Sikap");
        cbsikap.addItem("Sangat Baik");
        cbsikap.addItem("Baik");
        cbsikap.addItem("Cukup");
        cbsikap.addItem("Kurang");

        
        setBackground(new Color(0,0,0,0));
        
        tbsiswa.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbsiswa.getTableHeader().setOpaque(false);
        tbsiswa.getTableHeader().setBackground(new Color(32, 136, 203));
        tbsiswa.getTableHeader().setForeground(new Color(255,255,255));
        tbsiswa.setRowHeight(25);
    }
    private void showPanel(){
        panelMain.removeAll();
        panelMain.add(new MenuPenilaian_Kriteria());
        panelMain.repaint();
        panelMain.revalidate();
    }
 private void setTabelModel() {
    DefaultTableModel model = new DefaultTableModel();

    // Menentukan kolom tabel
    model.addColumn("No");
    model.addColumn("KODE");
    model.addColumn("NAMA"); // Ditambahkan kolom NAMA karena ada rs.getString("nama")
    model.addColumn("TAHUN");
    model.addColumn("Nilai Raport");
    model.addColumn("Nilai Presensi");
    model.addColumn("Sikap");
    model.addColumn("Nilai Ujian Praktik & Lisan");
    
    model.addColumn("C1");
    model.addColumn("C2");
    model.addColumn("C3");
    model.addColumn("C4");

    try {
        String sql = "SELECT * FROM data_nilai";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        int no = 1; // Nomor urut dimulai dari 1
        while (rs.next()) {
            model.addRow(new Object[]{
                no++,                                
                rs.getString("kode"),                
                rs.getString("nama"),               
                rs.getString("tahun_pelajaran"),    
                rs.getString("nilai_raport"), 
                rs.getString("presensi"),
                rs.getString("sikap"),
                rs.getString("nilai_ujian"),
                
                rs.getString("bobot_raport"),
                rs.getString("bobot_presensi"),
                rs.getString("bobot_sikap"),
                rs.getString("bobot_ujian")
            });
        }

        // Menampilkan model ke JTable
        tbsiswa.setModel(model);

    } catch (SQLException e) {
        Logger.getLogger(MenuPenilaian_Kriteria.class.getName()).log(Level.SEVERE, null, e);
        JOptionPane.showMessageDialog(null, "Gagal mengambil data: " + e.getMessage());
    }
}


private void insertNilai() {
    String kode = txtkode1.getText();
    String nama = cbnama.getSelectedItem().toString();
    String tp = cbtahun.getSelectedItem().toString();
    
    String raportStr = txtnilair.getText();
    String bobotRaportStr = txtnilair2.getText();
    
    String presensiStr = txtpresensi.getText();
    String bobotPresensiStr = txtnilaipresensi.getText();
    
    String sikap = cbsikap.getSelectedItem().toString();
    String bobotSikapStr = txtnilaisikap.getText();
    
    String nilaiUjianStr = txtnilair3.getText();
    String bobotUjianStr = txtnilair4.getText();

    // Validasi input sederhana
    if (kode.isEmpty() || nama.equals("Pilih Nama Alternatif") || tp.isEmpty() || 
        raportStr.isEmpty() || bobotRaportStr.isEmpty() || presensiStr.isEmpty() || 
        bobotPresensiStr.isEmpty() || sikap.equals("Pilih Sikap") || bobotSikapStr.isEmpty() || 
        nilaiUjianStr.isEmpty() || bobotUjianStr.isEmpty()) {
        
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi", "Validasi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        double raport = Double.parseDouble(raportStr);
        int bobotRaport = Integer.parseInt(bobotRaportStr);
        int presensi = Integer.parseInt(presensiStr);
        int bobotPresensi = Integer.parseInt(bobotPresensiStr);
        int bobotSikap = Integer.parseInt(bobotSikapStr);
        double nilaiUjian = Double.parseDouble(nilaiUjianStr);
        int bobotUjian = Integer.parseInt(bobotUjianStr);

        String sql = "INSERT INTO data_nilai (kode, nama, tahun_pelajaran, nilai_raport, bobot_raport, presensi, bobot_presensi, sikap, bobot_sikap, nilai_ujian, bobot_ujian) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, kode);
            st.setString(2, nama);
            st.setString(3, tp);
            st.setDouble(4, raport);
            st.setInt(5, bobotRaport);
            st.setInt(6, presensi);
            st.setInt(7, bobotPresensi);
            st.setString(8, sikap);
            st.setInt(9, bobotSikap);
            st.setDouble(10, nilaiUjian);
            st.setInt(11, bobotUjian);

            int inserted = st.executeUpdate();
            if (inserted > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
                resetForm();
                setTabelModel();
            }
        }
    } catch (SQLException | NumberFormatException e) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + e.getMessage());
    }
}




private void resetForm() {
     // Reset input teks
    txtkode1.setText("");
    txtnilair.setText("");
    txtnilair2.setText("");
    txtpresensi.setText("");
    txtnilaipresensi.setText("");
    txtnilaisikap.setText("");
    txtnilair3.setText("");
    txtnilair4.setText("");

    // Reset combo box ke item pertama
    if (cbnama.getItemCount() > 0) {
        cbnama.setSelectedIndex(0);
    }

    if (cbtahun.getItemCount() > 0) {
        cbtahun.setSelectedIndex(0);
    }

    if (cbsikap.getItemCount() > 0) {
        cbsikap.setSelectedIndex(0);
    }
}

   private void updateData() {
    String kode = txtkode1.getText();
    String nama = cbnama.getSelectedItem().toString();
    String tp = cbtahun.getSelectedItem().toString();

    String raportStr = txtnilair.getText();
    String presensiStr = txtpresensi.getText();
    String sikap = cbsikap.getSelectedItem().toString();
    String nilaiUjianStr = txtnilair3.getText();
    
    String bobotRaportStr = txtnilair2.getText();
    String bobotPresensiStr = txtnilaipresensi.getText();
    String bobotSikapStr = txtnilaisikap.getText();
    String bobotUjianStr = txtnilair4.getText();

    try {
        String sql = "UPDATE data_nilai SET " +
                     "nama = ?, " +
                     "tahun_pelajaran = ?, " +
                     "nilai_raport = ?, " +
                     "presensi = ?, " +
                     "sikap = ?, " +
                     "nilai_ujian = ?, " +
                     "bobot_raport = ?, " +
                     "bobot_presensi = ?, " +
                     "bobot_sikap = ?, " +
                     "bobot_ujian = ? " +
                     "WHERE kode = ?";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nama);
        pst.setString(2, tp);
        pst.setString(3, raportStr);
        pst.setString(4, presensiStr);
        pst.setString(5, sikap);
        pst.setString(6, nilaiUjianStr);
        pst.setString(7, bobotRaportStr);
        pst.setString(8, bobotPresensiStr);
        pst.setString(9, bobotSikapStr);
        pst.setString(10, bobotUjianStr);
        pst.setString(11, kode); // Untuk WHERE klausa

        int updated = pst.executeUpdate();
        if (updated > 0) {
            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui.");
            resetForm();
            setTabelModel(); // Refresh tabel setelah update
        } else {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan atau tidak ada yang diperbarui.");
        }

    } catch (SQLException e) {
        Logger.getLogger(MenuPenilaian_Kriteria.class.getName()).log(Level.SEVERE, null, e);
        JOptionPane.showMessageDialog(null, "Gagal memperbarui data: " + e.getMessage());
    }
}


    private void deleteData() {
    String kode = txtkode1.getText();

    if (kode.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Pilih data yang akan dihapus dari tabel terlebih dahulu.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(null, 
        "Apakah Anda yakin ingin menghapus data dengan kode: " + kode + "?", 
        "Konfirmasi Hapus Data", 
        JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        try {
            String sql = "DELETE FROM data_nilai WHERE kode = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, kode);

            int deleted = pst.executeUpdate();
            if (deleted > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");
                resetForm();
                setTabelModel(); // Refresh isi tabel setelah data dihapus
            } else {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan atau gagal dihapus.");
            }

        } catch (SQLException e) {
            Logger.getLogger(MenuPenilaian_Kriteria.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Gagal menghapus data: " + e.getMessage());
        }
    }
}
private void loadDataAlternatifToComboBox() {
    try {
        cbnama.removeAllItems(); // Kosongkan terlebih dahulu
        cbnama.addItem("Pilih Nama Alternatif"); // Tambahkan placeholder pertama

        String sql = "SELECT * FROM data_alternatif";
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            cbnama.addItem(rs.getString("alternatif"));
        }

        rs.close();
        st.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal memuat data alternatif: " + e.getMessage());
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbjeniskelamin = new javax.swing.ButtonGroup();
        panelMain = new javax.swing.JPanel();
        panelView = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbsiswa = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bthapus = new fosalgo.FButtonHapus();
        btTambah = new fosalgo.FButtonTambah();
        bBatal = new fosalgo.BATAL();
        panelRound1 = new fosalgo.PanelRound();
        txtnilair = new fosalgo.MyTextField();
        txtpresensi = new fosalgo.MyTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnilair3 = new fosalgo.MyTextField();
        jLabel7 = new javax.swing.JLabel();
        cbsikap = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbnama = new javax.swing.JComboBox<>();
        txtkode1 = new fosalgo.MyTextField();
        jLabel9 = new javax.swing.JLabel();
        cbtahun = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtnilair2 = new fosalgo.MyTextField();
        txtnilaipresensi = new fosalgo.MyTextField();
        txtnilaisikap = new fosalgo.MyTextField();
        txtnilair4 = new fosalgo.MyTextField();
        jLabel11 = new javax.swing.JLabel();
        panelRound2 = new fosalgo.PanelRound();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 255, 153));
        setPreferredSize(new java.awt.Dimension(1026, 716));
        setLayout(new java.awt.CardLayout());

        panelMain.setBackground(new java.awt.Color(255, 255, 255));
        panelMain.setLayout(new java.awt.CardLayout());

        panelView.setBackground(new java.awt.Color(255, 255, 255));

        tbsiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tbsiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbsiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbsiswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbsiswa);
        tbsiswa.getAccessibleContext().setAccessibleDescription("");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Data Nilai Alternatif");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/doc.png"))); // NOI18N

        bthapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Cancel_1.png"))); // NOI18N
        bthapus.setText("HAPUS");
        bthapus.setFillOver(new java.awt.Color(255, 51, 0));
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });

        btTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Download from the Cloud.png"))); // NOI18N
        btTambah.setText("SIMPAN");
        btTambah.setFillClick(new java.awt.Color(0, 153, 153));
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });

        bBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Restart.png"))); // NOI18N
        bBatal.setText("UBAH");
        bBatal.setFillOver(new java.awt.Color(255, 153, 0));
        bBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBatalActionPerformed(evt);
            }
        });

        panelRound1.setBackground(new java.awt.Color(204, 204, 204));
        panelRound1.setRoundBottomLeft(100);
        panelRound1.setRoundTopRight(100);

        txtnilair.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtnilair.setForeground(new java.awt.Color(0, 0, 0));
        txtnilair.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnilair.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnilairKeyReleased(evt);
            }
        });

        txtpresensi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtpresensi.setForeground(new java.awt.Color(0, 0, 0));
        txtpresensi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtpresensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpresensiKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("C3 : Sikap");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("C2 : Nilai Presensi");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("C1 : Nilai Rata-rata Raport");

        txtnilair3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtnilair3.setForeground(new java.awt.Color(0, 0, 0));
        txtnilair3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnilair3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnilair3KeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("C4 : Nilai Rata-rata Ujian Praktik & Lisan ");

        cbsikap.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        cbsikap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbsikapActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Menyusun Nilai Data Alternatif");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Nama");

        cbnama.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        cbnama.setToolTipText("Pilih Nama");
        cbnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbnamaActionPerformed(evt);
            }
        });

        txtkode1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtkode1.setForeground(new java.awt.Color(0, 0, 0));
        txtkode1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Kode");

        cbtahun.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        cbtahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2024/2025", "2025/2026" }));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Tahun Pelajaran");

        txtnilair2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtnilair2.setForeground(new java.awt.Color(0, 0, 0));
        txtnilair2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtnilaipresensi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtnilaipresensi.setForeground(new java.awt.Color(0, 0, 0));
        txtnilaipresensi.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtnilaisikap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtnilaisikap.setForeground(new java.awt.Color(0, 0, 0));
        txtnilaisikap.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtnilair4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtnilair4.setForeground(new java.awt.Color(0, 0, 0));
        txtnilair4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Matriks Keputusan");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbtahun, javax.swing.GroupLayout.Alignment.LEADING, 0, 150, Short.MAX_VALUE)
                                .addComponent(txtkode1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbnama, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(txtnilair3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbsikap, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtpresensi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnilair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnilair2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnilaipresensi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnilaisikap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnilair4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnilair, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnilair2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtpresensi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnilaipresensi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(cbnama, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtkode1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbsikap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnilaisikap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbtahun, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnilair3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtnilair4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83))
        );

        panelRound2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Keterangan Nilai Subkriteria:");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nilaic.png"))); // NOI18N

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel13))
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout panelViewLayout = new javax.swing.GroupLayout(panelView);
        panelView.setLayout(panelViewLayout);
        panelViewLayout.setHorizontalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        panelViewLayout.setVerticalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        panelMain.add(panelView, "card2");

        add(panelMain, "card2");
    }// </editor-fold>//GEN-END:initComponents


    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
    insertNilai();
    }//GEN-LAST:event_btTambahActionPerformed

    private void tbsiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbsiswaMouseClicked
    int row = tbsiswa.getSelectedRow();
    if (row >= 0) {
        txtkode1.setText(tbsiswa.getValueAt(row, 1).toString());
        cbnama.setSelectedItem(tbsiswa.getValueAt(row, 2).toString());
        cbtahun.setSelectedItem(tbsiswa.getValueAt(row, 3).toString());
        txtnilair.setText(tbsiswa.getValueAt(row, 4).toString());
        txtpresensi.setText(tbsiswa.getValueAt(row, 5).toString());
        cbsikap.setSelectedItem(tbsiswa.getValueAt(row, 6).toString());
        txtnilair3.setText(tbsiswa.getValueAt(row, 7).toString());
        
        txtnilair2.setText(tbsiswa.getValueAt(row, 8).toString());
        txtnilaipresensi.setText(tbsiswa.getValueAt(row, 9).toString());
        txtnilaisikap.setText(tbsiswa.getValueAt(row, 10).toString());
        txtnilair4.setText(tbsiswa.getValueAt(row, 11).toString());
    }
    }//GEN-LAST:event_tbsiswaMouseClicked

    private void bBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBatalActionPerformed
    updateData();
    }//GEN-LAST:event_bBatalActionPerformed

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed
     deleteData();
    }//GEN-LAST:event_bthapusActionPerformed

    private void cbnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbnamaActionPerformed
       String namaAlternatif = (String) cbnama.getSelectedItem();

    if (namaAlternatif != null && !namaAlternatif.equals("Pilih Nama Alternatif")) {
        try {
            String sql = "SELECT kode FROM data_alternatif WHERE alternatif = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, namaAlternatif);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                txtkode1.setText(rs.getString("kode"));
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil kode: " + e.getMessage());
        }
    } else {
        txtkode1.setText(""); // Kosongkan field jika belum memilih
    }
    }//GEN-LAST:event_cbnamaActionPerformed

    private void txtnilairKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnilairKeyReleased
    try {
        int nilai = Integer.parseInt(txtnilair.getText());

        if (nilai >= 85 && nilai <= 99) {
            txtnilair2.setText("4");
        } else if (nilai >= 75 && nilai <= 84) {
            txtnilair2.setText("3");
        } else if (nilai >= 70 && nilai <= 74) {
            txtnilair2.setText("2");
        } else if (nilai < 70) {
            txtnilair2.setText("1");
        } else {
            txtnilair2.setText(""); // Nilai tidak valid, misalnya >99
        }
    } catch (NumberFormatException e) {
        txtnilair2.setText(""); // Kosongkan jika input bukan angka
    }
    }//GEN-LAST:event_txtnilairKeyReleased

    private void txtpresensiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpresensiKeyReleased
       try {
        int nilai = Integer.parseInt(txtpresensi.getText());

        if (nilai == 100) {
            txtnilaipresensi.setText("4");
        } else if (nilai == 90) {
            txtnilaipresensi.setText("3");
        } else if (nilai == 80) {
            txtnilaipresensi.setText("2");
        } else if (nilai == 70) {
            txtnilaipresensi.setText("1");
        } else {
            txtnilaipresensi.setText(""); // Nilai tidak sesuai kriteria
        }
    } catch (NumberFormatException e) {
        txtnilaipresensi.setText(""); // Kosongkan jika input bukan angka
    }
    }//GEN-LAST:event_txtpresensiKeyReleased

    private void txtnilair3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnilair3KeyReleased
       try {
        int nilai = Integer.parseInt(txtnilair3.getText());

        if (nilai >= 85 && nilai <= 95) {
            txtnilair4.setText("4");
        } else if (nilai >= 75 && nilai <= 84) {
            txtnilair4.setText("3");
        } else if (nilai >= 70 && nilai <= 74) {
            txtnilair4.setText("2");
        } else if (nilai < 70) {
            txtnilair4.setText("1");
        } else {
            txtnilair4.setText(""); // Nilai tidak valid, misalnya >99
        }
    } catch (NumberFormatException e) {
        txtnilair4.setText(""); // Kosongkan jika input bukan angka
    }
    }//GEN-LAST:event_txtnilair3KeyReleased

    private void cbsikapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbsikapActionPerformed
       String sikap = cbsikap.getSelectedItem().toString();

    switch (sikap) {
        case "Sangat Baik":
            txtnilaisikap.setText("4");
            break;
        case "Baik":
            txtnilaisikap.setText("3");
            break;
        case "Cukup":
            txtnilaisikap.setText("2");
            break;
        case "Kurang":
            txtnilaisikap.setText("1");
            break;
        default:
            txtnilaisikap.setText(""); // Jika "Pilih Sikap" atau lainnya
            break;
    }
    }//GEN-LAST:event_cbsikapActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private fosalgo.BATAL bBatal;
    private fosalgo.FButtonTambah btTambah;
    private fosalgo.FButtonHapus bthapus;
    private javax.swing.JComboBox<String> cbnama;
    private javax.swing.JComboBox<String> cbsikap;
    private javax.swing.JComboBox<String> cbtahun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelMain;
    private fosalgo.PanelRound panelRound1;
    private fosalgo.PanelRound panelRound2;
    private javax.swing.JPanel panelView;
    private javax.swing.ButtonGroup rbjeniskelamin;
    private javax.swing.JTable tbsiswa;
    private fosalgo.MyTextField txtkode1;
    private fosalgo.MyTextField txtnilaipresensi;
    private fosalgo.MyTextField txtnilair;
    private fosalgo.MyTextField txtnilair2;
    private fosalgo.MyTextField txtnilair3;
    private fosalgo.MyTextField txtnilair4;
    private fosalgo.MyTextField txtnilaisikap;
    private fosalgo.MyTextField txtpresensi;
    // End of variables declaration//GEN-END:variables

}
        
