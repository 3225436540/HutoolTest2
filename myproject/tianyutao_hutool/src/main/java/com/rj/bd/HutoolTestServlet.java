package com.rj.bd;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import java.util.Map;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;

import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.mail.MailUtil;

/**
 * @desc  注册hutool邮件发送小案例
 * @author T
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/user.do"})
public class HutoolTestServlet extends HttpServlet{
	HutoolService hutool = new HutoolServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求
		String method = request.getParameter("method");
		System.out.println("当前方法为"+method);
		try {
			if ("zhuce".equals(method)) {
				this.zhuce(request,response);
			}else if("verify".equals(method)){
				this.verify(request,response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @desc  验证邮箱
	 * @param request
	 * @param response
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private void verify(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		String id = request.getParameter("userid");
		Map<String, Object> user = hutool.setstate(id);
		if (user != null) {
			hutool.mate_code(id);
		}
		response.setContentType("text/html;charset=utf-8");
	}

	/**
	 * @desc 注册用户
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	private void zhuce(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		//接前端输入框传过来的值
		String username = request.getParameter("user_name");
		String user_password = request.getParameter("user_password");
		String useremail = request.getParameter("user_email");
		String uuid = IdUtil.randomUUID();
		System.out.println(uuid);
		String N = "N";
		
		System.out.println("用户"+username+"密码："+user_password+"邮箱"+useremail );
		hutool.adduser(uuid,username,user_password,useremail,N);
		
		MailUtil.send(useremail, "新用户注册激活测试", "<a href='http://127.0.0.1:8089/tianyutao_hutool/user.do?method=verify&userid="+uuid+"'>【点击激活账号】</a>", true);
	}
}
	

	
	

