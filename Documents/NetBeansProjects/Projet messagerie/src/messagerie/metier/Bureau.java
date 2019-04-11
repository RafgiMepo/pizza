/**
 *
 * @author RafgiMepo
 */
package messagerie.metier;


public class Bureau {
    protected int idbur;
    protected String sigle;
    protected String tel; 


public Bureau(int idbur, String sigle, String tel){
    this.idbur=idbur;
    this.sigle=sigle;
    this.tel=tel;
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
}
