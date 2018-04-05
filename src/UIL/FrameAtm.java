/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UIL;

import DBL.Customers;
import DBL.CustomersDB;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class FrameAtm extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(FrameAtm.class.getName());
    FileHandler fh;
    int id,pin;
    float val;
    String bl;
    String name,address;int mobile,acc;float bal,with;
    /**
     * Creates new form FrameAtm
     */
    public FrameAtm() {
        initComponents();
        try
        {
            fh = new FileHandler(".\\Loggers\\Logger.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter); 
        }
        catch(IOException e)
        {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        id=1;
        pin=1234;
        generateBalance();
        generateDetails();
    }
    
    public FrameAtm(int id,int pin) {
        initComponents();
        try
        {
            fh = new FileHandler(".\\Loggers\\Logger.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter); 
        }
        catch(IOException e)
        {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        jLabel6.setText(date);
        this.id=id;
        this.pin=pin;
        generateBalance();
        generateDetails();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        btnWith = new javax.swing.JButton();
        labelName = new javax.swing.JLabel();
        btnViewdetails = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnDeposit = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ABC Bank ATM");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("WELCOME");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("TOTAL BALANCE");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 0));
        jLabel3.setText("5000.0");

        txtAmount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtAmount.setText("00.0");
        txtAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAmountKeyTyped(evt);
            }
        });

        btnWith.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnWith.setText("Withdraw");
        btnWith.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWithActionPerformed(evt);
            }
        });

        labelName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelName.setText("Mohamed Arshad");

        btnViewdetails.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnViewdetails.setText("View details");
        btnViewdetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewdetailsActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnDeposit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDeposit.setText("Deposit Cash");
        btnDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("05-04-2018");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/blue3.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(jLabel1))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(275, 275, 275)
                .addComponent(jLabel3))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(522, 522, 522)
                .addComponent(btnViewdetails))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(522, 522, 522)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(btnWith, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnDeposit))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(jLabel2))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(labelName))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jLabel3))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(btnViewdetails, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(278, 278, 278)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addComponent(btnWith, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(btnDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jLabel2))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(labelName))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnWithActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWithActionPerformed
       
        if(txtAmount.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter amount","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if(Float.parseFloat(txtAmount.getText())>val||Float.parseFloat(txtAmount.getText())==val)
        {
            JOptionPane.showMessageDialog(null,"Select amount less than balance","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if(Float.parseFloat(txtAmount.getText())==0)
        {
            JOptionPane.showMessageDialog(null,"Invalid amount","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            float txt=Float.parseFloat(txtAmount.getText());
            CustomersDB obj=new CustomersDB();
            Customers cobj=new Customers();
            cobj.setAccountBal(txt);
            cobj.setID(id);
            int row=0;
            try
            {
                row=obj.widthDrawmoney(cobj);
                if(row>0)
                {
                    generateBalance();
                    JOptionPane.showMessageDialog(null,"Await for cash","Success",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Error withdrawing","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(HeadlessException e)
            {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }//GEN-LAST:event_btnWithActionPerformed

    private void txtAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmountKeyTyped
       
         
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter) || enter==KeyEvent.VK_BACK_SPACE || enter==KeyEvent.VK_DELETE || enter==KeyEvent.VK_PERIOD))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtAmountKeyTyped

    private void btnViewdetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewdetailsActionPerformed
        
        generateBalance();
        generateDetails();
        FrameAuthenticate obj=new FrameAuthenticate(name,address, mobile, acc, bal, with);
        //FrameAuthenticate obj=FrameAuthenticate.getInstance("xxx",address, mobile, acc, bal, with);
        obj.show();
    }//GEN-LAST:event_btnViewdetailsActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        FrameRecognizer obj=new FrameRecognizer();
        obj.show();
        this.dispose();
        /*FrameNewcustomer obj=new FrameNewcustomer();
        obj.show();
        this.dispose();*/
        
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositActionPerformed

        if(txtAmount.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter amount","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if(Float.parseFloat(txtAmount.getText())==0||Float.parseFloat(txtAmount.getText())<500||
                Float.parseFloat(txtAmount.getText())>80000)
        {
            JOptionPane.showMessageDialog(null,"Invalid amount","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            float txt=Float.parseFloat(txtAmount.getText());
            CustomersDB obj=new CustomersDB();
            Customers cobj=new Customers();
            cobj.setAccountBal(txt);
            cobj.setID(id);
            int row=0;
            try
            {
                row=obj.depositMoney(cobj);
                if(row>0)
                {
                    generateBalance();
                    JOptionPane.showMessageDialog(null,"Cash deposited","Success",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Try depositing again","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(HeadlessException e)
            {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        
    }//GEN-LAST:event_btnDepositActionPerformed

    private void generateBalance()
    {
        ResultSet rs=null;
        String x="";
        try
        {
            CustomersDB obj=new CustomersDB();
            Customers cobj=new Customers();
            cobj.setPin(pin);
            cobj.setID(id);
            rs=obj.getAccountdetails(cobj);
            while(rs.next())
            {
                val=rs.getFloat(5);
                x=String.valueOf(val);
                acc=rs.getInt(2);
                bal=val;
                with=rs.getFloat(6);
            }
            jLabel3.setText(x);
            bl=x;
        }
        catch(SQLException e)
        {
            logger.log(Level.SEVERE, e.getMessage(), e);
            JOptionPane.showMessageDialog(null,"cannot retrive balance","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
     private void generateDetails()
    {
        ResultSet rs=null;
        String x="";
        try
        {
            CustomersDB obj=new CustomersDB();
            Customers cobj=new Customers();
            cobj.setPin(pin);
            cobj.setID(id);
            rs=obj.getDetails(cobj);
            while(rs.next())
            {
                labelName.setText(rs.getString(2));
                name=rs.getString(2);
                address=rs.getString(3);
                mobile=rs.getInt(4);
            }
        }
        catch(SQLException e)
        {
            logger.log(Level.SEVERE, e.getMessage(), e);
            JOptionPane.showMessageDialog(null,"cannot retrive balance","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
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
            java.util.logging.Logger.getLogger(FrameAtm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameAtm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameAtm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameAtm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameAtm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeposit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnViewdetails;
    private javax.swing.JButton btnWith;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelName;
    private javax.swing.JTextField txtAmount;
    // End of variables declaration//GEN-END:variables
}
