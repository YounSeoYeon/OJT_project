package com.agos.agw.dao;

import java.util.ArrayList;

import com.agos.agw.model.CardVO;

public interface ICardDAO {
	public ArrayList<CardVO> getCardList(); 	// 카드 목록 가져오기
}
