<%@ page import="servlet.springservlet.domain.member.MemberRepository" %>
<%@ page import="servlet.springservlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: hyunjun
  Date: 2022/03/17
  Time: 1:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // JSP도 결국은 Servlet으로 변환되기 때문에 request와 response를 따로 가져오거나 선언할 필요 없이 사용할 수 있음
    MemberRepository memberRepository = MemberRepository.getInstance();

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
</body>
</html>
