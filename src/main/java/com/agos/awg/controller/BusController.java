package com.agos.awg.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agos.awg.model.BusVO;
import com.agos.awg.service.BusService;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

@Controller
public class BusController {
	
	@Autowired
	BusService busservice;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		ArrayList<BusVO> vo = busservice.buslist();
		model.addAttribute("vo",vo);
		System.out.println(vo);
		return "index";
	}
	
	@RequestMapping(value = "/businsert", method = RequestMethod.GET)
	public String businsert() {
		return "businsert";
	}
	
	//��ü�ڵ� �ߺ��˻�
	@ResponseBody
	@RequestMapping("/buscodecheck")
	public int codecheck(@RequestParam("buscode") String buscode) {
		String checktext = busservice.codecheck(buscode);
		int result=1;
		System.out.println(checktext);
		if(checktext==null) {
			result =0;
			System.out.println(result);
			return result;
		}else {
			result=1;
			System.out.println(result);
			return result;
		}
	}
	
	//��ü�ڵ� ����� �ۼ��� ��� ���
	@RequestMapping("/dbinsert")
	public void dbinsert() {
		
	}
	
	//��ü�ڵ� ����
	@RequestMapping("/busupdate/{busIdx}")
	public String busupdate() {
		return "";
	}
	//��ü�ڵ� ����
}
