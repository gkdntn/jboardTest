<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글목록</title>
    <link rel="stylesheet" href="/jboard/css/style.css">    
</head>
<body>
    <div id="container">
       	<%@ include file="./_header.jsp" %>
        <main>
            <section class="list">
                <h3>글목록</h3>
                <article>
                    <table border="0">
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>글쓴이</th>
                            <th>날짜</th>
                            <th>조회</th>
                        </tr>
                        <c:forEach var="articleDto" items="${articleDto}">
                        <tr>
                            <td>${articleDto.row}</td>
                            <td><a href="/jboard/article/view.do?no=${articleDto.no}">${articleDto.title}</a>&nbsp;[${articleDto.comment}]</td>
                            <td>${articleDto.nick}</td>
                            <td>${articleDto.rdate}</td>
                            <td>${articleDto.hit}</td>
                        </tr>
                        </c:forEach>
                    </table>
                </article>
                <!-- 페이지 네비게이션 -->
                <div class="paging">
                	<c:set var="page" value="1" />
                    <c:if test="${page ne pageGroup.start}">
                    	<a href="/jboard/article/list.do?pg=${pageGroup.start - 1}" class="prev">이전</a>
                    </c:if>
                    <c:forEach var="i" begin="${pageGroup.start}" end="${pageGroup.end}">
                    	<a href="/jboard/article/list.do?pg=${i}" class="num">${i}</a>                
					</c:forEach>
					<c:set var="page" value="${lastPageNum}" />
                    <c:if test="${page > pageGroup.end}">
                    	<a href="/jboard/article/list.do?pg=${pageGroup.end + 1}" class="next">다음</a>
                    </c:if>
                </div>
                <!-- 글쓰기 버튼 -->
                <a href="/jboard/article/write.do" class="btnWrite">글쓰기</a>
            </section>
        </main>
        <%@ include file="./_footer.jsp" %>
    </div>
</body>
</html>