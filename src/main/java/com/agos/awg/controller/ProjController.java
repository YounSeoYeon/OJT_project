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

	//������Ʈ ����Ʈ ó��ȭ��
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
	
	//������Ʈ �߰�ȭ�� �̵�
	@RequestMapping("/projinsert")
	public String projinsert() {
		return "projinsert";
	}
	
	//������Ʈ�ڵ� �ߺ��˻�
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
	//������Ʈ ����� �ۼ��� �ߺ�Ȯ��,��ȿ���˻� ���ļ� ��� ���
	@RequestMapping("/projdbinsert")
	public String projdbinsert(ProjVO vo) {
		proj.projdbinsert(vo);
		return "popup";	// ����� �˾��ȳ�â���� �̵�
	}	
	
//	//��ü�ڵ� ���������� �̵� (��ü���� �����ͼ� �������� ��Ÿ�� �����)
//	@RequestMapping("/busupdateform/{bus_idx}")
//	public String busupdateform(@PathVariable int bus_idx,Model model) {
//		BusVO vo = busservice.busupdateform(bus_idx);
//		model.addAttribute("vo",vo);
//		return "/busupdateform"; // agw/busupdateform/�̰����
//	}
//	
//	//��ü������ db���
//	@RequestMapping("/busupdate/{bus_idx}")
//	public String busupdate(BusVO vo) {
//		System.out.println("�޾ƿ�?");
//		System.out.println(vo.getBus_code()+vo.getBus_fax());
//		busservice.busdbupdate(vo);
////		return "redirect:/";
////		return "./index"; // /agw/busupdate/resources/�̷��� ��		
////		return "redirect:./"+vo.getBus_idx(); // agw/busupdate/5
////		return "./busupdateform/"+vo.getBus_idx();
//		System.out.println("�ѱ�?");
////		return "redirect:../";
//		return "popup";	// ����� �˾��ȳ�â���� �̵�
//	}
//	
//	// ���� ������ -> ������ ��ü�� �迭�� ������
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
	
	
//	radio�� �ٲ�����
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
