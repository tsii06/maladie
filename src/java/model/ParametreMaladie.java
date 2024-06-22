/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.util.Vector;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class ParametreMaladie {
  Maladie maladie;
  int age_min;
  int age_max;
  Vector<Parametre> parametres;

    public Maladie getMaladie() {
        return maladie;
    }

    public void setMaladie(Maladie maladie) {
        this.maladie = maladie;
    }

    public int getAge_min() {
        return age_min;
    }

    public void setAge_min(int age_min) {
        this.age_min = age_min;
    }

    public int getAge_max() {
        return age_max;
    }

    public void setAge_max(int age_max) {
        this.age_max = age_max;
    }

    public Vector<Parametre> getParametres() {
        return parametres;
    }

    public void setParametres(Vector<Parametre> parametres) {
        this.parametres = parametres;
    }
    
   

  
}
