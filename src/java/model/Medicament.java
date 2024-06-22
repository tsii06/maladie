/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Map;
import java.util.Objects;
import java.util.Vector;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class Medicament {
   String medicament_id;
   String nom;
   double prix;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Medicament med = (Medicament) obj;
       return medicament_id.equals(med.medicament_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicament_id);
    }

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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
   
}
