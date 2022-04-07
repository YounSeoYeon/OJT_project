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

import com.agos.awg.common.Pagination;
import com.agos.awg.model.CardVO;
import com.agos.awg.service.card.CardService;

@Controller
public class CardController {
	@Autowired
	CardService service;
	
	// 카드 목록 페이지 이동
	@RequestMapping("/card")
	public String cardInfo(Model model) {
		return "/card/cardInfo";
	}
	
	// 카드 목록 리스트(카드 유형별)
	@RequestMapping("/card/cardList")
	public String cardList(
			@RequestParam("card_type") int card_type,
			@RequestParam(value="keyword", required = false) String keyword,
			@RequestParam(value="word", required = false) String word,	
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range,
			Model model) {
		
		//전체 게시글 개수
		int listCnt = service.getCardListCnt();

	    //Pagination 객체생성
		Pagination pagination = new Pagination();
		pagination.pageInfo(page, range, listCnt);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("card_type", card_type);
		map.put("keyword", keyword);
		map.put("word", word);
		map.put("pagination", pagination);
		
		// System.out.println(map);
		
		ArrayList<CardVO> cardList = service.getCardList(map);
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("cardList", cardList);
		return "/card/cardList";
	}
	
	// 카드 계정 등록 페이지 
	@RequestMapping("/card/insertCardView")
	public String insertCardView() {
		return "/card/insertCard";
	}
	
	// 카드 계정 중복 체크
	@ResponseBody
	@RequestMapping("/card/checkCardID")
	public int checkCardIndex(@RequestParam("card_id") String card_id) {
		int result = service.checkCardID(card_id);		
		return result;
	}
	
	// 카드 계정 등록
	@ResponseBody
	@RequestMapping("/card/insertCard")
	public String insertCard(CardVO cardVO) {
		service.insertCard(cardVO);
		
		String card_id = cardVO.getCard_id();
		return card_id;
	}
	
	// 카드 계정 수정 페이지 
	@RequestMapping("/card/updateCardView/{id}")
	public String updateCardView(@PathVariable("id") String card_id, Model model) {
		CardVO card = service.getCardInfo(card_id);
		
		model.addAttribute("card", card);
		return "/card/updateCard";
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
	public int deleteCard(@RequestParam("idArray[]") List<String> idArray) {
		int result = service.deleteCard(idArray);
		
		return result;
	}
}
