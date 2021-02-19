package com.rj.bd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 * @desc  接口
 * @author T
 *
 */
public interface HutoolService {
	//添加用户方法
	void adduser(String uuid, String username, String user_password, String useremail, String N) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;
	//根据用户id获取用户
	Map<String, Object> setstate(String id) throws ClassNotFoundException, SQLException;
	//根据用户id修改状态位
	void mate_code(String id) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

}
