/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import Interfaz.Login;
import Interfaz.Estructuras;
import Usuarios.TablaHash;
import Usuarios.Usuario;
import Biblioteca.*;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Alex
 */
public class Proyecto2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        JFrame login = new Login();
        login.show(true);
    }

}
