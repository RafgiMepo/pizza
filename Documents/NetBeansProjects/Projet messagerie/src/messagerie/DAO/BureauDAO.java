/**
 *
 * @author RafgiMepo
 */

package messagerie.DAO;

import java.sql.*;
import java.util.*;
import messagerie.metier.Bureau;
import myconnections.DBconnect;


public class BureauDAO extends DAO<Bureau>{
  Statement stmt=null;
  ResultSet rs=null;
  String new_tel,new_Sigle,new_description;
  int new_id=1,bursup;
  Bureau b;
  String Sigle, Tel,Description;
  public static String liste_desc, liste_emp;
  
  
    /**
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String[]args) throws SQLException{
        BureauDAO bur=new BureauDAO();
        bur.menu();
   
}
    /**
     *
     * @throws SQLException
     */
    public void menu () throws SQLException{
    int choix;
    Scanner sc=new Scanner(System.in);
    
    Connection dbConnect=DBconnect.getConnection();
     if(dbConnect==null){
     System.exit(1);
    }
    
    do{     
        System.out.println("Que voulez vous faire ? ");
        System.out.println("1. Lire le bureau");
        System.out.println("2. Creer un bureau");
        System.out.println("3. Modifier le bureau");
        System.out.println("4. Supprimer un bureau");
        System.out.println("5. Recherche");
        
        choix=sc.nextInt();
        
        switch (choix) {
            case 1:
                int idb,drap=0;
                System.out.println("Quel est l'id du bureau ?");
                idb=sc.nextInt();   
                   
                 stmt = dbConnect.createStatement();
                 rs=stmt.executeQuery("select * from bureau");
                 
                   while(rs.next()){
                       
                        if(rs.getInt("id_bur")==idb){
                            drap=1;
                        } 
                   }
                  if(drap==0){
                      System.out.println("L'id n'existe pas");
                  }
                  else{
                    read(idb);
                  }
                break;
                
            case 2:
                nouveau();
                create(b);
                break;
            //modifier dans le menu car petit code qui nécessite pas nécessairement une méthode en plus    
            case 3:
                int idmodif;
                
                System.out.println("Quel est l'id du bureau à modifier ?");
                idmodif=sc.nextInt(); 
                stmt = dbConnect.createStatement();
                    rs = stmt.executeQuery("select * from bureau");                 
                    while(rs.next()){
                        if(rs.getInt("Id_bur") == idmodif){
                            Sigle = rs.getString("SIGLE");
                            Tel = rs.getString("TEL");
                            Description=rs.getString("description");
                            break;
                        }
                    }
                b=new Bureau(idmodif,Sigle,Tel,Description);
                update(b);
                System.out.println("La modification est effectuée");
                break;
            // A obtenu de l'aide d'Alan Louis pour la fonction supprimé 
            case 4:
                int idsup,flag=0;
                System.out.println("Quel est l'id du bureau à supprimer ?");
                idsup=sc.nextInt(); 
                stmt = dbConnect.createStatement();
                    rs = stmt.executeQuery("select * from bureau");
                    String q = "select * from employe where id_bur = ?";
                    while(rs.next()){
                        if(rs.getInt("Id_bur") == idsup){
                            Sigle = rs.getString("SIGLE");
                            Tel = rs.getString("TEL");
                            Description=rs.getString("description");
                            break;
                        }
                    }
                 try(PreparedStatement pstm = dbConnect.prepareStatement(q)){
                        pstm.setInt(1, idsup);
                        ResultSet rs2 = pstm.executeQuery();
                        while(rs2.next()){
                            String n = rs2.getString("NOM");
                            String p = rs2.getString("PRENOM");
                            System.out.println(p+" "+n+" fait partie de ce bureau.");
                            flag = 1;
                        }
                    }
                    if(flag==0){
                        b = new Bureau(idsup, Sigle, Tel,Description);
                        delete(b);
                        System.out.println("La suppression à bien été effectuée.");
                    }
                    else{
                        System.out.println("Assignez ces employés à un autre bureau avant de le supprimer.");
                    } 
              
                break;
            case 5:                            
                recherche();
                break;
            case 6:
                System.out.println("Au revoir !");
                break;
            default:
                break;
        }
        
    }while(choix!=6);
    
    DBconnect.closeConnection();
}
    /**
     * méthode pour ajouter un nouveau bureau, je récupère mes éléments, je demande les caractéristiques du nouveau bureau et les compares avec ma base, si ce n'est pas dans la base on l'ajoute
     * @throws SQLException
     */
    public void nouveau() throws SQLException{
        int flag,drap;
        Scanner sc=new Scanner(System.in);
        Scanner sc2=new Scanner(System.in);
        System.out.println("Nouveau Bureau : ");
         Connection dbConnect=DBconnect.getConnection();
         if(dbConnect==null){
         System.exit(1);
    }
          do{
              flag=1;
              stmt=dbConnect.createStatement();
              rs=stmt.executeQuery("Select * FROM bureau ORDER BY id_bur DESC");
              
              if(rs.next()){
                 new_id=rs.getInt("id_bur")+1;   
              }
            
              do{
                  rs=stmt.executeQuery("Select * FROM bureau");
              drap=0;
              
              System.out.println("Nouveau sigle ?");
              new_Sigle=sc2.nextLine();
              
                while(rs.next()){
               
                if(new_Sigle.equals(rs.getString("Sigle"))){
                    
                   System.out.println("Ce sigle existe déjà");
                   drap=1;
                   
                  }                
                }
                
              } while(drap==1);
              
              
              System.out.println("Nouveau télephone ?");
              new_tel=sc2.nextLine();
              System.out.println("Nouvelle description ?");
              new_description=sc2.nextLine();
              
              
              while(rs.next()){
                  
                 int idd=rs.getInt("ID_BUR");
                 String sig=rs.getString("Sigle");
                 String phone=rs.getString("Tel");
                 String desc=rs.getString("description");
                 if((idd==new_id)&&(sig.equals(new_Sigle))&&(phone.equals(new_tel))&&(desc.equals(new_description))){
                    flag=0;
                    break;
                  }
              }
              if(flag==0){
                  System.out.println("Bureau déjà existant");
              }
                  
          }while(flag==0);
          b=new Bureau(new_id,new_Sigle,new_tel,new_description);
    }
    
    public void recherche() throws SQLException {
        
        Connection dbConnect=DBconnect.getConnection();
        if(dbConnect==null){
        System.exit(1);
        }
        Scanner sc=new Scanner(System.in);
        Scanner sc2=new Scanner(System.in);
            
            liste_desc=("Bureau trouvé : ");
        
            System.out.println("1.Recherche par id ");
            System.out.println("2.Recherche par description");
            System.out.println("Que voulez-vous faire ? ");
            int chx=sc.nextInt();


            if(chx==1){
                
                String requete="Select * from Bureau where id_bur=?";
                
                System.out.println("Id ? ");
                int id=sc.nextInt();
                int flag=0;        
                stmt=dbConnect.createStatement();
                rs=stmt.executeQuery("SELECT * FROM bureau");
                while(rs.next()){
                    if(id==rs.getInt("id_bur")){
                        flag=1;
                        break;
                    }
                }
                if(flag==0){
                    System.out.println("Erreur l'id n'existe pas");
                }
                else{
                     liste_desc+="\nSigle:"+rs.getString("Sigle")+",n° de téléphone: "+rs.getString("Tel")+",description : "+rs.getString("description");
                     System.out.println(liste_desc);
                }
            }
            else if(chx==2) {
               String requete="Select * from Bureau where description like ?";
               try(PreparedStatement pstm=dbConnect.prepareStatement(requete)){
                
                
                System.out.println("Votre recherche ?");
                String desc=sc2.nextLine();
                pstm.setString(1, "%"+desc+"%");
                rs=pstm.executeQuery();
                
                while(rs.next()){
                        
                      liste_desc+="\nSigle:"+rs.getString("Sigle")+"\n,n° de téléphone: "+rs.getString("Tel")+",\ndescription : "+rs.getString("description");
                    
                }
                System.out.println(liste_desc);
               }  
            }
            else 
            {
                System.out.println("Ce choix n'existe pas");
            }
    }
   
    /**
     *
     * @param idb
     * @return
     * @throws SQLException
     */
    @Override
    public Bureau read(int idb) throws SQLException {
        
            Connection dbConnect=DBconnect.getConnection();
            if(dbConnect==null){
            System.exit(1);
            }
        String requete="select * from Bureau where id_bur=?";
        String requete2="select * from Employe where id_bur = ?";
         try (PreparedStatement ps=dbConnect.prepareStatement(requete);PreparedStatement ps2=dbConnect.prepareStatement(requete2)) {

            ps.setInt(1, idb);
            ps2.setInt(1, idb);
            try (ResultSet rs=ps.executeQuery(); ResultSet rs2 = ps2.executeQuery()) {
                liste_emp = "Employés faisant partie de ce bureau :";
                while(rs2.next()){
                    liste_emp += "\n Prénom : " + rs2.getString("PRENOM") + ", Nom : " + rs2.getString("NOM") + ", matricule : " + rs2.getString("MATRICULE");
                }
                if (rs.next()) {
                    int Idbur=rs.getInt("id_bur");
                    String Sigle=rs.getString("Sigle");
                    String Tel=rs.getString("Tel");
                    String description=rs.getString("description");
                    System.out.println(Sigle+"  "+Tel+""+description);
                    return new Bureau(Idbur, Sigle, Tel,description);

                } else {
                    throw new SQLException("Code inconnu");
                }

            }
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    @Override
    
    public Bureau create(Bureau obj) throws SQLException {
            Connection dbConnect=DBconnect.getConnection();
            if(dbConnect==null){
            System.exit(1);
            }
        String req1="insert into Bureau(id_bur,Sigle,Tel,description) values(?,?,?,?)";
        String req2="select Id_bur from Bureau where Sigle=? and Tel=? and Description=?";
        
        try (PreparedStatement ps=dbConnect.prepareStatement(req1);
             PreparedStatement ps2=dbConnect.prepareStatement(req2)) {
            ps.setInt(1, obj.getidbur());
            ps.setString(2, obj.getSigle());
            ps.setString(3, obj.getTel());
            ps.setString(4, obj.getDescription());
            
            int n=ps.executeUpdate();
            
            if (n==0) {
                throw new SQLException("erreur de creation bureau, aucune ligne créée");
            }
           
            ps2.setString(1, obj.getSigle());
            ps2.setString(2, obj.getTel());
            ps2.setString(3, obj.getDescription());
            
            try (ResultSet rs=ps2.executeQuery()) {
                if (rs.next()) {
                    int Idbur=rs.getInt(1);
                    obj.setIdbur(Idbur);
                    return read(Idbur);
                } else {
                    throw new SQLException("erreur de création bureau, record introuvable");
                }
            }
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    @Override
    public Bureau update(Bureau obj) throws SQLException {
        
        Connection dbConnect=DBconnect.getConnection();
            if(dbConnect==null){
              System.exit(1);
            }
     String Nsigle,Ntel,Ndescription;
     
     Scanner sc= new Scanner(System.in);
     String requete="update Bureau set Sigle=?,Tel=?, Description=? where id_bur=?";
     try (PreparedStatement ps=dbConnect.prepareStatement(requete)) {

           /*System.out.println("Nouveau sigle ?");
           Nsigle=sc.nextLine();
           System.out.println("Nouveau tel ?");
           Ntel=sc.nextLine();
           System.out.println("Nouvelle description ?");
           Ndescription=sc.nextLine();*/
           
            ps.setInt(4, obj.getidbur());
            ps.setString(1, obj.getSigle());
            ps.setString(2, obj.getTel());
            ps.setString(3, obj.getDescription());
            
            int n=ps.executeUpdate();
            if (n==0) {
                throw new SQLException("aucune ligne bureau mise à jour");
            }
            return read(obj.getidbur());
        }   
    }

    /**
     * effacement de la ligne de l'id du bureau selectionné 
     * @param obj
     * @throws SQLException
     */
    @Override
    public void delete(Bureau obj) throws SQLException {
      //  int flag=1;
        Connection dbConnect=DBconnect.getConnection();
            if(dbConnect==null){
              System.exit(1);
            }
       String sql = "DELETE FROM Bureau WHERE id_bur=?";
       PreparedStatement stmt=dbConnect.prepareStatement(sql);
        stmt.setInt(1,obj.getidbur());
 

       int rowsDeleted = stmt.executeUpdate();
       if (rowsDeleted==0) {
                throw new SQLException("aucune ligne bureau effacée");
            }
    } 
}