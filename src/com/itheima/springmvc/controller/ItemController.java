package com.itheima.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itheima.springmvc.conversion.DateConverter;
import com.itheima.springmvc.exception.MessageException;
import com.itheima.springmvc.pojo.Items;
import com.itheima.springmvc.pojo.QueryVo;
import com.itheima.springmvc.service.ItemService;
import com.sun.org.apache.regexp.internal.recompile;
@Controller
public class ItemController {
	
	@Autowired
	public ItemService itemService;
	/**
	 * 1，ModelAndView	无敌	带着数据	返回视图路径
	 * 2，String 返回视图路径	model带数据	官方推荐 	解耦	数据	视图	分离	MVC
	 * 3，void	ajax	不用跳转视图	请求	 异步请求时		json数据返回（response）	异步请求时用
	 * @param model
	 * @return
	 * @throws MessageException 
	 */
	@RequestMapping(value= {"/itemList.action","/itemListhbc.action"})
	public String queryItemList(Model model, HttpServletRequest request,
			HttpServletResponse response) throws MessageException {
		
//		Integer integer = 1/0;
//		if(null == null) {
//			throw new MessageException("商品信息不能为空");
//		}
		// 从MySQL中查询
		List<Items> list = itemService.selectItemsList();
		
		model.addAttribute("itemList",list);
		return "itemList";
	}
	//修改页面入参 id；
	@RequestMapping("/itemEdit.action")
	public ModelAndView toEdit(Integer id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
	
		Items items = itemService.selectItemById(id);		
		// 创建ModelAndView，用来存放数据和视图
		ModelAndView modelAndView = new ModelAndView();
		// 设置数据到模型中
		modelAndView.addObject("item", items);
		// 设置视图jsp，需要设置视图的物理地址
		modelAndView.setViewName("editItem");
		return modelAndView;
	}
	//修改 update
	@RequestMapping("/updateitem.action")
	public String updateitem(QueryVo vo, MultipartFile pictureFile) throws IllegalStateException, IOException {
		
		//保存图片到 D：/img
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		String extension = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
		
		pictureFile.transferTo(new File("D:\\upload\\" + name + "." + extension) );
		
		vo.getItems().setPic(name + "." + extension);
		//修改
		itemService.updateItemsById(vo.getItems());
//		return "redirect:/itemEdit.action?id=" + vo.getItems().getId();
		return "redirect:/itemEdit/{id}.action" ;
//		return "forward:/itemList.action";  //最好不要用内部转发，会有错误
	}
	
	//删除多个
	@RequestMapping("/deletes.action")
	public ModelAndView deletesitems(Integer[] ids) {	
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("sucess");
		return modelAndView;
	}
	//修改
	@RequestMapping("/updates.action")
	public ModelAndView updates(QueryVo vo) {	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sucess");
		return modelAndView;
	}
	//json数据交互
	@RequestMapping("/json.action")
	@ResponseBody
	public Items json(@RequestBody Items items) {
		System.out.println(items);
		return items;
	}
	
	@RequestMapping("/itemEdit/{id}.action")
	public ModelAndView toEdit1(@PathVariable Integer id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
	
		Items items = itemService.selectItemById(id);		
		// 创建ModelAndView，用来存放数据和视图
		ModelAndView modelAndView = new ModelAndView();
		// 设置数据到模型中
		modelAndView.addObject("item", items);
		// 设置视图jsp，需要设置视图的物理地址
		modelAndView.setViewName("editItem");
		return modelAndView;
	}
	
	//登录
	@RequestMapping(value="/login.action", method=RequestMethod.GET)
	public String login( ) {
	
		return "login";
	}
	
	//提交
	@RequestMapping(value="/login.action", method=RequestMethod.POST)
	public String login(String username, HttpSession httpSession) {
		httpSession.setAttribute("USER_SESSION", username);
		return "redirect:/itemList.action";
	}
}
