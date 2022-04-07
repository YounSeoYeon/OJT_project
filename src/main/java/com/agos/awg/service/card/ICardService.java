package com.agos.awg.service.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agos.awg.model.CardVO;

@Service
public interface ICardService {
	public int checkCardID(String card_id); 								// 카드 계정 중복 체크
	public void insertCard(CardVO cardVO);									// 카드 계정 추가
	public CardVO getCardInfo(String card_id);								// 카드 정보 가져오기
	public int updateCard(CardVO cardVO);									// 카드 정보 수정
	public int deleteCard(List<String> idArray);							// 카드 정보 삭제
	public int getCardListCnt(HashMap<String, Object> map);					// 카드 정보 개수 확인
	public ArrayList<CardVO> getCardList(HashMap<String, Object> map);		// 카드 목록 가져오기
}
