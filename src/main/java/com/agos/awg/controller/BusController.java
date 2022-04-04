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

	//ó�� ����Ʈ ȭ��
	@RequestMapping("/")
	public String index(Model model) {
		ArrayList<BusVO> vo = busservice.buslist();			//buslist()�� ���Ÿ���� ����Ʈ ������. 
		model.addAttribute("vo",vo);
		System.out.println(vo);
		return "index";
	}
	
	//�����ϱ� ������ �̵�
	@RequestMapping("/index")
	public String index2(Model model) {
		ArrayList<BusVO> vo = busservice.buslist();
		model.addAttribute("vo",vo);
		System.out.println(vo);
		return "index";
	}
	
	//��ü �߰�ȭ�� �̵�
	@RequestMapping("/businsert")
	public String businsert() {
		return "businsert";
	}
	
	//��ü�ڵ� �ߺ��˻�
	@ResponseBody
	@RequestMapping("/buscodecheck")
	public int codecheck(@RequestParam("buscode") String buscode) {
		String checktext = busservice.codecheck(buscode);
		int result=1; //�ش��ϴ� ��ü�ڵ� ������ 1 // ������ 0
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
	
	//��ü�ڵ� ����� �ۼ��� �ߺ�Ȯ��,��ȿ���˻� ���ļ� ��� ���
	@RequestMapping("/dbinsert")
	public String busdbinsert(BusVO vo) {
		System.out.println(vo.getBus_code());
		busservice.busdbinsert(vo);
//		return "redirect:./";
		return "popup";	// ����� �˾��ȳ�â���� �̵�
	}	
	
	//��ü�ڵ� ���������� �̵� (��ü���� �����ͼ� �������� ��Ÿ�� �����)
	@RequestMapping("/busupdateform/{bus_idx}")
	public String busupdateform(@PathVariable int bus_idx,Model model) {
		BusVO vo = busservice.busupdateform(bus_idx);
		model.addAttribute("vo",vo);
		return "/busupdateform"; // agw/busupdateform/�̰����
	}
	
	//��ü������ db���
	@RequestMapping("/busupdate/{bus_idx}")
	public String busupdate(BusVO vo) {
		System.out.println("�޾ƿ�?");
		System.out.println(vo.getBus_code()+vo.getBus_fax());
		busservice.busdbupdate(vo);
//		return "redirect:/";
//		return "./index"; // /agw/busupdate/resources/�̷��� ��		
//		return "redirect:./"+vo.getBus_idx(); // agw/busupdate/5
//		return "./busupdateform/"+vo.getBus_idx();
		System.out.println("�ѱ�?");
//		return "redirect:../";
		return "popup";	// ����� �˾��ȳ�â���� �̵�
	}
	
	// ���� ������ -> ������ ��ü�� �迭�� ������
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
	

// -----------------------------------------------------------------------
	
	// ��ü Ÿ�� ������ �ش� ��ü�鸸 ��������(filter)
	@ResponseBody
	@RequestMapping("/filter/{value}")
	public String filter(@PathVariable String value) {
		return "";
	}
	
		
}
