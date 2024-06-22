/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Vector;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class MedicamentParametre extends Medicament{
   Vector<ParametrePatient>  parametres;
   int qte=0;
   double prixTotal=0;
   
   public void setPrixTotal(){
       int [] qte = new int[parametres.size()];
       for(int i =0 ;i<parametres.size();i++ ){
          
           qte[i]=parametres.get(i).getQte_necessaire();
       }
       int max= qte[0];
       for(int i=0; i<qte.length;i++){
           if(max<qte[i]){
               max=qte[i];
           }
       
       }
       this.qte=max;
       this.prixTotal= max*this.prix;
   }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
   
   
    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Vector<ParametrePatient> getParametres() {
        return parametres;
    }

    public void setParametres(Vector<ParametrePatient> parametres) {
        this.parametres = parametres;
    }
     public boolean  isContainsSameParam (MedicamentParametre ma){
        for (ParametrePatient parametre1 : this.getParametres()) {
            for (ParametrePatient parametre2 : ma.getParametres()) {
                // Utilisation de la méthode equals pour vérifier si le Parametre est présent dans le deuxième vecteur
                if (parametre1.parametre.equals(parametre2.parametre)) {
                   return false;
                }
            }
        }
        return true;
    }
     
     
   

      
}
