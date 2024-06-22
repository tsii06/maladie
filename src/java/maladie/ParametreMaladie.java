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

public class ParametreMaladie {
    int idparametremaladie;
    int idmaladie;
    int idparametre;

    public int getIdparametremaladie() {
        return idparametremaladie;
    }

    public void setIdparametremaladie(int idparametremaladie) {
        this.idparametremaladie = idparametremaladie;
    }

    public int getIdmaladie() {
        return idmaladie;
    }

    public void setIdmaladie(int idmaladie) {
        this.idmaladie = idmaladie;
    }

    public int getIdparametre() {
        return idparametre;
    }

    public void setIdparametre(int idparametre) {
        this.idparametre = idparametre;
    }
    
    public ArrayList<ParametreMaladie> getParamMaladie(int idMaladie) throws Exception{
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect();
        ArrayList<ParametreMaladie> listA = new ArrayList<>();
       
        try {
            String sql = "select * from parametremaladie where idmaladie = "+idMaladie;
            //System.out.println(sql+"\n");
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                ParametreMaladie p = new ParametreMaladie();
                p.setIdmaladie(resultSet.getInt("idmaladie"));
                p.setIdparametre(resultSet.getInt("idparametre"));
                p.setIdparametremaladie(resultSet.getInt("idparametremaladie"));
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
