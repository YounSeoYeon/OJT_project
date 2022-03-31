package com.agos.agw.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.agos.agw.model.CardVO;

@Service
public interface ICardService {
	public ArrayList<CardVO> getCardList(); 	// 카드 목록 가져오기
	public int checkCardIndex(String index); 	// 카드 계정 중복 체크
}
