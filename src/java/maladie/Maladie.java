/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maladie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Maladie {
    int idmaladie;
    String maladie;

    public int getIdmaladie() {
        return idmaladie;
    }

    public void setIdmaladie(int idmaladie) {
        this.idmaladie = idmaladie;
    }

    public String getMaladie() {
        return maladie;
    }

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }
    
    public ArrayList<Maladie> getAllMaladie() throws Exception{
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect();
        ArrayList<Maladie> listA = new ArrayList<>();
       
        try {
            String sql = "select * from maladie";
            //System.out.println(sql+"\n");
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Maladie m = new Maladie();
                m.setIdmaladie(resultSet.getInt("idmaladie"));
                m.setMaladie(resultSet.getString("maladie"));
                listA.add(m);
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
