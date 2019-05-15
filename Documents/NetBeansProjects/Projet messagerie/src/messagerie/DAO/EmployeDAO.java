/**
 *
 * @author RafgiMepo
*/
package messagerie.DAO;

import java.sql.*;
import java.util.*;
import messagerie.metier.Employe;
import myconnections.DBconnect;


public class EmployeDAO extends DAO<Employe> {
    
    Employe e;
    Statement stmt=null;
    ResultSet rs=null;
    String matricule, nom, prenom;
    int id_emp,id_bur;
    String new_nom,new_prenom,new_matricule;
    int new_id,new_idbur;
    
    
    public static void main(String[]args) throws SQLException{
        EmployeDAO emp=new EmployeDAO();
        emp.menu();
   
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
        System.out.println("1. Lire un employe");
        System.out.println("2. Creer un employe");
        System.out.println("3. Modifier un employe");
        System.out.println("4. Supprimer un eploye");
        
        choix=sc.nextInt();
        
        switch (choix) {
            case 1:
                int ide;
                System.out.println("Quel est l'id de l'employe ?");
                ide=sc.nextInt();
                read(ide);
                break;
                
            case 2:
                nouveau();
                create(e);
                break;
            
            case 3:
                int idmodif;
                
                System.out.println("Quel est l'id de l'employe à modifier ?");
                idmodif=sc.nextInt(); 
                stmt = dbConnect.createStatement();
                    rs = stmt.executeQuery("select * from employe");                 
                    while(rs.next()){
                        if(rs.getInt("Id_emp") == idmodif){
                            matricule = rs.getString("MATRICULE");
                            nom= rs.getString("NOM");
                            prenom= rs.getString("PRENOM");
                            id_bur=rs.getInt("id_bur");
                            break;
                        }
                    }
                e=new Employe(id_emp,matricule,nom,prenom,id_bur);
                update(e);
                System.out.println("La modification est effectuée");
                break;
            
            case 4:
                int idsup,flag=0;
                System.out.println("Quel est l'id du bureau à supprimer ?");
                idsup=sc.nextInt(); 
                stmt = dbConnect.createStatement();
                    rs = stmt.executeQuery("select * from employe");
                    String q = "select * from employe where id_emp = ?";
                    while(rs.next()){
                        if(rs.getInt("id_emp") == idsup){
                            matricule = rs.getString("MATRICULE");
                            nom= rs.getString("NOM");
                            prenom= rs.getString("PRENOM");
                            id_bur=rs.getInt("id_bur");
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
                        e = new Employe(idsup, matricule, nom,prenom,id_bur);
                        delete(e);
                        System.out.println("La suppression à bien été effectuée.");
                    }
                    else{
                        System.out.println("Assignez ces employés à un autre bureau avant de le supprimer.");
                    } 
              
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
   
    public void nouveau() throws SQLException{
        int flag;
        Scanner sc=new Scanner(System.in);
        Scanner sc2=new Scanner(System.in);
        System.out.println("Nouveau Employe : ");
         Connection dbConnect=DBconnect.getConnection();
         if(dbConnect==null){
         System.exit(1);
    }
          do{
              flag=1;
              stmt=dbConnect.createStatement();
              rs=stmt.executeQuery("Select * FROM employe");
              System.out.println("Nouveau id de l'employe ?");
              new_id=sc.nextInt();
              System.out.println("Nouveau matricule ?");
              new_matricule=sc2.nextLine();
              System.out.println("Nouveau nom ?");
              new_nom=sc2.nextLine();
              System.out.println("Nouveau prenom ?");
              new_prenom=sc2.nextLine();
              System.out.println("Nouveau id bur ?");
              new_idbur=sc2.nextInt();
              while(rs.next()){
                  
                 int idemp=rs.getInt("id_emp");
                 String bur=rs.getString("id_bur");
                 String name=rs.getString("nom");
                 String mtc=rs.getString("Matricule");
                 String fname=rs.getString("Prenom");
                 
                 if((idemp==new_id)&&(mtc.equals(new_matricule))&&(name.equals(new_nom))&&(fname.equals(new_prenom))&&(bur.equals(new_idbur))){
                    flag=0;
                    break;
                  }
              }
              if(flag==0){
                  System.out.println("Employe déjà existant");
              }
                  
          }while(flag==0);
          e=new Employe(new_id,new_matricule,new_nom,new_prenom,new_idbur);
    }

    @Override
    public Employe read(int ide) throws SQLException {
        Connection dbConnect=DBconnect.getConnection();
            if(dbConnect==null){
            System.exit(1);
            }
        String requete="select * from Employe where id_emp=?";
         try (PreparedStatement ps=dbConnect.prepareStatement(requete)) {

            ps.setInt(1, ide);
            try (ResultSet rs=ps.executeQuery()) {
                if (rs.next()) {
                    int id_emp=rs.getInt("id_emp");
                    String matricule=rs.getString("Matricule");
                    String nom=rs.getString("Nom");
                    String prenom=rs.getString("Prenom");
                    int id_bur=rs.getInt("id_bur");
                    
                    System.out.println(matricule+"  "+nom+""+prenom+""+id_bur);
                    return new Employe(id_emp, matricule, nom, prenom,id_bur);

                } else {
                    throw new SQLException("Code inconnu");
                }

            }
        }
    }

    @Override
    public Employe create(Employe obj) throws SQLException {

            Connection dbConnect=DBconnect.getConnection();
            if(dbConnect==null){
            System.exit(1);
            }
        String req1="insert into Employe(id_emp,matricule,nom,prenom,id_bur) values(?,?,?,?,?)";
        String req2="select id_emp from Employe where matricule=?, nom=?, prenom=? and id_bur=?";
        
        try (PreparedStatement ps=dbConnect.prepareStatement(req1);
             PreparedStatement ps2=dbConnect.prepareStatement(req2)) {
            ps.setInt(1, obj.getId_emp());
            ps.setString(2, obj.getMatricule());
            ps.setString(3, obj.getNom());
            ps.setString(4, obj.getPrenom());
            ps.setInt(5, obj.getId_bur());
            
            int n=ps.executeUpdate();
            
            if (n==0) {
                throw new SQLException("erreur de creation employe, aucune ligne créée");
            }
           
            ps2.setString(1, obj.getMatricule());
            ps2.setString(2, obj.getNom());
            ps2.setString(3, obj.getPrenom());
            ps2.setInt(4, obj.getId_bur());
            
            try (ResultSet rs=ps2.executeQuery()) {
                if (rs.next()) {
                    int id_emp=rs.getInt(1);
                    obj.setId_emp(id_emp);
                    return read(id_emp);
                } else {
                    throw new SQLException("erreur de création client, record introuvable");
                }
            }
        }
    }

    @Override
    public Employe update(Employe obj) throws SQLException {
        Connection dbConnect=DBconnect.getConnection();
            if(dbConnect==null){
              System.exit(1);
            }
     String Nmat,Nnom,Nprenom;
     int Nid_bur;
     
     Scanner sc= new Scanner(System.in);
     String requete="update Employe set Matricule=?,Nom=?,prenom=?,id_bur=? where id_emp=?";
     try (PreparedStatement ps=dbConnect.prepareStatement(requete)) {

           System.out.println("Nouveau matricule ?");
           Nmat=sc.nextLine();
           System.out.println("Nouveau nom ?");
           Nnom=sc.nextLine();
           System.out.println("Nouveau prenom ? ");
           Nprenom=sc.nextLine();
           System.out.println("Nouveau id bureau ? ");
           Nid_bur=sc.nextInt();
           
            ps.setInt(5, obj.getId_emp());
            ps.setString(1, Nmat);
            ps.setString(2, Nnom);
            ps.setString(3, Nprenom);
            ps.setInt(4, Nid_bur);
            
            int n=ps.executeUpdate();
            if (n==0) {
                throw new SQLException("aucune ligne employe mise à jour");
            }
            return read(obj.getId_emp());
        }   
    }

    @Override
    public void delete(Employe obj) throws SQLException {
     
        Connection dbConnect=DBconnect.getConnection();
            if(dbConnect==null){
              System.exit(1);
            }
       String sql = "DELETE FROM Employe WHERE id_emp=?";
       PreparedStatement stmt=dbConnect.prepareStatement(sql);
        stmt.setInt(1,obj.getId_emp());
 

       int rowsDeleted = stmt.executeUpdate();
       if (rowsDeleted==0) {
                throw new SQLException("aucune ligne employe effacée");
            }
    } 
    

}
