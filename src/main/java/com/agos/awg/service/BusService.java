package com.agos.awg.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.agos.awg.dao.IBusDAO;
import com.agos.awg.model.BusVO;

@Service
public class BusService implements IBusService {

	@Autowired
	@Qualifier("IBusDAO")
	IBusDAO dao;
	
	public ArrayList<BusVO> buslist() {
		return dao.buslist();
	}

	public String codecheck(String buscode) {
		return dao.codecheck(buscode);
	}

	public void busdbinsert(BusVO vo) {
		dao.busdbinsert(vo);
	}

	public BusVO busupdateform(int bus_idx) {
		return dao.busupdateform(bus_idx);
	}
	
	public void busdbupdate(BusVO vo) {
		dao.busdbupdate(vo);
	}

	public void busdbdelete(int bus_idx) {
		dao.busdbdelete(bus_idx);
	}

	public ArrayList<String> indexcheck() {
		return dao.indexcheck();
	}

	public ArrayList<BusVO> filter(HashMap<String, String> map) {
		System.out.println(map);
		return dao.filter(map);
	}


}
