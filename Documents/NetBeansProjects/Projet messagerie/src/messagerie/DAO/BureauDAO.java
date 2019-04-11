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
  String new_tel,new_Sigle;
  int new_id,bursup;
  Bureau b;
  
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
        
        choix=sc.nextInt();
        
        switch (choix) {
            case 1:
                int idb;
                System.out.println("Quel est l'id du bureau ?");
                idb=sc.nextInt();
                read(idb);
                break;
                
            case 2:
                nouveau();
                create(b);
                break;
                
            case 3:
                modif();
                break;
            
            case 4:
                int idsup;
                System.out.println("Quel est l'id du bureau à supprimer ?");
                idsup=sc.nextInt();            
                delete(b);
                break;
            
            case 5:
                System.out.println("Au revoir !");
                break;
            default:
                break;
        }
        
    }while(choix!=5);
    
    DBconnect.closeConnection();
}
    /**
     * méthode pour ajouter un nouveau bureau, je récupère mes éléments, je demande les caractéristiques du nouveau bureau et les compares avec ma base, si ce n'est pas dans la base on l'ajoute
     * @throws SQLException
     */
    public void nouveau() throws SQLException{
        int flag;
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
              rs=stmt.executeQuery("Select * FROM bureau");
              System.out.println("Nouveau id bureau ?");
              new_id=sc.nextInt();
              System.out.println("Nouveau sigle ?");
              new_Sigle=sc2.nextLine();
              System.out.println("Nouveau télephone ?");
              new_tel=sc2.nextLine();
              while(rs.next()){
                  
                 int idd=rs.getInt("ID_BUR");
                 String sig=rs.getString("Sigle");
                 String phone=rs.getString("Tel");
                 
                 if((idd==new_id)&&(sig.equals(new_Sigle))&&(phone.equals(new_tel))){
                    flag=0;
                    break;
                  }
              }
              if(flag==0){
                  System.out.println("Bureau déjà existant");
              }
                  
          }while(flag==0);
          b=new Bureau(new_id,new_Sigle,new_tel);
        
    }
    
    /**
     * méthode modifier
     */
    public void modif() throws SQLException{
        int flag,id_mod;
        String new_sigle,new_tel;
        
        Scanner sc= new Scanner(System.in);
        Scanner sc2=new Scanner(System.in);
        
        Connection dbConnect=DBconnect.getConnection();
         if(dbConnect==null){
             System.exit(1);
          }
        
         do{
              flag=1;
             
              stmt=dbConnect.createStatement();
              rs=stmt.executeQuery("Select * FROM bureau");
              System.out.println("id du bureau à modifier?");
              id_mod=sc.nextInt();
              
               System.out.println("Nouveau Sigle ?");
               new_sigle=sc2.nextLine();
               System.out.println("Nouveau tel ?");
               new_tel=sc2.nextLine();
                  
              while(rs.next()){
                 
                 int idbur=rs.getInt("ID_BUR");
                 String sig=rs.getString("Sigle");
                 String phone=rs.getString("Tel");
                 
                 if((id_mod==idbur)){
                    flag=0;
                    break;
                  }
              }
              if(flag==0){
      
                  String sql = "UPDATE Bureau SET id_bur=?, Sigle=?, Tel=? WHERE idbur=?";
                  PreparedStatement statement=dbConnect.prepareStatement(sql);
                  statement.setString(1, "id_mod");
                  statement.setString(2, "new_sig");
                  statement.setString(3, "new_tel");
 
              }
              else {
                  System.out.println("id bureau non trouvé");
              }
                  
          }while(flag==1);
          
                 
    }
    
    /**
     * méthode supprimé, on ouvre la connexion et séléctionne tous les éléments du bureau pour supprimé celui qu'on veut, on met une condition avec un flag si on a trouvé l'élément dans la table
     * @throws java.sql.SQLException
     */
  /*  public void supp() throws SQLException{
       int bursup,flag;
       
 
        Scanner sc=new Scanner(System.in);
        Connection dbConnect=DBconnect.getConnection();
         if(dbConnect==null){
            System.exit(1);
           }
       
        do{
              flag=1;
              stmt=dbConnect.createStatement();
              rs=stmt.executeQuery("Select * FROM bureau");
              System.out.println("id du bureau à supprimer ?");
              bursup=sc.nextInt();
              
              while(rs.next()){
                  
                 int idd=rs.getInt("ID_BUR");
                 String sig=rs.getString("Sigle");
                 String phone=rs.getString("Tel");
                 
                 if(idd!=bursup){
                  } else {
                     flag=0;
                     break;
                  }
              }
                  
          }while(flag==1);
    }*/
    
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
         try (PreparedStatement ps=dbConnect.prepareStatement(requete)) {

            ps.setInt(1, idb);
            try (ResultSet rs=ps.executeQuery()) {
                if (rs.next()) {
                    int Idbur=rs.getInt("id_bur");
                    String Sigle=rs.getString("Sigle");
                    String Tel=rs.getString("Tel");
                    System.out.println(Sigle+"  "+Tel);
                    return new Bureau(Idbur, Sigle, Tel);

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
        String req1="insert into Bureau(id_bur,Sigle,Tel) values(?,?,?)";
        String req2="select Id_bur from Bureau where Sigle=? and Tel=?";
        
        try (PreparedStatement ps=dbConnect.prepareStatement(req1);
             PreparedStatement ps2=dbConnect.prepareStatement(req2)) {
            ps.setInt(1, obj.getidbur());
            ps.setString(2, obj.getSigle());
            ps.setString(3, obj.getTel());
            
            int n=ps.executeUpdate();
            
            if (n==0) {
                throw new SQLException("erreur de creation client, aucune ligne créée");
            }
           
            ps2.setString(1, obj.getSigle());
            ps2.setString(2, obj.getTel());
            
            try (ResultSet rs=ps2.executeQuery()) {
                if (rs.next()) {
                    int Idbur=rs.getInt(1);
                    obj.setIdbur(Idbur);
                    return read(Idbur);
                } else {
                    throw new SQLException("erreur de création client, record introuvable");
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
        String requete="update bureau set Sigle=?,Tel=? where Idbur=?";
            Connection dbConnect=DBconnect.getConnection();
            if(dbConnect==null){
                System.exit(1);
              }
     try (PreparedStatement ps=dbConnect.prepareStatement(requete)) {

            ps.setInt(8, obj.getidbur());
            ps.setString(1, obj.getSigle());
            ps.setString(2, obj.getTel());
            
            int n=ps.executeUpdate();
            if (n==0) {
                throw new SQLException("aucune ligne client mise à jour");
            }
            return read(obj.getidbur());
        }   
    }

    /**
     *
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
      /*  do{
            stmt=dbConnect.createStatement();
            String req="SELECT FROM Bureau where Id_bur=?";    
            String requete="delete from Bureau where Id_bur= ?";
            PreparedStatement ps=dbConnect.prepareStatement(requete);
              
            /*try (PreparedStatement ps=dbConnect.prepareStatement(requete)) {
                 PreparedStatement ps2=dbConnect.prepareStatement(req);*/

       /*          ps.setInt(1, obj.getidbur());

                 int n=ps.executeUpdate();
                 if (n==0) {
                     throw new SQLException("aucune ligne bureau effacée");
                  }
         
         }while(flag==1); */
       String req="SELECT FROM Bureau WHERE id_bur=?";
       String sql = "DELETE FROM Bureau WHERE id_bur=?";
       PreparedStatement stmt=dbConnect.prepareStatement(req);
         int Idbur=rs.getInt("id_bur");
         String Sigle=rs.getString("Sigle");
         String Tel=rs.getString("Tel");
         
       PreparedStatement statement = dbConnect.prepareStatement(sql);
       statement.setInt(1, bursup);
 
       int rowsDeleted = statement.executeUpdate();
       if (rowsDeleted > 0) {
            System.out.println("A user was deleted successfully!");
        }
        }  
}
