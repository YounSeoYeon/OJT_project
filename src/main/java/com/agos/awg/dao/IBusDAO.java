package com.agos.awg.dao;

import java.util.ArrayList;

import com.agos.awg.model.BusVO;

public interface IBusDAO {

	ArrayList<BusVO> buslist();
	String codecheck(String buscode);
	void busdbinsert(BusVO vo);
	void delete(int idx);

}
