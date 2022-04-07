package com.agos.awg.service;

import java.util.ArrayList;
import java.util.List;

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

	// 프로젝트 정보 수정
	@Override
	public int updateProject(ProjVO ProjVO) {
		return dao.updateProject(ProjVO);
	}

	// 프로젝트 정보 삭제
	@Override
	public int deleteProject(List<String> idxArray) {
		return dao.deleteProject(idxArray);
	}
	
	// 카드 정보 조회
	@Override
	public ProjVO getProjectInfo(String project_idx) {
		return dao. getProjectInfo(project_idx);
	}

}
