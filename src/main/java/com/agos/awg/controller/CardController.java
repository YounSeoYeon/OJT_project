package com.agos.awg.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	// ī�� ��� ������ �̵�
	@RequestMapping("/card")
	public String cardInfo(Model model) {
		return "/card/cardInfo";
	}
	
	// ī�� ��� ����Ʈ(ī�� ������)
	@RequestMapping("/card/cardList")
	public String cardList(
			@RequestParam("card_type") int card_type,
			@RequestParam(value="keyword", required = false) String keyword,
			@RequestParam(value="word", required = false) String word,			
			Model model) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("card_type", card_type);
		map.put("keyword", keyword);
		map.put("word", word);
		
		System.out.println(map);
		
		ArrayList<CardVO> cardList = service.getCardList(map);
		
		model.addAttribute("cardList", cardList);
		return "/card/cardList";
	}
	
	// ī�� ���� ��� ������ 
	@RequestMapping("/card/insertCardView")
	public String insertCardView() {
		return "/card/insertCard";
	}
	
	// ī�� ���� �ߺ� üũ
	@ResponseBody
	@RequestMapping("/card/checkCardID")
	public int checkCardIndex(@RequestParam("card_id") String card_id) {
		int result = service.checkCardID(card_id);		
		return result;
	}
	
	// ī�� ���� ���
	@ResponseBody
	@RequestMapping("/card/insertCard")
	public String insertCard(CardVO cardVO) {
		service.insertCard(cardVO);
		
		String card_id = cardVO.getCard_id();
		return card_id;
	}
	
	// ī�� ���� ���� ������ 
	@RequestMapping("/card/updateCardView/{id}")
	public String updateCardView(@PathVariable("id") String card_id, Model model) {
		CardVO card = service.getCardInfo(card_id);
		
		model.addAttribute("card", card);
		return "/card/updateCard";
	}
	
	// ī�� ���� ����
	@ResponseBody
	@RequestMapping("/card/updateCard")
	public int updateCard(CardVO cardVO) {
		int result = service.updateCard(cardVO);
		
		return result;
	}
	
	// ī�� ���� ����
	@ResponseBody
	@RequestMapping("/card/deleteCard")
	public int deleteCard(@RequestParam("idArray[]") List<String> idArray) {
		int result = service.deleteCard(idArray);
		
		return result;
	}
}
