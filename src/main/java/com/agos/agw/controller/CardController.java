package com.agos.agw.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agos.agw.model.CardVO;
import com.agos.agw.service.CardService;

@Controller
public class CardController {
	@Autowired
	CardService service;
	
	// 카드 목록 페이지 이동
	@RequestMapping("/")
	public String cardInfo(Model model) {
		return "cardInfo";
	}
	
	// 카드 목록 리스트(카드 유형별)
	@RequestMapping("/cardList")
	public String cardList(@RequestParam("card_type") int card_type, Model model) {
		ArrayList<CardVO> cardList = service.getCardList(card_type);
		
		model.addAttribute("cardList", cardList);
		return "cardList";
	}
	
	// 카드 계정 등록 페이지 
	@RequestMapping("/insertCardView")
	public String insertCardView() {
		return "insertCard";
	}
	
	// 카드 계정 중복 체크
	@ResponseBody
	@RequestMapping("/checkCardIndex")
	public int checkCardIndex(@RequestParam("data") String index) {
		int result = service.checkCardIndex(index);		
		return result;
	}
	
	// 카드 계정 등록
	@ResponseBody
	@RequestMapping("/insertCard")
	public String insertCard(CardVO cardVO) {
		service.insertCard(cardVO);
		
		String card_idx = cardVO.getCard_idx();
		return card_idx;
	}
	
	// 카드 계정 수정 페이지 
	@RequestMapping("/updateCardView/{index}")
	public String updateCardView(@PathVariable("index") String card_idx, Model model) {
		CardVO card = service.getCardInfo(card_idx);
		
		model.addAttribute("card", card);
		return "updateCard";
	}
	
	// 카드 정보 수정
	@ResponseBody
	@RequestMapping("/updateCard")
	public String updateCard(CardVO cardVO) {
		service.updateCard(cardVO);
		
		String card_idx = cardVO.getCard_idx();
		return card_idx;
	}
}
