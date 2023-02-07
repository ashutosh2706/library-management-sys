/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsys.frames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import static librarymanagementsys.util.DBAttributes.BOOK_AUTHOR;
import static librarymanagementsys.util.DBAttributes.BOOK_DB;
import static librarymanagementsys.util.DBAttributes.BOOK_ID;
import static librarymanagementsys.util.DBAttributes.BOOK_NAME;
import static librarymanagementsys.util.DBAttributes.BOOK_QTY;
import static librarymanagementsys.util.DBAttributes.BRANCH;
import static librarymanagementsys.util.DBAttributes.COURSE;
import static librarymanagementsys.util.DBAttributes.DUE_DATE;
import static librarymanagementsys.util.DBAttributes.ISSUE_BOOK_ID;
import static librarymanagementsys.util.DBAttributes.ISSUE_BOOK_NAME;
import static librarymanagementsys.util.DBAttributes.ISSUE_DATE;
import static librarymanagementsys.util.DBAttributes.ISSUE_DB;
import static librarymanagementsys.util.DBAttributes.ISSUE_STATUS;
import static librarymanagementsys.util.DBAttributes.ISSUE_STUD_ID;
import static librarymanagementsys.util.DBAttributes.ISSUE_STUD_NAME;
import static librarymanagementsys.util.DBAttributes.STUD_DB;
import static librarymanagementsys.util.DBAttributes.STUD_ID;
import static librarymanagementsys.util.DBAttributes.STUD_NAME;
import librarymanagementsys.util.DBConnection;

/**
 *
 * @author Ashutosh
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
    public IssueBook() {
        super("Issue Books");
        initComponents();
    }
    
    private void getBookDetails() {
        int bkId = Integer.parseInt(txt_book_id.getText());
        try {
            Connection conn = DBConnection.getConnection();
            String query = "Select * from " + BOOK_DB + " where " + BOOK_ID + " = ?;";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, bkId);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                lb1.setText(rs.getString(BOOK_ID));
                lb2.setText(rs.getString(BOOK_NAME));
                lb3.setText(rs.getString(BOOK_AUTHOR));
                lb4.setText(rs.getString(BOOK_QTY));
            } else {
                lb9.setText("Invalid Book ID!!");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void getStudentDetails() {
        int Id = Integer.parseInt(txt_stud_id.getText());
        try {
            Connection conn = DBConnection.getConnection();
            String query = "Select * from " + STUD_DB + " where " + STUD_ID + " = ?;";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, Id);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                lb5.setText(rs.getString(STUD_ID));
                lb6.setText(rs.getString(STUD_NAME));
                lb7.setText(rs.getString(COURSE));
                lb8.setText(rs.getString(BRANCH));
            } else {
                lb10.setText("Invalid Student ID!!");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean issueBook() {
        int rowsAffected = 0;
        int bookId = Integer.parseInt(txt_book_id.getText());
        int studId = Integer.parseInt(txt_stud_id.getText());
        String bkName = lb2.getText();
        String studName = lb6.getText();
        Date issueDt = issuedt.getDatoFecha();
        Date dueDt = duedt.getDatoFecha();
        
        // convert these util dates to sql dates
        Long l1 = issueDt.getTime();
        Long l2 = dueDt.getTime();
        java.sql.Date sissueDt = new java.sql.Date(l1);
        java.sql.Date sdueDt = new java.sql.Date(l2);
        
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "insert into " + ISSUE_DB + "(" + ISSUE_BOOK_ID + ", " + ISSUE_BOOK_NAME + ", " +
                    ISSUE_STUD_ID + ", " + ISSUE_STUD_NAME + ", " + ISSUE_DATE + ", " + DUE_DATE + ", " +
                    ISSUE_STATUS + ") values (?,?,?,?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setString(2, bkName);
            pst.setInt(3, studId);
            pst.setString(4, studName);
            pst.setDate(5, sissueDt);
            pst.setDate(6, sdueDt);
            pst.setString(7, "pending");
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected > 0;
    }
    
    private void updateBookCount() {
        int rowsAffected = 0;
        int id = Integer.parseInt(txt_book_id.getText());
        try {
            Connection conn = DBConnection.getConnection();
            String query = "update " + BOOK_DB + " set quantity=quantity-1 where " + BOOK_ID + " = ?;";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            rowsAffected = pst.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(rowsAffected > 0) {
            int qty = Integer.parseInt(lb4.getText());
            lb4.setText(Integer.toString(qty-1));
        } else JOptionPane.showMessageDialog(this, "Error Occurred");
    }
    
    public boolean isAlreadyIssued() {
        boolean b = false;
        int bookId = Integer.parseInt(txt_book_id.getText());
        int studId = Integer.parseInt(txt_stud_id.getText());
        try {
            Connection conn = DBConnection.getConnection();
            String query = "select * from " + ISSUE_DB + " where " + ISSUE_BOOK_ID + " = ? and " + ISSUE_STUD_ID + " = ? and " + 
                    ISSUE_STATUS + " = ?;";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, bookId);
            pst.setInt(2, studId);
            pst.setString(3, "pending");
            ResultSet rs = pst.executeQuery();
            if(rs.next()) b = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return b;
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
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        issuedt = new rojeru_san.componentes.RSDateChooser();
        duedt = new rojeru_san.componentes.RSDateChooser();
        b2 = new rojerusan.RSMaterialButtonCircle();
        jLabel8 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lb3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lb9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lb1 = new javax.swing.JLabel();
        lb4 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_book_id = new javax.swing.JTextField();
        txt_stud_id = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lb8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lb10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lb5 = new javax.swing.JLabel();
        lb6 = new javax.swing.JLabel();
        lb7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 50, 149));
        jPanel6.setForeground(new java.awt.Color(255, 50, 149));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 165, 380, 5));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 50, 149));
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/books/icons8_Books_52px_1.png"))); // NOI18N
        jLabel27.setText(" Issue Book");
        jPanel7.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 60, 330, 120));

        jPanel12.setBackground(new java.awt.Color(255, 50, 149));
        jPanel12.setForeground(new java.awt.Color(255, 50, 149));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 165, 380, 5));

        issuedt.setColorBackground(new java.awt.Color(255, 50, 149));
        issuedt.setColorForeground(new java.awt.Color(255, 50, 149));
        issuedt.setPlaceholder("Issue Date");
        jPanel7.add(issuedt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 570, 350, 50));

        duedt.setColorBackground(new java.awt.Color(255, 50, 149));
        duedt.setColorForeground(new java.awt.Color(255, 50, 149));
        duedt.setPlaceholder("Due Date");
        jPanel7.add(duedt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 690, 350, 50));

        b2.setBackground(new java.awt.Color(0, 204, 0));
        b2.setText("Issue Book");
        b2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        jPanel7.add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 820, 460, 90));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 50, 149));
        jLabel8.setText("Due Date :");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 690, 160, 50));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 50, 149));
        jLabel31.setText("Book ID :");
        jPanel7.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 280, 120, 50));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 50, 149));
        jLabel32.setText("Student ID :");
        jPanel7.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 420, 160, 50));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 50, 149));
        jLabel33.setText("Issue Date :");
        jPanel7.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 570, 160, 50));

        jPanel4.setBackground(new java.awt.Color(255, 50, 149));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBackground(new java.awt.Color(102, 102, 255));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/books/icons8_Rewind_48px.png"))); // NOI18N
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        jPanel17.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 60));

        jPanel4.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 60));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/books/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel10.setText("   Book Details");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 280, 120));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 320, 5));

        lb3.setBackground(new java.awt.Color(255, 255, 255));
        lb3.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lb3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 590, 210, 50));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Book Name :");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 120, 50));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Author :");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, 80, 50));

        lb9.setBackground(new java.awt.Color(255, 255, 255));
        lb9.setFont(new java.awt.Font("Yu Gothic Medium", 1, 20)); // NOI18N
        lb9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(lb9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 810, 230, 50));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Book ID :");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 90, 50));

        lb1.setBackground(new java.awt.Color(255, 255, 255));
        lb1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lb1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 210, 50));

        lb4.setBackground(new java.awt.Color(255, 255, 255));
        lb4.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lb4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 690, 210, 50));

        lb2.setBackground(new java.awt.Color(255, 255, 255));
        lb2.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lb2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 480, 210, 50));

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Quantity :");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 690, 90, 50));

        jPanel7.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 980));

        txt_book_id.setBackground(new java.awt.Color(204, 204, 204));
        txt_book_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_book_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_book_id.setToolTipText("");
        txt_book_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 50, 149)));
        txt_book_id.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_book_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_book_idFocusLost(evt);
            }
        });
        txt_book_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_book_idActionPerformed(evt);
            }
        });
        jPanel7.add(txt_book_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 280, 350, 50));

        txt_stud_id.setBackground(new java.awt.Color(204, 204, 204));
        txt_stud_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_stud_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_stud_id.setToolTipText("");
        txt_stud_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 50, 149)));
        txt_stud_id.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_stud_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_stud_idFocusLost(evt);
            }
        });
        txt_stud_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stud_idActionPerformed(evt);
            }
        });
        jPanel7.add(txt_stud_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 420, 350, 50));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 320, 5));

        lb8.setBackground(new java.awt.Color(255, 255, 255));
        lb8.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lb8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lb8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 680, 210, 50));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Student Name :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 150, 50));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Course :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, 80, 50));

        lb10.setBackground(new java.awt.Color(255, 255, 255));
        lb10.setFont(new java.awt.Font("Yu Gothic Medium", 1, 20)); // NOI18N
        lb10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(lb10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 810, 230, 50));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Student ID :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 120, 50));

        lb5.setBackground(new java.awt.Color(255, 255, 255));
        lb5.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lb5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lb5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 210, 50));

        lb6.setBackground(new java.awt.Color(255, 255, 255));
        lb6.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lb6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lb6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, 210, 50));

        lb7.setBackground(new java.awt.Color(255, 255, 255));
        lb7.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lb7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lb7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 590, 210, 50));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/books/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel3.setText("   Student Details");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 330, 120));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Branch :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 690, 90, 50));

        jPanel7.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 450, 980));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1850, 980));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1850, 980));

        setSize(new java.awt.Dimension(1868, 1027));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        new HomePage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void txt_stud_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stud_idActionPerformed

    }//GEN-LAST:event_txt_stud_idActionPerformed

    private void txt_book_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_book_idActionPerformed

    }//GEN-LAST:event_txt_book_idActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        if(lb4.getText().equals("0")) {
            JOptionPane.showMessageDialog(this, "Book is out of stock");
            return;
        }
        
        if(!isAlreadyIssued()) {
            if(issueBook()) { JOptionPane.showMessageDialog(this, "Book Issued"); updateBookCount(); }
            else JOptionPane.showMessageDialog(this, "Book issue failed!");
        } else {
            JOptionPane.showMessageDialog(this, "Book already issued");
        }
    }//GEN-LAST:event_b2ActionPerformed

    private void txt_book_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_book_idFocusLost
        if(!txt_book_id.getText().equals("")) getBookDetails();
    }//GEN-LAST:event_txt_book_idFocusLost

    private void txt_stud_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_stud_idFocusLost
        if(!txt_stud_id.getText().equals("")) getStudentDetails();
    }//GEN-LAST:event_txt_stud_idFocusLost

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
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonCircle b2;
    private rojeru_san.componentes.RSDateChooser duedt;
    private rojeru_san.componentes.RSDateChooser issuedt;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb10;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JLabel lb5;
    private javax.swing.JLabel lb6;
    private javax.swing.JLabel lb7;
    private javax.swing.JLabel lb8;
    private javax.swing.JLabel lb9;
    private javax.swing.JTextField txt_book_id;
    private javax.swing.JTextField txt_stud_id;
    // End of variables declaration//GEN-END:variables
}
