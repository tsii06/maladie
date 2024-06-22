/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class CombinaisonMedicament {
    Vector<MedicamentParametre>  medicaments = new Vector<MedicamentParametre>();
    double prix =0;
    int nb_parametre=0;
     
    public void incrementePrix(double prix){
        this.prix= this.prix+prix;
    }

      public void incremente(int nb){
        this.nb_parametre=this.nb_parametre+nb;
    }
    public int getNb_parametre() {
        return nb_parametre;
    }

    public void setNb_parametre(int nb_parametre) {
        this.nb_parametre = nb_parametre;
    }
    
    public Vector<MedicamentParametre> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Vector<MedicamentParametre> medicaments) {
        this.medicaments = medicaments;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
     public boolean  isContainsSameParam (MedicamentParametre ma){
        for (MedicamentParametre mp: this.medicaments) {
            for (ParametrePatient pp: mp.parametres) {
                // Utilisation de la méthode equals pour vérifier si le Parametre est présent dans le deuxième vecteur
                for(ParametrePatient ppp : ma.parametres){
                    if(pp.parametre.equals(ppp.parametre)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    
      @Override
    public int hashCode() {
        return medicaments.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CombinaisonMedicament combinaison = (CombinaisonMedicament) obj;
        return nb_parametre == combinaison.nb_parametre && medicaments.equals(combinaison.medicaments);
    }   
    
     public void reorder(){
       Collections.sort(medicaments, new Comparator<MedicamentParametre>() {
            @Override
            public int compare(MedicamentParametre m1, MedicamentParametre m2) {
                // Tri en ordre décroissant
                return Integer.compare(Integer.parseInt(m2.getMedicament_id().substring(3)),Integer.parseInt(m1.getMedicament_id().substring(3)));
            }
        });
    }
    
}
