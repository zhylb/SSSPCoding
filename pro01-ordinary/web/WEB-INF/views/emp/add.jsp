<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
  <script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.9.1.min.js"></script>
  <script type="text/javascript">
    $(function(){
      $("#lastName").change(function(){
        var val = $(this).val();
        val = $.trim(val);
        $(this).val(val);

        //若修改的 lastName 和之前的 lastName 一致, 则不发送 Ajax 请求, 直接 alert:lastName 可用!
        var _oldLastName = $("#_oldLastName").val();
        _oldLastName = $.trim(_oldLastName);
        if(_oldLastName != null && _oldLastName != "" && _oldLastName == val){
          alert("lastName 可用!");
          return;
        }

        var url = "${pageContext.request.contextPath }/ajaxValidateLastName";
        var args = {"lastName":val,"date":new Date()};

        $.post(url, args, function(data){
          if(data == "0"){
            alert("lastName 可用!");
          }else if(data == "1"){
            alert("lastName 不可用!");
          }else{
            alert("网络或程序出错. ");
          }
        });
      });
    })
  </script>
</head>
<body>

<c:set value="${pageContext.request.contextPath }/emp" var="url"></c:set>
<c:if test="${emp.id != null }">
  <c:set value="${pageContext.request.contextPath }/emp/${emp.id}" var="url"></c:set>
</c:if>

<form:form action="${url}" method="POST" modelAttribute="emp">

  <c:if test="${emp.id != null }">
    <input type="hidden" id="_oldLastName" value="${emp.lastName}"/>
    <form:hidden path="id"/>
<%--    <form:hidden path="createDate"/>--%>
    <input type="hidden" name="_method" value="PUT"/>
  </c:if>

  LastName: <form:input path="lastName" id="lastName"/>
  <br>
  Email: <form:input path="email"/>
  <br>
  Birth: <form:input path="birth"/>
  <br>
  Department:
  <form:select path="department.id" items="${depts }"
               itemLabel="departmentName" itemValue="id"></form:select>
  <br>
  <input type="submit" value="Submit"/>

</form:form>

</body>
</html>