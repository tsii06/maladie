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


public class V_fanafodyParametre {
    int idfanafodyparametre;
    int idfanafody;
    int idparametre;
    double niveau;
    String nom;
    double prixunitaire;

    public int getIdfanafodyparametre() {
        return idfanafodyparametre;
    }

    public void setIdfanafodyparametre(int idfanafodyparametre) {
        this.idfanafodyparametre = idfanafodyparametre;
    }

    public int getIdfanafody() {
        return idfanafody;
    }

    public void setIdfanafody(int idfanafody) {
        this.idfanafody = idfanafody;
    }

    public int getIdparametre() {
        return idparametre;
    }

    public void setIdparametre(int idparametre) {
        this.idparametre = idparametre;
    }

    public double getNiveau() {
        return niveau;
    }

    public void setNiveau(double niveau) {
        this.niveau = niveau;
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
    
    public ArrayList<V_fanafodyParametre> getFanafodyParametre(int idParametre) throws Exception{
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect();
        ArrayList<V_fanafodyParametre> listA = new ArrayList<>();
       
        try {
            String sql = "select * from v_fanafodyParametre where idParametre  = "+idParametre;
            //System.out.println(sql+"\n");
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                V_fanafodyParametre v = new V_fanafodyParametre();
                v.setIdfanafody(resultSet.getInt("idfanafody"));
                v.setIdfanafodyparametre(resultSet.getInt("idfanafodyparametre"));
                v.setIdparametre(resultSet.getInt("idParametre"));
                v.setNiveau(resultSet.getDouble("niveau"));
                v.setNom(resultSet.getString("nom"));
                v.setPrixunitaire(resultSet.getDouble("prixunitaire"));
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
