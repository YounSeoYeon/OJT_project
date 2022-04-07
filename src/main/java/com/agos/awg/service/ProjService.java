package com.agos.awg.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.agos.awg.dao.IProjDAO;
import com.agos.awg.model.ProjVO;

@Service
public class ProjService implements IProjService {
	
	@Autowired
	@Qualifier("IProjDAO")
	IProjDAO dao;

	@Override
	public ArrayList<ProjVO> projlist() {
		return dao.projlist();
	}

	@Override
	public String codecheck(int projcode) {
		return dao.codecheck(projcode);
	}

	public void projdbinsert(ProjVO vo) {
		dao.projdbinsert(vo);
	}

	public ArrayList<ProjVO> projfilter(String value) {
		return dao.projfilter(value);
	}

	public ArrayList<ProjVO> projsearchfilter(String search) {
		return dao.projsearchfilter(search);
	}

}
