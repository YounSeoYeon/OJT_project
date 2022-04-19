package com.agos.awg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
	@RequestMapping("/index")
	public String index(Model model) {
		
		//----------------------------------
		
		ArrayList<BusVO> vo = busservice.buslist();			//buslist()�� ���Ÿ���� ����Ʈ ������. 
		System.out.println(vo.size());
		String[] tel = new String[3];
		String[] fax = new String[3];
		String[] regno = new String[3];
		 
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
			}else {	//��ȭ��ȣ ���� 031-0000-0000 11�ڸ��϶�
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
		
		// ����ڹ�ȣ
		for(int i=0; i<vo.size(); i++) {
			regno[0]=vo.get(i).getBus_reg_no().substring(0,3);
			regno[1]=vo.get(i).getBus_reg_no().substring(3,5);
			regno[2]=vo.get(i).getBus_reg_no().substring(5,10);
			vo.get(i).setBus_reg_no(regno[0]+"-"+regno[1]+"-"+regno[2]);
		}
		
		
		model.addAttribute("vo",vo);
		System.out.println(vo);
		return "/business/index";
	}
	
	
	//��ü �߰�ȭ�� �̵�
	@RequestMapping("/businsert")
	public String businsert() {
		return "/business/businsert";
	}
	
	//��ü�ڵ� �ߺ��˻�
	@ResponseBody
	@RequestMapping("/buscodecheck")
	public int codecheck(@RequestParam("buscode") String buscode) {
		String checktext = busservice.codecheck(buscode);
		int result=1; //�ش��ϴ� ��ü�ڵ� ������ 1 // ������ 0	// �ڵ��������������� 2
		System.out.println(checktext);
		char[] array = buscode.toCharArray();
		System.out.println(array[0]);
		
		if(checktext==null) {	
			result =0;
			System.out.println(result);
			return result;
		}
		else if(array[0] != 'p' && array[0] != 'c' && checktext!=null) {
			result =2;
			System.out.println(result);
			return result;
		}		
		else {
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
		String[] email = vo.getBus_email().split(",");
		vo.setBus_tel(tel[0]+tel[1]+tel[2]);
		vo.setBus_fax(fax[0]+fax[1]+fax[2]);
		vo.setBus_email(email[0]+"@"+email[1]);
		
		System.out.println(vo.getBus_code());
		System.out.println(vo.getBus_tel());
		System.out.println(vo.getBus_email());
		busservice.busdbinsert(vo);
		
		return "popup";	// ����� �˾��ȳ�â���� �̵�
	}	
	
	//��ü�ڵ� ���������� �̵� (��ü���� �����ͼ� �������� ��Ÿ�� �����)
	@RequestMapping("/busupdateform/{bus_idx}")
	public String busupdateform(@PathVariable int bus_idx,Model model) {
		BusVO vo = busservice.busupdateform(bus_idx);
		model.addAttribute("vo",vo);
		return "business/busupdateform"; // agw/busupdateform/�̰����
	}
	
	//��ü������ db���
	@RequestMapping("/busupdate/{bus_idx}")
	public String busupdate(BusVO vo) {
		System.out.println("�޾ƿ�?");
		System.out.println(vo.getBus_code()+vo.getBus_fax());
		busservice.busdbupdate(vo);
		System.out.println("�ѱ�?");
		return "popup";	// ����� �˾��ȳ�â���� �̵�
	}
	
	// ���� ������ -> ������ ��ü�� �迭�� ������
	@RequestMapping("/busdelete/{chkvalue}")
	public String busdelete(@PathVariable ArrayList<Integer> chkvalue) {
		System.out.println(chkvalue);
		int a = chkvalue.get(0);
		System.out.println(a+"//"+chkvalue.size());
		for(int i=0; i<chkvalue.size(); i++) {
			busservice.busdbdelete(chkvalue.get(i));			
		}
		return "redirect:/index";
	}
	

// -----------------------------------------------------------------------
	
	// ��ü Ÿ��+�˻����� �ش� ��ü�鸸 ��������(filter)
	
	@RequestMapping("/filter")
	public String filter3(@RequestParam (required = false) String bustype, 
					@RequestParam(required = false) String search, Model model) {
		System.out.println("����");
		System.out.println(bustype+"&&"+search);
		HashMap<String, String> map = new HashMap<>();
		map.put("bustype", bustype);
		map.put("search", search);
		System.out.println(map.get("bustype")+"/"+map.get("search"));
		ArrayList<BusVO> vo = busservice.filter(map);
		System.out.println(vo);
		
		String[] tel = new String[3];
		String[] fax = new String[3];
		String[] regno = new String[3];
		 
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
			}else {	//��ȭ��ȣ ���� 031-0000-0000 11�ڸ��϶�
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
		
		// ����ڹ�ȣ
		for(int i=0; i<vo.size(); i++) {
			regno[0]=vo.get(i).getBus_reg_no().substring(0,3);
			regno[1]=vo.get(i).getBus_reg_no().substring(3,5);
			regno[2]=vo.get(i).getBus_reg_no().substring(5,10);
			vo.get(i).setBus_reg_no(regno[0]+"-"+regno[1]+"-"+regno[2]);
		}		
		
		model.addAttribute("vo",vo);
		return "business/top";
	}
	
	
	
	// �����ٿ�ε� �����
//	@RequestMapping("/excel")
//	public void downexcel(HttpServletResponse response) throws IOException {
//		Workbook workbook = new HSSFWorkbook();
//        Sheet sheet = workbook.createSheet();
//        int rowNo = 0;
// 
//        Row headerRow = sheet.createRow(rowNo++);
//        headerRow.createCell(0).setCellValue("�ε�����ȣ");
//        headerRow.createCell(1).setCellValue("��ü�ڵ�");
//        headerRow.createCell(2).setCellValue("��ü��");
//        headerRow.createCell(3).setCellValue("����ڹ�ȣ");
//        headerRow.createCell(4).setCellValue("��ǥ��");
//        headerRow.createCell(5).setCellValue("��ȭ��ȣ");
//        headerRow.createCell(6).setCellValue("�ѽ���ȣ");
//        headerRow.createCell(7).setCellValue("�ּ�");
//        headerRow.createCell(9).setCellValue("����");
// 
//        ArrayList<BusVO> vo = busservice.buslist();
//        for (BusVO board : vo) {
//            Row row = sheet.createRow(rowNo++);
//            row.createCell(0).setCellValue(board.getBus_idx());
//            row.createCell(1).setCellValue(board.getBus_code());
//            row.createCell(2).setCellValue(board.getBus_nm());
//            row.createCell(3).setCellValue(board.getBus_reg_no());
//            row.createCell(4).setCellValue(board.getBus_rep());
//            row.createCell(5).setCellValue(board.getBus_tel());
//            row.createCell(6).setCellValue(board.getBus_fax());
//            row.createCell(7).setCellValue(board.getBusAddress());
//            row.createCell(8).setCellValue(board.getBus_item());
//        }
// 
//        response.setContentType("ms-vnd/excel");
//        response.setHeader("Content-Disposition", "attachment;filename=boardlist.xls");
// 
//        workbook.write(response.getOutputStream());
//        workbook.close();
//	}
//	
	
	
	
	/****** Excel ���� - 2022-04-08 �Ͽ� �߰� *******/
	@RequestMapping("/excel/business")
	public void downloadExcel(HttpServletResponse res) throws IOException {
		final String fileName = "businessList.xlsx";
		
		/* ���� �׸��� */
		final String[] colNames = {
			"��ü �ڵ�", "��ȣ", "��ǥ��", "����� ��Ϲ�ȣ", "��ȭ ��ȣ", "�ѽ� ��ȣ", "�����", "ȸ�� ����", "ȸ�� �ּ�"
		};
		
		// �� ������
		final int[] colWidths = {
			3000, 5000, 3000, 6000, 5000, 5000, 3000, 8000, 8000
		};
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = null;
		XSSFCell cell = null;
		XSSFRow row = null;
		
		//Font
		Font fontHeader = workbook.createFont();
		fontHeader.setFontName("���� ���");			//�۾�ü
		fontHeader.setFontHeight((short)(9 * 20));	//������
		fontHeader.setBold(true);					//����(����)
		
		Font fontBody = workbook.createFont();
		fontBody.setFontName("���� ���");				//�۾�ü
		fontBody.setFontHeight((short)(9 * 20));	//������
		
		// ���� ��� ����
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFont(fontHeader);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headerStyle.setBorderTop(BorderStyle.THICK); 	// �� �� �׵θ� �Ǽ� ����
		headerStyle.setBorderBottom(BorderStyle.THICK); // �� �Ʒ� �׵θ� �Ǽ� ����
		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		// ���� �ٵ� ����
		CellStyle bodyStyle = workbook.createCellStyle();
		bodyStyle.setFont(fontBody);
		bodyStyle.setAlignment(HorizontalAlignment.CENTER);
		bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		
		//rows
		int rowCnt = 1;
		int cellCnt = 1;
		
		ArrayList<BusVO> list = busservice.buslist();
		
		// ���� ��Ʈ�� ����
		sheet = workbook.createSheet("��ü ���");
		row = sheet.createRow(rowCnt++);
		
		//��� ���� ����
		for (int i = 0; i < colNames.length; i++) {
			cell = row.createCell(i+1);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(colNames[i]);
			sheet.setColumnWidth(i+1, colWidths[i]);	//column width ����
		}
		
		//������ �κ� ����
		for(BusVO vo : list) {
			cellCnt = 1;
			
			row = sheet.createRow(rowCnt++);
			
			// ��ü�ڵ� 
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBus_code());
			
			// ��ȣ
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBus_nm());
			
			// ��ǥ��
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBus_rep());
			
			// ����� ��Ϲ�ȣ
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBus_reg_no());
			
			// ��ȭ ��ȣ
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBus_tel());
			
			// �ѽ� ��ȣ
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBus_fax());
			
			// �����
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBus_item());
			
			// ȸ�� ����
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBus_email());
			
			// ȸ�� �ּ�
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBusAddress());
		}
		res.setContentType("application/vnd.ms-excel");
		// ���� ���ϸ� ����
		res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		workbook.write(res.getOutputStream());
		workbook.close();
	}
}
