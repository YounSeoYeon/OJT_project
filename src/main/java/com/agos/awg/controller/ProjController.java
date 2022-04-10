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
	
	// 카드 계정 수정 페이지 
	@RequestMapping("/project/updateProjectView/{idx}")
	public String updateCardView(@PathVariable("idx") String poject_idx, Model model) {
		ProjVO project = proj.getProjectInfo(poject_idx);
		
		model.addAttribute("project", project);
		return "/updateProject";
	}
	
	// 프로젝트 정보 수정
	@ResponseBody
	@RequestMapping("/project/updateProject")
	public int updateProject(ProjVO projVO) {
		int result = proj.updateProject(projVO);
		
		return result;
	}
	
	// 프로젝트 정보 삭제
	@ResponseBody
	@RequestMapping("/project/deleteProject")
	public int deleteProject(@RequestParam("idxArray[]") List<String> idxArray) {
		int result = proj.deleteProject(idxArray);
		
		return result;
	}
	
	/****** Excel 추출 - 2022-04-08 하영 추가 *******/
	@RequestMapping("/excel/project")
	public void downloadExcel(HttpServletResponse res) throws IOException {
		final String fileName = "projectList.xlsx";
		
		/* 엑셀 그리기 */
		final String[] colNames =  {
			"프로젝트 코드", "프로젝트 명", "계약 금액", "발주처", "계약 시작 기간", "계약 만료 기간"
		};
		
		// 열 사이즈
		final int[] colWidths = {
			3000, 5000, 3000, 3000, 5000, 5000
		};
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = null;
		XSSFCell cell = null;
		XSSFRow row = null;
		
		//Font
		Font fontHeader = workbook.createFont();
		fontHeader.setFontName("맑은 고딕");			//글씨체
		fontHeader.setFontHeight((short)(9 * 20));	//사이즈
		fontHeader.setBold(true);					//볼드(굵게)
		
		Font fontBody = workbook.createFont();
		fontBody.setFontName("맑은 고딕");				//글씨체
		fontBody.setFontHeight((short)(9 * 20));	//사이즈
		
		// 엑셀 헤더 셋팅
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFont(fontHeader);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headerStyle.setBorderTop(BorderStyle.THICK); 	// 셀 위 테두리 실선 적용
		headerStyle.setBorderBottom(BorderStyle.THICK); // 셀 아래 테두리 실선 적용
		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		// 엑셀 바디 셋팅
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
		
		// 엑셀 시트명 설정
		sheet = workbook.createSheet("프로젝트 목록");
		row = sheet.createRow(rowCnt++);
		
		//헤더 정보 구성
		for (int i = 0; i < colNames.length; i++) {
			cell = row.createCell(i+1);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(colNames[i]);
			sheet.setColumnWidth(i+1, colWidths[i]);	//column width 지정
		}
		
		//데이터 부분 생성
		for(ProjVO vo : list) {
			cellCnt = 1;
			
			row = sheet.createRow(rowCnt++);
			
			// 프로젝트 코드 
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getProj_code());
			
			// 프로젝트 명
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getProj_nm());
			
			// 계약 금액
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getProj_amount());
			
			// 발주처
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getProj_buyer());
			
			// 계약 시작 기간
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getProj_start_date());
			
			// 계약 만료 기간
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getProj_end_date());
		}
		res.setContentType("application/vnd.ms-excel");
		// 엑셀 파일명 설정
		res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		workbook.write(res.getOutputStream());
		workbook.close();
	}
		
}
