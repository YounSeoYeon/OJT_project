package com.agos.awg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
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

	//처음 리스트 화면
	@RequestMapping("/index")
	public String index(Model model) {
		
		//----------------------------------
		
		ArrayList<BusVO> vo = busservice.buslist();			//buslist()의 결과타입이 리스트 형식임. 
		System.out.println(vo.size());
		String[] tel = new String[3];
		String[] fax = new String[3];
		String[] regno = new String[3];
		 
		//전화번호
		for(int i=0; i<vo.size(); i++) {
			if(vo.get(i).getBus_tel().length()==10) {		//전화번호 길이 02-0000-0000 10자리일때
				tel[0]=vo.get(i).getBus_tel().substring(0,2);
				tel[1]=vo.get(i).getBus_tel().substring(2,6);
				tel[2]=vo.get(i).getBus_tel().substring(6,10);
				vo.get(i).setBus_tel(tel[0]+"-"+tel[1]+"-"+tel[2]);
			}else if(vo.get(i).getBus_tel().length()==9) {	//전화번호 길이 02-000-0000 9자리일때
				tel[0]=vo.get(i).getBus_tel().substring(0,2);
				tel[1]=vo.get(i).getBus_tel().substring(2,5);
				tel[2]=vo.get(i).getBus_tel().substring(5,9);
				vo.get(i).setBus_tel(tel[0]+"-"+tel[1]+"-"+tel[2]);
			}else {	//전화번호 길이 031-0000-0000 11자리일때
				tel[0]=vo.get(i).getBus_tel().substring(0,3);
				tel[1]=vo.get(i).getBus_tel().substring(3,7);
				tel[2]=vo.get(i).getBus_tel().substring(7,11);
				vo.get(i).setBus_tel(tel[0]+"-"+tel[1]+"-"+tel[2]);
			}
		}
		
		// 팩스번호
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
		
		// 사업자번호
		for(int i=0; i<vo.size(); i++) {
			regno[0]=vo.get(i).getBus_reg_no().substring(0,3);
			regno[1]=vo.get(i).getBus_reg_no().substring(3,5);
			regno[2]=vo.get(i).getBus_reg_no().substring(5,10);
			vo.get(i).setBus_reg_no(regno[0]+"-"+regno[1]+"-"+regno[2]);
		}
		
		
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
		int result=1; //해당하는 업체코드 있을때 1 // 없을때 0	// 코드유형맞지않을때 2
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

	//업체코드 등록폼 작성후 중복확인,유효성검사 거쳐서 디비에 등록
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
		
		return "popup";	// 등록후 팝업안내창으로 이동
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
		System.out.println("넘김?");
		return "popup";	// 등록후 팝업안내창으로 이동
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
		return "redirect:/index";
	}
	

// -----------------------------------------------------------------------
	
	// 업체 타입+검색조건 해당 업체들만 나오도록(filter)
	
	@RequestMapping("/filter/{bustype}9{search}")
	public String filter3(@RequestParam (required = false) String bustype, 
					@RequestParam(required = false) String search, Model model) {
		System.out.println("받음");
		System.out.println(bustype+"&&"+search);
		HashMap<String, String> map = new HashMap<>();
		map.put("bustype", bustype);
		map.put("search", search);
		System.out.println(map.get("bustype")+"/"+map.get("search"));
		ArrayList<BusVO> vo = busservice.filter(map);
		System.out.println(vo.get(0).getBus_code());
		
		String[] tel = new String[3];
		String[] fax = new String[3];
		String[] regno = new String[3];
		 
		//전화번호
		for(int i=0; i<vo.size(); i++) {
			if(vo.get(i).getBus_tel().length()==10) {		//전화번호 길이 02-0000-0000 10자리일때
				tel[0]=vo.get(i).getBus_tel().substring(0,2);
				tel[1]=vo.get(i).getBus_tel().substring(2,6);
				tel[2]=vo.get(i).getBus_tel().substring(6,10);
				vo.get(i).setBus_tel(tel[0]+"-"+tel[1]+"-"+tel[2]);
			}else if(vo.get(i).getBus_tel().length()==9) {	//전화번호 길이 02-000-0000 9자리일때
				tel[0]=vo.get(i).getBus_tel().substring(0,2);
				tel[1]=vo.get(i).getBus_tel().substring(2,5);
				tel[2]=vo.get(i).getBus_tel().substring(5,9);
				vo.get(i).setBus_tel(tel[0]+"-"+tel[1]+"-"+tel[2]);
			}else {	//전화번호 길이 031-0000-0000 11자리일때
				tel[0]=vo.get(i).getBus_tel().substring(0,3);
				tel[1]=vo.get(i).getBus_tel().substring(3,7);
				tel[2]=vo.get(i).getBus_tel().substring(7,11);
				vo.get(i).setBus_tel(tel[0]+"-"+tel[1]+"-"+tel[2]);
			}
		}
		
		// 팩스번호
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
		
		// 사업자번호
		for(int i=0; i<vo.size(); i++) {
			regno[0]=vo.get(i).getBus_reg_no().substring(0,3);
			regno[1]=vo.get(i).getBus_reg_no().substring(3,5);
			regno[2]=vo.get(i).getBus_reg_no().substring(5,10);
			vo.get(i).setBus_reg_no(regno[0]+"-"+regno[1]+"-"+regno[2]);
		}		
		
		model.addAttribute("vo",vo);
		return "top";
	}
	
	
	
	// 엑셀다운로드 만들기
	@RequestMapping("/excel")
	public void downexcel(HttpServletResponse response) throws IOException {
		Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int rowNo = 0;
 
        Row headerRow = sheet.createRow(rowNo++);
        headerRow.createCell(0).setCellValue("인덱스번호");
        headerRow.createCell(1).setCellValue("업체코드");
        headerRow.createCell(2).setCellValue("업체명");
        headerRow.createCell(3).setCellValue("사업자번호");
        headerRow.createCell(4).setCellValue("대표자");
        headerRow.createCell(5).setCellValue("전화번호");
        headerRow.createCell(6).setCellValue("팩스번호");
        headerRow.createCell(7).setCellValue("주소");
        headerRow.createCell(9).setCellValue("종목");
 
        ArrayList<BusVO> vo = busservice.buslist();
        for (BusVO board : vo) {
            Row row = sheet.createRow(rowNo++);
            row.createCell(0).setCellValue(board.getBus_idx());
            row.createCell(1).setCellValue(board.getBus_code());
            row.createCell(2).setCellValue(board.getBus_nm());
            row.createCell(3).setCellValue(board.getBus_reg_no());
            row.createCell(4).setCellValue(board.getBus_rep());
            row.createCell(5).setCellValue(board.getBus_tel());
            row.createCell(6).setCellValue(board.getBus_fax());
            row.createCell(7).setCellValue(board.getBusAddress());
            row.createCell(8).setCellValue(board.getBus_item());
        }
 
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=boardlist.xls");
 
        workbook.write(response.getOutputStream());
        workbook.close();
	}
	
	
	
	
	/****** Excel 추출 - 2022-04-08 하영 추가 *******/
	@RequestMapping("/excel/business")
	public void downloadExcel(HttpServletResponse res) throws IOException {
		final String fileName = "businessList.xlsx";
		
		/* 엑셀 그리기 */
		final String[] colNames = {
			"업체 코드", "상호", "대표자", "사업자 등록번호", "전화 번호", "팩스 번호", "종목명", "회사 메일", "회사 주소"
		};
		
		// 열 사이즈
		final int[] colWidths = {
			3000, 5000, 3000, 6000, 5000, 5000, 3000, 8000, 8000
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
		
		ArrayList<BusVO> list = busservice.buslist();
		
		// 엑셀 시트명 설정
		sheet = workbook.createSheet("업체 목록");
		row = sheet.createRow(rowCnt++);
		
		//헤더 정보 구성
		for (int i = 0; i < colNames.length; i++) {
			cell = row.createCell(i+1);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(colNames[i]);
			sheet.setColumnWidth(i+1, colWidths[i]);	//column width 지정
		}
		
		//데이터 부분 생성
		for(BusVO vo : list) {
			cellCnt = 1;
			
			row = sheet.createRow(rowCnt++);
			
			// 업체코드 
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBus_code());
			
			// 상호
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBus_nm());
			
			// 대표자
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBus_rep());
			
			// 사업자 등록번호
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBus_reg_no());
			
			// 전화 번호
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBus_tel());
			
			// 팩스 번호
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBus_fax());
			
			// 종목명
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBus_item());
			
			// 회사 메일
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBus_email());
			
			// 회사 주소
			cell = row.createCell(cellCnt++);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBusAddress());
		}
		res.setContentType("application/vnd.ms-excel");
		// 엑셀 파일명 설정
		res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		workbook.write(res.getOutputStream());
		workbook.close();
	}
}
