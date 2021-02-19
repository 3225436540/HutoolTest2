package com.rj.bd;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.hutool.extra.pinyin.PinyinUtil;

/**
 * @desc  文字转拼音小例子
 * @author T
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/code.do"})
public class Code extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		// 允许该域发起跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");//*允许任何域
        // 允许的外域请求方式
        response.setHeader("Access-Control-Allow-Methods", "POST, GET");
        // 在999999秒内，不需要再发送预检验请求，可以缓存该结果
        response.setHeader("Access-Control-Max-Age", "999999");
        // 允许跨域请求包含某请求头,x-requested-with请求头为异步请求
        response.setHeader("Access-Control-Allow-Headers","x-requested-with");
		/* 是否携带cookie */
		response.setHeader("Access-Control-Allow-Credentials", "true"); 
		
		
		if ("pinyinzhuanhuan".equals(method)) {
			this.pinyinzhuanhuan(request,response);
		}
	}
	
	/**
	 * @desc  拼音转换
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	private void pinyinzhuanhuan(HttpServletRequest request, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("服务");
		String pinyin = request.getParameter("inputvalue");//获取输入内容
		
		String pinyin2 = PinyinUtil.getPinyin(pinyin, " "); //转换字符串为拼音
		pinyin=pinyin.replace("", " ");//每个字之间插入空格
		System.out.println(pinyin);
		System.out.println(pinyin2);
		Map<String, Object> msg = new HashMap<String, Object>();
		
		if (pinyin2!=null) {
			msg.put("flag",true);
			msg.put("err", pinyin);
			msg.put("err2", pinyin2);
		}else{
			msg.put("flag", false);
			msg.put("err", pinyin);
			msg.put("err2",pinyin2);
		}
		ObjectMapper mapper = new ObjectMapper();
	    String json = mapper.writeValueAsString(msg);
		System.out.println(json);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}
	
}
