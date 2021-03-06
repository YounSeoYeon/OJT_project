package com.agos.awg.service.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agos.awg.dao.card.ICardDAO;
import com.agos.awg.model.CardVO;

@Service
public class CardService implements ICardService {
	@Autowired
	ICardDAO dao;
	
	// 카드 목록 가져오기	
	@Override
	public ArrayList<CardVO> getCardList(HashMap<String, Object> map) {
		return dao.getCardList(map);
	}
	
	// 카드 중복 체크
	@Override
	public int checkDuplicate(HashMap<String, Object> map) {
		return dao.checkDuplicate(map);
	}

	// 카드 계정 등록
	@Override
	public void insertCard(CardVO cardVO) {
		dao.insertCard(cardVO);
	}

	// 카드 정보 가져오기
	@Override
	public CardVO getCardInfo(String card_id) {
		return dao.getCardInfo(card_id);
	}

	// 카드 계정 수정
	@Override
	public int updateCard(CardVO cardVO) {
		return dao.updateCard(cardVO);
	}

	// 카드 계정 삭제
	@Override
	public int deleteCard(List<String> idArray) {
		return dao.deleteCard(idArray);
	}

	// 전체 카드 개수 확인
	@Override
	public int getCardListCnt(HashMap<String, Object> map) {
		return dao.getCardListCnt(map);
	}
}
