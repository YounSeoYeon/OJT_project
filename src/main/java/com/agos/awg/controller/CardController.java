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
import com.agos.awg.model.PaginationVO;
import com.agos.awg.service.card.CardService;

@Controller
public class CardController {
	@Autowired
	CardService service;
	
	// 카드 목록 페이지 이동
	@RequestMapping("/card")
	public String cardInfo() {
		return "/card/cardInfo";
	}
	
	// 카드 목록 리스트(카드 유형별, 검색, 페이징)
	@RequestMapping("/card/cardList")
	public String cardList(
			@RequestParam(value="card_type", required = false, defaultValue = "-1") int card_type,
			@RequestParam(value="filter", required = false) String filter,
			@RequestParam(value="keyword", required = false) String keyword,	
			@RequestParam(value="page", required = false, defaultValue = "1") int page,
			@RequestParam(value="range", required = false, defaultValue = "1") int range,
			Model model) {
		
		PaginationVO pagination = getPagination(card_type, filter, keyword, page, range);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("card_type", card_type);
		map.put("filter", filter);
		map.put("keyword", keyword);
		map.put("pagination", pagination);
		// System.out.println(map);
		
		// 카드 목록 가져오기
		ArrayList<CardVO> cardList = service.getCardList(map);
		
		model.addAttribute("cardList", cardList);
		return "/card/cardList";
	}
	
	// 페이지 범위 - 페이징 처리
	@RequestMapping("/card/page")
	public String page(
			@RequestParam(value="card_type", required = false, defaultValue = "-1") int card_type,
			@RequestParam(value="filter", required = false) String filter,
			@RequestParam(value="keyword", required = false) String keyword,	
			@RequestParam(value="page", required = false, defaultValue = "1") int page,
			@RequestParam(value="range", required = false, defaultValue = "1") int range,
			Model model) {
		
		model.addAttribute("pagination", getPagination(card_type, filter, keyword, page, range));
		
		return "/card/page";
	}
	
	// 카드 계정 등록 페이지 
	@RequestMapping("/card/insertCardView")
	public String insertCardView() {
		return "/card/insertCard";
	}
	
	// 중복 체크
	@ResponseBody
	@RequestMapping("/card/checkDuplicate")
	public int checkCardIndex(
			@RequestParam("key") String key,
			@RequestParam("value") String value) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("key", key);
		map.put("value", value);
		
		int result = service.checkDuplicate(map);		
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
	
	// pagination 생성 및 페이지 범위 설정
	private PaginationVO getPagination(int card_type, String filter, String keyword, int page, int range) {
		// 목록 개수
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("card_type", card_type);
		map.put("filter", filter);
		map.put("keyword", keyword);
		
		// 전체 리스트 수 구하기
		int listCnt = service.getCardListCnt(map);

	    //Pagination 객체 생성 및 현재 페이지 정보 설정
		PaginationVO pagination = new PaginationVO();
		pagination.pageInfo(page, range, listCnt);
		
		return pagination;
	};
}
