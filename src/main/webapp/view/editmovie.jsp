<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html >
<head>
<meta charset="ISO-8859-1">
<title> Add Movie</title>
<style type="text/css">
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
</style>
</head>
<body>

  <div  align = "center">
    <h1> Add Movie</h1>      
    
<form:form method="post" action="/editMovie" modelAttribute="movie">
Movie Name <form:input path="name" placeholder = "${movie.name}"/><br>
Year Of Release <form:input path="yearOfRelease" placeholder = "${movie.yearOfRealease}" /><br>
Cast <form:input path="cast" placeholder = "${movie.cast}" /><br>
Director <form:input path="director" placeholder =  "${movie.director}" /><br>
<input type="submit" value="Submit">



</form:form>

  </div>

</body>
</html>