<%--
  Created by IntelliJ IDEA.
  User: Norbert Fabian
  Date: 08.04.2016
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pagetemplate pageTitle="Documents">
    <jsp:attribute name="content">
            <h1>Documents</h1>
    <table class="table">
        <thead>
        <tr>
            <th>Document</th>
            <th>PDF</th>
            <th>DOCX</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${documents}" var="name">
                <tr>
                    <td class="vertical-align"><c:out value="${name}"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/documents/pdf/${name}" class="btn btn-primary">
                            Download as PDF
                        </a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/documents/docx/${name}" class="btn btn-primary">
                            Download as DOCX
                        </a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/documents/delete/${name}" class="btn btn-primary">
                            Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </jsp:attribute>
</t:pagetemplate>