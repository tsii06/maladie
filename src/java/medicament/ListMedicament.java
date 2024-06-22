/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicament;

import java.util.ArrayList;


public class ListMedicament {
    ArrayList<DetailMedicament> d_medicaments;
    double prix_total = 0;

    public ArrayList<DetailMedicament> getD_medicaments() {
        return d_medicaments;
    }

    public void setD_medicaments(ArrayList<DetailMedicament> d_medicaments) {
        this.d_medicaments = d_medicaments;
    }

    public double getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(double prix_total) {
        this.prix_total = prix_total;
    }
    
    public void getMedicaments(ArrayList<Medicament> meds) throws Exception{
        d_medicaments = new ArrayList<DetailMedicament>();
        for(Medicament med : meds){
            Fanafody f = new Fanafody();
            f=f.getFanafodyByNom(med.getNom());
            System.out.println("ggg :" +f.getNom());
            DetailMedicament det = new DetailMedicament();
            det.setIdfanafody(f.getIdfanafody());
            det.setNom(f.getNom());
            det.setPrixunitaire(f.getPrixunitaire());
            det.setQuantite((int) Math.round(med.getNombre()));
            det.setPrix_total(det.getPrixunitaire()*det.getQuantite());
            if(med.getNombre()!=0){
                this.d_medicaments.add(det);
                prix_total = prix_total+det.getPrix_total();
            }
            
        }
    
    }
    
}
