package com.agos.agw.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agos.agw.dao.ICardDAO;
import com.agos.agw.model.CardVO;

@Service
public class CardService implements ICardService {
	@Autowired
	ICardDAO dao;
	
	// 카드 목록 가져오기	
	@Override
	public ArrayList<CardVO> getCardList(int card_type) {
		return dao.getCardList(card_type);
	}
	
	// 카드 계정 중복 체크
	@Override
	public int checkCardIndex(String index) {
		return dao.checkCardIndex(index);
	}

	// 카드 계정 등록
	@Override
	public void insertCard(CardVO cardVO) {
		dao.insertCard(cardVO);
	}

	// 카드 정보 가져오기
	@Override
	public CardVO getCardInfo(String card_idx) {
		return dao.getCardInfo(card_idx);
	}
}
