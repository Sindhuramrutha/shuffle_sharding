<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html >
<head>
<meta charset="ISO-8859-1">
<title> Add Movie</title>

<style type="text/css">
.header {
  background-color: #90EE90;
  text-align: center;
  padding: 20px;
}
.footer {
            position: fixed;
            padding: 10px 10px 0px 10px;
            bottom: 0;
            width: 100%;
            height: 40px;
            background: #90EE90;
        }
    label {
        display: inline-block;
        width: 200px;
        margin: 5px;
        text-align: left;
    }
    input[type=text], input[type=password], select {
        width: 200px;  
    }
    
    button {
        padding: 10px;
        margin: 10px;
    }
    
    .addbtn
{
border-radius: 7px;
background-color: #7FFFD4;
  align: left;
  width: 7%;
}
input[type=submit] {
  background-color: #7FFFD4;
       border-radius: 6px;
           color  : black;
  padding: 16px 32px;
  text-decoration: none;
  margin: 2px 2px;
  cursor: pointer;
}
    
</style>
</head>
<body>
<div class="header">
  <h1></h1>
</div>
  <div  align = "center">
    <h1> Add Movie</h1>      
    
<form:form method="post" action="/movie" modelAttribute="movie">



Movie Name        &nbsp &nbsp &nbsp &nbsp<form:input path="name"/><br> <br/>
Year Of Release   &nbsp<form:input path="yearOfRelease"/><br>  <br/>
Cast              &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<form:input path="cast"/><br> <br/>
Director          &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<form:input path="director"/><br> <br/> <br> <br/>

      <b> <input  type="submit" value="Add Movie"/> </b>
</form:form>

  </div>
  <div class="footer">
  <p></p>
</div>

</body>
</html>