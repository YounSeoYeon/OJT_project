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

	//처음 리스트 화면
	@RequestMapping("/")
	public String index(Model model) {
		ArrayList<BusVO> vo = busservice.buslist();			//buslist()의 결과타입이 리스트 형식임. 
		System.out.println(vo.size());
		String[] tel = new String[3];
		String[] fax = new String[3];
		
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
			}else {	//전화번호 길이 031-000-0000 11자리일때
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
		
		model.addAttribute("vo",vo);
		System.out.println(vo);
		return "index";
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
		String[] tel = vo.getBus_tel().split(",");
		String[] fax = vo.getBus_fax().split(",");
		vo.setBus_tel(tel[0]+tel[1]+tel[2]);
		vo.setBus_fax(fax[0]+fax[1]+fax[2]);
		
		System.out.println(vo.getBus_code());
		System.out.println(vo.getBus_tel());
		busservice.busdbinsert(vo);
//		return "redirect:./";
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
//		return "redirect:/";
//		return "./index"; // /agw/busupdate/resources/이렇게 감		
//		return "redirect:./"+vo.getBus_idx(); // agw/busupdate/5
//		return "./busupdateform/"+vo.getBus_idx();
		System.out.println("넘김?");
//		return "redirect:../";
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
		return "redirect:/";
	}
	

// -----------------------------------------------------------------------
	
	// 업체 타입 선택후 해당 업체들만 나오도록(filter)
//	@ResponseBody
//	@RequestMapping("/filter/{value}")
//	public String filter(@PathVariable String value,Model model) {
//		ArrayList<BusVO> vo = busservice.filter(value);
//		model.addAttribute("vo",vo);
//		System.out.println(vo);
//		return "top";
//	}
	
	// radio값만 바꼈을때
	@RequestMapping("/filter/{value}")
	public String filter(@PathVariable String value, Model model) {
		ArrayList<BusVO> vo = busservice.filter(value);
		model.addAttribute("vo",vo);
		System.out.println(vo);
		return "top";
	}
	
	//radio + search 값 둘다 바꼈을 때 -> search 버튼 누르면
	@RequestMapping("/filter2/{search}")
	public String filter2(@PathVariable String search, Model model) {
		System.out.println(search);
		ArrayList<BusVO> vo = busservice.filter2(search);
		model.addAttribute("vo",vo);
		System.out.println(vo);
		return "top";
	}
	
	/****** Excel 추출 - 2022-04-08 하영 추가 *******/
	@RequestMapping("/excel/business")
	public void downloadExcel(HttpServletResponse res) throws IOException {
	
		ArrayList<BusVO> list = busservice.buslist();
		
		Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("업체 목록");
        int rowNo = 0;
        
        Row headerRow = sheet.createRow(rowNo++);
        headerRow.createCell(0).setCellValue("업체 코드");
        headerRow.createCell(1).setCellValue("상호");
        headerRow.createCell(2).setCellValue("대표자");
        headerRow.createCell(3).setCellValue("사업자 등록번호");
        headerRow.createCell(4).setCellValue("전화 번호");
        headerRow.createCell(5).setCellValue("팩스 번호");
        headerRow.createCell(6).setCellValue("종목명");
        headerRow.createCell(7).setCellValue("회사 메일");
        headerRow.createCell(8).setCellValue("회사 주소");
        
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
