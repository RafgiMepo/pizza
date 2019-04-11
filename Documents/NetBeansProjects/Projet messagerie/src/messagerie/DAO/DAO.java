/**
 *
 * @author RafgiMepo
 */

package messagerie.DAO;
import java.sql.Connection;
import java.sql.SQLException;


public abstract class DAO<T> {

   protected Connection dbConnect ;
	

   public  void setConnection(Connection nouvdbConnect) {
      dbConnect=nouvdbConnect;
   }

	public abstract T read(int id)throws SQLException;
	
	
	public abstract T create(T obj) throws SQLException;
	
	
	public abstract T update(T obj)throws SQLException;
	

	public abstract void delete(T obj) throws SQLException;
        
      
}