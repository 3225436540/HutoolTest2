package com.rj.bd;

import java.awt.Font;
import java.util.List;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.word.Word07Writer;

/**、
 * @desc  word导出
 * @author T
 *
 */
public class ceshi {

	public static void main(String[] args) {
		
		//请求列表页
		String listContent = HttpUtil.get("https://www.oschina.net/action/ajax/get_more_news_list?newsType=&p=2");
		//使用正则获取所有标题
		List<String> titles = ReUtil.findAll("<span class=\"text-ellipsis\">(.*?)</span>", listContent, 1);
		//用来存储内容
		String str = null;
		for (String title : titles) {
		    //打印标题
		    Console.log(title);
		}
		for (int i = 0; i < titles.size(); i++) {
			 //将爬到到的内容给字符串
			 str = titles.toString();
		}
		Word07Writer writer = new Word07Writer();
		// 添加段落（标题）
		writer.addText(new Font("方正小标宋简体", Font.PLAIN, 22), "测试");
		// 添加段落（正文）
		writer.addText(new Font("宋体", Font.PLAIN, 15), str);
		//writer.addText(new Font("宋体", Font.PLAIN, 15), "2. 因双方就本协议的签订、履行或解释发生争议，双方应持平等、友好、争取合理解决问题的态度协商解决；");
		
		// 写出到文件
		writer.flush(FileUtil.file("D:/测试.docx"));
		System.out.println("word导出成功");
		// 关闭
		writer.close();	
	}
	
}
