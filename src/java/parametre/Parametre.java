/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parametre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Parametre {
    int idparametre;
    String parametre;

    public int getIdparametre() {
        return idparametre;
    }

    public void setIdparametre(int idparametre) {
        this.idparametre = idparametre;
    }

    public String getParametre() {
        return parametre;
    }

    public void setParametre(String parametre) {
        this.parametre = parametre;
    }
    
    public ArrayList<Parametre> getParametres() throws Exception{
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect();
        ArrayList<Parametre> listA = new ArrayList<>();
       
        try {
            String sql = "select * from parametre";
            //System.out.println(sql+"\n");
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Parametre p = new Parametre();
                p.setIdparametre(resultSet.getInt("idparametre"));
                p.setParametre(resultSet.getString("parametre"));
                listA.add(p);
            }
            
            resultSet.close();
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listA;
    }
}
