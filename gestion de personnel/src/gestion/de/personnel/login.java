/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.de.personnel;

import java.awt.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.*;
/**
 *
 * @author aarab
 */
public class login extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    /**
     * Creates new form login
     */
    public login() {
        initComponents();
        Toolkit toolkit=getToolkit();
        Dimension size=toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
        conn= db.java_db();
        currentDate();
    }
    public void currentDate(){
        Calendar cal=new GregorianCalendar();
        int month=cal.get(Calendar.MONTH);
        int year=cal.get(Calendar.YEAR);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        lbl_date.setText(day+"/"+(month+1)+"/"+year);
        int sec=cal.get(Calendar.SECOND);
        int min=cal.get(Calendar.MINUTE);
        int hour=cal.get(Calendar.HOUR);
        lbl_time.setText(hour+":"+min+":"+sec);
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
        txt_username = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        lbl_date = new javax.swing.JMenu();
        lbl_time = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_username);
        txt_username.setBounds(160, 250, 170, 30);

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(250, 330, 80, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mot de passe");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(40, 290, 100, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nom d'utilisateur");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(39, 250, 100, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Entrer SVP votre nom d'utilisateur et votre mot de passe");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 204, 370, 40);

        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });
        jPanel1.add(txt_password);
        txt_password.setBounds(160, 290, 170, 30);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestion/de/personnel/images/bk3.jpg"))); // NOI18N
        jLabel5.setText("k,");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(0, 0, 790, 460);

        jMenu1.setText("Bonjour Admin");
        jMenuBar1.add(jMenu1);

        lbl_date.setText("Date");
        jMenuBar1.add(lbl_date);

        lbl_time.setText("Temps");
        jMenuBar1.add(lbl_time);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String sql="select id,username,password,division from users where (username =? and password = ? and division = 'Admin')";
        try{
            int count=0;
            pst=conn.prepareStatement(sql);
            pst.setString(1,txt_username.getText());
            pst.setString(2,txt_password.getText());
            //pst.setString(3, txt_combo.getSelectedItem().toString());
            rs=pst.executeQuery();
            while(rs.next()){
                String username=rs.getString(2);
                int empId=rs.getInt(1);
                Emp.empUsername=username;
                Emp.empId=empId;
                count=count+1;
            }
            //String access = (txt_combo.getSelectedItem().toString());
            String access = "Admin";
            if(access=="Admin"){
                if(count==1){
                    JOptionPane.showMessageDialog(null, "Succés");
                    MainMenu j=new MainMenu();
                    j.setVisible(true);
                    this.dispose();
                    java.util.Date currentDate = GregorianCalendar.getInstance().getTime();
                    DateFormat df = DateFormat.getDateInstance();
                    String dateString = df.format(currentDate);

                    java.util.Date d = new java.util.Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    String timeString = sdf.format(d);

                    String value0 = timeString;
                    String values = dateString;
                   
                    String valuee=Emp.empUsername;
                    int value = Emp.empId;
                    String reg = "insert into Audit (nom_utilisateur,emp_id,date,etat) values ('"+valuee+"','"+value+"','"+value0+" / "+values+"','Connecté')";
                    pst=conn.prepareStatement(reg);
                    pst.execute();
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe ou bien la section séléctionnée est érroné !");
                }
            }
            else if(access=="Salarié"){
                if(count==1){
                    JOptionPane.showMessageDialog(null, "Succés");
                    MainMenu_user j=new MainMenu_user();
                    j.setVisible(true);
                    this.dispose();
                    java.util.Date currentDate = GregorianCalendar.getInstance().getTime();
                    DateFormat df = DateFormat.getDateInstance();
                    String dateString = df.format(currentDate);

                    java.util.Date d = new java.util.Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    String timeString = sdf.format(d);

                    String value0 = timeString;
                    String values = dateString;
                   
                    String valuee=Emp.empUsername;
                    int value = Emp.empId;
                    String reg = "insert into Audit (nom_utilisateur,emp_id,date,etat) values ('"+valuee+"','"+value+"','"+value0+" / "+values+"','Connecté')";
                    pst=conn.prepareStatement(reg);
                    pst.execute();
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe ou bien la section séléctionnée est érroné !");
                }
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu lbl_date;
    private javax.swing.JMenu lbl_time;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
