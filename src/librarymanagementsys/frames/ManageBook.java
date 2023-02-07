/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsys.frames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import librarymanagementsys.frames.HomePage;
import static librarymanagementsys.util.DBAttributes.BOOK_AUTHOR;
import static librarymanagementsys.util.DBAttributes.BOOK_DB;
import static librarymanagementsys.util.DBAttributes.BOOK_ID;
import static librarymanagementsys.util.DBAttributes.BOOK_NAME;
import static librarymanagementsys.util.DBAttributes.BOOK_QTY;
import librarymanagementsys.util.DBConnection;

/**
 *
 * @author Ashutosh
 */
public class ManageBook extends javax.swing.JFrame {

    /**
     * Creates new form ManageBook
     */
    public ManageBook() {
        super("Books Management");
        initComponents();
        fetchBookDetails();
    }
    
    // fetch book details from db
    String bkName, bkAuthor;
    int bkId, bkQty;
    DefaultTableModel model;
    
    
    public void fetchBookDetails() {
        
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from " + BOOK_DB + ";");
            while(rs.next()) {
                String bookId = rs.getString(BOOK_ID);
                String bookName = rs.getString(BOOK_NAME);
                String bookAuthor = rs.getString(BOOK_AUTHOR);
                int quantity = rs.getInt(BOOK_QTY);
                
                // Create an object array
                Object[] obj = {bookId, bookName, bookAuthor, quantity}; 
                
                model = (DefaultTableModel) table_bk_details.getModel();
                // using this model we can add rows fetched from db
                model.addRow(obj);  // pass the object array
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Databse Connection Error");
            e.printStackTrace();
        }
    }
    // add new book details to db
    public boolean addNewBook() throws NumberFormatException {
        bkId = Integer.parseInt(txt_id.getText());
        bkName = txt_name.getText();
        bkAuthor = txt_author.getText();
        bkQty = Integer.parseInt(txt_qty.getText());
        if(bkName.equals("") || bkAuthor.equals("")) {
            JOptionPane.showMessageDialog(this, "Empty Fields are not allowed");
            return false;
        }
        int rowsAffected = 0;
        try {
            Connection conn = DBConnection.getConnection();
            String query = "Insert into " + BOOK_DB + " values (?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, bkId);
            pst.setString(2, bkName);
            pst.setString(3, bkAuthor);
            pst.setInt(4, bkQty);
            rowsAffected = pst.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected > 0;
    }
    
    public boolean updateRecord() throws NumberFormatException{
        bkId = Integer.parseInt(txt_id.getText());
        bkName = txt_name.getText();
        bkAuthor = txt_author.getText();
        bkQty = Integer.parseInt(txt_qty.getText());
        if(bkName.equals("") || bkAuthor.equals("")) {
            JOptionPane.showMessageDialog(this, "Empty Fields are not allowed");
            return false;
        }
        int rowsAffected = 0;
        
        try {
            Connection conn = DBConnection.getConnection();
            String query = "update " + BOOK_DB + " set " + BOOK_NAME + " = ?, " + BOOK_AUTHOR + " = ?, " + BOOK_QTY + " = ? where "
                    + BOOK_ID + " = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, bkName);
            pst.setString(2, bkAuthor);
            pst.setInt(3, bkQty);
            pst.setInt(4, bkId);
            rowsAffected = pst.executeUpdate();
         
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return rowsAffected > 0;
    }
    
    // delete a book
    public boolean deleteRecord() throws NumberFormatException {
        bkId = Integer.parseInt(txt_id.getText());
        int rowsAffected = 0;
        Connection conn = DBConnection.getConnection();
        String query = "delete from " + BOOK_DB + " where " + BOOK_ID + " = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, bkId);
            rowsAffected = pst.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return rowsAffected > 0;
        
    }
    
    // clear table
    
    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) table_bk_details.getModel();
        model.setRowCount(0);
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
        jPanel16 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txt_author = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txt_qty = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        b1 = new rojerusan.RSMaterialButtonCircle();
        b2 = new rojerusan.RSMaterialButtonCircle();
        b3 = new rojerusan.RSMaterialButtonCircle();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_bk_details = new rojerusan.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(255, 50, 149));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/books/icons8_Rewind_48px.png"))); // NOI18N
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        jPanel16.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 70));

        jPanel1.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 70));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/books/icons8_Contact_26px.png"))); // NOI18N
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 60, 50));

        jLabel20.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Book ID");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 90, 40));

        txt_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_id.setToolTipText("Username");
        txt_id.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_id.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel1.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 350, 50));

        jLabel22.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Book Name");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 130, 40));

        txt_name.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_name.setToolTipText("Username");
        txt_name.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_name.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel1.add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 350, 50));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/books/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 60, 50));

        txt_author.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_author.setToolTipText("Username");
        txt_author.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_author.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel1.add(txt_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, 350, 50));

        jLabel24.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Book Author");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, 150, 40));

        txt_qty.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_qty.setToolTipText("Username");
        txt_qty.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_qty.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel1.add(txt_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 620, 350, 50));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/books/icons8_Unit_26px.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, 60, 50));

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Quantity");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 560, 90, 40));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/books/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 60, 50));

        b1.setBackground(new java.awt.Color(255, 50, 149));
        b1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 10, true));
        b1.setText("Delete");
        b1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        jPanel1.add(b1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 790, 160, 80));

        b2.setBackground(new java.awt.Color(255, 50, 149));
        b2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 10, true));
        b2.setText("ADD");
        b2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        jPanel1.add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 790, 160, 80));

        b3.setBackground(new java.awt.Color(255, 50, 149));
        b3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 10, true));
        b3.setText("Update");
        b3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });
        jPanel1.add(b3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 790, 160, 80));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 980));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_bk_details.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Name", "Author", "Quantity"
            }
        ));
        table_bk_details.setColorBackgoundHead(new java.awt.Color(102, 102, 255));
        table_bk_details.setColorBordeFilas(new java.awt.Color(102, 102, 255));
        table_bk_details.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        table_bk_details.setColorSelBackgound(new java.awt.Color(255, 50, 149));
        table_bk_details.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        table_bk_details.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        table_bk_details.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        table_bk_details.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        table_bk_details.setIntercellSpacing(new java.awt.Dimension(0, 0));
        table_bk_details.setRowHeight(40);
        table_bk_details.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_bk_detailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_bk_details);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 890, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 50, 149));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/books/icons8_Books_52px_1.png"))); // NOI18N
        jLabel1.setText("  Manage Books");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 360, 50));

        jPanel3.setBackground(new java.awt.Color(255, 50, 149));
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 430, 5));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 1270, 980));

        setSize(new java.awt.Dimension(1868, 1027));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        new HomePage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel18MouseClicked

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        try {
            if(deleteRecord()) {
            JOptionPane.showMessageDialog(this, "Books details deleted successfully");
            clearTable();
            fetchBookDetails();
            } else JOptionPane.showMessageDialog(this, "Error Occurred");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Book ID can't be NULL");
        }
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        try {
            if(addNewBook()) {
            JOptionPane.showMessageDialog(this, "Books details added successfully");
            clearTable();
            fetchBookDetails();
            } else JOptionPane.showMessageDialog(this, "Error Occurred");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Book ID or Qty can't be NULL");
        }
    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        try {
            if(updateRecord()) {
            JOptionPane.showMessageDialog(this, "Record Updated Successfully");
            clearTable();
            fetchBookDetails();
            } else JOptionPane.showMessageDialog(this, "Error Occurred");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Book ID or Qty can't be NULL");
        }
    }//GEN-LAST:event_b3ActionPerformed

    private void table_bk_detailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_bk_detailsMouseClicked
        int row_no = table_bk_details.getSelectedRow();
        TableModel model = table_bk_details.getModel();
        // get the respective details from model
        txt_id.setText(model.getValueAt(row_no, 0).toString());
        txt_name.setText(model.getValueAt(row_no, 1).toString());
        txt_author.setText(model.getValueAt(row_no, 2).toString());
        txt_qty.setText(model.getValueAt(row_no, 3).toString());
    }//GEN-LAST:event_table_bk_detailsMouseClicked

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
            java.util.logging.Logger.getLogger(ManageBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonCircle b1;
    private rojerusan.RSMaterialButtonCircle b2;
    private rojerusan.RSMaterialButtonCircle b3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSTableMetro table_bk_details;
    private javax.swing.JTextField txt_author;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_qty;
    // End of variables declaration//GEN-END:variables
}
