/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Patient {
    int idPatient;
    String patient;
    Date dateNaissance;

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }
    
    public void insertPatient(String nom,Date date) throws Exception{
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect();
        
        try {
            String sql = "insert into patient values (default,'"+nom+"','"+date+"')";
            System.out.print("sql "+sql+"\n");
            Statement statement = c.createStatement();
            statement.executeUpdate(sql);
        
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Patient> getAllPatient() throws Exception{
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect();
        ArrayList<Patient> listA = new ArrayList<>();
       
        try {
            String sql = "select * from patient";
            System.out.println(sql+"\n");
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Patient p = new Patient();
                p.setIdPatient(resultSet.getInt("idPatient"));
                p.setPatient(resultSet.getString("patient"));
                p.setDateNaissance(resultSet.getDate("dateNaissance"));
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
