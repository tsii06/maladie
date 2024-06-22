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


public class IntervalleAge {
    int idintervalleage;
    int idparametremaladie;
    int idage;
    int debutinter;
    int fininter;

    public int getIdintervalleage() {
        return idintervalleage;
    }

    public void setIdintervalleage(int idintervalleage) {
        this.idintervalleage = idintervalleage;
    }

    public int getIdparametremaladie() {
        return idparametremaladie;
    }

    public void setIdparametremaladie(int idparametremaladie) {
        this.idparametremaladie = idparametremaladie;
    }

    public int getIdage() {
        return idage;
    }

    public void setIdage(int idage) {
        this.idage = idage;
    }

    public int getDebutinter() {
        return debutinter;
    }

    public void setDebutinter(int debutinter) {
        this.debutinter = debutinter;
    }

    public int getFininter() {
        return fininter;
    }

    public void setFininter(int fininter) {
        this.fininter = fininter;
    }
    
    /*public ArrayList<IntervalleAge> getIntervalle(int idParam) throws Exception{
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect("docteur");
        ArrayList<IntervalleAge> listA = new ArrayList<>();
       
        try {
            String sql = "select * from intervalleAge where idparametre = "+idParam;
            System.out.println(sql+"\n");
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                
                listA.add(p);
            }
            
            resultSet.close();
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listA;
    }*/

    
}
