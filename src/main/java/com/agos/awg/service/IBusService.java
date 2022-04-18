package com.agos.awg.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.agos.awg.model.BusVO;

public interface IBusService {
	ArrayList<BusVO> buslist();
	String codecheck(String buscode);
	void busdbinsert(BusVO vo);
	BusVO busupdateform(int bus_idx);
	void busdbupdate(BusVO vo);
	void busdbdelete(int bus_idx);
	ArrayList<String> indexcheck();
	ArrayList<BusVO> filter(HashMap<String, String> map);
}
