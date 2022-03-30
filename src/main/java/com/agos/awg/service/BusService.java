package com.agos.awg.service;

import java.util.ArrayList;

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

}
