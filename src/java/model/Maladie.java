/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Map;
import java.util.Objects;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class Maladie {
   private String maladie_id;
    String nom;

    public Maladie(){}
    public Maladie(String id){
        this.maladie_id=id;
    }

    public String getMaladie_id() {
        return maladie_id;
    }

    public void setMaladie_id(String maladie_id) {
        this.maladie_id = maladie_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Maladie maladie = (Maladie) obj;
       return maladie_id.equals(maladie.maladie_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maladie_id);
    }
    
}
