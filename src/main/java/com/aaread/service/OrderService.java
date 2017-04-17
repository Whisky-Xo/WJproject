package com.aaread.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aaread.mapper.FenzhanMapper;
import com.aaread.mapper.IorderMapper;
import com.aaread.mapper.UsersMapper;
import com.aaread.mapper.WaterMapper;
import com.aaread.model.Fenzhan;
import com.aaread.model.Iorder;
import com.aaread.model.Water;

@Service
public class OrderService {

	@Autowired
	private IorderMapper orderMapper;
	@Autowired
	private WaterMapper waterMapper;
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private FenzhanMapper fenzhanMapper;
	
	@Transactional
	public void updateOrderSucc(Iorder order){
		Water water = new Water();
		water.setOrderId(order.getOrderid());
		water.setAmount(order.getAmount());
		water.setUid(order.getUid());
		int row = waterMapper.insertSelective(water);
		if(row>0){
			usersMapper.updateBalance(order.getUid(),order.getAmount());
			order.setStatus(1);
			orderMapper.updateByPrimaryKeySelective(order);
		}
	}
	@Transactional
	public void updateFenzhan(Water water,List<Fenzhan> fenzhanList){
		int row = waterMapper.insertSelective(water);
		if(row>0){
			usersMapper.updateBalance(water.getUid(),water.getAmount());
		}
		for(Fenzhan f: fenzhanList){
			f.setWaterId(water.getId());
			fenzhanMapper.insert(f);
		}
	}
}
