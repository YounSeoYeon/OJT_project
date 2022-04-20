package com.agos.awg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
//		System.out.println(vo.get(0).getProj_start_date().substring(2,10));
		
		ArrayList<String> amountList = new ArrayList<String>();
		
		for(int i=0; i<vo.size(); i++) {
			startdate=vo.get(i).getProj_start_date().substring(2,10);
			enddate=vo.get(i).getProj_end_date().substring(2,10);			
			vo.get(i).setProj_start_date(startdate);
			vo.get(i).setProj_end_date(enddate);
			
			/* Amount õ ���� �޸�  */
			String amount = Integer.toString(vo.get(i).getProj_amount());
			String FormattedAmount = amount.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			amountList.add(FormattedAmount);
		}
		
		model.addAttribute("vo",vo);
		model.addAttribute("amountList", amountList);
//		System.out.println(vo);
//		System.out.println(amountList);
		return "project/projindex";
	}
	
	//������Ʈ �߰�ȭ�� �̵�
	@RequestMapping("/projinsert")
	public String projinsert() {
		return "project/projinsert";
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
	
//	radio�� �ٲ�����
//	@RequestMapping("/projfilter/{value}")
//	public String projfilter(@PathVariable String value, Model model) {
//		ArrayList<ProjVO> vo = proj.projfilter(value);
//		ArrayList<String> amountList = new ArrayList<String>();
//		
//		for(int i=0; i<vo.size(); i++) {
//			/* Amount õ ���� �޸�  */
//			String amount = Integer.toString(vo.get(i).getProj_amount());
//			String FormattedAmount = amount.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
//			amountList.add(FormattedAmount);
//		}
//		
//		model.addAttribute("vo",vo);
//		model.addAttribute("amountList", amountList);
////		System.out.println(amountList);
//		return "project/projtop";
//	}
	
	
	@RequestMapping("/projsearchfilter/{search}")
	public String projsearchfilter(@PathVariable String search, Model model) {
		ArrayList<ProjVO> vo = proj.projsearchfilter(search);
		ArrayList<String> amountList = new ArrayList<String>();
		
		for(int i=0; i<vo.size(); i++) {
			/* Amount õ ���� �޸�  */
			String amount = Integer.toString(vo.get(i).getProj_amount());
			String FormattedAmount = amount.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			amountList.add(FormattedAmount);
		}
		
		model.addAttribute("vo",vo);
		model.addAttribute("amountList", amountList);
//		System.out.println(amountList);
		return "project/projtop";
	}
	
	// ������Ʈ ���� ������ �̵� 
	@RequestMapping("/project/updateProjectView/{idx}")
	public String updateCardView(@PathVariable("idx") String poject_idx, Model model) {
		ProjVO project = proj.getProjectInfo(poject_idx);
		
		/* Date ���� ���� : date-local ���� */
		String FormattedStartDate = project.getProj_start_date().replace(' ', 'T');
		String FormattedEndDate = project.getProj_end_date().replace(' ', 'T');
		
		project.setProj_start_date(FormattedStartDate);
		project.setProj_end_date(FormattedEndDate);
		
		/* Amount õ ���� �޸�  */
		String amount = Integer.toString(project.getProj_amount());
		String FormattedAmount = amount.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
		
		// System.out.println("�ݾ� : " + FormattedAmount);
		
		model.addAttribute("project", project);
		model.addAttribute("amount", FormattedAmount);
		
		return "project/updateProject";
	}
	
	// ������Ʈ ���� ����
	@ResponseBody
	@RequestMapping("/project/updateProject")
	public int updateProject(ProjVO projVO) {
		System.out.println(projVO);
		
		int result = proj.updateProject(projVO);
		
		return result;
	}
	
	// ������Ʈ ���� ����
	@ResponseBody
	@RequestMapping("/project/deleteProject")
	public int deleteProject(@RequestParam("idxArray[]") List<String> idxArray) {
		int result = proj.deleteProject(idxArray);
		
		return result;
	}
	
	/****** Excel ���� - 2022-04-08 �Ͽ� �߰� *******/
	@RequestMapping("/excel/project")
	public void downloadExcel(HttpServletResponse res) throws IOException {
		final String fileName = "projectList.xlsx";
		
		/* ���� �׸��� */
		final String[] colNames =  {
			"������Ʈ �ڵ�", "������Ʈ ��", "��� �ݾ�", "����ó", "��� ���� �Ⱓ", "��� ���� �Ⱓ"
		};
		
		// �� ������
		final int[] colWidths = {
			3000, 5000, 3000, 3000, 5000, 5000
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
		
		ArrayList<ProjVO> list = proj.projlist();
		
		// ���� ��Ʈ�� ����
		sheet = workbook.createSheet("������Ʈ ���");
		row = sheet.createRow(rowCnt++);
		
		//��� ���� ����
		for (int i = 0; i < colNames.length; i++) {
			cell = row.createCell(i+1);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(colNames[i]);
			sheet.setColumnWidth(i+1, colWidths[i]);	//column width ����
		}
		
		//������ �κ� ����
		for(ProjVO vo : list) {
			cellCnt = 1;
			
			row = sheet.createRow(rowCnt++);
			
			// ������Ʈ �ڵ� 
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getProj_code());
			
			// ������Ʈ ��
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getProj_nm());
			
			// ��� �ݾ�
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getProj_amount());
			
			// ����ó
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getProj_buyer());
			
			// ��� ���� �Ⱓ
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getProj_start_date());
			
			// ��� ���� �Ⱓ
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getProj_end_date());
		}
		res.setContentType("application/vnd.ms-excel");
		// ���� ���ϸ� ����
		res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		workbook.write(res.getOutputStream());
		workbook.close();
	}
		
}
