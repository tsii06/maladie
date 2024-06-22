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
import patient.Age;


public class V_parametremaladie {
    int idparametremaladie;
    int idmaladie;
    int idage;
    String maladie;
    int idparametre;
    String parametre;
    int debutinter;
    int fininter;

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

    public int getIdage() {
        return idage;
    }

    public void setIdage(int idage) {
        this.idage = idage;
    }

    public String getMaladie() {
        return maladie;
    }

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }

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
    
    public ArrayList<V_parametremaladie> getIntervalleAge(int age,int idMaladie) throws Exception{
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect();
        ArrayList<V_parametremaladie> listA = new ArrayList<>();
        int idage  = this.getIdAge(age);
        try {
            String sql = "select * from v_parametremaladie where idage = "+idage+" and idmaladie = "+idMaladie;
            System.out.println(sql+"\n");
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                V_parametremaladie v = new V_parametremaladie();
                v.setIdparametremaladie(resultSet.getInt("idparametremaladie"));
                v.setDebutinter(resultSet.getInt("debutinter"));
                v.setFininter(resultSet.getInt("fininter"));
                v.setIdage(resultSet.getInt("idage"));
                v.setIdmaladie(resultSet.getInt("idmaladie"));
                v.setIdparametre(resultSet.getInt("idparametre"));
                v.setMaladie(resultSet.getString("maladie"));
                v.setParametre(resultSet.getString("parametre"));
                listA.add(v);
            }
            
            resultSet.close();
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listA;
    }
    
    public int getIdAge(int age) throws Exception{
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect();
        String sql = "select idage from age where debut<= "+age+" and fin>= "+age;
          
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
               return resultSet.getInt("idage");
            }
            return 0;
    }
    
    public ArrayList<V_parametremaladie> getIntervalle(int idMaladie,int age,int idparametre) throws Exception{
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect();
        ArrayList<V_parametremaladie> listA = new ArrayList<>();
       int idage  = this.getIdAge(age);
        try {
            String sql = "select * from v_parametreMaladie where idmaladie = "+idMaladie+" and idage = "+idage+" and idParametre = "+idparametre;
            System.out.println(sql+"\n");
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                V_parametremaladie v = new V_parametremaladie();
                v.setIdparametremaladie(resultSet.getInt("idparametremaladie"));
                v.setDebutinter(resultSet.getInt("debutinter"));
                v.setFininter(resultSet.getInt("fininter"));
                v.setIdage(resultSet.getInt("idage"));
                v.setIdmaladie(resultSet.getInt("idmaladie"));
                v.setIdparametre(resultSet.getInt("idparametre"));
                v.setMaladie(resultSet.getString("maladie"));
                v.setParametre(resultSet.getString("parametre"));
                listA.add(v);
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
