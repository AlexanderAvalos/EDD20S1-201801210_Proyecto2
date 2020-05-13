/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Interfaz.Estructuras;
import Usuarios.NodoTabla;
import Usuarios.TablaHash;
import Usuarios.Usuario;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Alex
 */
public class Login extends javax.swing.JFrame {

    public static int carnet;
    Estructuras estructuras = new Estructuras();
    public JFrame log;
    JSONParser parser = new JSONParser();

    public Login() {
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

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        textField2 = new java.awt.TextField();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        jButton3 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(java.awt.Color.lightGray);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Crear Cuenta");
        jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel1MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel1MouseMoved(evt);
            }
        });
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        jButton2.setText("C.A");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        textField2.setName(""); // NOI18N
        textField2.setText("carnet");
        textField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField2ActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        label1.setText("Usuario");

        label2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        label2.setText("Contraseña");

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton3.setText("Iniciar Sesion");
        jButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPasswordField1.setText("password");
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPasswordField1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String aux = "";
        String texto = "";
        JFileChooser file = new JFileChooser("../Documents/");
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileFilter filtro = new FileNameExtensionFilter("Archivos Json (*.json)", "json");
        file.setFileFilter(filtro);
        file.showOpenDialog(this);
        File archivo = file.getSelectedFile();
        if (archivo != null) {
            if (archivo.getName().equals("usuarios.json")) {
                try {
                    leerUsuario(archivo);
                } catch (IOException ex) {
                    System.out.println("error");
                } catch (ParseException ex) {
                    System.out.println("error");
                }
            } else if (archivo.getName().equals("Libros.json")) {
                try {
                    leerLibro(archivo);
                } catch (IOException ex) {
                    System.out.println("error");
                } catch (ParseException ex) {
                    System.out.println("error");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error de carga");
            }
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {
            if (jPasswordField1 != null && textField2 != null) {
                int carnet = Integer.valueOf(textField2.getText());
                String pass = jPasswordField1.getText();
                int id = Estructuras.getTablahash().posicionTabla(carnet);
                Usuario user = Estructuras.getTablahash().Tabla[id].lst.buscar(carnet);
                if (user.getCarnet() == carnet) {
                    if (user.getPass().equals(pass)) {
                        JOptionPane.showMessageDialog(null, "Bienvenido " + user.getNombre());
                        Login.carnet = user.getCarnet();
                        Menu menu = new Menu();
                        menu.show(true);
                        this.hide();
                    } else {
                        JOptionPane.showMessageDialog(null, "credeciales incorrectas");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "credeciales incorrectas");
                }

            } else {
                JOptionPane.showMessageDialog(null, "llene campos");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Llene campos");
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        AgregarUser nuevo = new AgregarUser();
        nuevo.show();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed

    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseMoved
        jLabel1.setForeground(Color.BLACK);
    }//GEN-LAST:event_jLabel1MouseMoved

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged

    }//GEN-LAST:event_jLabel1MouseDragged

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        jLabel1.setForeground(Color.darkGray);
    }//GEN-LAST:event_jLabel1MouseExited

    private void textField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField2ActionPerformed

    }//GEN-LAST:event_textField2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed
    private void leerLibro(File archivo) throws FileNotFoundException, IOException, ParseException {
        JSONObject libro = new JSONObject();
        libro = (JSONObject) parser.parse(new FileReader(archivo.getPath()));
        JSONArray books = (JSONArray) libro.get("libros");
        for (Object obj : books) {
            if (obj != null) {
                JSONObject item = (JSONObject) obj;
                Long bn = new Long((long) item.get("ISBN"));
                int isbn = bn.intValue();
                Long añ = new Long((long) item.get("Año"));
                int año = añ.intValue();
                String idioma = (String) item.get("Idioma");
                String titulo = (String) item.get("Titulo");
                String editorial = (String) item.get("Editorial");
                String autor = (String) item.get("Autor");
                Long edi = new Long((long) item.get("Edicion"));
                int edicion = edi.intValue();
                String categoria = (String) item.get("Categoria");
                System.out.println(isbn);
            }
        }

    }

    private void leerUsuario(File archivo) throws FileNotFoundException, IOException, ParseException {

        JSONObject libro = new JSONObject();
        libro = (JSONObject) parser.parse(new FileReader(archivo.getPath()));
        JSONArray books = (JSONArray) libro.get("Usuarios");
        for (Object obj : books) {
            if (obj != null) {
                JSONObject item = (JSONObject) obj;
                Long bn = new Long((long) item.get("Carnet"));
                int carnet = bn.intValue();
                String nombre = (String) item.get("Nombre");
                String apellido = (String) item.get("Apellido");
                String carrera = (String) item.get("Carrera");
                String pass = (String) item.get("Password");
                int id = Estructuras.getTablahash().posicionTabla(carnet);
                Usuarios.Usuario nuevo = new Usuario(carnet, nombre, apellido, carrera, pass);
                Estructuras.getTablahash().insertar(id, nuevo);
                System.out.println(carnet + " " + pass);
            }
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.TextField textField2;
    // End of variables declaration//GEN-END:variables
}
