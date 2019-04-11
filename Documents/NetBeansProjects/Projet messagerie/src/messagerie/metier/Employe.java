/**
 *
 * @author RafgiMepo
 */
package messagerie.metier;


public class Employe {
    protected int id_emp;
    protected String matricule;
    protected String nom; 
    protected String prenom;
    protected int id_bur;

public Employe(int id_emp, String matricule, String nom,String prenom,int id_bur){
   this.id_emp=id_emp;
   this.matricule=matricule;
   this.nom=nom;
   this.prenom=prenom;
   this.id_bur=id_bur;
}

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getId_bur() {
        return id_bur;
    }

    public void setId_bur(int id_bur) {
        this.id_bur = id_bur;
    }
}