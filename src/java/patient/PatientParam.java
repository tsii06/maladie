/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import maladie.Maladie;
import maladie.ParametreMaladie;
import parametre.V_parametremaladie;


public class PatientParam {
    int idPatientParam;
    int idPatient;
    int idparametre;
    int niveau;

    public int getIdPatientParam() {
        return idPatientParam;
    }

    public void setIdPatientParam(int idPatientParam) {
        this.idPatientParam = idPatientParam;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdparametre() {
        return idparametre;
    }

    public void setIdparametre(int idparametre) {
        this.idparametre = idparametre;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    
    public void verifMaladie(int idpatient,int age) throws Exception{
        ArrayList<Integer> idMaladie = this.getMaladie(idpatient);
        ArrayList<PatientParam> pateintP = this.getParamPatient(idpatient);
        ArrayList<Integer> idParam = null;
        ArrayList<Double> pourcentage = new ArrayList<>();
        
        for(int i = 0;i<idMaladie.size();i++){
            idParam = new ArrayList<>();
            System.out.print("maladie "+idMaladie.get(i)+"\n");
            ArrayList<ParametreMaladie> paramM = new ParametreMaladie().getParamMaladie(idMaladie.get(i));
            
            for(int x = 0;x<paramM.size();x++){
                for(int j = 0;j<pateintP.size();j++){
                    if(paramM.get(x).getIdparametre() == pateintP.get(j).getIdparametre() ){
                        
                        /**********RAHA ASIANA ANLE INTERVALLE************/
                        ArrayList<V_parametremaladie> vParam = new V_parametremaladie().getIntervalle(idMaladie.get(i), age, pateintP.get(j).getIdparametre());
                        if(vParam.size()>0){
                            if(vParam.get(0).getDebutinter() < pateintP.get(j).getNiveau() && vParam.get(0).getFininter() > pateintP.get(j).getNiveau()){
                                //System.out.print(vParam.get(0).getDebutinter()+" < "+pateintP.get(j).getNiveau()+" && "+vParam.get(0).getFininter()+" > "+pateintP.get(j).getNiveau()+"\n");
                                idParam.add(pateintP.get(j).getIdparametre());
                            }
                        }
                        
                        /**********RAHA TSY ASIANA ANLE INTERVALLE************/
                        /*idParam.add(pateintP.get(j).getIdparametre());*/
                    }
                }
            }
            
            ArrayList<ParametreMaladie> paramMaladie = new ParametreMaladie().getParamMaladie(idMaladie.get(i)); 
            double pourcent = (idParam.size() * 100) / paramMaladie.size();
            //System.out.print(idParam.size()+" * 100 / "+paramMaladie.size()+"\n");
            pourcentage.add(pourcent);
        }
        
        System.out.print("pourcent "+pourcentage+"\n");
    }
    
    public ArrayList<Integer> getMaladie(int idPatient) throws Exception{
        ArrayList<Maladie> listMaladie  = new Maladie().getAllMaladie();
        ArrayList<Integer> idParametre = null;
        ArrayList<Integer> param = null;
        ArrayList<PatientParam> patientParam = this.getParamPatient(idPatient);
        ArrayList<Integer> idMaladie = new ArrayList<>();
       
        for(int i = 0;i<listMaladie.size();i++){
            ArrayList<ParametreMaladie> paramMaladie = new ParametreMaladie().getParamMaladie(listMaladie.get(i).getIdmaladie());
            param = new ArrayList<>();
            idParametre = new ArrayList<>();
            
            for(int j = 0;j<paramMaladie.size();j++){
                param.add(paramMaladie.get(j).getIdparametre());
            }
            
            for(int x = 0;x<patientParam.size();x++){
                if(param.contains(patientParam.get(x).getIdparametre())){
                    //System.out.print("idmaladie  "+listMaladie.get(i).getIdmaladie()+"   "+patientParam.get(x).getIdparametre()+"\n");
                    idParametre.add(patientParam.get(x).getIdparametre());
                }
            }
            
            if(new HashSet<>(param).equals(new HashSet<>(idParametre))){
                //System.out.print(listMaladie.get(i).getIdmaladie()+"\n");
                idMaladie.add(listMaladie.get(i).getIdmaladie());
            }
        }
        
        return idMaladie;
    }
    
    public void insertPatientParam(int idPatient,Date dateNaissance,int idparametre,int niveau) throws Exception{
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect();
        
        try {
            String sql = "insert into patientParam values (default,"+idPatient+",'"+dateNaissance+"',"+idparametre+","+niveau+");";
            //System.out.print("sql "+sql+"\n");
            Statement statement = c.createStatement();
            statement.executeUpdate(sql);
        
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<PatientParam> getParamPatient(int idPatient) throws Exception{
        connex.Connexion connex = new connex.Connexion();
        Connection c = connex.connect();
        ArrayList<PatientParam> listA = new ArrayList<>();
       
        try {
            String sql = "select * from patientparam where idPatient = "+idPatient;
            //System.out.println(sql+"\n");
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                PatientParam p = new PatientParam();
                p.setIdPatient(resultSet.getInt("idPatient"));
                p.setIdPatientParam(resultSet.getInt("idPatientParam"));
                p.setIdparametre(resultSet.getInt("idparametre"));
                p.setNiveau(resultSet.getInt("niveau"));
                listA.add(p);
            }
            
            resultSet.close();
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listA;
    }
}
