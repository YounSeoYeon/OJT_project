package com.agos.agw.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.agos.agw.model.CardVO;
import com.agos.agw.service.CardService;

@Controller
public class CardController {
	@Autowired
	CardService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		ArrayList<CardVO> cardList = service.getCardList();
		
		model.addAttribute("cardList", cardList);
		return "card";
	}
	
}
