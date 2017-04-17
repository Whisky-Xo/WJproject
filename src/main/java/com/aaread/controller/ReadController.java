package com.aaread.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aaread.mapper.ArticleMapper;
import com.aaread.model.Article;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class ReadController {

	@Autowired
	private ArticleMapper articleMapper;
	
	@RequestMapping(value="/")
	public String index(@RequestParam(defaultValue="1")int pageNo, @RequestParam(defaultValue="10")int pageSize, Integer type, Model model,HttpServletRequest request) {
		
		PageHelper.startPage(pageNo,pageSize);
		List<Article> list = articleMapper.listVideo(type);
		PageInfo<Article> pageInfo = new PageInfo<Article>(list,5);
		List<Article> rlist = articleMapper.selectRandom();
		model.addAttribute("list", rlist);
		model.addAttribute("type", type);
		model.addAttribute("page", pageInfo);
		return "web2/default";
	}
	@RequestMapping(value="video_{param}")
	public String video(@PathVariable(value="param") String param, Model model,HttpServletRequest request) {
		String[] arr = StringUtils.splitPreserveAllTokens(param, "_");
		Integer type = null;
		int pageNo = 1;
		int pageSize = 10;
		if(!StringUtils.isEmpty(arr[0])){
			type = Integer.valueOf(arr[0]);
		}
		if(!StringUtils.isEmpty(arr[1])){
			pageNo = Integer.valueOf(arr[1]);
		}
		PageHelper.startPage(pageNo,pageSize);
		List<Article> list = articleMapper.listVideo(type);
		PageInfo<Article> pageInfo = new PageInfo<Article>(list,5);
		List<Article> rlist = articleMapper.selectRandom();
		model.addAttribute("list", rlist);
		model.addAttribute("type", type);
		model.addAttribute("page", pageInfo);
		return "web2/default";
	}
	@RequestMapping(value="page_{pageNo}")
	public String page(@PathVariable(value="pageNo") int pageNo, @RequestParam(defaultValue="10")int pageSize, Model model,HttpServletRequest request) {
		
		PageHelper.startPage(pageNo,pageSize);
		List<Article> list = articleMapper.list();
		PageInfo<Article> pageInfo = new PageInfo<Article>(list,5);
		 List<Article> rlist = articleMapper.selectRandom();
	     model.addAttribute("list", rlist);
		model.addAttribute("page", pageInfo);
		return "web2/default";
	}
	@RequestMapping(value="item_{id}")
	public String item(@PathVariable(value="id") int id, Model model,HttpServletRequest request) {
		Article data = articleMapper.selectByPrimaryKey((long)id);
		List<Article> list = articleMapper.selectRandom();
		model.addAttribute("data", data);
		model.addAttribute("list", list);
		if(data.getType() != 0){
			return "web2/item_video";
		}
		return "web2/item";
	}
}
