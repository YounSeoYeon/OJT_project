package com.agos.awg.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agos.awg.model.ProjVO;
import com.agos.awg.service.ProjService;

@Controller
public class ProjController {
	
	@Autowired
	ProjService proj;

	//프로젝트 리스트 처음화면
	@RequestMapping("/projindex")
	public String projindex(Model model) {
		ArrayList<ProjVO> vo = proj.projlist();
		String startdate = "";
		String enddate = "";
		System.out.println(vo.get(0).getProj_start_date().substring(2,10));
		
		for(int i=0; i<vo.size(); i++) {
			startdate=vo.get(i).getProj_start_date().substring(2,10);
			enddate=vo.get(i).getProj_end_date().substring(2,10);			
			vo.get(i).setProj_start_date(startdate);
			vo.get(i).setProj_end_date(enddate);			
		}
		
		model.addAttribute("vo",vo);
		System.out.println(vo);
		return "projindex";
	}
	
	//프로젝트 추가화면 이동
	@RequestMapping("/projinsert")
	public String projinsert() {
		return "projinsert";
	}
	
	//프로젝트코드 중복검사
	@ResponseBody
	@RequestMapping("/projcodecheck")
	public int codecheck(@RequestParam("projcode") int projcode) {
		String checktext = proj.codecheck(projcode);
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
	

// ----------------------------------------------------------
	//프로젝트 등록폼 작성후 중복확인,유효성검사 거쳐서 디비에 등록
	@RequestMapping("/projdbinsert")
	public String projdbinsert(ProjVO vo) {
		proj.projdbinsert(vo);
		return "popup";	// 등록후 팝업안내창으로 이동
	}	
	
//	//업체코드 수정폼으로 이동 (업체정보 가져와서 수정폼에 나타내 줘야함)
//	@RequestMapping("/busupdateform/{bus_idx}")
//	public String busupdateform(@PathVariable int bus_idx,Model model) {
//		BusVO vo = busservice.busupdateform(bus_idx);
//		model.addAttribute("vo",vo);
//		return "/busupdateform"; // agw/busupdateform/이경로임
//	}
//	
//	//업체수정후 db등록
//	@RequestMapping("/busupdate/{bus_idx}")
//	public String busupdate(BusVO vo) {
//		System.out.println("받아옴?");
//		System.out.println(vo.getBus_code()+vo.getBus_fax());
//		busservice.busdbupdate(vo);
////		return "redirect:/";
////		return "./index"; // /agw/busupdate/resources/이렇게 감		
////		return "redirect:./"+vo.getBus_idx(); // agw/busupdate/5
////		return "./busupdateform/"+vo.getBus_idx();
//		System.out.println("넘김?");
////		return "redirect:../";
//		return "popup";	// 등록후 팝업안내창으로 이동
//	}
//	
//	// 삭제 여러개 -> 삭제할 업체들 배열로 가져옴
//	@RequestMapping("/busdelete/{idxlist}")
//	public String busdelete(@PathVariable ArrayList<Integer> idxlist) {
//		System.out.println(idxlist);
//		int a = idxlist.get(0);
//		System.out.println(a+"//"+idxlist.size());
//		for(int i=0; i<idxlist.size(); i++) {
//			busservice.busdbdelete(idxlist.get(i));			
//		}
//		return "redirect:/";
//	}
//	
//
//// -----------------------------------------------------------------------
	
	
//	radio값 바꼈을때
	@RequestMapping("/projfilter/{value}")
	public String projfilter(@PathVariable String value, Model model) {
		ArrayList<ProjVO> vo = proj.projfilter(value);
		model.addAttribute("vo",vo);
		System.out.println(vo);
		return "projtop";
	}
	
	
	@RequestMapping("/projsearchfilter/{search}")
	public String projsearchfilter(@PathVariable String search, Model model) {
		ArrayList<ProjVO> vo = proj.projsearchfilter(search);
		model.addAttribute("vo",vo);
		System.out.println(vo);
		return "projtop";
	}
	
		
}
