package View;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;


public class MenuPerhitungan extends javax.swing.JPanel {
    private int totalPages;
    
    private final Connection conn;

    private Map<String, Object> parameter;
    
    public MenuPerhitungan() {
        initComponents();
        inisialisasiTabelKosong();

        conn = koneksi.getConnection();
        


        setBackground(new Color(0,0,0,0));
        
        tbsiswa.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbsiswa.getTableHeader().setOpaque(false);
        tbsiswa.getTableHeader().setBackground(new Color(32, 136, 203));
        tbsiswa.getTableHeader().setForeground(new Color(255,255,255));
        tbsiswa.setRowHeight(25);
        
        tbsiswa1.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbsiswa1.getTableHeader().setOpaque(false);
        tbsiswa1.getTableHeader().setBackground(new Color(32, 136, 203));
        tbsiswa1.getTableHeader().setForeground(new Color(255,255,255));
        tbsiswa1.setRowHeight(25);
        
        tbsiswa2.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbsiswa2.getTableHeader().setOpaque(false);
        tbsiswa2.getTableHeader().setBackground(new Color(32, 136, 203));
        tbsiswa2.getTableHeader().setForeground(new Color(255,255,255));
        tbsiswa2.setRowHeight(25);
        
        tbsiswa3.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbsiswa3.getTableHeader().setOpaque(false);
        tbsiswa3.getTableHeader().setBackground(new Color(32, 136, 203));
        tbsiswa3.getTableHeader().setForeground(new Color(255,255,255));
        tbsiswa3.setRowHeight(25);
    }
    private void showPanel(){
        panelMain.removeAll();
        panelMain.add(new MenuPerhitungan());
        panelMain.repaint();
        panelMain.revalidate();
    }
    
private void inisialisasiTabelKosong() {
    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("No");
    model.addColumn("KODE");
    model.addColumn("NAMA");
    model.addColumn("TAHUN");
    model.addColumn("C1");
    model.addColumn("C2");
    model.addColumn("C3");
    model.addColumn("C4");

    // Tidak menambahkan baris apa pun → isi tabel tetap kosong
    tbsiswa.setModel(model);
}

private void setTabelModel() {
    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("No");
    model.addColumn("KODE");
    model.addColumn("NAMA");
    model.addColumn("TAHUN");
    model.addColumn("C1");
    model.addColumn("C2");
    model.addColumn("C3");
    model.addColumn("C4");

    String tahunDipilih = (String) cbtahun.getSelectedItem();

    try {
        String sql;
        PreparedStatement pst;

        if (tahunDipilih.equalsIgnoreCase("Semua")) {
            sql = "SELECT * FROM data_nilai ORDER BY tahun_pelajaran ASC";
            pst = conn.prepareStatement(sql);
        } else {
            sql = "SELECT * FROM data_nilai WHERE tahun_pelajaran = ? ORDER BY tahun_pelajaran ASC";
            pst = conn.prepareStatement(sql);
            pst.setString(1, tahunDipilih);
        }

        ResultSet rs = pst.executeQuery();
        int no = 1;
        while (rs.next()) {
            model.addRow(new Object[]{
                no++,
                rs.getString("kode"),
                rs.getString("nama"),
                rs.getString("tahun_pelajaran"),
                rs.getString("bobot_raport"),
                rs.getString("bobot_presensi"),
                rs.getString("bobot_sikap"),
                rs.getString("bobot_ujian")
            });
        }

        tbsiswa.setModel(model);

    } catch (SQLException e) {
        Logger.getLogger(MenuPerhitungan.class.getName()).log(Level.SEVERE, null, e);
        JOptionPane.showMessageDialog(null, "Gagal mengambil data: " + e.getMessage());
    }
}
private void tampilkanRanking() {
    DefaultTableModel modelPreferensi = (DefaultTableModel) tbsiswa2.getModel();
    int rowCount = modelPreferensi.getRowCount();

    // List untuk menyimpan data preferensi
    List<Object[]> dataList = new ArrayList<>();

    for (int i = 0; i < rowCount; i++) {
        String kode = modelPreferensi.getValueAt(i, 1).toString();
        String nama = modelPreferensi.getValueAt(i, 2).toString();
        String tahun = modelPreferensi.getValueAt(i, 3).toString();
        double preferensi = Double.parseDouble(modelPreferensi.getValueAt(i, 8).toString());

        dataList.add(new Object[] { kode, nama, tahun, preferensi });
    }

    // Urutkan data berdasarkan preferensi descending (terbesar ke terkecil)
    dataList.sort((a, b) -> Double.compare((double) b[3], (double) a[3]));

    // Tabel ranking
    DefaultTableModel modelRanking = new DefaultTableModel();
    modelRanking.addColumn("Ranking");
    modelRanking.addColumn("KODE");
    modelRanking.addColumn("NAMA");
    modelRanking.addColumn("TAHUN");
    modelRanking.addColumn("Nilai Preferensi");

    int ranking = 1;
    for (Object[] data : dataList) {
        modelRanking.addRow(new Object[] {
            ranking++,
            data[0], // kode
            data[1], // nama
            data[2], // tahun
            String.format("%.4f", data[3]) // preferensi
        });
    }


    tbsiswa3.setModel(modelRanking);
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbjeniskelamin = new javax.swing.ButtonGroup();
        panelMain = new javax.swing.JPanel();
        panelView = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelRound1 = new fosalgo.PanelRound();
        cbtahun = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        btTambah = new fosalgo.FButtonTambah();
        scrollPane = new javax.swing.JScrollPane();
        tbsiswa = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        panelRound2 = new fosalgo.PanelRound();
        jLabel12 = new javax.swing.JLabel();
        bnormalisasi = new fosalgo.BATAL();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbsiswa1 = new javax.swing.JTable();
        panelRound3 = new fosalgo.PanelRound();
        jLabel14 = new javax.swing.JLabel();
        bhitungpreferensi = new fosalgo.BATAL();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbsiswa2 = new javax.swing.JTable();
        bthapus = new fosalgo.FButtonHapus();
        panelRound4 = new fosalgo.PanelRound();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbsiswa3 = new javax.swing.JTable();
        btsimpan = new fosalgo.FButtonTambah();

        setBackground(new java.awt.Color(153, 255, 153));
        setPreferredSize(new java.awt.Dimension(1026, 716));
        setLayout(new java.awt.CardLayout());

        panelMain.setBackground(new java.awt.Color(255, 255, 255));
        panelMain.setLayout(new java.awt.CardLayout());

        panelView.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Matriks Perhitungan");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new.png"))); // NOI18N

        panelRound1.setBackground(new java.awt.Color(204, 204, 204));
        panelRound1.setRoundBottomLeft(100);
        panelRound1.setRoundTopRight(100);

        cbtahun.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        cbtahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2024/2025", "2025/2026" }));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Tahun Pelajaran");

        btTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        btTambah.setText("TAMPILKAN");
        btTambah.setFillClick(new java.awt.Color(0, 153, 153));
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });

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
        scrollPane.setViewportView(tbsiswa);
        tbsiswa.getAccessibleContext().setAccessibleDescription("");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("1. Matriks Keputusan");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbtahun, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbtahun, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(349, 349, 349))
        );

        panelRound2.setBackground(new java.awt.Color(204, 204, 204));
        panelRound2.setRoundBottomRight(100);
        panelRound2.setRoundTopLeft(100);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("2. Normalisasi");

        bnormalisasi.setText("NORMALISASIKAN");
        bnormalisasi.setFillOver(new java.awt.Color(255, 153, 0));
        bnormalisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnormalisasiActionPerformed(evt);
            }
        });

        tbsiswa1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tbsiswa1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbsiswa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbsiswa1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbsiswa1);

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bnormalisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bnormalisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        panelRound3.setBackground(new java.awt.Color(204, 204, 204));
        panelRound3.setRoundBottomRight(100);
        panelRound3.setRoundTopLeft(100);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("3. Nilai Presensi");

        bhitungpreferensi.setText("HITUNG PREFERENSI");
        bhitungpreferensi.setFillOver(new java.awt.Color(255, 153, 0));
        bhitungpreferensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhitungpreferensiActionPerformed(evt);
            }
        });

        tbsiswa2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tbsiswa2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbsiswa2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbsiswa2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbsiswa2);

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bhitungpreferensi, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(bhitungpreferensi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        bthapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Synchronize.png"))); // NOI18N
        bthapus.setText("BERSIHKAN");
        bthapus.setFillOver(new java.awt.Color(255, 51, 0));
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });

        panelRound4.setBackground(new java.awt.Color(204, 204, 204));
        panelRound4.setRoundBottomLeft(100);
        panelRound4.setRoundTopRight(100);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("4. Ranking");

        tbsiswa3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tbsiswa3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbsiswa3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbsiswa3MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbsiswa3);

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        btsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Download from the Cloud.png"))); // NOI18N
        btsimpan.setText("SIMPAN");
        btsimpan.setFillClick(new java.awt.Color(0, 153, 153));
        btsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelViewLayout = new javax.swing.GroupLayout(panelView);
        panelView.setLayout(panelViewLayout);
        panelViewLayout.setHorizontalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        panelViewLayout.setVerticalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelMain.add(panelView, "card2");

        add(panelMain, "card2");
    }// </editor-fold>//GEN-END:initComponents


    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
    setTabelModel();
    }//GEN-LAST:event_btTambahActionPerformed

    private void tbsiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbsiswaMouseClicked
    

    }//GEN-LAST:event_tbsiswaMouseClicked

    private void bnormalisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnormalisasiActionPerformed

    DefaultTableModel modelSumber = (DefaultTableModel) tbsiswa.getModel();
    int rowCount = modelSumber.getRowCount();

    if (rowCount == 0) {
        JOptionPane.showMessageDialog(null, "Tabel belum diisi. Silakan tampilkan data terlebih dahulu.");
        return;
    }

    // Cari nilai maksimum tiap kolom C1 - C4
    double maxC1 = Double.MIN_VALUE;
    double maxC2 = Double.MIN_VALUE;
    double maxC3 = Double.MIN_VALUE;
    double maxC4 = Double.MIN_VALUE;

    for (int i = 0; i < rowCount; i++) {
        double c1 = Double.parseDouble(modelSumber.getValueAt(i, 4).toString());
        double c2 = Double.parseDouble(modelSumber.getValueAt(i, 5).toString());
        double c3 = Double.parseDouble(modelSumber.getValueAt(i, 6).toString());
        double c4 = Double.parseDouble(modelSumber.getValueAt(i, 7).toString());

        maxC1 = Math.max(maxC1, c1);
        maxC2 = Math.max(maxC2, c2);
        maxC3 = Math.max(maxC3, c3);
        maxC4 = Math.max(maxC4, c4);
    }

    // Buat model baru untuk tabel normalisasi
    DefaultTableModel modelNormalisasi = new DefaultTableModel();
    modelNormalisasi.addColumn("No");
    modelNormalisasi.addColumn("KODE");
    modelNormalisasi.addColumn("NAMA");
    modelNormalisasi.addColumn("TAHUN");
    modelNormalisasi.addColumn("C1");
    modelNormalisasi.addColumn("C2");
    modelNormalisasi.addColumn("C3");
    modelNormalisasi.addColumn("C4");

    // Normalisasi & tampilkan
    int no = 1;
    for (int i = 0; i < rowCount; i++) {
        String kode = modelSumber.getValueAt(i, 1).toString();
        String nama = modelSumber.getValueAt(i, 2).toString();
        String tahun = modelSumber.getValueAt(i, 3).toString();

        double c1 = Double.parseDouble(modelSumber.getValueAt(i, 4).toString());
        double c2 = Double.parseDouble(modelSumber.getValueAt(i, 5).toString());
        double c3 = Double.parseDouble(modelSumber.getValueAt(i, 6).toString());
        double c4 = Double.parseDouble(modelSumber.getValueAt(i, 7).toString());

        double r1 = c1 / maxC1;
        double r2 = c2 / maxC2;
        double r3 = c3 / maxC3;
        double r4 = c4 / maxC4;

        modelNormalisasi.addRow(new Object[]{
            no++, kode, nama, tahun,
            String.format("%.4f", r1),
            String.format("%.4f", r2),
            String.format("%.4f", r3),
            String.format("%.4f", r4)
        });
    }

    // Tampilkan hasil normalisasi ke tabel kanan
    tbsiswa1.setModel(modelNormalisasi);
    JOptionPane.showMessageDialog(null, "Data berhasil dinormalisasikan.");
    
    }//GEN-LAST:event_bnormalisasiActionPerformed

    private void clearAllTables() {
    // Reset isi tabel tabelsiswa
    DefaultTableModel model1 = (DefaultTableModel) tbsiswa.getModel();
    model1.setRowCount(0);

    // Reset isi tabel tabelsiswa1 (normalisasi)
    DefaultTableModel model2 = (DefaultTableModel) tbsiswa1.getModel();
    model2.setRowCount(0);

    // Reset isi tabel tabelsiswa2 (nilai preferensi atau ranking)
    DefaultTableModel model3 = (DefaultTableModel) tbsiswa2.getModel();
    model3.setRowCount(0);
    
    DefaultTableModel model4 = (DefaultTableModel) tbsiswa3.getModel();
    model4.setRowCount(0);

}

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed

        clearAllTables();     
    }//GEN-LAST:event_bthapusActionPerformed

    private void tbsiswa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbsiswa1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbsiswa1MouseClicked

    private void tbsiswa2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbsiswa2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbsiswa2MouseClicked

    private void bhitungpreferensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhitungpreferensiActionPerformed
    DefaultTableModel modelNormalisasi = (DefaultTableModel) tbsiswa1.getModel();
    int rowCount = modelNormalisasi.getRowCount();

    if (rowCount == 0) {
        JOptionPane.showMessageDialog(null, "Data normalisasi belum tersedia. Silakan normalisasikan terlebih dahulu.");
        return;
    }

    // Ambil bobot dari database
    double w1 = 0.0, w2 = 0.0, w3 = 0.0, w4 = 0.0;

    try {
        String sql = "SELECT kode, bobot FROM data_kriteria";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String kode = rs.getString("kode");
            double bobot = rs.getDouble("bobot");

            switch (kode) {
                case "C1": w1 = bobot; break;
                case "C2": w2 = bobot; break;
                case "C3": w3 = bobot; break;
                case "C4": w4 = bobot; break;
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal mengambil bobot dari database: " + e.getMessage());
        return;
    }

    // Model hasil preferensi
    DefaultTableModel modelPreferensi = new DefaultTableModel();
    modelPreferensi.addColumn("No");
    modelPreferensi.addColumn("KODE");
    modelPreferensi.addColumn("NAMA");
    modelPreferensi.addColumn("TAHUN");
    modelPreferensi.addColumn("C1 x " + w1);
    modelPreferensi.addColumn("C2 x " + w2);
    modelPreferensi.addColumn("C3 x " + w3);
    modelPreferensi.addColumn("C4 x " + w4);
    modelPreferensi.addColumn("Preferensi");

    int no = 1;
    for (int i = 0; i < rowCount; i++) {
        String kode = modelNormalisasi.getValueAt(i, 1).toString();
        String nama = modelNormalisasi.getValueAt(i, 2).toString();
        String tahun = modelNormalisasi.getValueAt(i, 3).toString();

        double r1 = Double.parseDouble(modelNormalisasi.getValueAt(i, 4).toString());
        double r2 = Double.parseDouble(modelNormalisasi.getValueAt(i, 5).toString());
        double r3 = Double.parseDouble(modelNormalisasi.getValueAt(i, 6).toString());
        double r4 = Double.parseDouble(modelNormalisasi.getValueAt(i, 7).toString());

        double c1w = r1 * w1;
        double c2w = r2 * w2;
        double c3w = r3 * w3;
        double c4w = r4 * w4;

        double preferensi = c1w + c2w + c3w + c4w;

        modelPreferensi.addRow(new Object[] {
            no++, kode, nama, tahun,
            String.format("%.4f", c1w),
            String.format("%.4f", c2w),
            String.format("%.4f", c3w),
            String.format("%.4f", c4w),
            String.format("%.4f", preferensi)
        });
    }

    tbsiswa2.setModel(modelPreferensi);
    JOptionPane.showMessageDialog(null, "Hitung Preferensi berhasil.");
    tampilkanRanking();
    }//GEN-LAST:event_bhitungpreferensiActionPerformed

    private void tbsiswa3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbsiswa3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbsiswa3MouseClicked

    private void btsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsimpanActionPerformed
    DefaultTableModel modelRanking = (DefaultTableModel) tbsiswa2.getModel();
        int rowCount = modelRanking.getRowCount();

    if (rowCount == 0) {
        JOptionPane.showMessageDialog(null, "Data ranking belum tersedia.");
        return;
    }

    try {
    // Format desimal 4 digit
    DecimalFormat df = new DecimalFormat("0.0000");

    // Urutkan data berdasarkan kolom preferensi secara descending
    List<Object[]> dataList = new ArrayList<>();

    for (int i = 0; i < rowCount; i++) {
        Object[] rowData = new Object[modelRanking.getColumnCount()];
        for (int j = 0; j < modelRanking.getColumnCount(); j++) {
            rowData[j] = modelRanking.getValueAt(i, j);
        }
        dataList.add(rowData);
    }

    // Sort descending berdasarkan nilai preferensi (kolom index ke-8)
    dataList.sort((a, b) -> {
        double prefA = Double.parseDouble(a[8].toString());
        double prefB = Double.parseDouble(b[8].toString());
        return Double.compare(prefB, prefA); // descending
    });

    // Simpan data yang sudah diurutkan ke database
    String sqlInsert = "INSERT INTO data_ranking (kode, nama, tahun, nilai_preferensi, ranking) VALUES (?, ?, ?, ?, ?)";
    PreparedStatement psInsert = conn.prepareStatement(sqlInsert);

    int ranking = 1;
    for (Object[] row : dataList) {
        String kode = row[1].toString();
        String nama = row[2].toString();
        String tahun = row[3].toString();

        // Format nilai preferensi menjadi 4 digit di belakang koma
        double nilaiPreferensi = Double.parseDouble(df.format(Double.parseDouble(row[8].toString())));

        psInsert.setString(1, kode);
        psInsert.setString(2, nama);
        psInsert.setString(3, tahun);
        psInsert.setDouble(4, nilaiPreferensi);
        psInsert.setInt(5, ranking++);

        psInsert.addBatch();
    }

    psInsert.executeBatch();
    JOptionPane.showMessageDialog(null, "Data ranking berhasil disimpan sesuai urutan preferensi.");

} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Gagal menyimpan ranking: " + e.getMessage());
}
    }//GEN-LAST:event_btsimpanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private fosalgo.BATAL bhitungpreferensi;
    private fosalgo.BATAL bnormalisasi;
    private fosalgo.FButtonTambah btTambah;
    private fosalgo.FButtonHapus bthapus;
    private fosalgo.FButtonTambah btsimpan;
    private javax.swing.JComboBox<String> cbtahun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel panelMain;
    private fosalgo.PanelRound panelRound1;
    private fosalgo.PanelRound panelRound2;
    private fosalgo.PanelRound panelRound3;
    private fosalgo.PanelRound panelRound4;
    private javax.swing.JPanel panelView;
    private javax.swing.ButtonGroup rbjeniskelamin;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tbsiswa;
    private javax.swing.JTable tbsiswa1;
    private javax.swing.JTable tbsiswa2;
    private javax.swing.JTable tbsiswa3;
    // End of variables declaration//GEN-END:variables


}
        
