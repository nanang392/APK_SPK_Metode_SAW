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

public class MenuAlternatif extends javax.swing.JPanel {
    private int totalPages;
    
    private final Connection conn;
    String namaasrama;
    String Kelas;
    String kelas;
    String no;
    String noruangan;
    String walikelas;
    private Map<String, Object> parameter;
    
    public MenuAlternatif() {
        initComponents();
        
        conn = koneksi.getConnection();
        setTabelModel();
        
        setBackground(new Color(0,0,0,0));
        
        tbsiswa.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbsiswa.getTableHeader().setOpaque(false);
        tbsiswa.getTableHeader().setBackground(new Color(32, 136, 203));
        tbsiswa.getTableHeader().setForeground(new Color(255,255,255));
        tbsiswa.setRowHeight(25);
    }
    private void showPanel(){
        panelMain.removeAll();
        panelMain.add(new MenuAlternatif());
        panelMain.repaint();
        panelMain.revalidate();
    }
    private void setTabelModel() {
    DefaultTableModel model = new DefaultTableModel();

    // Menentukan kolom tabel
    model.addColumn("No");
    model.addColumn("KODE");
    model.addColumn("NAMA ALTERNATIF");
    model.addColumn("NISN");

    // Mengisi data dari database
    try {
        String sql = "SELECT * FROM data_alternatif"; // Ganti dengan nama tabel sesuai database kamu
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        int no = 1; // Nomor urut dimulai dari 1
        while (rs.next()) {
            model.addRow(new Object[] {
                no++,                                // Kolom No (nomor urut)
                rs.getString("kode"),                // Kolom Kode
                rs.getString("alternatif"),          // Kolom Nama Alternatif
                rs.getString("nisn")                 // Kolom NISN
            });
        }

        // Tampilkan model ke JTable
        tbsiswa.setModel(model);

    } catch (SQLException e) {
        Logger.getLogger(MenuAlternatif.class.getName()).log(Level.SEVERE, null, e);
        JOptionPane.showMessageDialog(null, "Gagal mengambil data: " + e.getMessage());
    }
}

private void insertData() {
    String kode = txtkode.getText();
    String alternatif = txtnama.getText();
    String nisn = txtnisn.getText();

    // Validasi input sederhana
    if (kode.isEmpty() || alternatif.isEmpty() || nisn.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi", "Validasi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        String sql = "INSERT INTO data_alternatif (kode, alternatif, nisn) VALUES (?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, kode);
            st.setString(2, alternatif);
            st.setString(3, nisn);

            int rowInserted = st.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
                resetForm();     // Opsional: Kosongkan field input
                setTabelModel(); // Refresh tabel
            }
        }
    } catch (SQLException e) {
        Logger.getLogger(MenuAlternatif.class.getName()).log(Level.SEVERE, null, e);
        JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + e.getMessage());
    }
}


private void resetForm() {
    txtkode.setText("");
    txtnama.setText("");
    txtnisn.setText("");
}

   private void updateData() {
    String kode = txtkode.getText();
    String alternatif = txtnama.getText();
    String nisn = txtnisn.getText();

    if (kode.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Pilih data yang ingin diubah dari tabel");
        return;
    }

    String sql = "UPDATE data_alternatif SET alternatif = ?, nisn = ? WHERE kode = ?";

    try (PreparedStatement st = conn.prepareStatement(sql)) {
        st.setString(1, alternatif);
        st.setString(2, nisn);
        st.setString(3, kode);

        int rows = st.executeUpdate();
        if (rows > 0) {
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate");
            resetForm();
            setTabelModel();
        } else {
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal update: " + e.getMessage());
    }
}


    private void deleteData() {
    String kode = txtkode.getText();

    if (kode.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus dari tabel");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        String sql = "DELETE FROM data_alternatif WHERE kode = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, kode);
            st.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
            resetForm();
            setTabelModel();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus: " + e.getMessage());
        }
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        txtkode = new fosalgo.MyTextField();
        txtnama = new fosalgo.MyTextField();
        txtnisn = new fosalgo.MyTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

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
        jLabel1.setText("Data Alternatif");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usr.png"))); // NOI18N

        bthapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Trash.png"))); // NOI18N
        bthapus.setText("HAPUS");
        bthapus.setFillOver(new java.awt.Color(255, 51, 0));
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });

        btTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        btTambah.setText("TAMBAH");
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

        txtkode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtkode.setForeground(new java.awt.Color(0, 0, 0));
        txtkode.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtnama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtnama.setForeground(new java.awt.Color(0, 0, 0));
        txtnama.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtnisn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtnisn.setForeground(new java.awt.Color(0, 0, 0));
        txtnisn.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("NISN");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Nama Alternatif");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Kode");

        javax.swing.GroupLayout panelViewLayout = new javax.swing.GroupLayout(panelView);
        panelView.setLayout(panelViewLayout);
        panelViewLayout.setHorizontalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelViewLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelViewLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnisn, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelViewLayout.createSequentialGroup()
                            .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelViewLayout.createSequentialGroup()
                                    .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        panelViewLayout.setVerticalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtnisn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelMain.add(panelView, "card2");

        add(panelMain, "card2");
    }// </editor-fold>//GEN-END:initComponents


    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
    insertData();
    }//GEN-LAST:event_btTambahActionPerformed

    private void tbsiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbsiswaMouseClicked
    int row = tbsiswa.getSelectedRow();
        if (row >= 0) {
            txtkode.setText(tbsiswa.getValueAt(row, 1).toString());
            txtnama.setText(tbsiswa.getValueAt(row, 2).toString());
            txtnisn.setText(tbsiswa.getValueAt(row, 3).toString());
        }
    }//GEN-LAST:event_tbsiswaMouseClicked

    private void bBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBatalActionPerformed
    updateData();
    }//GEN-LAST:event_bBatalActionPerformed

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed
     deleteData();
    }//GEN-LAST:event_bthapusActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private fosalgo.BATAL bBatal;
    private fosalgo.FButtonTambah btTambah;
    private fosalgo.FButtonHapus bthapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelView;
    private javax.swing.ButtonGroup rbjeniskelamin;
    private javax.swing.JTable tbsiswa;
    private fosalgo.MyTextField txtkode;
    private fosalgo.MyTextField txtnama;
    private fosalgo.MyTextField txtnisn;
    // End of variables declaration//GEN-END:variables

}
        
