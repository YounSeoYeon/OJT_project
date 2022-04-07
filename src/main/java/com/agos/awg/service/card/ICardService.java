package com.agos.awg.service.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agos.awg.model.CardVO;

@Service
public interface ICardService {
	public int checkCardID(String card_id); 								// ī�� ���� �ߺ� üũ
	public void insertCard(CardVO cardVO);									// ī�� ���� �߰�
	public CardVO getCardInfo(String card_id);								// ī�� ���� ��������
	public int updateCard(CardVO cardVO);									// ī�� ���� ����
	public int deleteCard(List<String> idArray);							// ī�� ���� ����
	public int getCardListCnt();											// ��ü ī�� ���� ���� Ȯ��
	public ArrayList<CardVO> getCardList(HashMap<String, Object> map);		// ī�� ��� ��������
}
