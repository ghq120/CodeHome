
import org.junit.Test;

import com.ghq.model.utils.Db;


public class TestDb {
	@Test
	public void textupdate(){
		String sql = "UPDATE vote_tb SET votenum = votenum + 1 WHERE ID = ? ";
		int row = Db.exeUpdate(sql,1);
		if (row > 0) {
			System.out.println("true");
		}
		System.out.println("false");
		
		
		/*String sql = "INSERT INTO vote_tb VALUES(3,'123',0)";
		int row = Db.exeUpdate(sql);
		if (row > 0) {
			System.out.println("true");
		}else{
			System.out.println("false");
		}*/
		

	}
}
