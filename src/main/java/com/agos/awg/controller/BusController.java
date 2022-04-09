package com.agos.awg.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
		System.out.println(vo.size());
		String[] tel = new String[3];
		String[] fax = new String[3];
		
		//��ȭ��ȣ
		for(int i=0; i<vo.size(); i++) {
			if(vo.get(i).getBus_tel().length()==10) {		//��ȭ��ȣ ���� 02-0000-0000 10�ڸ��϶�
				tel[0]=vo.get(i).getBus_tel().substring(0,2);
				tel[1]=vo.get(i).getBus_tel().substring(2,6);
				tel[2]=vo.get(i).getBus_tel().substring(6,10);
				vo.get(i).setBus_tel(tel[0]+"-"+tel[1]+"-"+tel[2]);
			}else if(vo.get(i).getBus_tel().length()==9) {	//��ȭ��ȣ ���� 02-000-0000 9�ڸ��϶�
				tel[0]=vo.get(i).getBus_tel().substring(0,2);
				tel[1]=vo.get(i).getBus_tel().substring(2,5);
				tel[2]=vo.get(i).getBus_tel().substring(5,9);
				vo.get(i).setBus_tel(tel[0]+"-"+tel[1]+"-"+tel[2]);
			}else {	//��ȭ��ȣ ���� 031-000-0000 11�ڸ��϶�
				tel[0]=vo.get(i).getBus_tel().substring(0,3);
				tel[1]=vo.get(i).getBus_tel().substring(3,7);
				tel[2]=vo.get(i).getBus_tel().substring(7,11);
				vo.get(i).setBus_tel(tel[0]+"-"+tel[1]+"-"+tel[2]);
			}
		}
		
		// �ѽ���ȣ
		for(int i=0; i<vo.size(); i++) {
			if(vo.get(i).getBus_fax().length()==10) {		
				fax[0]=vo.get(i).getBus_fax().substring(0,2);
				fax[1]=vo.get(i).getBus_fax().substring(2,6);
				fax[2]=vo.get(i).getBus_fax().substring(6,10);
				vo.get(i).setBus_fax(fax[0]+"-"+fax[1]+"-"+fax[2]);
			}else if(vo.get(i).getBus_fax().length()==9) {	
				fax[0]=vo.get(i).getBus_fax().substring(0,2);
				fax[1]=vo.get(i).getBus_fax().substring(2,5);
				fax[2]=vo.get(i).getBus_fax().substring(5,9);
				vo.get(i).setBus_fax(fax[0]+"-"+fax[1]+"-"+fax[2]);
			}else {	
				fax[0]=vo.get(i).getBus_fax().substring(0,3);
				fax[1]=vo.get(i).getBus_fax().substring(3,7);
				fax[2]=vo.get(i).getBus_fax().substring(7,11);
				vo.get(i).setBus_fax(fax[0]+"-"+fax[1]+"-"+fax[2]);
			}
		}
		
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
		String[] tel = vo.getBus_tel().split(",");
		String[] fax = vo.getBus_fax().split(",");
		vo.setBus_tel(tel[0]+tel[1]+tel[2]);
		vo.setBus_fax(fax[0]+fax[1]+fax[2]);
		
		System.out.println(vo.getBus_code());
		System.out.println(vo.getBus_tel());
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
//	@ResponseBody
//	@RequestMapping("/filter/{value}")
//	public String filter(@PathVariable String value,Model model) {
//		ArrayList<BusVO> vo = busservice.filter(value);
//		model.addAttribute("vo",vo);
//		System.out.println(vo);
//		return "top";
//	}
	
	// radio���� �ٲ�����
	@RequestMapping("/filter/{value}")
	public String filter(@PathVariable String value, Model model) {
		ArrayList<BusVO> vo = busservice.filter(value);
		model.addAttribute("vo",vo);
		System.out.println(vo);
		return "top";
	}
	
	//radio + search �� �Ѵ� �ٲ��� �� -> search ��ư ������
	@RequestMapping("/filter2/{search}")
	public String filter2(@PathVariable String search, Model model) {
		System.out.println(search);
		ArrayList<BusVO> vo = busservice.filter2(search);
		model.addAttribute("vo",vo);
		System.out.println(vo);
		return "top";
	}
	
	/****** Excel ���� - 2022-04-08 �Ͽ� �߰� *******/
	@RequestMapping("/excel/business")
	public void downloadExcel(HttpServletResponse res) throws IOException {
	
		ArrayList<BusVO> list = busservice.buslist();
		
		Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("��ü ���");
        int rowNo = 0;
        
        Row headerRow = sheet.createRow(rowNo++);
        headerRow.createCell(0).setCellValue("��ü �ڵ�");
        headerRow.createCell(1).setCellValue("��ȣ");
        headerRow.createCell(2).setCellValue("��ǥ��");
        headerRow.createCell(3).setCellValue("����� ��Ϲ�ȣ");
        headerRow.createCell(4).setCellValue("��ȭ ��ȣ");
        headerRow.createCell(5).setCellValue("�ѽ� ��ȣ");
        headerRow.createCell(6).setCellValue("�����");
        headerRow.createCell(7).setCellValue("ȸ�� ����");
        headerRow.createCell(8).setCellValue("ȸ�� �ּ�");
        
        for (BusVO business : list) {
            Row row = sheet.createRow(rowNo++);
            row.createCell(0).setCellValue(business.getBus_code());
            row.createCell(1).setCellValue(business.getBus_nm());
            row.createCell(2).setCellValue(business.getBus_rep());
            row.createCell(3).setCellValue(business.getBus_reg_no());
            row.createCell(4).setCellValue(business.getBus_tel());
            row.createCell(5).setCellValue(business.getBus_fax());
            row.createCell(6).setCellValue(business.getBus_item());
            row.createCell(7).setCellValue(business.getBus_email());
            row.createCell(8).setCellValue(business.getBusAddress());
        }
        
        res.setContentType("ms-vnd/excel");
        res.setHeader("Content-Disposition", "attachment;filename=boardlist.xls");
 
        workbook.write(res.getOutputStream());
        workbook.close();
	}
}
