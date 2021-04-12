/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Class.CoPhieu;
import static Frame.FrMain.jPanelMQLCP;
import static Frame.FrMain.ketNoi;
import Panel.PanelNDT;
import Panel.PanelTTCP;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author NHT
 */
public class FrAddCP extends javax.swing.JFrame {

    public int kt=1;

    public CoPhieu layDataCP(String maCP) {
        
        CoPhieu tknh=null;
        Vector vt;
        String sql = "select * from CoPhieu WHERE MaCP='"+maCP+"'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tknh = new CoPhieu(rs.getString("MaCP"), rs.getString("MaSan"), rs.getString("TenCT"), rs.getString("DiaChi"), rs.getString("SDT"), rs.getString("Fax"), rs.getString("DiaChiWeb"), rs.getString("Email"), rs.getInt("SLCoPhieu"),rs.getFloat("GiaNiemYet"));

            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(FrMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tknh;
    }
    /**
     * Creates new form QuanLiNDT
     */
    public FrAddCP() {
        initComponents();
    }

    public FrAddCP(int kt, String maCP) throws HeadlessException {
        this.kt = kt;
        initComponents();
        CoPhieu cp=layDataCP(maCP);
        this.jTextFieldCongTy.setText(cp.getTenCT());
        this.jTextFieldDiaChi.setText(cp.getDiaChi());
        this.jTextFieldDiaChiWeb.setText(cp.getDiachiWed());
        this.jTextFieldEmail.setText(cp.getEmail());
        this.jTextFieldFax.setText(cp.getFax());
        this.jTextFieldMCP.setText(cp.getMaCP());
       
        if("HNX".equals(cp.getMaSan()))
        {
            this.jComboBoxMaSan.setSelectedItem("HNX");
        }
        else
        {
            this.jComboBoxMaSan.setSelectedItem("HSX");
        }
        this.jTextFieldSDT.setText(cp.getSDT());
        this.jTextFieldSoLg.setText(String.valueOf(cp.getSoLg()));
        this.jTextFieldGiaNiemYet.setText(String.valueOf(cp.getGiaNiemYet()));
       
        if(kt==2)
        {
            jTextFieldMCP.setEditable(false);
            jTextFieldGiaNiemYet.setEditable(false);
        }
    }
    
    public void addGiaTC(String maCP,float giaTC)
    {
        String sql = "INSERT INTO GiaThamChieu VALUES('"+maCP+"',CONVERT(DATE,GETDATE()),"+giaTC+")";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void addCoPhieu(CoPhieu cp) {
       
        String sql = "INSERT INTO CoPhieu VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, cp.getMaCP());
            ps.setString(2, cp.getMaSan());
            ps.setString(3, cp.getTenCT());
            ps.setString(4, cp.getDiaChi());
            ps.setString(5,cp.getSDT()) ;
            ps.setString(6, cp.getFax());
            ps.setString(7, cp.getDiachiWed());
            ps.setString(8, cp.getEmail());
            ps.setInt(9, cp.getSoLg());
            ps.setFloat(10, cp.getGiaNiemYet());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FrAddCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        addGiaTC(cp.getMaCP(), cp.getGiaNiemYet());
    }

    public void updateCP(CoPhieu cp) {
       
        String sql = " UPDATE CoPhieu SET MaSan=?,TenCT=?,DiaChi=?,SDT=?,Fax=?,DiaChiWeb=?,Email=?,SLCoPhieu=? WHERE MaCP='" + cp.getMaCP()+ "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, cp.getMaSan());
            ps.setString(2, cp.getTenCT());
            ps.setString(3, cp.getDiaChi());
            ps.setString(4,cp.getSDT()) ;
            ps.setString(5, cp.getFax());
            ps.setString(6, cp.getDiachiWed());
            ps.setString(7, cp.getEmail());
            ps.setInt(8, cp.getSoLg());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FrAddCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean testMaCP()
    {
        boolean tonTai = false;
        String sql = "select * from CoPhieu where MaCP ='" + jTextFieldMCP.getText() + "'" ;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tonTai = true;
            }
            rs.close();
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PanelNDT.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tonTai;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldMCP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldCongTy = new javax.swing.JTextField();
        jTextFieldDiaChi = new javax.swing.JTextField();
        jTextFieldDiaChiWeb = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldSoLg = new javax.swing.JTextField();
        jButtonXN = new javax.swing.JButton();
        jButtonCa = new javax.swing.JButton();
        jTextFieldSDT = new javax.swing.JTextField();
        jTextFieldFax = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jComboBoxMaSan = new javax.swing.JComboBox<>();
        jTextFieldGiaNiemYet = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("Email:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Mã Sàn:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Fax:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("SĐT:");

        jTextFieldMCP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldMCP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 51, 51)));
        jTextFieldMCP.setPreferredSize(new java.awt.Dimension(0, 25));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Địa Chỉ Web:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("Số Lượng Niêm Yết:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Mã Cổ Phiếu:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Tên Công Ty:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Địa Chỉ");

        jTextFieldCongTy.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldCongTy.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 0, 51)));
        jTextFieldCongTy.setPreferredSize(new java.awt.Dimension(0, 25));

        jTextFieldDiaChi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 0, 51)));
        jTextFieldDiaChi.setPreferredSize(new java.awt.Dimension(0, 25));

        jTextFieldDiaChiWeb.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldDiaChiWeb.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 0, 51)));
        jTextFieldDiaChiWeb.setPreferredSize(new java.awt.Dimension(0, 25));

        jTextFieldEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 0, 51)));
        jTextFieldEmail.setPreferredSize(new java.awt.Dimension(0, 25));

        jTextFieldSoLg.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldSoLg.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 0, 51)));
        jTextFieldSoLg.setPreferredSize(new java.awt.Dimension(0, 25));

        jButtonXN.setBackground(new java.awt.Color(0, 0, 0));
        jButtonXN.setForeground(new java.awt.Color(255, 255, 255));
        jButtonXN.setText("Xác Nhận");
        jButtonXN.setBorder(null);
        jButtonXN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXNActionPerformed(evt);
            }
        });

        jButtonCa.setBackground(new java.awt.Color(0, 0, 0));
        jButtonCa.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCa.setText("Cancel");
        jButtonCa.setBorder(null);
        jButtonCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCaActionPerformed(evt);
            }
        });

        jTextFieldSDT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 0, 51)));
        jTextFieldSDT.setPreferredSize(new java.awt.Dimension(0, 25));

        jTextFieldFax.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldFax.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 0, 51)));
        jTextFieldFax.setPreferredSize(new java.awt.Dimension(0, 25));

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("ĐĂNG KÍ CỔ PHIẾU");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(375, 375, 375)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel16)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jComboBoxMaSan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HNX", "HSX" }));
        jComboBoxMaSan.setBorder(null);
        jComboBoxMaSan.setPreferredSize(new java.awt.Dimension(64, 30));

        jTextFieldGiaNiemYet.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldGiaNiemYet.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 0, 51)));
        jTextFieldGiaNiemYet.setOpaque(false);
        jTextFieldGiaNiemYet.setPreferredSize(new java.awt.Dimension(0, 25));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("Giá Niêm Yết");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel10))
                                .addGap(75, 75, 75)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldMCP, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jButtonXN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(55, 55, 55)
                                        .addComponent(jButtonCa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextFieldDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldCongTy, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldFax, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldDiaChiWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldSoLg, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxMaSan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldGiaNiemYet, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(100, Short.MAX_VALUE))))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldMCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jComboBoxMaSan, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldCongTy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldDiaChiWeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldSoLg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldGiaNiemYet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonXN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCaActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCaActionPerformed

    private void jButtonXNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXNActionPerformed
        // TODO add your handling code here:
        if("".equals(jTextFieldCongTy.getText())||"".equals(jTextFieldDiaChi.getText())||"".equals(jTextFieldDiaChiWeb.getText())||"".equals(jTextFieldEmail.getText())||"".equals(jTextFieldFax.getText())||"".equals(jTextFieldMCP.getText())||"".equals(jTextFieldSDT.getText())||"".equals(jTextFieldSoLg.getText()))
        {
            JOptionPane.showMessageDialog(this, "Vui Lòng Điền Đầy Đủ Thông Tin");
            return;
        }
        
        String reEmail="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
        String reSDT="^(\\+?\\d{1,}(\\s?|\\-?)\\d*(\\s?|\\-?)\\(?\\d{2,}\\)?(\\s?|\\-?)\\d{3,}\\s?\\d{3,})$";
        String reMaCP="[a-zA-Z0-9]{3}";
        String reDiaChiWeb="(https?:\\/\\/(www\\.)?|(www\\.))[A-z0-9_-]{3,20}(\\.[A-z]{1,4}){1,2}";
        String reFax="^(\\+?\\d{1,}(\\s?|\\-?)\\d*(\\s?|\\-?)\\(?\\d{2,}\\)?(\\s?|\\-?)\\d{3,}\\s?\\d{3,})$";
        String reNumber="^[0-9]{1,6}$";
        String reFloat="\\-?\\d+\\.\\d+";
        
        if (!jTextFieldMCP.getText().matches(reMaCP)) {
            JOptionPane.showMessageDialog(this, "Mã Cổ Phiếu Phải Có 3 Kí Tự");
            return;
        }
        if (!jTextFieldSDT.getText().matches(reSDT)) {
            JOptionPane.showMessageDialog(this, "SĐT Không Đúng Định Dạng");
            return;
        }
        if (!jTextFieldDiaChiWeb.getText().matches(reDiaChiWeb)) {
            JOptionPane.showMessageDialog(this, "Địa Chỉ Web Không Đúng Định Dạng");
            return;
        }
        if (!jTextFieldEmail.getText().matches(reEmail)) {
            JOptionPane.showMessageDialog(this, "Email Không Đúng Định Dạng");
            return;
        }
        if (!jTextFieldFax.getText().matches(reFax)) {
            JOptionPane.showMessageDialog(this, "Fax Không Đúng Định Dạng");
            return;
        }
        if(!jTextFieldSoLg.getText().matches(reNumber))
        {
            JOptionPane.showMessageDialog(this, "Số Lượng Phải Là Kiểu Số");
            return;
        }
        if(Integer.valueOf(jTextFieldSoLg.getText())<=0)
        {
            JOptionPane.showMessageDialog(this, "Số Lượng Phải > 0");
            return;
        }
       
        
        if(kt==3)
        {
            this.setVisible(false);
        }
        else if(kt==1)
        {
            if (testMaCP()) {
                JOptionPane.showMessageDialog(this, "MaCP Đã Tồn Tại");
                return;
            }
            
            if(!jTextFieldGiaNiemYet.getText().matches(reNumber) && !jTextFieldGiaNiemYet.getText().matches(reFloat))
            {
                 JOptionPane.showMessageDialog(this, "Giá Niêm Yết Không Hợp Lệ");
                  return;
            }
            
            addCoPhieu(new CoPhieu(jTextFieldMCP.getText(), jComboBoxMaSan.getItemAt(jComboBoxMaSan.getSelectedIndex()), jTextFieldCongTy.getText(), jTextFieldDiaChi.getText(), jTextFieldSDT.getText(), jTextFieldFax.getText(), jTextFieldDiaChiWeb.getText(), jTextFieldEmail.getText(), Integer.valueOf(jTextFieldSoLg.getText()),Float.valueOf(jTextFieldGiaNiemYet.getText())));
            System.out.println(jTextFieldMCP.getText());
            JOptionPane.showMessageDialog(this, "SUCCES");
            this.setVisible(false);

            jPanelMQLCP.removeAll();
            jPanelMQLCP.add(new PanelTTCP());
            jPanelMQLCP.repaint();
            jPanelMQLCP.revalidate();
        }
        else
        {
            
            updateCP(new CoPhieu(jTextFieldMCP.getText(), jComboBoxMaSan.getItemAt(jComboBoxMaSan.getSelectedIndex()), jTextFieldCongTy.getText(), jTextFieldDiaChi.getText(), jTextFieldSDT.getText(), jTextFieldFax.getText(), jTextFieldDiaChiWeb.getText(), jTextFieldEmail.getText(), Integer.valueOf(jTextFieldSoLg.getText()),Float.valueOf(jTextFieldGiaNiemYet.getText())));
            JOptionPane.showMessageDialog(this, "SUCCES");
            this.setVisible(false);
           
            jPanelMQLCP.removeAll();
            jPanelMQLCP.add(new PanelTTCP());
            jPanelMQLCP.repaint();
            jPanelMQLCP.revalidate();
        }
    }//GEN-LAST:event_jButtonXNActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrAddCP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrAddCP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrAddCP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrAddCP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrAddCP().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCa;
    private javax.swing.JButton jButtonXN;
    private javax.swing.JComboBox<String> jComboBoxMaSan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextFieldCongTy;
    private javax.swing.JTextField jTextFieldDiaChi;
    private javax.swing.JTextField jTextFieldDiaChiWeb;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFax;
    private javax.swing.JTextField jTextFieldGiaNiemYet;
    private javax.swing.JTextField jTextFieldMCP;
    private javax.swing.JTextField jTextFieldSDT;
    private javax.swing.JTextField jTextFieldSoLg;
    // End of variables declaration//GEN-END:variables
}