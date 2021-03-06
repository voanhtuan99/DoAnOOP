/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Panel;

import static Frame.FrMain.ketNoi;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author NHT
 */
class CountryCellRenderer extends JLabel implements TableCellRenderer
{
    
    public int kt[];
    public static int currentRow = -1;
    
    public CountryCellRenderer(int kt[])
    {
        this.kt=new int[kt.length];
        this.kt=kt;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        if (kt[row] == 0) {
            this.setForeground(Color.YELLOW);
        } else if (kt[row] == 1) {
            this.setForeground(Color.RED);
        } else {
            this.setForeground(Color.GREEN);
        }
        this.setFont(new Font("Tahoma", 0, 18));
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setText(value.toString());
        return this;
    }
}

public class PanelGiaCP extends javax.swing.JPanel {

    /**
     * Creates new form PanelGiaCP
     */
    private String maSan;
    public PanelGiaCP(String maSan) {
        initComponents();
        this.maSan=maSan;
        layDataGiaCP();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane9 = new javax.swing.JScrollPane();
        jTableGiaCP = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        jTableGiaCP.setBackground(new java.awt.Color(0, 0, 0));
        jTableGiaCP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTableGiaCP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "M??CP", "Gi??ThamChi???u","GiaTr???n","Gi??S??n"
            }
        ));
        jTableGiaCP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableGiaCP.setFillsViewportHeight(true);
        jTableGiaCP.setFocusable(false);
        jTableGiaCP.setRowHeight(30);
        jTableGiaCP.setSelectionBackground(new java.awt.Color(255, 0, 51));
        jTableGiaCP.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTableGiaCP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableGiaCPMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTableGiaCP);
        jTableGiaCP.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        jTableGiaCP.getTableHeader().setOpaque(false);
        jTableGiaCP.getTableHeader().setBackground(Color.BLUE);
        jTableGiaCP.getTableHeader().setForeground(new Color(0,0,0));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("B???NG GI?? TR???C TUY???N");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(286, 286, 286)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(570, 570, 570)
                        .addComponent(jLabel9)))
                .addContainerGap(310, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel9)
                .addGap(50, 50, 50)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    public float layBienDoGia() {
        float temp = 0;
        try {

            String sql = "SELECT BienDoGia FROM SanGiaoDIch WHERE MaSan='" + maSan + "'";
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                temp = rs.getFloat(1);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PanelGiaCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    public float layGiaCpHT(String maCP) {
        float giaTC = 0;
        String sql = "SELECT GiaTC FROM GiaThamChieu WHERE MaCP='" + maCP + "' AND NgayGD=CONVERT(DATE,GETDATE()-1)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                giaTC = rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PanelGiaCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return giaTC;
    }

    public void layDataGiaCP() {

        DefaultTableModel dtm = (DefaultTableModel) jTableGiaCP.getModel();
        dtm.setNumRows(0);
        float temp = 0;
        float bienDoGia = layBienDoGia();
        Vector vt;
        Vector<Integer> kt = new Vector<>();
        String sql = "EXEC spLayGiaTC @X = '" + maSan + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getString(1));

                temp = rs.getFloat(2);
                Locale localeVN = new Locale("vi", "VN");
                NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                String str1 = currencyVN.format(temp);

                vt.add(str1);
                str1 = currencyVN.format(temp + (temp * bienDoGia));
                vt.add(str1);
                str1 = currencyVN.format(temp - (temp * bienDoGia));
                vt.add(str1);

                if (layGiaCpHT(rs.getString(1)) > temp) {
                    kt.add(1);
                } else if (layGiaCpHT(rs.getString(1)) < temp) {
                    kt.add(-1);
                } else {
                    kt.add(0);
                }
                dtm.addRow(vt);

            }
            jTableGiaCP.setModel(dtm);

        
            DefaultTableCellRenderer rendar1 = new DefaultTableCellRenderer();
            rendar1.setForeground(new Color(247, 223, 49));
            DefaultTableCellRenderer rendar2 = new DefaultTableCellRenderer();
            rendar2.setForeground(new Color(175, 25, 255));
            DefaultTableCellRenderer rendar3 = new DefaultTableCellRenderer();
            rendar3.setForeground(new Color(102, 204, 255));
            DefaultTableCellRenderer rendar4 = new DefaultTableCellRenderer();
            rendar4.setForeground(Color.RED);
            DefaultTableCellRenderer rendar5 = new DefaultTableCellRenderer();
            rendar5.setForeground(Color.YELLOW);
            DefaultTableCellRenderer rendar6 = new DefaultTableCellRenderer();
            rendar6.setForeground(Color.GREEN);
            
            int a[]=new int[jTableGiaCP.getRowCount()];
            int h=0;
            for(int i:kt)
            {
                a[h++]=i;
            }
            
            jTableGiaCP.setDefaultRenderer(Object.class, new CountryCellRenderer(a));
            jTableGiaCP.getColumnModel().getColumn(1).setCellRenderer(rendar1);
            jTableGiaCP.getColumnModel().getColumn(2).setCellRenderer(rendar2);
            jTableGiaCP.getColumnModel().getColumn(3).setCellRenderer(rendar3);

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(PanelGiaCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void jTableGiaCPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableGiaCPMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableGiaCPMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTableGiaCP;
    // End of variables declaration//GEN-END:variables
}
