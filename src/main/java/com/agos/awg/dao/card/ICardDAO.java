package com.agos.awg.dao.card;

import java.util.ArrayList;
import java.util.List;

import com.agos.awg.model.CardVO;

public interface ICardDAO {
	public ArrayList<CardVO> getCardList(int card_type);		// ī�� ��� ��������
	public int checkCardID(String card_id); 					// ī�� ���� �ߺ� üũ
	public void insertCard(CardVO cardVO);						// ī�� ���� �߰�
	public CardVO getCardInfo(String card_id);					// ī�� ���� ��������
	public int updateCard(CardVO cardVO);						// ī�� ���� ����
	public int deleteCard(List<String> idArray);				// ī�� ���� ����
}				
