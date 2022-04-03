package com.agos.awg.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agos.awg.model.BusVO;
import com.agos.awg.service.BusService;

@Controller
public class BusController {
	
	@Autowired
	BusService busservice;

	//처음 리스트 화면
	@RequestMapping("/")
	public String index(Model model) {
		ArrayList<BusVO> vo = busservice.buslist();			//buslist()의 결과타입이 리스트 형식임. 
		model.addAttribute("vo",vo);
		System.out.println(vo);
		return "buscodelist";
	}
	
	//관리하기 눌르고 이동
	@RequestMapping("/index")
	public String index2(Model model) {
		ArrayList<BusVO> vo = busservice.buslist();
		model.addAttribute("vo",vo);
		System.out.println(vo);
		return "index";
	}
	
	//업체 추가화면 이동
	@RequestMapping("/businsert")
	public String businsert() {
		return "businsert";
	}
	
	//업체코드 중복검사
	@ResponseBody
	@RequestMapping("/buscodecheck")
	public int codecheck(@RequestParam("buscode") String buscode) {
		String checktext = busservice.codecheck(buscode);
		int result=1; //해당하는 업체코드 있을때 1 // 없을때 0
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
	
	//업체코드 등록폼 작성후 중복확인,유효성검사 거쳐서 디비에 등록
	@RequestMapping("/dbinsert")
	public String busdbinsert(BusVO vo) {
		System.out.println(vo.getBus_code());
		busservice.busdbinsert(vo);
		return "redirect:./";
	}	
	
	//업체코드 수정폼으로 이동 (업체정보 가져와서 수정폼에 나타내 줘야함)
	@RequestMapping("/busupdateform/{bus_idx}")
	public String busupdateform(@PathVariable int bus_idx,Model model) {
		BusVO vo = busservice.busupdateform(bus_idx);
		model.addAttribute("vo",vo);
		return "/busupdateform"; // agw/busupdateform/이경로임
	}
	
	//업체수정후 db등록
	@RequestMapping("/busupdate/{bus_idx}")
	public String busupdate(BusVO vo) {
		System.out.println("받아옴?");
		System.out.println(vo.getBus_code()+vo.getBus_fax());
		busservice.busdbupdate(vo);
//		return "redirect:/";
//		return "./index"; // /agw/busupdate/resources/이렇게 감		
//		return "redirect:./"+vo.getBus_idx(); // agw/busupdate/5
//		return "./busupdateform/"+vo.getBus_idx();
		System.out.println("넘김?");
		return "redirect:../";
	}
	
	// 삭제 여러개 -> 삭제할 업체들 배열로 가져옴
	@RequestMapping("/busdelete/{idxlist}")
	public String busdelete(@PathVariable ArrayList<Integer> idxlist) {
		System.out.println(idxlist);
		int a = idxlist.get(0);
		System.out.println(a+"//"+idxlist.size());
		for(int i=0; i<idxlist.size(); i++) {
			busservice.busdbdelete(idxlist.get(i));			
		}
		return "redirect:/";
	}
		
}
