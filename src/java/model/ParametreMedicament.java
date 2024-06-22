/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class ParametreMedicament extends  Medicament{
       Vector<ParametrePatient> parametre_patient = new  Vector<ParametrePatient>();
       int quantite_necessaire;
       double prixTotal;

    public String getMedicament_id() {
        return medicament_id;
    }

    public void setMedicament_id(String medicament_id) {
        this.medicament_id = medicament_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Vector<ParametrePatient> getParametre_patient() {
        return parametre_patient;
    }

    public void setParametre_patient(Vector<ParametrePatient> parametre_patient) {
        this.parametre_patient = parametre_patient;
    }
       
   

  

    public int getQuantite_necessaire() {
        return quantite_necessaire;
    }

    public void setQuantite_necessaire(int quantite_necessaire) {
        this.quantite_necessaire = quantite_necessaire;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prix) {
        this.prixTotal = prix;
    }
       
    
       
       
       
   
}
