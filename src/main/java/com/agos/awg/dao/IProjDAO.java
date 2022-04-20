package com.agos.awg.dao;

import java.util.ArrayList;
import java.util.List;

import com.agos.awg.model.ProjVO;

public interface IProjDAO {

	ArrayList<ProjVO> projlist();
	String codecheck(int projcode);
	void projdbinsert(ProjVO vo);
	ArrayList<ProjVO> projfilter(String value);
	ArrayList<ProjVO> projsearchfilter(String search);
	public int updateProject(ProjVO ProjVO);			// 프로젝트 정보 수정
	public int deleteProject(List<String> idxArray);	// 프로젝트 정보 삭제
	public ProjVO getProjectInfo(String project_idx);	// 프로젝트 정보 조회
}
