/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Age {
    int idage;
    String age;
    int debut;
    int fin;

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut) {
        this.debut = debut;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }
    
    public int getIdage() {
        return idage;
    }

    public void setIdage(int idage) {
        this.idage = idage;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    
    public ArrayList<Age> getAllAge() throws Exception{
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect();
        ArrayList<Age> listA = new ArrayList<>();
       
        try {
            String sql = "select * from age";
            System.out.println(sql+"\n");
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Age age = new Age();
                age.setIdage(resultSet.getInt("idage"));
                age.setAge(resultSet.getString("age"));
                age.setDebut(resultSet.getInt("debut"));
                age.setFin(resultSet.getInt("fin"));
                listA.add(age);
            }
            
            resultSet.close();
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listA;
    }
    
    public int getDateActuel() throws Exception{
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect();
        int actuel = 0;
      
        try {
            String sql = "SELECT EXTRACT(YEAR FROM CURRENT_DATE) AS current_year";
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                actuel = resultSet.getInt("current_year");
            }
            
            resultSet.close();
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return actuel;
    }
    
    
    public int getAnneNaissance(Date dateNaissance) throws Exception {
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect();
        int naissance = 0;
        try {
            String sql = "SELECT extract(year from date '"+dateNaissance+"') AS year";
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            
            while (resultSet.next()) {
                naissance = resultSet.getInt("year");
            }

            resultSet.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return naissance;
    }
    
    public int getAgePatient(Date dateNaissance) throws Exception{
        int naissance = this.getAnneNaissance(dateNaissance);
        int actuel = this.getDateActuel();
        int age = actuel - naissance;
        int idAge = 0;
        
        ArrayList<Age> listAge = this.getAllAge();
        
        for(int i = 0;i<listAge.size();i++){
            if(age >= 30){
               idAge = listAge.get(i).getIdage();
            }else{
                if(listAge.get(i).getDebut() < age && listAge.get(i).getFin() > age){
                    idAge = listAge.get(i).getIdage();
                }
            }
            
        }
        
        return idAge;
    }
}
