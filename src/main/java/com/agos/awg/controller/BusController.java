package com.agos.awg.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agos.awg.model.BusVO;
import com.agos.awg.service.BusService;

@Controller
public class BusController {
	
	@Autowired
	BusService busservice;

	@RequestMapping("/")
	public String index(Model model) {
		ArrayList<BusVO> vo = busservice.buslist();
		model.addAttribute("vo",vo);
		System.out.println(vo);
		return "index";
	}
	
	@RequestMapping("/businsert")
	public String businsert() {
		return "businsert";
	}
	
	//업체코드 중복검사
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
	
	//업체코드 등록폼 작성후 디비에 등록
//	@RequestMapping("/dbinsert")
//	public void busdbinsert(@ModelAttribute BusVO vo) {
//		busservice.busdbinsert(vo);
//		System.out.println("등록");
//		return "index";
//	}

	@RequestMapping("/dbinsert/{bus_code}")
	 public void busdbinsert(
	            @RequestParam("bus_code")String bus_code,
	            @RequestParam("bus_nm")String bus_nm, 
	            @RequestParam("bus_rep")String bus_rep,
	            @RequestParam("bus_reg_no")String bus_reg_no,
	            @RequestParam("bus_tel1")String bus_tel1, 
	            @RequestParam("bus_tel2")String bus_tel2,
	            @RequestParam("bus_tel3")String bus_tel3,
	            @RequestParam("bus_fax1")String bus_fax1, 
	            @RequestParam("bus_fax2")String bus_fax2,
	            @RequestParam("bus_fax3")String bus_fax3,
	            @RequestParam("bus_item")String bus_item,
	            @RequestParam("bus_email")String bus_email, 
	            @RequestParam("busAddress")String busAddress,
	            BusVO vo
	            )
	 
	 {
		 System.out.println("받음");
		 vo.setBus_code(bus_code);
		 vo.setBus_nm(bus_nm);
		 vo.setBus_rep(bus_rep);
		 vo.setBus_reg_no(bus_reg_no);
		 vo.setBus_tel(bus_tel1+"-"+bus_tel2+"-"+bus_tel3);
		 vo.setBus_fax(bus_fax1+"-"+bus_fax2+"-"+bus_fax3);
		 vo.setBus_item(bus_item);
		 vo.setBus_email(bus_email);
		 vo.setBusAddress(busAddress);
		 
		 busservice.busdbinsert(vo);
		 System.out.println("완료");
	   
	 }
	
	
	
	//업체코드 수정
//	@RequestMapping("/busupdate/{busIdx}")
//	public String busupdate() {
//		return "";
//	}
	
	//업체코드 삭제
	@ResponseBody
	@RequestMapping("/busdelete")
	public String busdelete(HttpServletRequest request) {
		String[] ajaxmsg = request.getParameterValues("valueArr");
		int size = ajaxmsg.length;
		for(int i=0; i<size; i++) {
			busservice.delete(Integer.parseInt(ajaxmsg[i]));
		}
		return "redirect:/";
	}
	
}
