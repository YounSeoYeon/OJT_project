package com.agos.agw.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agos.agw.model.CardVO;
import com.agos.agw.service.CardService;

@Controller
public class CardController {
	@Autowired
	CardService service;
	
	// 카드 목록 페이지
	@RequestMapping("/")
	public String cardInfo(Model model) {
		ArrayList<CardVO> cardList = service.getCardList();
		
		model.addAttribute("cardList", cardList);
		return "cardInfo";
	}
	
	// 카드 계정 등록 페이지 
	@RequestMapping("/insertCard")
	public String insertCard() {
		return "insertCard";
	}
	
	// 카드 계정 중복 체크
	@ResponseBody
	@RequestMapping("/checkCardIndex")
	public int checkCardIndex(@RequestParam("data") String index) {
		int result = service.checkCardIndex(index);		
		return result;
	}
}
