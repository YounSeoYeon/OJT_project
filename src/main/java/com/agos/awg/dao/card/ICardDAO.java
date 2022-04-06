package com.agos.awg.dao.card;

import java.util.ArrayList;
import java.util.List;

import com.agos.awg.model.CardVO;

public interface ICardDAO {
	public ArrayList<CardVO> getCardList(int card_type);		// 카드 목록 가져오기
	public int checkCardIndex(String index); 					// 카드 계정 중복 체크
	public void insertCard(CardVO cardVO);						// 카드 계정 추가
	public CardVO getCardInfo(String card_idx);					// 카드 정보 가져오기
	public int updateCard(CardVO cardVO);						// 카드 정보 수정
	public int deleteCard(List<String> indexArray);					// 카드 정보 삭제
}				
