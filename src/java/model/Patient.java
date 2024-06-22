/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Patient {
    String patient_id;

    String nom;
    int age;
    Date date_consultation;
    Vector<Parametre> parametres;
    
    Maladie maladie_exacte;
    
    Vector<Combinaison> combinaisons;
    
    Vector<Maladie> maladies_possibles;

    public Vector<Combinaison> getCombinaisons() {
        return combinaisons;
    }

    public void setCombinaisons(Vector<Combinaison> combinaisons) {
        this.combinaisons = combinaisons;
    }

    public Vector<Maladie> getMaladies_possibles() {
        return maladies_possibles;
    }

    public void setMaladies_possibles(Vector<Maladie> maladies_possibles) {
        this.maladies_possibles = maladies_possibles;
    }

    public Maladie getMaladie_exacte() {
        return maladie_exacte;
    }

    public void setMaladie_exacte(Maladie maladie_exacte) {
        this.maladie_exacte = maladie_exacte;
    }

    
    
    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }
    
    public Date getDate_consultation() {
        return date_consultation;
    }

    public void setDate_consultation(Date date_consultation) {
        this.date_consultation = date_consultation;
    }


    
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Vector<Parametre> getParametres() {
        return parametres;
    }

    public void setParametres(Vector<Parametre> parametres) {
        this.parametres = parametres;
    }
    

    public Map<MaladieP,Vector<Parametre>> GetMaladiePossibleV2() throws Exception{
       Map<MaladieP,Vector<Parametre>> maladies = new HashMap();
            Connection connex = connexion.Connexion.connect();
             for(int i=0;i<this.getParametres().size();i++){
                Vector<MaladieP> m = this.getParametres().get(i).GetMaladieByParametre(connex,this.age);
                for(int j=0;j<m.size();j++){
                    Vector<Parametre> param = new Vector<Parametre>();
                    if(maladies.get(m.get(j))!=null){
                        param = maladies.get(m.get(j));
                    }
                    param.add(this.getParametres().get(i));
                   maladies.put(m.get(j),maladies.getOrDefault(m.get(j),param));           
                }
        }
        return maladies;
    }
    
    public Vector<MaladieP> changeMapToVector(Map<MaladieP,Vector<Parametre>> map){
        Vector<MaladieP> maladies = new Vector<MaladieP>();
        
         for (Map.Entry<MaladieP,Vector<Parametre>> entry : map.entrySet()) {
             MaladieP mp = entry.getKey();
             Vector<Parametre> pm = entry.getValue();
             mp.setParametre(pm);
             maladies.add(mp);
        }
        return maladies;
    }
    
    public Vector<Combinaison> getCombinaison(Vector<MaladieP> maladiePs){
        Vector<Combinaison> resultats = new Vector<Combinaison>();
        for (int i = 0; i < maladiePs.size(); i++) {
            Combinaison com = new Combinaison();
            com.getMaladie().add(maladiePs.get(i));
            com.incremente(maladiePs.get(i).getParametre().size());
            for(int j = 0 ; j<maladiePs.size();j++){ 
                if(i!=j){ 
                    if(maladiePs.get(i).isContainsSameParam(maladiePs.get(j))){
                        if(com.isContainsSameParam(maladiePs.get(j))){
                             com.getMaladie().add(maladiePs.get(j));
                            com.incremente(maladiePs.get(j).getParametre().size());
                        }
                        
                     }               
                }        
            }
            com.reorder();
            resultats.add(com);
        }                
           Set<Combinaison> ensembleC = new HashSet<>(resultats);      
           Vector<Combinaison> comb = new Vector<>(ensembleC);
          Collections.sort(comb, new Comparator<Combinaison>() {
            @Override
            public int compare(Combinaison c1, Combinaison c2) {
                // Tri en ordre décroissant
                return Integer.compare(c2.getNbParametre(),c1.getNbParametre());
            }
        });
        return comb;
    }
    
      public Vector<Combinaison> getMaladiePatient(Vector<Combinaison> com) throws Exception{
        Vector<Combinaison> res = new Vector<Combinaison>();
        if(com.size()>0){
            int nbP = com.get(0).getNbParametre();
            for(Combinaison c : com){
                if(c.getNbParametre()==nbP){
                    res.add(c);
                }
            }
        } else {
            Combinaison cc = new Combinaison();
            MaladieP pp = new MaladieP();
            pp.setNom("Not found");
            Vector<Parametre> p = new Vector<Parametre>();
            pp.setParametre(p);
            cc.getMaladie().add(pp);
            res.add(cc);
            return res;

        }
        
        return res;
    }
    
    
    public void insert(Connection c) throws SQLException{
      //  patient_id | nom | age | date_consultation
        String sql = "insert into patient (nom,age,date_consultation) values (?,?,?)";
        PreparedStatement s = c.prepareStatement(sql);
        s.setString(1,this.getNom());
        s.setInt(2,this.getAge());
        s.setDate(3,this.getDate_consultation());
        s.executeUpdate();
        
    } 
    public String getLastPatient(Connection c) throws Exception{
        Statement s = c.createStatement();
        ResultSet r = s.executeQuery("select last_value as id from seq_patient;");
        int id = 0;
        while(r.next()){
            id = r.getInt("id");
        }
        return "PATIENT"+id;
    }
    public void insertParametrePatient(Connection c) throws Exception{
        
        for(int i=0;i<this.getParametres().size();i++){
            //param_patient_id | parametre_id | patient_id | valeur
            String sql = "insert into param_patient (parametre_id,patient_id,valeur) values (?,?,?) ";
            PreparedStatement stat = c.prepareStatement(sql);
            stat.setString(1,this.getParametres().get(i).getParametre_id());
            stat.setString(2,this.getPatient_id());
            stat.setInt(3,this.getParametres().get(i).getCoefficient());
            stat.executeUpdate();
            stat.close();
         }
        
    }
    
     public Patient getPatientById(Connection c,String patient_id) throws SQLException{
        Patient p = new Patient();
           Statement stat = c.createStatement();
       ResultSet res = stat.executeQuery("select * from patient where patient_id='"+patient_id+"'");
        while(res.next()){
            p.setPatient_id(patient_id);
            p.setAge(res.getInt("age"));
            p.setNom(res.getString("nom"));
            
        }
        stat.close();
        res.close();
        return p;
    }
     
     
    public static MaladieP getKeyFromHashMap(Map<MaladieP, Integer> hashMap, MaladieP key) {
        for (Map.Entry<MaladieP, Integer> entry : hashMap.entrySet()) {
            if (entry.getKey().equals(key)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
  public Map<String,ParametreMedicament> getMedicamentPossible (Connection c) throws SQLException{
      Map<String,ParametreMedicament> resultats= new HashMap<>();
      String sql= "select * from v_medicament where patient_id=? ";
      PreparedStatement stat = c.prepareStatement(sql);
     // stat.setString(1,this.patient_id);
      stat.setString(1,"PATIENT43");
      ResultSet res = stat.executeQuery();
      while(res.next()){
          ParametreMedicament pm = new ParametreMedicament();
          pm.medicament_id=res.getString("medicament_id");
          pm.nom=res.getString("nom");
          pm.prix=res.getDouble("prix");
          Parametre p = new Parametre();
              p.parametre_id=res.getString("parametre_id");
              p.designation = res.getString("designation");
              System.out.println(pm.medicament_id);
              System.out.println(resultats.get(pm.medicament_id));
          if(resultats.get(pm.medicament_id)!=null){           
              //resultats.get(pm.medicament_id).parametre_patient.put(p,res.getInt("qte_necessaire"));
              ParametreMedicament ppm = resultats.get(pm.medicament_id);
              ParametrePatient pp  = new ParametrePatient();
              pp.parametre = p;
              pp.qte_necessaire = res.getInt("qte_necessaire");
              ppm.parametre_patient.add(pp);
              resultats.replace(pm.medicament_id, ppm);
              System.out.println("Leo be df");
          }
          else {       
                ParametrePatient pp  = new ParametrePatient();
              pp.parametre = p;
              pp.qte_necessaire = res.getInt("qte_necessaire");
              pm.parametre_patient.add(pp);
         }
          resultats.put(pm.medicament_id, pm);
      }
      return resultats;
  }
  public Map<MedicamentParametre,Vector<ParametrePatient>> getMedicamentPossibleV2 (Connection c) throws SQLException{
      Map<MedicamentParametre,Vector<ParametrePatient>> resultats= new HashMap<>();
      String sql= "select * from v_medicament where patient_id=? ";
      PreparedStatement stat = c.prepareStatement(sql);
     stat.setString(1,this.patient_id);
     // stat.setString(1,"PATIENT43");
      ResultSet res = stat.executeQuery();
      while(res.next()){
          MedicamentParametre med = new MedicamentParametre();
          med.medicament_id = res.getString("medicament_id");
          med.nom = res.getString("nom");
          med.prix = res.getDouble("prix");  
          ParametrePatient pp = new ParametrePatient();
          Parametre pm  = new Parametre();
          pm.parametre_id=res.getString("parametre_id");
          pm.designation = res.getString("designation");
          pp.parametre = pm;
          pp.qte_necessaire = res.getInt("qte_necessaire");
          Vector<ParametrePatient> param = new Vector<ParametrePatient>();
          if(resultats.get(med)!=null){
                param = resultats.get(med);
          }
          param.add(pp);
          resultats.put(med,resultats.getOrDefault(med,param));
      }
     return  resultats;
  }  
   public Vector<MedicamentParametre> changeMapMedToVector(Map<MedicamentParametre,Vector<ParametrePatient>> map){
        Vector<MedicamentParametre> medicaments = new Vector<MedicamentParametre>();
        
         for (Map.Entry<MedicamentParametre,Vector<ParametrePatient>> entry : map.entrySet()) {
             MedicamentParametre mp = entry.getKey();
             Vector<ParametrePatient> pp = entry.getValue();
             mp.setParametres(pp);
             mp.setPrixTotal();
             medicaments.add(mp);
       }
        return medicaments;
    }
    
      public Vector<CombinaisonMedicament> getCombinaisonMedicament(Vector<MedicamentParametre> medP){
        Vector<CombinaisonMedicament> resultats = new Vector<CombinaisonMedicament>();
        for (int i = 0; i < medP.size(); i++) {
            CombinaisonMedicament com = new CombinaisonMedicament();
            com.getMedicaments().add(medP.get(i));
            com.incremente(medP.get(i).getParametres().size());
            com.incrementePrix(medP.get(i).getPrixTotal());
            for(int j = 0 ; j<medP.size();j++){ 
                if(i!=j){ 
                    if(medP.get(i).isContainsSameParam(medP.get(j))){
                        if(com.isContainsSameParam(medP.get(j))){
                            com.getMedicaments().add(medP.get(j));
                            com.incremente(medP.get(j).getParametres().size());
                            com.incrementePrix(medP.get(j).getPrixTotal());
                        }
                     }               
                }   
            }
           com.reorder();

           
           if(com.nb_parametre==this.parametres.size()){
                           resultats.add(com);
           }
            System.out.println(this.parametres.size());
        }                
           Set<CombinaisonMedicament> ensembleC = new HashSet<>(resultats);      
           Vector<CombinaisonMedicament> comb = new Vector<>(ensembleC);

        return comb;
    }
      
            public Vector<CombinaisonMedicament> getCombinaisonMedicamentV2(Vector<MedicamentParametre> medP){
        Vector<CombinaisonMedicament> resultats = new Vector<CombinaisonMedicament>();
        for (int i = 0; i < medP.size(); i++) {
            CombinaisonMedicament com = new CombinaisonMedicament();
            com.getMedicaments().add(medP.get(i));
            com.incremente(medP.get(i).getParametres().size());
            com.incrementePrix(medP.get(i).getPrixTotal());
            for(int j = 0 ; j<medP.size();j++){ 
                if(i!=j){ 
                    if(medP.get(i).isContainsSameParam(medP.get(j))){
                        if(com.isContainsSameParam(medP.get(j))){
                            com.getMedicaments().add(medP.get(j));
                            com.incremente(medP.get(j).getParametres().size());
                            com.incrementePrix(medP.get(j).getPrixTotal());
                        }
                     }               
                }   
            }
           com.reorder();
           resultats.add(com);
//           if(com.nb_parametre==this.parametres.size()){
//                           resultats.add(com);
//           }
            System.out.println(this.parametres.size());
        }                
           Set<CombinaisonMedicament> ensembleC = new HashSet<>(resultats);      
           Vector<CombinaisonMedicament> comb = new Vector<>(ensembleC);
           Collections.sort(comb, new Comparator<CombinaisonMedicament>() {
            @Override
            public int compare(CombinaisonMedicament c1, CombinaisonMedicament c2) {
                // Tri en ordre décroissant
                return Integer.compare(c2.getNb_parametre(),c1.getNb_parametre());
            }
        });

        return comb;
    }
      
    public CombinaisonMedicament getMedicamentPatient(Vector<CombinaisonMedicament> medP){
        CombinaisonMedicament comb = new CombinaisonMedicament();
      
        comb = medP.get(0);
        for(int i=0; i<medP.size();i++){
           if(comb.getPrix()>medP.get(i).getPrix()){
               comb=medP.get(i);
           }
       
       }
        return comb;
    }
    public Vector<CombinaisonMedicament> getMedicamentPatientV2(Vector<CombinaisonMedicament> medP){
        CombinaisonMedicament comb = new CombinaisonMedicament();
      Vector<CombinaisonMedicament> res = new Vector<CombinaisonMedicament>();
        if(medP.size()>0){
            int nbP = medP.get(0).getNb_parametre();
            for(CombinaisonMedicament c : medP){
                if(c.getNb_parametre()==nbP){
                    res.add(c);
                }
            }
        } else {
            CombinaisonMedicament cc = new CombinaisonMedicament();
            MedicamentParametre pp = new MedicamentParametre();
            pp.setNom("Not found");
            Vector<ParametrePatient> p = new Vector<ParametrePatient>();
            pp.setParametres(p);
            cc.getMedicaments().add(pp);
            res.add(cc);
            return res;

        }
        double prix_min = res.get(0).getPrix();
        for(int i=0; i<res.size();i++){
           if(prix_min>res.get(i).getPrix()){
               prix_min=res.get(i).getPrix();
           }
       
       }
        Vector<CombinaisonMedicament> resu = new Vector<CombinaisonMedicament>();
        for(CombinaisonMedicament cm  : res){
            if(cm.getPrix()==prix_min){
                resu.add(cm);
            }
        }
        return resu;
    }
      
    
}
