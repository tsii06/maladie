/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connex;

import java.sql.Connection;
import java.sql.DriverManager;


public class Connexion {
    public static Connection connect() throws Exception{
        Class.forName("org.postgresql.Driver");
        String url ="jdbc:postgresql://localhost:5432/docteur?characterEncoding=UTF-8";
        String user = "postgres";
        String password = "pronokeys06";
        Connection connex = DriverManager.getConnection(url, user, password);
        return connex;
    }
}
