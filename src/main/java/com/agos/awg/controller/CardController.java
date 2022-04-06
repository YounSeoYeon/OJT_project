package com.agos.awg.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agos.awg.model.CardVO;
import com.agos.awg.service.card.CardService;

@Controller
public class CardController {
	@Autowired
	CardService service;
	
	// 카드 목록 페이지 이동
	@RequestMapping("/card")
	public String cardInfo(Model model) {
		return "card/cardInfo";
	}
	
	// 카드 목록 리스트(카드 유형별)
	@RequestMapping("/card/cardList")
	public String cardList(@RequestParam("card_type") int card_type, Model model) {
		ArrayList<CardVO> cardList = service.getCardList(card_type);
		
		model.addAttribute("cardList", cardList);
		return "card/cardList";
	}
	
	// 카드 계정 등록 페이지 
	@RequestMapping("/card/insertCardView")
	public String insertCardView() {
		return "card/insertCard";
	}
	
	// 카드 계정 중복 체크
	@ResponseBody
	@RequestMapping("/card/checkCardIndex")
	public int checkCardIndex(@RequestParam("index") String index) {
		int result = service.checkCardIndex(index);		
		return result;
	}
	
	// 카드 계정 등록
	@ResponseBody
	@RequestMapping("/card/insertCard")
	public String insertCard(CardVO cardVO) {
		service.insertCard(cardVO);
		
		String card_idx = cardVO.getCard_idx();
		return card_idx;
	}
	
	// 카드 계정 수정 페이지 
	@RequestMapping("/card/updateCardView/{index}")
	public String updateCardView(@PathVariable("index") String card_idx, Model model) {
		CardVO card = service.getCardInfo(card_idx);
		
		model.addAttribute("card", card);
		return "card/updateCard";
	}
	
	// 카드 정보 수정
	@ResponseBody
	@RequestMapping("/card/updateCard")
	public int updateCard(CardVO cardVO) {
		int result = service.updateCard(cardVO);
		
		return result;
	}
	
	// 카드 정보 삭제
	@ResponseBody
	@RequestMapping("/card/deleteCard")
	public int deleteCard(@RequestParam("indexArray[]") List<String> indexArray) {
		int result = service.deleteCard(indexArray);
		
		return result;
	}
}
