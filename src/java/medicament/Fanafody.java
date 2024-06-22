/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicament;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

    
public class Fanafody {
    int idfanafody;
    String nom;
    double prixunitaire;

    public int getIdfanafody() {
        return idfanafody;
    }

    public void setIdfanafody(int idfanafody) {
        this.idfanafody = idfanafody;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrixunitaire() {
        return prixunitaire;
    }

    public void setPrixunitaire(double prixunitaire) {
        this.prixunitaire = prixunitaire;
    }
    
    public ArrayList<Fanafody> getFanafody() throws Exception{
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect();
        ArrayList<Fanafody> listA = new ArrayList<>();
       
        try {
            String sql = "select * from fanafody";
            //System.out.println(sql+"\n");
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Fanafody f = new Fanafody();
                f.setIdfanafody(resultSet.getInt("idfanafody"));
                f.setNom(resultSet.getString("nom"));
                f.setPrixunitaire(resultSet.getDouble("prixunitaire"));
                listA.add(f);
            }
            
            resultSet.close();
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listA;
    }
    public Fanafody getFanafodyByNom(String nom) throws Exception{
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect();
        Fanafody f = new Fanafody();
        try {
            String sql = "select * from fanafody where nom = '"+nom.trim()+"'";
            //System.out.println(sql+"\n");
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                
                f.setIdfanafody(resultSet.getInt("idfanafody"));
                f.setNom(resultSet.getString("nom"));
                f.setPrixunitaire(resultSet.getDouble("prixunitaire"));
               
            }
            
            resultSet.close();
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
