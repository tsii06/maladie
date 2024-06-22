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
public class MaladieP extends Maladie{
    Vector<Parametre> parametre;

    public Vector<Parametre> getParametre() {
        return parametre;
    }

    public void setParametre(Vector<Parametre> parametre) {
        this.parametre = parametre;
    }
    
    public boolean  isContainsSameParam (MaladieP ma){
        for (Parametre parametre1 : this.getParametre()) {
            for (Parametre parametre2 : ma.getParametre()) {
                // Utilisation de la méthode equals pour vérifier si le Parametre est présent dans le deuxième vecteur
                if (parametre1.equals(parametre2)) {
                   return false;
                }
            }
        }
        return true;
    }
    
}
