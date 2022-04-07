<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<input type="hidden" id="pagination" data-page="${pagination.page}" data-range="${pagination.range}" data-rangeSize="${pagination.rangeSize}"/>
		<ul class="pagination">
			<c:if test="${pagination.prev}">
				<li class="pageItem">
					<a id="prev" class="pageLink">&laquo;</a>
				</li>
			</c:if>
			<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="idx">
				<li class="pageItem <c:out value="${pagination.page == idx ? 'active' : ''}"/>">
					<a id="page" class="pageLink">${idx}</a>
				</li>
			</c:forEach>
			<c:if test="${pagination.next}">
				<li class="pageItem">
					<a id="next" class="pageLink">&raquo;</a>
				</li>
			</c:if>
		</ul>
	</body>
</html>