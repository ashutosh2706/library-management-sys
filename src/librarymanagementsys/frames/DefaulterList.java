/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsys.frames;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static librarymanagementsys.util.DBAttributes.DUE_DATE;
import static librarymanagementsys.util.DBAttributes.ISSUE_BOOK_NAME;
import static librarymanagementsys.util.DBAttributes.ISSUE_DATE;
import static librarymanagementsys.util.DBAttributes.ISSUE_DB;
import static librarymanagementsys.util.DBAttributes.ISSUE_STATUS;
import static librarymanagementsys.util.DBAttributes.ISSUE_STUD_NAME;
import librarymanagementsys.util.DBConnection;

/**
 *
 * @author Ashutosh
 */
public class DefaulterList extends javax.swing.JFrame {

    /**
     * Creates new form DefaulterList
     */
    DefaultTableModel model;
    public DefaulterList() {
        super("Defaulter's List");
        initComponents();
        fetchDefaulterDetails();
    }
    
    public void fetchDefaulterDetails() {
        
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from " + ISSUE_DB + " where " + ISSUE_STATUS + " = 'pending';");
            //if(!rs.next()) { JOptionPane.showMessageDialog(this, "No Record Found!!"); return; }
            while(rs.next()) {
                String Id = rs.getString("id");
                String studName = rs.getString(ISSUE_STUD_NAME);
                String bkName = rs.getString(ISSUE_BOOK_NAME);
                String issue1 = rs.getString(ISSUE_DATE);
                String due1 = rs.getString(DUE_DATE);
                String status = rs.getString(ISSUE_STATUS);
                
                // Create an object array
                Object[] obj = {Id, bkName, studName, issue1, due1, status}; 
                
                model = (DefaultTableModel) table_defaulter_details.getModel();
                // using this model we can add rows fetched from db
                model.addRow(obj);  // pass the object array
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Databse Connection Error");
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_defaulter_details = new rojerusan.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 50, 149));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/books/icons8_Books_52px_1.png"))); // NOI18N
        jLabel1.setText(" Defaulter's List");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 330, 110));

        jPanel2.setBackground(new java.awt.Color(255, 50, 149));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 450, 5));

        jPanel17.setBackground(new java.awt.Color(102, 102, 255));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/books/icons8_Rewind_48px.png"))); // NOI18N
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        jPanel17.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 50, 50));

        jPanel1.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 50));

        table_defaulter_details.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Book Name", "Student", "Issued On", "Due Date", "Status"
            }
        ));
        table_defaulter_details.setColorBackgoundHead(new java.awt.Color(102, 102, 255));
        table_defaulter_details.setColorBordeFilas(new java.awt.Color(102, 102, 255));
        table_defaulter_details.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        table_defaulter_details.setColorSelBackgound(new java.awt.Color(255, 50, 149));
        table_defaulter_details.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        table_defaulter_details.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        table_defaulter_details.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        table_defaulter_details.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        table_defaulter_details.setIntercellSpacing(new java.awt.Dimension(0, 0));
        table_defaulter_details.setRowHeight(40);
        table_defaulter_details.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_defaulter_detailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_defaulter_details);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 1200, 410));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1376, 654));

        setSize(new java.awt.Dimension(1394, 701));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        new HomePage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void table_defaulter_detailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_defaulter_detailsMouseClicked

    }//GEN-LAST:event_table_defaulter_detailsMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DefaulterList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DefaulterList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DefaulterList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DefaulterList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DefaulterList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSTableMetro table_defaulter_details;
    // End of variables declaration//GEN-END:variables
}
