/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Connection;
import java.util.ArrayList;
import medicament.Medicament;
import patient.PatientParam;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
 //            Medicament m = new Medicament();
   //    m.getMedicament(1);
//       m.getMinim();
//       ArrayList<String> res = m.getFanafodyPatient(1);
//       for(int i = 0 ; i<res.size();i++){
//            System.out.println(res.get(i));
//       }
      PatientParam p = new PatientParam();
       p.verifMaladie(1,20);
        Connection c = connex.Connexion.connect();
       
    }
    
}
