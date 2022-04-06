package com.agos.awg.service.card;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agos.awg.dao.card.ICardDAO;
import com.agos.awg.model.CardVO;

@Service
public class CardService implements ICardService {
	@Autowired
	ICardDAO dao;
	
	// ī�� ��� ��������	
	@Override
	public ArrayList<CardVO> getCardList(int card_type) {
		return dao.getCardList(card_type);
	}
	
	// ī�� ���� �ߺ� üũ
	@Override
	public int checkCardIndex(String index) {
		return dao.checkCardIndex(index);
	}

	// ī�� ���� ���
	@Override
	public void insertCard(CardVO cardVO) {
		dao.insertCard(cardVO);
	}

	// ī�� ���� ��������
	@Override
	public CardVO getCardInfo(String card_idx) {
		return dao.getCardInfo(card_idx);
	}

	// ī�� ���� ����
	@Override
	public int updateCard(CardVO cardVO) {
		return dao.updateCard(cardVO);
	}

	// ī�� ���� ����
	@Override
	public int deleteCard(List<String> indexArray) {
		return dao.deleteCard(indexArray);
	}
}
