package com.aaread.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aaread.mapper.ContentMapper;
import com.aaread.mapper.DianzanMapper;
import com.aaread.mapper.DingyueMapper;
import com.aaread.mapper.FenzhanMapper;
import com.aaread.mapper.MsgMapper;
import com.aaread.mapper.TopicMapper;
import com.aaread.mapper.UsersMapper;
import com.aaread.mapper.WaterMapper;
import com.aaread.model.Content;
import com.aaread.model.Dianzan;
import com.aaread.model.Dingyue;
import com.aaread.model.Fenzhan;
import com.aaread.model.Msg;
import com.aaread.model.Topic;
import com.aaread.model.Users;
import com.aaread.model.Water;
import com.aaread.redis.client.RedisCache;
import com.aaread.service.OrderService;
import com.aaread.service.UserService;
import com.aaread.util.HttpClientUtils;
import com.aaread.util.MD5Util;
import com.aaread.util.OcssUtiil;
import com.ksyun.ks3.dto.ObjectMetadata;
import com.ksyun.ks3.utils.Md5Utils;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ContentMapper contentMapper;
	@Autowired
	private TopicMapper topicMapper;
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private DianzanMapper dianzanMapper;
	@Autowired
	private DingyueMapper dingyueMapper;
	@Autowired
	private MsgMapper msgMapper;
	@Autowired
	private RedisCache redisCache;
	@Autowired
	private OrderService orderService;
	@Autowired
	private FenzhanMapper fenzhanMapper;
	@Autowired
	private WaterMapper waterMapper;
	@ResponseBody
	@RequestMapping(value="save")
	public Map<String,Object> save(
			@RequestParam(value = "myFile", required = false) MultipartFile file,
			Content content,HttpServletRequest request){
		Map<String,Object> result =  new HashMap<String, Object>();
		try {
			String uid = (String) request.getAttribute("login_user");
			content.setUid(uid);
			content.setCreateTime(new Date());
			Users user = usersMapper.selectByUid(uid);
			content.setUsername(user.getName());
			if(file != null){
				String fileName = file.getOriginalFilename();
				fileName = "user/"+generateFileName(fileName);
				InputStream in = file.getInputStream();
				// 创建上传Object的Metadata
				ObjectMetadata meta = new ObjectMetadata();
				// 必须设置ContentLength
				meta.setContentLength(file.getSize());
				meta.setContentType("image/jpeg");
				OcssUtiil.sendFileToOss(fileName,in,meta );
				content.setPic(fileName);
			}
			contentMapper.insertSelective(content);
			usersMapper.updateArtNum(uid);
			result.put("code", 0);
			result.put("msg", "发布成功");
			result.put("data", content.getId());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", -1);
			result.put("msg", "发布失败");
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value="save2")
	public Map<String,Object> save2(
			Content content,HttpServletRequest request){
		Map<String,Object> result =  new HashMap<String, Object>();
		try {
			String uid = (String) request.getAttribute("login_user");
			content.setUid(uid);
			content.setCreateTime(new Date());
			Users user = usersMapper.selectByUid(uid);
			content.setUsername(user.getName());
			if(!StringUtils.isEmpty(content.getPic())){
				String fileName = "";
				fileName = "user/"+generateFileName(fileName);
				// 创建上传Object的Metadata
				ObjectMetadata meta = new ObjectMetadata();
				// 必须设置ContentLength
				String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+userService.getAccessToken()+"&media_id="+content.getPic();
				byte[] file = HttpClientUtils.getHttpResultBytes(url);
				InputStream in = new ByteArrayInputStream(file);
				meta.setContentLength(file.length);
				meta.setContentType("image/jpeg");
				OcssUtiil.sendFileToOss(fileName, in, meta);
				content.setPic(fileName);
			}
			contentMapper.insertSelective(content);
			usersMapper.updateArtNum(uid);
			result.put("code", 0);
			result.put("msg", "发布成功");
			result.put("data", content.getId());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", -1);
			result.put("msg", "发布失败");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="updateCate")
	public Map<String,Object> updateCate(long id,String cate,Long topicId){
		Map<String,Object> result =  new HashMap<String, Object>();
		try {
			Content content = contentMapper.selectByPrimaryKey(id);
			content.setTopicId(topicId);
			contentMapper.updateByPrimaryKeySelective(content);
			result.put("code", 0);
			result.put("msg", "发布成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", -1);
			result.put("msg", "发布失败");
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value="myList")
	public Map<String,Object> myList(){
		Map<String,Object> result =  new HashMap<String, Object>();
		try {
			//TODO
			redisCache.putString("aaa", "aaaaa");
			List<Content> content = null;
			result.put("code", 0);
			result.put("msg", "发布成功");
			result.put("data", redisCache.getString("aa"));
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", -1);
			result.put("msg", "发布失败");
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value="list")
	public Map<String,Object> list(Long topicId,String uid,Integer start,@RequestParam(defaultValue="10")Integer limit){
		Map<String,Object> result =  new HashMap<String, Object>();
		try {
			//TODO
			List<Content> contents = contentMapper.list(topicId,uid,start,limit);
			if(contents != null && contents.size()>0){
				for(Content c : contents){
					Users u = usersMapper.selectByUid(c.getUid());
					c.setUserPic(u.getPic());
				}
			}
			result.put("code", 0);
			result.put("msg", "成功");
			result.put("data", contents);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", -1);
			result.put("msg", "失败");
		}
		return result;
	}

	@RequestMapping(value="dingyue")
	public String topicList(Model model){
		try {
			List<Topic> contents = topicMapper.list();
			model.addAttribute("datas", contents);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dingyue";
	}
	@RequestMapping(value="faxian")
	public String faxian(Model model){
		try {
			List<Topic> contents = topicMapper.list();
			model.addAttribute("datas", contents);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "faxian";
	}
	@RequestMapping(value="xuanzetopic")
	public String xuanzetopic(Model model){
		try {
			List<Topic> contents = topicMapper.list();
			model.addAttribute("datas", contents);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "xuanzetopic";
	}
	@RequestMapping(value="topic")
	public String topicList(int id,Model model){
		Topic topic = topicMapper.selectByPrimaryKey(Long.valueOf(id));
		model.addAttribute("topic", topic);
		return "topic";
	}
	@RequestMapping(value="toic_jieshao")
	public String topicDetail(int id,Model model,HttpServletRequest request){
		String uid = (String) request.getAttribute("login_user");
		Topic topic = topicMapper.selectByPrimaryKey(Long.valueOf(id));
		Dingyue dingyue = dingyueMapper.selectByUidTid(id, uid);
		model.addAttribute("topic", topic);
		model.addAttribute("dingyue", dingyue);
		return "toic_jieshao";
	}
	@RequestMapping(value="neirong")
	public String nerong(long id,Model model){
		Content content = contentMapper.selectByPrimaryKey(id);
		if(content.getTopicId()!=null){
			Topic topic = topicMapper.selectByPrimaryKey(content.getTopicId());
			if(topic != null){
				content.setTopicName(topic.getName());
			}
		}
		Users u = usersMapper.selectByUid(content.getUid());
		content.setUserPic(u.getPic());
		model.addAttribute("content", content);
		return "neirong";
	}
	
	@RequestMapping(value="my")
	public String my(Model model,HttpServletRequest request){
		try {
			String uid = (String) request.getAttribute("login_user");
			Users u = usersMapper.selectByUid(uid); 
			model.addAttribute("user", u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "my";
	}
	@RequestMapping(value="taren")
	public String taren(String uid,Model model,HttpServletRequest request){
		try {
//			String uid = (String) request.getAttribute("login_user");
			Users u = usersMapper.selectByUid(uid); 
			model.addAttribute("user", u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "taren";
	}
	@RequestMapping(value="gerenzl")
	public String gerenzl(Model model,HttpServletRequest request){
		try {
			String uid = (String) request.getAttribute("login_user");
			Users u = usersMapper.selectByUid(uid); 
			model.addAttribute("user", u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "gerenzl";
	}
	
	@RequestMapping(value="qianbao")
	public String qianbao(Model model,HttpServletRequest request){
		try {
			String uid = (String) request.getAttribute("login_user");
			Users u = usersMapper.selectByUid(uid); 
			model.addAttribute("user", u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qianbao";
	}
	
	@ResponseBody
	@RequestMapping(value="dianzan")
	public Map<String,Object> dianzan(long cId,HttpServletRequest request){
		Map<String,Object> result =  new HashMap<String, Object>();
		try {
			String uid = (String) request.getAttribute("login_user");
			Dianzan d = new Dianzan();
			d.setUid(uid);
			d.setcId(cId);
			dianzanMapper.insertSelective(d);
			contentMapper.updateDianzan(cId);
			result.put("code", 0);
			result.put("msg", "成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", -1);
			result.put("msg", "失败");
		}
		return result;
	}
	@RequestMapping(value="dingyueTopic")
	public String dingyueTopic(long tid,HttpServletRequest request){
		try {
			String uid = (String) request.getAttribute("login_user");
			Dingyue d = new Dingyue();
			d.setUid(uid);
			d.setTopicId(tid);
			dingyueMapper.insertSelective(d);
			topicMapper.updateDingyue(tid);
			return "redirect:toic_jieshao.html?msg=1&id="+tid;
		} catch (Exception e) {
		}
		return "redirect:toic_jieshao.html?id="+tid;
	}
	@RequestMapping(value="quxiaoDingyue")
	public String quxiaoDingyue(int tid,HttpServletRequest request){
		try {
			String uid = (String) request.getAttribute("login_user");
			Dingyue dingyue = dingyueMapper.selectByUidTid(tid, uid);
			if(dingyue != null){
				dingyueMapper.deleteByPrimaryKey(dingyue.getId());
				topicMapper.quxiaoDingyue(tid);
			}
			return "redirect:toic_jieshao.html?msg=1&id="+tid;
		} catch (Exception e) {
		}
		return "redirect:toic_jieshao.html?id="+tid;
	}
	
	@RequestMapping(value="msg")
	public String msg(Model model,HttpServletRequest request){
		String uid = (String) request.getAttribute("login_user");
		 List<Msg> msgs= msgMapper.selectMsgList(uid);
		 if(msgs != null && msgs.size()>0){
			 for(Msg msg : msgs){
				 Users fUser = null;
				 if(msg.getSender().equals(uid)){
					 fUser = usersMapper.selectByUid(msg.getReceiver());
				 }else{
					 fUser = usersMapper.selectByUid(msg.getSender());
				 }
				 msg.setFuser(fUser);
				 Msg last = msgMapper.selectByPrimaryKey(msg.getId());
				 msg.setContent(last.getContent());
				 msg.setCreateTime(last.getCreateTime());
			 }
		 }
		 model.addAttribute("msgs", msgs);
		return "msg";
	}
	@RequestMapping(value="msg_item")
	public String msgItem(String other, Model model,HttpServletRequest request){
		String uid = (String) request.getAttribute("login_user");
		String sid = null;
		if(other.compareTo(uid)>=0){
			sid = other+uid;
		}else{
			sid = uid+other;
		}
		sid = StringUtils.replaceOnce(MD5Util.MD5Encode(sid, "UTF-8"),"-","");
		List<Msg> msgs = msgMapper.selectMsgItemList(sid);
		Users user = usersMapper.selectByUid(uid);
		Users otherUser = usersMapper.selectByUid(other);
		model.addAttribute("user", user);
		model.addAttribute("uid", uid);
		model.addAttribute("other", otherUser);
		model.addAttribute("msgs", msgs);
		return "msg_item";
	}
	@RequestMapping(value="msgSave")
	public String msgSave(Model model,Msg msg,HttpServletRequest request){
		String uid = (String) request.getAttribute("login_user");
		String sid = null;
		if(msg.getReceiver().compareTo(uid)>=0){
			sid = msg.getReceiver()+uid;
		}else{
			sid = uid+msg.getReceiver();
		}
		sid = StringUtils.replaceOnce(MD5Util.MD5Encode(sid, "UTF-8"),"-","");
		msg.setStatus(0);
		msg.setCreateTime(new Date());
		msg.setSid(sid);
		msgMapper.insertSelective(msg); 
//		
//		Users user = usersMapper.selectByUid(uid);
//		Users other = usersMapper.selectByUid(msg.getReceiver());
//		List<Msg> msgs = msgMapper.selectMsgItemList(sid);
//		model.addAttribute("msgs", msgs);
//		model.addAttribute("user", user);
//		model.addAttribute("other", other);
		return "redirect:msg_item?other="+msg.getReceiver();
	}
	
	
	
	private String generateFileName(String fileName) {
    	UUID uuid = UUID.randomUUID();
    	int position = fileName.lastIndexOf(".");
    	String extension = ".jpg";
    	if(position > 0){
    		extension = fileName.substring(position);
    	}
    	return uuid + extension;
    }
	
	
	
	@RequestMapping(value="saveUser")
	public String saveUser(Users user,HttpServletRequest request){
		String uid = (String) request.getAttribute("login_user");
		Users u = usersMapper.selectByUid(uid);
		user.setId(u.getId());
		usersMapper.updateByPrimaryKeySelective(user);
		return "redirect:gerenzl.html";
	}
	
	@RequestMapping(value="input")
	public String input(String name,Model model,HttpServletRequest request){
		model.addAttribute("name", name);
		return "input";
	}
	
	
	@RequestMapping(value = "/{path}")
	public String path(@PathVariable String path,
			@RequestParam(defaultValue = "") String url,
			@RequestParam(defaultValue = "") String message, ModelMap model) {
		if (!StringUtils.isEmpty(message)) {
			//model.addAttribute("message", message);
		}
		model.put("menu", path);
		model.put("url", url);
		if(StringUtils.isEmpty(path)){
			return "web2/default";
		}
		return "/" + path;
	}
	@RequestMapping(value="mydingyue")
	public String mydingyue(Model model,HttpServletRequest request){
		String uid = (String) request.getAttribute("login_user");
		Users user = usersMapper.selectByUid(uid);
		model.addAttribute("user", user);
		return "mydingyue";
	}
	
	@ResponseBody
	@RequestMapping(value="dingyuepay")
	public Map<String,Object> dingyuepay(int days, HttpServletRequest request){
		Map<String,Object> result =  new HashMap<String, Object>();
		try {
			String uid = (String) request.getAttribute("login_user");
			Users user = usersMapper.selectByUid(uid);
			if(user.getBalance()!=null && user.getBalance()>0
					&& (user.getBalance()-days*10) >=0){
				Date end = null;
				if(user.getEndTime()==null || user.getEndTime().getTime() < System.currentTimeMillis()){
					end = DateUtils.addDays(new Date(), days);
				}else{
					end = DateUtils.addDays(user.getEndTime(), days);
				}
				user.setOldbalance(user.getBalance());
				user.setEndTime(end);
				user.setBalance(user.getBalance()-days*10);
				int rows = usersMapper.updateEndBalance(user);
				if(rows>0){
					result.put("code", 0);
					result.put("msg", "成功");
				}else{
					result.put("code", -1);
					result.put("msg", "失败");
				}
				
			}else{
				result.put("code", -1);
				result.put("msg", "余额不足");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", -1);
			result.put("msg", "失败");
		}
		return result;
	}
	
	
	@RequestMapping(value = "gaofeimx")
	public String gaofeimx(Model model,HttpServletRequest request){
		String uid = (String) request.getAttribute("login_user");
		List<Water> ws = waterMapper.selectGaofeiMx(uid);
		long total = 0L;
		if(ws != null && ws.size()>0){
			for(Water w : ws){
				total+=w.getAmount();
			}
		}
		
		model.addAttribute("ws", ws);
		model.addAttribute("total", total);
		return "gaofeimx";
	}
	@RequestMapping(value = "gaofeixq")
	public String gaofeixq(String waterId, Model model,HttpServletRequest request){
		String uid = (String) request.getAttribute("login_user");
		List<Fenzhan> fenzhanList = fenzhanMapper.selectList(uid,waterId);
		model.addAttribute("fenzhanList", fenzhanList);
		return "gaofeixq";
	}
	@Autowired
	private UserService userService;
	@RequestMapping(value = "fabu")
	public String fabu(Model model,HttpServletRequest request){
		String openId = (String) request.getAttribute("login_user");
		String appId = PayController.APPID;
		String ticket = userService.getJSApiTicket(openId);
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		String nonceStr = UUID.randomUUID().toString();
		String url="http://www.aaread.com/fabu.html";
		Map<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("jsapi_ticket", ticket);
		paramsMap.put("timestamp", timestamp);
		paramsMap.put("noncestr", nonceStr);
		paramsMap.put("url", url);
		String signature = PayController.createSign(paramsMap,null);
		model.addAllAttributes(paramsMap);
		model.addAttribute("signature", signature);
		model.addAttribute("appId", appId);
		return "fabu";
	}
	
	@RequestMapping(value = "fenzhan")
	public void fenzhan(){
		Date start = DateUtils.truncate(DateUtils.addDays(new Date(), -100), Calendar.DAY_OF_MONTH);
		Date end = DateUtils.truncate(DateUtils.addDays(new Date(), 1), Calendar.DAY_OF_MONTH);
		DateFormat df = new SimpleDateFormat("yyyMMdd");
		String dateStr = df.format(new Date());
		List<Map<String, Object>> list = dianzanMapper.selectZangGroup(start, end);
		long count = usersMapper.selectYouxiaoUsersCount(end);
		if(count == 0){
			return ;
		}
		long totalMoney = count*100;
		Map<String,Water> userMap = new HashMap<String, Water>();
		Map<String,List<Fenzhan>> fenMap = new HashMap<String, List<Fenzhan>>();
		int totalZan = 0;
		for(Map<String, Object> item : list){
			long zan = (long) item.get("num");
			totalZan+=zan;
		}
		for(Map<String, Object> item : list){
			long cId = (long) item.get("c_id");
			long zan = (long) item.get("num");
			Content c = contentMapper.selectByPrimaryKey(cId);
			String uid = c.getUid();
			Water w = userMap.get(uid);
			long amount = totalMoney*zan/totalZan;
			if(amount == 0){
				continue;
			}
			if(w == null){
				w = new Water();
				w.setUid(uid);
				w.setType(1);
				String orderId = MD5Util.MD5Encode(uid+dateStr, "utf-8");
				w.setOrderId(orderId.replaceAll("-", ""));
				w.setAmount(amount);
				userMap.put(uid, w);
			}else{
				w.setAmount(w.getAmount()+amount);
			}
			List<Fenzhan> fenzs = fenMap.get(uid);
			if(fenzs == null){
				fenzs = new ArrayList<Fenzhan>();
				fenMap.put(uid, fenzs);
			}
			Fenzhan fenz = new Fenzhan();
			fenz.setAmount1((int)amount);
			fenz.setcId(cId);
			fenz.setNum1((int)zan);
			fenz.setTotalAmount(amount);
			fenz.setUid(uid);
			fenzs.add(fenz);
			
		}
		for(Entry<String, Water> en : userMap.entrySet()){
			String key = en.getKey();
			List<Fenzhan> fenzs = fenMap.get(key);
			orderService.updateFenzhan(en.getValue(),fenzs);
		}
		
	}
	public static void main(String[] args) {
		Date start = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
		Date end = DateUtils.truncate(DateUtils.addDays(new Date(), 1), Calendar.DAY_OF_MONTH);
		System.out.println(start);
		System.out.println(end);
	}
	
	
}
