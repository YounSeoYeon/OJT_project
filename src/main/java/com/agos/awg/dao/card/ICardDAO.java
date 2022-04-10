package com.agos.awg.dao.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.agos.awg.model.CardVO;

public interface ICardDAO {
	public void insertCard(CardVO cardVO);									// ī�� ���� �߰�
	public CardVO getCardInfo(String card_id);								// ī�� ���� ��������
	public int updateCard(CardVO cardVO);									// ī�� ���� ����
	public int deleteCard(List<String> idArray);							// ī�� ���� ����
	public int checkDuplicate(HashMap<String, Object> map); 				// ī�� �ߺ� üũ
	public int getCardListCnt(HashMap<String, Object> map);					// ī�� ���� ���� Ȯ��
	public ArrayList<CardVO> getCardList(HashMap<String, Object> map);		// ī�� ��� ��������
}				
