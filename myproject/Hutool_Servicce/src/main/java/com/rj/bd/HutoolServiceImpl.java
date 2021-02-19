package com.rj.bd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.rj.bd.dao.DaoImpl;
import com.rj.bd.dao.IDao;
/**
 * @desc  实现类
 * @author T
 *
 */
public class HutoolServiceImpl implements HutoolService {
	IDao dao = new DaoImpl();
	
	/**
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 * @desc 添加用户
	 */
	public void adduser(String uuid, String username, String user_password, String useremail,String N) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		String sql="INSERT INTO user VALUES(?,?,?,?,?)";
		dao.executeUpdate(sql, new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR}, new Object[]{uuid,username,user_password,useremail,N});
		
	}
	
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @desc 根据用户id获取用户
	 */
	public Map<String, Object> setstate(String id) throws ClassNotFoundException, SQLException {
		String sql = "select * from user where userid = ?";
		return dao.executeQueryForMap(sql, new int []{Types.VARCHAR}, new Object[]{id});
	}

	public void mate_code(String id) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		String sql = "update user set  user_state='Y' where userid=?";
		dao.executeUpdate(sql, new int[]{Types.VARCHAR}, new Object[]{id});
		
	}

	
}
