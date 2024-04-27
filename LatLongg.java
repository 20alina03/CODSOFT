package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 
*/

public class LatLongg extends javax.swing.JFrame {

    /**
     * Creates new form UIstart
     */
    public LatLongg() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        citynamehere = new javax.swing.JTextField();
        checkbtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        citynamehere1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/background.png"))); // NOI18N
        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 80)); // NOI18N
        jLabel1.setText("Weather ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(220, 50, 460, 68);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 55)); // NOI18N
        jLabel2.setText("ForeCast");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(280, 140, 233, 59);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel3.setText("Enter Latitude");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(500, 260, 190, 32);

        citynamehere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                citynamehereActionPerformed(evt);
            }
        });
        jPanel1.add(citynamehere);
        citynamehere.setBounds(480, 310, 270, 40);

        checkbtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        checkbtn.setText("Check");
        checkbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbtnActionPerformed(evt);
            }
        });
        jPanel1.add(checkbtn);
        checkbtn.setBounds(340, 380, 106, 42);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel4.setText("Enter Longitude");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(50, 260, 190, 32);

        citynamehere1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                citynamehere1ActionPerformed(evt);
            }
        });
        jPanel1.add(citynamehere1);
        citynamehere1.setBounds(30, 310, 250, 40);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(20, 20, 72, 23);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/background.png"))); // NOI18N
        jLabel6.setText("jLabel5");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(-10, 0, 830, 510);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void citynamehereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_citynamehereActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_citynamehereActionPerformed

    private void checkbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbtnActionPerformed
        // TODO add your handling code here:
        URL url = null;
        String lat = citynamehere.getText().trim();
        String lon = citynamehere1.getText().trim();
        CityHolder.latitude=Double.parseDouble(citynamehere.getText());
        
         CityHolder.longitude=Double.parseDouble(citynamehere1.getText());
        
        
        if(!lat.isEmpty()&& !lon.isEmpty())
        {
            try {
        // Create a URL with the latitude and longitude values
        url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=f350f945d01ad5389e7d50882c4e61a5");
            // Read the JSON data from the URL

        
        } catch (MalformedURLException ex) {
            Logger.getLogger(LatLongg.class.getName()).log(Level.SEVERE, null, ex);
        }
        US u = new US();
        String jsonData= u.stream(url);
        JSONParser parser = new JSONParser();
       JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(jsonData);
        } catch (ParseException ex) {
            Logger.getLogger(LatLongg.class.getName()).log(Level.SEVERE, null, ex);
        }
        String temperature = null;
 System.out.println("Temperature: " + temperature + " Kelvin");
        String area = null;
        area = (String)jsonObject.get("name");
            CityHolder.cityName=area;
        System.out.println("Area: " + area);

   
        // Extract the temperature and area information from the JSON data
        JSONObject mainObject = null;
        mainObject = (JSONObject) jsonObject.get("main");
        }
        else
        {
             JOptionPane.showMessageDialog(this, "Please Enter Proper Lat/Long Values", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        options OptionsFrame=new options();
         this.hide();
        OptionsFrame.setVisible(true);
       
    }//GEN-LAST:event_checkbtnActionPerformed

    private void citynamehere1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_citynamehere1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_citynamehere1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        UIstart u= new UIstart();
        u.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LatLongg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LatLongg().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkbtn;
    private javax.swing.JTextField citynamehere;
    private javax.swing.JTextField citynamehere1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    private String stream(URL url) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}

class US{
    
    public String stream(URL url) 
    {
        StringBuilder json = new StringBuilder();
    try (InputStream input = url.openStream()) {
        InputStreamReader isr = new InputStreamReader(input);
        BufferedReader reader = new BufferedReader(isr);
       
        int c;
        while ((c = reader.read()) != -1) {
            json.append((char) c);
        }
        return json.toString();
    }
    catch(IOException e)
    {
        e.printStackTrace();
    }
    return json.toString();

    }
    
}

