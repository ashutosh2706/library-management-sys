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
import static librarymanagementsys.util.DBAttributes.BRANCH;
import static librarymanagementsys.util.DBAttributes.COURSE;
import static librarymanagementsys.util.DBAttributes.STUD_DB;
import static librarymanagementsys.util.DBAttributes.STUD_ID;
import static librarymanagementsys.util.DBAttributes.STUD_NAME;
import librarymanagementsys.util.DBConnection;

/**
 *
 * @author Ashutosh
 */
public class ManageStudent extends javax.swing.JFrame {

    /**
     * Creates new form ManageStudent
     */
    public ManageStudent() {
        super("Students Management");
        initComponents();
        fetchStudentDetails();
    }
    
    // fetch student details from db
    String studName, course, branch;
    int studId;
    DefaultTableModel model;
    
    
    public void fetchStudentDetails() {
        
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from " + STUD_DB + ";");
            while(rs.next()) {
                int studId = rs.getInt(STUD_ID);
                String studName = rs.getString(STUD_NAME);
                String course = rs.getString(COURSE);
                String branch = rs.getString(BRANCH);
                
                // Create an object array
                Object[] obj = {studId, studName, course, branch}; 
                
                model = (DefaultTableModel) table_stud_details.getModel();
                // using this model we can add rows fetched from db
                model.addRow(obj);  // pass the object array
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Databse Connection Error");
            e.printStackTrace();
        }
    }
    // add new student details to db
    public boolean addNewStudent() throws NumberFormatException {
        studId = Integer.parseInt(txt_id.getText());
        studName = txt_name.getText();
        course = combo_course.getSelectedItem().toString();
        branch = combo_branch.getSelectedItem().toString();
        if(studName.equals("")) {
            JOptionPane.showMessageDialog(this, "Empty Fields are not allowed");
            return false;
        }
        int rowsAffected = 0;
        try {
            Connection conn = DBConnection.getConnection();
            String query = "Insert into " + STUD_DB + " values (?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, studId);
            pst.setString(2, studName);
            pst.setString(3, course);
            pst.setString(4, branch);
            rowsAffected = pst.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected > 0;
    }
    
    public boolean updateRecord() throws NumberFormatException{
        studId = Integer.parseInt(txt_id.getText());
        studName = txt_name.getText();
        course = combo_course.getSelectedItem().toString();
        branch = combo_branch.getSelectedItem().toString();
        if(studName.equals("")) {
            JOptionPane.showMessageDialog(this, "Empty Fields are not allowed");
            return false;
        }
        int rowsAffected = 0;
        
        try {
            Connection conn = DBConnection.getConnection();
            String query = "update " + STUD_DB + " set " + STUD_NAME + " = ?, " + COURSE + " = ?, " + BRANCH + " = ? where "
                    + STUD_ID + " = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, studName);
            pst.setString(2, course);
            pst.setString(3, branch);
            pst.setInt(4, studId);
            rowsAffected = pst.executeUpdate();
         
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return rowsAffected > 0;
    }
    
    // delete a student
    public boolean deleteRecord() throws NumberFormatException {
        studId = Integer.parseInt(txt_id.getText());
        int rowsAffected = 0;
        Connection conn = DBConnection.getConnection();
        String query = "delete from " + STUD_DB + " where " + STUD_ID + " = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, studId);
            rowsAffected = pst.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return rowsAffected > 0;
        
    }
    
    // clear table
    
    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) table_stud_details.getModel();
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
        jLabel24 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        b1 = new rojerusan.RSMaterialButtonCircle();
        b2 = new rojerusan.RSMaterialButtonCircle();
        b3 = new rojerusan.RSMaterialButtonCircle();
        combo_branch = new javax.swing.JComboBox();
        combo_course = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_stud_details = new rojerusan.RSTableMetro();
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
        jLabel20.setText("Student ID");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 90, 40));

        txt_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_id.setToolTipText("Username");
        txt_id.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_id.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        jPanel1.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 350, 50));

        jLabel22.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Student Name");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 130, 40));

        txt_name.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_name.setToolTipText("Username");
        txt_name.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_name.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel1.add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 350, 50));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/books/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 60, 50));

        jLabel24.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Course");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, 150, 40));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/books/icons8_Unit_26px.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, 60, 50));

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Branch");
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

        combo_branch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        combo_branch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select--", "CSE", "IT", "EE", "EEE", "ETC", "Bio-Tech", "History", "Political Science", "Economics", "BBA", "MBA", "Physics", "Chemistry", "Mathematics" }));
        combo_branch.setBorder(null);
        combo_branch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        combo_branch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_branchActionPerformed(evt);
            }
        });
        jPanel1.add(combo_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 620, 350, 50));

        combo_course.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        combo_course.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select--", "Engineering", "BioTech", "Management", "Humanities", "Science" }));
        combo_course.setBorder(null);
        combo_course.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        combo_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_courseActionPerformed(evt);
            }
        });
        jPanel1.add(combo_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, 350, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 980));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_stud_details.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Name", "Course", "Branch"
            }
        ));
        table_stud_details.setColorBackgoundHead(new java.awt.Color(102, 102, 255));
        table_stud_details.setColorBordeFilas(new java.awt.Color(102, 102, 255));
        table_stud_details.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        table_stud_details.setColorSelBackgound(new java.awt.Color(255, 50, 149));
        table_stud_details.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        table_stud_details.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        table_stud_details.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        table_stud_details.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        table_stud_details.setIntercellSpacing(new java.awt.Dimension(0, 0));
        table_stud_details.setRowHeight(40);
        table_stud_details.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_stud_detailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_stud_details);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 890, 610));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 50, 149));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/books/icons8_Student_Male_100px.png"))); // NOI18N
        jLabel1.setText("  Manage Students");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 440, 100));

        jPanel3.setBackground(new java.awt.Color(255, 50, 149));
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 460, 5));

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
            JOptionPane.showMessageDialog(this, "Student details deleted successfully");
            clearTable();
            fetchStudentDetails();
            } else JOptionPane.showMessageDialog(this, "Error Occurred");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Student ID can't be NULL");
        }
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        try {
            if(addNewStudent()) {
            JOptionPane.showMessageDialog(this, "Student details added successfully");
            clearTable();
            fetchStudentDetails();
            } else JOptionPane.showMessageDialog(this, "Error Occurred");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Student ID can't be NULL");
        }
    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        try {
            if(updateRecord()) {
            JOptionPane.showMessageDialog(this, "Record Updated Successfully");
            clearTable();
            fetchStudentDetails();
            } else JOptionPane.showMessageDialog(this, "Error Occurred");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Student ID can't be NULL");
        }
    }//GEN-LAST:event_b3ActionPerformed

    private void table_stud_detailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_stud_detailsMouseClicked
        int row_no = table_stud_details.getSelectedRow();
        TableModel model = table_stud_details.getModel();
        // get the respective details from model
        txt_id.setText(model.getValueAt(row_no, 0).toString());
        txt_name.setText(model.getValueAt(row_no, 1).toString());
        combo_course.setSelectedItem(model.getValueAt(row_no, 2).toString());
        combo_branch.setSelectedItem(model.getValueAt(row_no, 3).toString());
    }//GEN-LAST:event_table_stud_detailsMouseClicked

    private void combo_branchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_branchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_branchActionPerformed

    private void combo_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_courseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_courseActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

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
            java.util.logging.Logger.getLogger(ManageStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonCircle b1;
    private rojerusan.RSMaterialButtonCircle b2;
    private rojerusan.RSMaterialButtonCircle b3;
    private javax.swing.JComboBox combo_branch;
    private javax.swing.JComboBox combo_course;
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
    private rojerusan.RSTableMetro table_stud_details;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    // End of variables declaration//GEN-END:variables
}
