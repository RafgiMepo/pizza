/**
 *
 * @author RafgiMepo
 */
package messagerie.metier;


public class Message {
    protected int id_msg;
    protected String contenu;
    protected String date_envoi; 
    protected int id_emp;


public Message(int id_msg, String contenu, String date_envoi, int id_emp){
    this.id_msg=id_msg;
    this.contenu=contenu;
    this.date_envoi=date_envoi;
    this.id_emp=id_emp;
}

    public int getId_msg() {
        return id_msg;
    }

    public void setId_msg(int id_msg) {
        this.id_msg = id_msg;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate_envoi() {
        return date_envoi;
    }

    public void setDate_envoi(String date_envoi) {
        this.date_envoi = date_envoi;
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

}