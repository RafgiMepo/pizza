/**
 *
 * @author RafgiMepo
 */
package messagerie.metier;


public class Bureau {
    protected int idbur;
    protected String sigle;
    protected String tel; 
    protected String description;


public Bureau(int idbur, String sigle, String tel, String description){
    this.idbur=idbur;
    this.sigle=sigle;
    this.tel=tel;
    this.description=description;
}

    public int getidbur() {
        return idbur;
    }

    public void setIdbur(int idbur) {
        this.idbur = idbur;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public String getDescription(){
        return description;
    }
}
