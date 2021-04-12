/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Class.LuuKy;
import Class.TKNH;
import static Frame.FrMain.ketNoi;
import Panel.PanelNDT;
import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NHT
 */
public class FrDetailNDT extends javax.swing.JFrame {

    /**
     * Creates new form FrDetailNDT
     */
    private String maTK;
    public FrDetailNDT() {
        initComponents();
        layDataLK();
        layDataTKNH();
        layDataNDT();
    }
    public FrDetailNDT(String maTK) {
        initComponents();
        this.maTK=maTK;
        layDataLK();
        layDataTKNH();
        layDataNDT();
    }
    
    public int laySoLgCPHienCo(String maTK,String maCP)
    {
        int temp=0;
        String sql = "SELECT SoLg FROM SoHuu WHERE MaTK='"+ maTK+ "' AND MaCP='"+maCP+"'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                temp=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
    
    public int laySoLgCoPhieuDangBan(String maTK,String maCP)
    {
        int temp=0;
        String sql = "SELECT SUM(SLDat)\n" +
                    "FROM (SELECT *FROM LenhDat WHERE MaCP='"+maCP+"' AND CONVERT(DATE,NgayGioDat) >= CONVERT(DATE,GETDATE()) "
                    + "AND LoaiGD='Ban' AND (MaTrangThai!='CHK' OR MaTrangThai!='DH')) LD \n" +
                    "INNER JOIN (SELECT *FROM TKNH WHERE MaTK= '"+maTK+"') TK \n" +
                    "ON LD.MaTKNH = TK.MaTKNH ";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                temp=temp+rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
    
     public float laySoTienDangMua(String maTKNH)
    {
        float soTienDat=0;
        String sql = "SELECT SUM(SLDat*GiaDat) FROM LenhDat WHERE MaTKNH='"+maTKNH+"' AND LoaiGD='Mua' AND CONVERT(DATE,NgayGioDat) >= CONVERT(DATE,GETDATE()) "
                + "AND (MaTrangThai!='CHK' OR MaTrangThai!='DH') " ;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                soTienDat=rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soTienDat;
    }
    
    public float laySoTienHienCo(String maTKNH)
    {
        float soTienDangCo = 0;
        String sql = "SELECT SoTien FROM TKNH WHERE MaTKNH='"+maTKNH+"'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                soTienDangCo=rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soTienDangCo;
    }
    
    public void layDataLK() {

        DefaultTableModel dtm = (DefaultTableModel) jTableLuuKy.getModel();
        dtm.setNumRows(0);

       
        Vector vt;
        String sql = "select * from SoHuu WHERE MaTK='"+maTK+"'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LuuKy cp = new LuuKy(rs.getString("MaTK"), rs.getString("MaCP"), rs.getInt("SoLg"));
                vt = new Vector();
                vt.add(cp.getMaTK());
                vt.add(cp.getMaCP());
                vt.add(cp.getSoLg());
                vt.add(laySoLgCPHienCo(maTK, cp.getMaCP())-laySoLgCoPhieuDangBan(maTK, cp.getMaCP()));
                dtm.addRow(vt);
            }
            jTableLuuKy.setModel(dtm);
            rs.close();
            ps.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(FrDetailNDT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void layDataTKNH() {

        DefaultTableModel dtm = (DefaultTableModel) jTableTKNN.getModel();
        dtm.setNumRows(0);
        
      
        String sql = "select * from TKNH WHERE MaTK='"+maTK+"'";
        Vector vt;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TKNH tknh = new TKNH(rs.getString("MaTK"), rs.getString("MaTKNH"), rs.getString("MaNH"), rs.getFloat("SoTien"));

                vt = new Vector();
                vt.add(tknh.getMaTK());
                vt.add(tknh.getMaTKNN());
                vt.add(tknh.getMaNH());

                Locale localeVN = new Locale("vi", "VN");
                NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                String str1 = currencyVN.format(tknh.getTien());
                vt.add(str1);
                
                str1 = currencyVN.format(laySoTienHienCo(tknh.getMaTKNN())-laySoTienDangMua(tknh.getMaTKNN()));
                vt.add(str1);
                
                dtm.addRow(vt);
            }
            jTableTKNN.setModel(dtm);
            rs.close();
            ps.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(FrDetailNDT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void layDataNDT() {

       
        String sql = "select * from NhaDauTu WHERE MaTK='"+maTK+"'";
        Vector vt;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               
                jTextFieldMaTk.setText(rs.getString("MaTK"));
                jTextFieldHo.setText(rs.getString("Ho"));
                jTextFieldTen.setText(rs.getString("Ten"));
                jTextFieldNS.setText(rs.getString("NgaySinh"));
                jTextFieldNoiSinh.setText(rs.getString("NoiSinh"));
                jTextFielPhai.setText(rs.getString("Phai"));
                jTextFieldDiaChi.setText(rs.getString("DiaChi"));
                jTextFieldEmail.setText(rs.getString("Email"));
                jTextFieldSDT.setText(rs.getString("SDT"));
                jTextFieldCMND.setText(rs.getString("CMND"));
                jTextFieldNC.setText(rs.getString("NgayCap"));
                jTextFieldNoiC.setText(rs.getString("NoiCap"));
                jTextFieldTrNgNc.setText(rs.getString("TrNgNc"));
            }
            rs.close();
            ps.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(FrDetailNDT.class.getName()).log(Level.SEVERE, null, ex);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelTTNDT = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldMaTk = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldHo = new javax.swing.JTextField();
        jTextFieldTrNgNc = new javax.swing.JTextField();
        jTextFieldNoiC = new javax.swing.JTextField();
        jTextFieldCMND = new javax.swing.JTextField();
        jTextFieldNC = new javax.swing.JTextField();
        jTextFieldNoiSinh = new javax.swing.JTextField();
        jTextFielPhai = new javax.swing.JTextField();
        jTextFieldDiaChi = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldSDT = new javax.swing.JTextField();
        jTextFieldTen = new javax.swing.JTextField();
        jTextFieldNS = new javax.swing.JTextField();
        jPanelTKNH = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableTKNN = new javax.swing.JTable();
        jPanelLK = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableLuuKy = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanelTTNDT.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Mã Tài Khoản:");

        jTextFieldMaTk.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jTextFieldMaTk.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        jTextFieldMaTk.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Họ Và Tên Lót:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Tên:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Ngày Sinh:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Nơi Sinh:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Phái:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Địa Chỉ:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Email:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("SĐT:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("CMND:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Ngày Cấp:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Nơi Cấp:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Trong Ngoài Nước:");

        jTextFieldHo.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jTextFieldHo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        jTextFieldHo.setOpaque(false);

        jTextFieldTrNgNc.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jTextFieldTrNgNc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        jTextFieldTrNgNc.setOpaque(false);

        jTextFieldNoiC.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jTextFieldNoiC.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        jTextFieldNoiC.setOpaque(false);

        jTextFieldCMND.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jTextFieldCMND.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        jTextFieldCMND.setOpaque(false);

        jTextFieldNC.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jTextFieldNC.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        jTextFieldNC.setOpaque(false);

        jTextFieldNoiSinh.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jTextFieldNoiSinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        jTextFieldNoiSinh.setOpaque(false);

        jTextFielPhai.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jTextFielPhai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        jTextFielPhai.setOpaque(false);

        jTextFieldDiaChi.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jTextFieldDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        jTextFieldDiaChi.setOpaque(false);

        jTextFieldEmail.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jTextFieldEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        jTextFieldEmail.setOpaque(false);

        jTextFieldSDT.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jTextFieldSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        jTextFieldSDT.setOpaque(false);

        jTextFieldTen.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jTextFieldTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        jTextFieldTen.setOpaque(false);

        jTextFieldNS.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jTextFieldNS.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        jTextFieldNS.setOpaque(false);

        javax.swing.GroupLayout jPanelTTNDTLayout = new javax.swing.GroupLayout(jPanelTTNDT);
        jPanelTTNDT.setLayout(jPanelTTNDTLayout);
        jPanelTTNDTLayout.setHorizontalGroup(
            jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTTNDTLayout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelTTNDTLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(147, 147, 147)
                        .addComponent(jTextFieldNoiC, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTTNDTLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(133, 133, 133)
                        .addComponent(jTextFieldNC))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTTNDTLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(160, 160, 160)
                        .addComponent(jTextFieldCMND))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTTNDTLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(175, 175, 175)
                        .addComponent(jTextFieldSDT))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTTNDTLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(167, 167, 167)
                        .addComponent(jTextFieldEmail))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTTNDTLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(152, 152, 152)
                        .addComponent(jTextFieldDiaChi))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTTNDTLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(176, 176, 176)
                        .addComponent(jTextFielPhai))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTTNDTLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(143, 143, 143)
                        .addComponent(jTextFieldNoiSinh))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTTNDTLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(129, 129, 129)
                        .addComponent(jTextFieldNS))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTTNDTLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(179, 179, 179)
                        .addComponent(jTextFieldTen))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTTNDTLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(64, 64, 64)
                        .addComponent(jTextFieldTrNgNc))
                    .addGroup(jPanelTTNDTLayout.createSequentialGroup()
                        .addGroup(jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(94, 94, 94)
                        .addGroup(jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldMaTk, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                            .addComponent(jTextFieldHo))))
                .addGap(6, 6, 6))
        );
        jPanelTTNDTLayout.setVerticalGroup(
            jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTTNDTLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelTTNDTLayout.createSequentialGroup()
                        .addGroup(jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelTTNDTLayout.createSequentialGroup()
                                .addGroup(jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldMaTk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addComponent(jTextFieldHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addComponent(jTextFieldNS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldNoiSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFielPhai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldNC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextFieldNoiC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTTNDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTrNgNc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông Tin Nhà Đầu Tư", jPanelTTNDT);

        jPanelTKNH.setBackground(new java.awt.Color(255, 255, 255));

        jTableTKNN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableTKNN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MãTK", "MãTKNN","Tên Ngân Hàng","Số Tiền","Số Tiền Thực Tế"
            }
        ));
        jTableTKNN.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableTKNN.setFillsViewportHeight(true);
        jTableTKNN.setFocusable(false);
        jTableTKNN.setRowHeight(30);
        jTableTKNN.setSelectionBackground(new java.awt.Color(255, 0, 0));
        jTableTKNN.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTableTKNN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTKNNMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableTKNN);
        jTableTKNN.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        jTableTKNN.getTableHeader().setOpaque(false);
        jTableTKNN.getTableHeader().setBackground(Color.BLUE);
        jTableTKNN.getTableHeader().setForeground(new Color(0,0,0));

        javax.swing.GroupLayout jPanelTKNHLayout = new javax.swing.GroupLayout(jPanelTKNH);
        jPanelTKNH.setLayout(jPanelTKNHLayout);
        jPanelTKNHLayout.setHorizontalGroup(
            jPanelTKNHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTKNHLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanelTKNHLayout.setVerticalGroup(
            jPanelTKNHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTKNHLayout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tài Khoản Ngân Hàng", jPanelTKNH);

        jPanelLK.setBackground(new java.awt.Color(255, 255, 255));

        jTableLuuKy.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableLuuKy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MãTK", "MãCP","SốLượng","SLThựcTế"
            }
        ));
        jTableLuuKy.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableLuuKy.setFillsViewportHeight(true);
        jTableLuuKy.setRowHeight(30);
        jTableLuuKy.setSelectionBackground(new java.awt.Color(255, 0, 51));
        jTableLuuKy.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTableLuuKy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLuuKyMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableLuuKy);
        jTableLuuKy.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        jTableLuuKy.getTableHeader().setOpaque(false);
        jTableLuuKy.getTableHeader().setBackground(Color.BLUE);
        jTableLuuKy.getTableHeader().setForeground(new Color(0,0,0));

        javax.swing.GroupLayout jPanelLKLayout = new javax.swing.GroupLayout(jPanelLK);
        jPanelLK.setLayout(jPanelLKLayout);
        jPanelLKLayout.setHorizontalGroup(
            jPanelLKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLKLayout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanelLKLayout.setVerticalGroup(
            jPanelLKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLKLayout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lưu Ký", jPanelLK);

        jPanel4.setBackground(new java.awt.Color(255, 102, 0));

        jLabel14.setFont(new java.awt.Font("Consolas", 2, 24)); // NOI18N
        jLabel14.setText("CHI TIẾT NHÀ ĐẦU TƯ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(402, 402, 402))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableLuuKyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLuuKyMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableLuuKyMouseClicked

    private void jTableTKNNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTKNNMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTableTKNNMouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

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
//            java.util.logging.Logger.getLogger(FrDetailNDT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrDetailNDT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrDetailNDT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrDetailNDT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrDetailNDT().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelLK;
    private javax.swing.JPanel jPanelTKNH;
    private javax.swing.JPanel jPanelTTNDT;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableLuuKy;
    private javax.swing.JTable jTableTKNN;
    private javax.swing.JTextField jTextFielPhai;
    private javax.swing.JTextField jTextFieldCMND;
    private javax.swing.JTextField jTextFieldDiaChi;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldHo;
    private javax.swing.JTextField jTextFieldMaTk;
    private javax.swing.JTextField jTextFieldNC;
    private javax.swing.JTextField jTextFieldNS;
    private javax.swing.JTextField jTextFieldNoiC;
    private javax.swing.JTextField jTextFieldNoiSinh;
    private javax.swing.JTextField jTextFieldSDT;
    private javax.swing.JTextField jTextFieldTen;
    private javax.swing.JTextField jTextFieldTrNgNc;
    // End of variables declaration//GEN-END:variables
}
