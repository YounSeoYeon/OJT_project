package com.agos.awg.common;

public class Pagination {
	private int listSize = 10;		// 한 패이지당 보여줄 리스트 수
	private int rangeSize = 10;		// 한 페이지당 보여줄 페이지 범위 
	private int page;				// 현재 페이지 번호
	private int range; 				// 현재 페이지 범위 
	private int listCnt;			// 전체 리스트 수
	private int pageCnt;			// 전체 페이지 수
	private int startList; 			// 현재 페이지의 시작 리스트
	private int startPage; 			// 현재 페이지 범위의 시작 페이지 번호
	private int endPage;			// 현재 페이지 범위의 마지막 페이지 번호
	private boolean prev;			// 이전 버튼 생성 여부
	private boolean next;			// 다음 버튼 생성 여부
	
	/** 현재 페이지 범위에 대한 정보를 구하는 함수 **/
	public void pageInfo(int page, int range, int listCnt) {
		this.page = page;
		this.range = range;
		this.listCnt = listCnt;

		//전체 페이지수 
		this.pageCnt = (int) Math.ceil(listCnt/listSize);
		
		//시작 페이지
		this.startPage = (range - 1) * rangeSize + 1 ;
		
		//끝 페이지
		this.endPage = range * rangeSize;
				
		//게시판 시작번호
		this.startList = (page - 1) * listSize;

		//이전 버튼 상태
		this.prev = range == 1 ? false : true;
		
		//다음 버튼 상태
		this.next = endPage > pageCnt ? false : true;
		// 마지막 페이지 번호가 전체 페이지 수 보다 크면, 마지막 페이지 번호 = 전체 페이지 수 (페이지가 10개 단위로 만들어 지기때문)
		if (this.endPage > this.pageCnt) {
			this.endPage = this.pageCnt;
			this.next = false;
		}
	}
	
	/** Getter Setter **/
	public int getListSize() {
		return listSize;
	}
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	public int getRangeSize() {
		return rangeSize;
	}
	public void setRangeSize(int rangeSize) {
		this.rangeSize = rangeSize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getListCnt() {
		return listCnt;
	}
	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public int getStartList() {
		return startList;
	}
	public void setStartList(int startList) {
		this.startList = startList;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
}
