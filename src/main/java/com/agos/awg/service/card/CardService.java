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
	
	// ī�� ��� ��������	
	@Override
	public ArrayList<CardVO> getCardList(HashMap<String, Object> map) {
		return dao.getCardList(map);
	}
	
	// ī�� ���� �ߺ� üũ
	@Override
	public int checkCardID(String card_id) {
		return dao.checkCardID(card_id);
	}

	// ī�� ���� ���
	@Override
	public void insertCard(CardVO cardVO) {
		dao.insertCard(cardVO);
	}

	// ī�� ���� ��������
	@Override
	public CardVO getCardInfo(String card_id) {
		return dao.getCardInfo(card_id);
	}

	// ī�� ���� ����
	@Override
	public int updateCard(CardVO cardVO) {
		return dao.updateCard(cardVO);
	}

	// ī�� ���� ����
	@Override
	public int deleteCard(List<String> idArray) {
		return dao.deleteCard(idArray);
	}

	// ��ü ī�� ���� Ȯ��
	@Override
	public int getCardListCnt(HashMap<String, Object> map) {
		return dao.getCardListCnt(map);
	}
}
