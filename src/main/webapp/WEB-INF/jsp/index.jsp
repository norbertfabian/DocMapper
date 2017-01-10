<%--
  Created by IntelliJ IDEA.
  User: Norbert Fabian
  Date: 26.12.2016
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pagetemplate pageTitle="Fill template">
    <jsp:attribute name="content">
        <form:form method="post" action="${pageContext.request.contextPath}/process"
                   modelAttribute="values" cssClass="form-horizontal">

            <h1>Fill template</h1>
            <table class="table vertical-align-table">
                <tr>
                    <td>Project title:</td>
                    <td>
                        <div>
                            <form:label path="projectTitle" cssClass="col-xs-6 control-label"/>
                            <form:input path="projectTitle" cssClass="form-control"/>
                            <form:errors path="projectTitle" cssClass="help-block"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Project acronym:</td>
                    <td>
                        <div>
                            <form:label path="projectAcronym" cssClass="col-xs-6 control-label"/>
                            <form:input path="projectAcronym" cssClass="form-control"/>
                            <form:errors path="projectAcronym" cssClass="help-block"/>
                        </div>
                    </td>
                </tr>
                <tr>
                <tr>
                    <td>Signature date:</td>
                    <td>
                        <div>
                            <form:label path="signatureDate" cssClass="col-xs-6 control-label"/>
                            <form:input path="signatureDate" cssClass="form-control"/>
                            <form:errors path="signatureDate" cssClass="help-block"/>
                        </div>
                        <i>Write as text. Use the format you want to have in your document. <br/>
                            Live blank if you want to use the current date in dd.mm.yyyy format.</i>
                    </td>
                </tr>
                    <td>Template:</td>
                    <td>
                        <div>
                            <form:select path="template" cssClass="col-xs-6 form-control">
                                <form:options items="${templates}"/>
                            </form:select>
                        </div>
                    </td>
                </tr>

                <!-- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
                <!-- CHANGE END INDEX TO CHANGE THE AMOUNT OF PARTNERS -->
                <!-- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
                <c:forEach begin="0" end="10" varStatus="partnerIndex">
                    <tr>
                        <td>Partner ${partnerIndex.index}:</td>
                        <td>
                            <form:select path="pics[${partnerIndex.index}]" cssClass="col-xs-6 form-control">
                                <form:option value=""/>
                                <c:forEach var="partner" items="${partners}" >
                                    <option value="${partner.pic}" label="${partner.country} - ${partner.name}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <div class="row pull-right">
                <div class="col-lg-8">
                    <b>Generate pdf:</b> <form:checkbox path="generatePdf"/>
                </div>
                <div class="col-lg-4">
                    <button class="btn btn-primary " type="submit">Fill template</button>
                </div>
            </div>
        </form:form>
    </jsp:attribute>
</t:pagetemplate>
