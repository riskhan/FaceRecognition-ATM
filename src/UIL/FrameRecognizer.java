/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UIL;

import BLL.FaceDetector;
import BLL.NeuralNet;
import BLL.Util;
import BLL.PreProcess;
import DBL.Customers;
import DBL.CustomersDB;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.VideoInputFrameGrabber;

/**
 *
 * @author User
 */
public class FrameRecognizer extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(FrameRecognizer.class.getName());
    FileHandler fh;
    FrameGrabber grabber;
    IplImage ipimg;
    OpenCVFrameConverter.ToIplImage converter=new OpenCVFrameConverter.ToIplImage();
    BufferedImage bImg,captured,detected,hist;
    File file;
    String name,address;int mobile,acc;float bal,with;
    
    class captureImage implements Runnable{
        protected volatile boolean runn = false;
        protected volatile FrameGrabber gr;
        Util uobj=new Util();
        @Override
        public void run() {
            try
            {
                grabber=new VideoInputFrameGrabber(0);
                grabber.start();
                while(runn)
                {
                    Frame frame=grabber.grab();
                    ipimg=converter.convertToIplImage(frame);
                    if(ipimg!=null)
                    {
                        //cvFlip(ipimg, ipimg, 1);
                        bImg=uobj.ipltoBuffered(ipimg);
                        Graphics g=jPanel1.getGraphics();
                        if (g.drawImage(bImg, 0, 0, getWidth(), getHeight() -150 , 0, 0, bImg.getWidth(), bImg.getHeight(), null))
                        if(runn == false)
                        {
                            System.out.println("Going to wait()");
			    this.wait();
                        }
                    }
                }
            }
            catch(Exception e)
            {
                 e.printStackTrace();
            }
        }    
    }
    /**
     * Creates new form videoFrame
     */
    public FrameRecognizer(){
        initComponents();
        capture();
        try
        {
            fh = new FileHandler(".\\Logger.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter); 
        }
        catch(IOException e)
        {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    private void capture()
    {
        captureImage capt=new captureImage();
        Thread t=new Thread(capt);
        t.setDaemon(true);
        capt.runn=true;
        t.start();
    }
    
    private void openFile()//alternative to real time detection
    {
        final JFrame frame = new JFrame("Select image to be recognized");
        JFileChooser fc=new JFileChooser(".\\trainingset");
        int returnVal = fc.showOpenDialog(frame);
        if(returnVal==JFileChooser.APPROVE_OPTION)
        {
            file=fc.getSelectedFile();
            try
            {
                String filename=file.toString();
            }
            catch(Exception e)
            {
                logger.log(Level.WARNING, "Error in reading file {0}", e);
            }
        }
    }
    
    /**
     * this is the method to recognize the faces
     */
    private void recognizeFaces(BufferedImage newImage)//get the buffered image as parameter
    {
        NeuralNet nnet=new NeuralNet();
        ResultSet rs=null,rs2=null;
        Customers cobj=new Customers();
        double feature[]=new double[80];
        int id=1,pin=0;
        //do the gabor filtering
        //id=nnet.recognizeFaces(feature);//get the recognized id
        pin=Integer.parseInt(txtPIN.getText());
        cobj.setID(id);
        cobj.setPin(pin);
        try
        {
            CustomersDB cdobj=new CustomersDB();
            rs=cdobj.getAccountdetails(cobj);
            rs2=cdobj.getDetails(cobj);
            while(rs.next())
            {
                acc=rs.getInt(2);
                bal=rs.getFloat(5);
                with=rs.getFloat(6);
            }
            while(rs2.next())
            {
                name=rs2.getString(2);
                address=rs2.getString(3);
                mobile=rs2.getInt(4);
            }
            FrameAuthenticate auth=new FrameAuthenticate(name, address, mobile, acc,bal, with);
            auth.show();
            this.dispose();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"cannot retrive values","ERROR",JOptionPane.ERROR_MESSAGE);
            logger.log(Level.WARNING, "Cannot retrive values {0}", e);
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
        btnVerify = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPIN = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        btnFileimage = new javax.swing.JButton();
        btnGotologin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(320, 240));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        btnVerify.setText("VERIFY");
        btnVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("ATM");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("RECOGNIZE YOURSELF");

        txtPIN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPIN.setToolTipText("Enter your account number and verify");
        txtPIN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPINKeyTyped(evt);
            }
        });

        jToolBar1.setRollover(true);

        btnFileimage.setText("File");
        btnFileimage.setFocusable(false);
        btnFileimage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFileimage.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFileimage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileimageActionPerformed(evt);
            }
        });
        jToolBar1.add(btnFileimage);

        btnGotologin.setText("login");
        btnGotologin.setFocusable(false);
        btnGotologin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGotologin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGotologin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGotologinActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGotologin);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtPIN, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(136, 136, 136))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(btnVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jLabel1)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyActionPerformed

        PreProcess pobj=new PreProcess();
        
        if(txtPIN.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Please enter your PIN and try again","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            try 
            {
                FaceDetector obj=new FaceDetector();
                captured=bImg;
                detected=obj.detectFace(captured);
                if(detected==null)
                {
                    JOptionPane.showMessageDialog(null,"Please capture again","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    hist=pobj.histogramEqualization(detected);
                    recognizeFaces(hist);
                }
                
            } 
            catch (HeadlessException e)
            {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }//GEN-LAST:event_btnVerifyActionPerformed

    private void txtPINKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPINKeyTyped

        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter) || enter==KeyEvent.VK_BACK_SPACE || enter==KeyEvent.VK_DELETE || enter==KeyEvent.VK_PERIOD))
        {
            JOptionPane.showMessageDialog(null,"enter number values only");
            evt.consume();
        }
    }//GEN-LAST:event_txtPINKeyTyped

    private void btnFileimageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileimageActionPerformed

        if(txtPIN.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Please enter your PIN and try again","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            try 
            {
                //openFile();
                hist=ImageIO.read(file);
                recognizeFaces(hist);
            } 
            catch (IOException e) 
            {
                logger.log(Level.WARNING, "Error in reading image{0}", e);
            }
        }
    }//GEN-LAST:event_btnFileimageActionPerformed

    private void btnGotologinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGotologinActionPerformed
        FrameLogin obj=new FrameLogin();
        obj.show();
        this.dispose();
    }//GEN-LAST:event_btnGotologinActionPerformed

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
            java.util.logging.Logger.getLogger(FrameRecognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameRecognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameRecognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameRecognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameRecognizer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFileimage;
    private javax.swing.JButton btnGotologin;
    private javax.swing.JButton btnVerify;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField txtPIN;
    // End of variables declaration//GEN-END:variables
}
