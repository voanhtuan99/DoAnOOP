/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Panel;

import Frame.FrMain;
import static Frame.FrMain.ketNoi;
import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NHT
 */
public class PanelLL extends javax.swing.JPanel {

    /**
     * Creates new form PanelGiaCP
     */
    public PanelLL() {
        initComponents();
        layDataLLenh();
    }

      public void layDataLLenh() {
        DefaultTableModel dtm = (DefaultTableModel) jTableLL.getModel();
        dtm.setNumRows(0);
       
        Vector vt;
        String sql = "select * from LoaiLenh";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getString("MaLoaiLenh"));
                vt.add(rs.getString("DienGiai"));
                vt.add(rs.getString("ThoiGian"));
                dtm.addRow(vt);
            }
            jTableLL.setModel(dtm);
            rs.close();
            ps.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(FrMain.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane9 = new javax.swing.JScrollPane();
        jTableLL = new javax.swing.JTable();
        jButtonBackCP = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        jTableLL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableLL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MãLoạiLệnh", "MôTả","GiờBắtĐầu"
            }
        ));
        jTableLL.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableLL.setFillsViewportHeight(true);
        jTableLL.setFocusable(false);
        jTableLL.setRowHeight(30);
        jTableLL.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        jTableLL.getTableHeader().setOpaque(false);
        jTableLL.getTableHeader().setBackground(Color.BLUE);
        jTableLL.getTableHeader().setForeground(new Color(0,0,0));
        jTableLL.setSelectionBackground(new java.awt.Color(255, 51, 51));
        jTableLL.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTableLL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLLMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTableLL);

        jButtonBackCP.setText("Back");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("LOẠI LỆNH");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 302, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(298, 298, 298))
            .addGroup(layout.createSequentialGroup()
                .addGap(648, 648, 648)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonBackCP, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(714, 714, 714))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel9)
                .addGap(50, 50, 50)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jButtonBackCP)
                .addContainerGap(81, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTableLLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLLMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableLLMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBackCP;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTableLL;
    // End of variables declaration//GEN-END:variables
}
