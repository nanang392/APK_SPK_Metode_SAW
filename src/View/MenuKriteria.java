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

public class MenuKriteria extends javax.swing.JPanel {
    private int totalPages;
    
    private final Connection conn;

    private Map<String, Object> parameter;
    
    public MenuKriteria() {
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
        panelMain.add(new MenuKriteria());
        panelMain.repaint();
        panelMain.revalidate();
    }
    private void setTabelModel() {
    DefaultTableModel model = new DefaultTableModel();

    // Menentukan kolom tabel
    model.addColumn("No");
    model.addColumn("KODE");
    model.addColumn("KRITERIA");
    model.addColumn("Wj");
    model.addColumn("BOBOT");
    model.addColumn("ATRIBUT");
    

    // Mengisi data dari database
    try {
        String sql = "SELECT * FROM data_kriteria"; // Ganti dengan nama tabel sesuai database kamu
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        int no = 1; // Nomor urut dimulai dari 1
        while (rs.next()) {
            model.addRow(new Object[] {
                no++,                                
                rs.getString("kode"),                
                rs.getString("kriteria"),  
                rs.getString("wj"),
                rs.getString("bobot"), 
                rs.getString("atribut") 
            });
        }

        // Tampilkan model ke JTable
        tbsiswa.setModel(model);

    } catch (SQLException e) {
        Logger.getLogger(MenuKriteria.class.getName()).log(Level.SEVERE, null, e);
        JOptionPane.showMessageDialog(null, "Gagal mengambil data: " + e.getMessage());
    }
}

private void insertData() {
    String kode = txtkode.getText();
    String kriteria = txtkriteria.getText();
    String wj = txtwj.getText();
    String bobotText = txtbobot.getText();
    String atribut = txtatribut.getText();

    // Validasi input sederhana
    if (kode.isEmpty() || kriteria.isEmpty() || wj.isEmpty() || bobotText.isEmpty() || atribut.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi", "Validasi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        double bobot = Double.parseDouble(bobotText); // Konversi string ke decimal (double)

        String sql = "INSERT INTO data_kriteria (kode, kriteria, wj, bobot, atribut) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, kode);
            st.setString(2, kriteria);
            st.setString(3, wj);
            st.setDouble(4, bobot);
            st.setString(5, atribut);

            int rowInserted = st.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
                resetForm();     // Kosongkan field input (jika ada method ini)
                setTabelModel(); // Refresh data di tabel (jika ada)
            }
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Nilai bobot harus berupa angka desimal (contoh: 0.5)", "Validasi", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        Logger.getLogger(MenuKriteria.class.getName()).log(Level.SEVERE, null, e);
        JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + e.getMessage());
    }
}



private void resetForm() {
    txtkode.setText("");
    txtkriteria.setText("");
    txtwj.setText("");
    txtbobot.setText("");
    txtatribut.setText("");
}

 private void updateData() {
    String kode = txtkode.getText();
    String kriteria = txtkriteria.getText();
    String wj = txtwj.getText();
    String bobot = txtbobot.getText();
    String atribut = txtatribut.getText();

    if (kode.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Pilih data yang ingin diubah dari tabel");
        return;
    }

    String sql = "UPDATE data_kriteria SET kriteria = ?, wj = ?, bobot = ?, atribut = ? WHERE kode = ?";

    try (PreparedStatement st = conn.prepareStatement(sql)) {
        st.setString(1, kriteria);
        st.setString(2, wj);
        st.setString(3, bobot);
        st.setString(4, atribut);
        st.setString(5, kode);

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
        String sql = "DELETE FROM data_kriteria WHERE kode = ?";

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
        txtkriteria = new fosalgo.MyTextField();
        txtwj = new fosalgo.MyTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtatribut = new fosalgo.MyTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtbobot = new fosalgo.MyTextField();

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
        jLabel1.setText("Bobot Kriteria");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pwr.png"))); // NOI18N

        bthapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Cancel_1.png"))); // NOI18N
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

        txtkriteria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtkriteria.setForeground(new java.awt.Color(0, 0, 0));
        txtkriteria.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtwj.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtwj.setForeground(new java.awt.Color(0, 0, 0));
        txtwj.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Wj");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Kriteria");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Kode");

        txtatribut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtatribut.setForeground(new java.awt.Color(0, 0, 0));
        txtatribut.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Atribut");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Bobot");

        txtbobot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtbobot.setForeground(new java.awt.Color(0, 0, 0));
        txtbobot.setHorizontalAlignment(javax.swing.JTextField.CENTER);

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
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelViewLayout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(86, 86, 86)
                            .addComponent(txtatribut, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelViewLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtwj, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(txtkriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelViewLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtbobot, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(40, Short.MAX_VALUE))
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
                    .addComponent(txtkriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtwj, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtbobot, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtatribut, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            txtkriteria.setText(tbsiswa.getValueAt(row, 2).toString());
            txtwj.setText(tbsiswa.getValueAt(row, 3).toString());
            txtbobot.setText(tbsiswa.getValueAt(row, 4).toString());
            txtatribut.setText(tbsiswa.getValueAt(row, 5).toString());
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelView;
    private javax.swing.ButtonGroup rbjeniskelamin;
    private javax.swing.JTable tbsiswa;
    private fosalgo.MyTextField txtatribut;
    private fosalgo.MyTextField txtbobot;
    private fosalgo.MyTextField txtkode;
    private fosalgo.MyTextField txtkriteria;
    private fosalgo.MyTextField txtwj;
    // End of variables declaration//GEN-END:variables

}
        
