package com.agos.awg.service.card;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agos.awg.model.CardVO;

@Service
public interface ICardService {
	public ArrayList<CardVO> getCardList(int card_type); 	// ī�� ��� ��������
	public int checkCardIndex(String index); 				// ī�� ���� �ߺ� üũ
	public void insertCard(CardVO cardVO);					// ī�� ���� �߰�
	public CardVO getCardInfo(String card_idx);				// ī�� ���� ��������
	public int updateCard(CardVO cardVO);					// ī�� ���� ����
	public int deleteCard(List<String> indexArray);				// ī�� ���� ����
}
