package com.agos.awg.dao;

import java.util.ArrayList;

import com.agos.awg.model.ProjVO;

public interface IProjDAO {

	ArrayList<ProjVO> projlist();
	String codecheck(int projcode);
	void projdbinsert(ProjVO vo);
	ArrayList<ProjVO> projfilter(String value);
	ArrayList<ProjVO> projsearchfilter(String search);
}
