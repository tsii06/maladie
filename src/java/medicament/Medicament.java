/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicament;

import it.ssc.pl.milp.LP;
import it.ssc.pl.milp.Solution;
import it.ssc.pl.milp.SolutionType;
import it.ssc.pl.milp.Variable;
import java.util.ArrayList;
import patient.PatientParam;

public class Medicament {
    String nom;
    double nombre;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getNombre() {
        return nombre;
    }

    public void setNombre(double nombre) {
        this.nombre = nombre;
    }

    public Medicament(String nom, double nombre) {
        this.nom = nom;
        this.nombre = nombre;
    }

    public Medicament() {
    }
    
    public ArrayList<Medicament> getMedicament(int idPatient) throws Exception{
        ArrayList<String> eq = this.getFanafodyPatient(idPatient);
        ArrayList<Medicament> listM = new ArrayList<>();
        
        LP lp = new LP(eq); 
        SolutionType solution_type=lp.resolve();
         
        if(solution_type==SolutionType.OPTIMUM) {
            Solution soluzione=lp.getSolution();
            for(Variable var:soluzione.getVariables()) {
                Medicament m = new Medicament(var.getName(),var.getValue());
                System.out.print(m.getNom()+"   "+m.getNombre()+"\n");
                listM.add(m);
            }
        }
        
        return listM;
    }
    public ArrayList<Medicament> getMedicamentV2(int idPatient) throws Exception{
        ArrayList<String> eq = this.getFanafodyPatient(idPatient);
        ArrayList<Medicament> listM = new ArrayList<>();
        
        LP lp = new LP(eq); 
        SolutionType solution_type=lp.resolve();
         
        if(solution_type==SolutionType.OPTIMUM) {
            Solution soluzione=lp.getSolution();
            for(Variable var:soluzione.getVariables()) {
                Medicament m = new Medicament(var.getName(),var.getValue());
                System.out.print(m.getNom()+"   "+m.getNombre()+"\n");
                listM.add(m);
            }
        }
        
        return listM;
    }
    
    public StringBuilder getMinim() throws Exception{
        ArrayList<Fanafody> listF = new Fanafody().getFanafody();
        StringBuilder str = new StringBuilder();
        StringBuilder test = new StringBuilder();
        StringBuilder space = new StringBuilder();
        space.append(" ");
        
        for(int i =0;i<listF.size();i++){
            str.append(String.valueOf(listF.get(i).getPrixunitaire())).append(listF.get(i).getNom()).append(space);

            if (i < listF.size() - 1) {
                str.append("+").append(space);
            }
        }
        
        test.append("min: ");
        
        return test.append(str);
    }
    
    public ArrayList<String> getFanafodyPatient(int idPatient) throws Exception{
        ArrayList<PatientParam> paramP = new PatientParam().getParamPatient(idPatient);
        ArrayList<String> listEq = new ArrayList<>();
        StringBuilder s = this.getMinim();
        listEq.add(s.toString());
        StringBuilder space = new StringBuilder();
        space.append(" ");
        
        for (int i = 0; i < paramP.size(); i++) {
            ArrayList<V_fanafodyParametre> fP = new V_fanafodyParametre().getFanafodyParametre(paramP.get(i).getIdparametre());
            StringBuilder str = new StringBuilder();

            for (int j = 0; j < fP.size(); j++) {
                str.append(String.valueOf(fP.get(j).getNiveau())).append(fP.get(j).getNom()).append(space);

                if (j < fP.size() - 1) {
                    str.append("+").append(space);
                }
            }
            str.append(">= "+String.valueOf(paramP.get(i).getNiveau()));
            listEq.add(str.toString());
        }
        
        for(int i = 0;i<listEq.size();i++){
            System.out.print(listEq.get(i)+"\n");
        }
        
        return listEq;
    }
}
