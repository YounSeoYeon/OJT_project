<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<table>
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll"/></th>
					<th>카드 명의자</th>
					<th>카드 번호</th>
					<th>유효 기간</th>
					<th>카드 관리 계정</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${ not empty cardList }">
						<c:forEach items="${cardList}" var="card" >
							<tr>
								<td class="checkbox"><input type="checkbox" name="card" value="${card.card_id}" /></td>
								<td>${card.card_name}</td>
								<td>${card.card_no}</td>
								<td>${(card.card_ep).substring(0,7)}</td>
								<td>${card.card_id}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6">정보가 존재하지 않습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</body>
</html>