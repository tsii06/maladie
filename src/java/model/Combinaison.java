  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class Combinaison {
    Vector<MaladieP> maladie = new Vector<MaladieP>() ;
    int nbParametre=0 ;

    public void incremente(int nb){
        this.nbParametre=this.nbParametre+nb;
    }
    
    public int getNbParametre() {
        return nbParametre;
    }

    public void setNbParametre(int nbParametre) {
        this.nbParametre = nbParametre;
    }

    public Vector<MaladieP> getMaladie() {
        return maladie;
    }
    
    
    

    public void setMaladie(Vector<MaladieP> maladie) {
        this.maladie = maladie;
    }
     @Override
    public int hashCode() {
        return maladie.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Combinaison combinaison = (Combinaison) obj;
        return nbParametre == combinaison.nbParametre && maladie.equals(combinaison.maladie);
    }   
    
    public void reorder(){
       Collections.sort(maladie, new Comparator<MaladieP>() {
            @Override
            public int compare(MaladieP m1, MaladieP m2) {
                // Tri en ordre décroissant
                return Integer.compare(Integer.parseInt(m2.getMaladie_id().substring(3)),Integer.parseInt(m1.getMaladie_id().substring(3)));
            }
        });
    }
    
      public boolean  isContainsSameParam (MaladieP ma){
        for (MaladieP mp: this.maladie) {
            for (Parametre pp: mp.parametre) {
                // Utilisation de la méthode equals pour vérifier si le Parametre est présent dans le deuxième vecteur
                for(Parametre ppp : ma.parametre){
                    if(pp.equals(ppp)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
}