<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
<title>View All Movies</title> 

<style> 
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
table, th, td {
  border: 1px solid black;
}
table {
  width: 70%;
}
table {
  border-collapse: collapse;
}
td {
  height: 35px;
  padding: 5px 10px;
  text-align: left;
}

tr:nth-child(even) {background-color: #f2f2f2;}
th {
  background-color: #00FF7F;
  color: Black;
   height: 40px;
}
button {
  background-color: #04AA6D;
  color: black;
  padding: 5px 10px;
  margin: 3px 0;
  border: none;
  cursor: pointer;
  width: 30%;
  opacity: 0.9;
}
button:hover {
  opacity:1;
}
.deletebtn {
border-radius: 7px;
  width: 98%;
}
.addbtn
{
border-radius: 7px;
background-color: #7FFFD4;
  align: left;
  width: 10%;
}
.deletebtn {
  align : float;
  background-color: #7FFFD4;
}
</style>
</head>

<body>
<div class="header">
  <p align = "right"><a href="logout" align="right">Logout</a></p>
</div>

<div align="center"> 
  <h2 align=center>View All Movies</h2><br/>
 <a href="/AddMovie"><button type="button" class="addbtn" align =left><b> Add Movie </b></button> </a> <br/>
<table align=center border=1>
<tr>
<th> ID </th>
<th> Movie Name </th>
<th> Year Of Release </th>
<th> Cast </th>
<th> Director </th>
<th> Action </th>
</tr>
<c:forEach var="movie" items="${moviedata.get()}">

<tr>
<td>${movie.id}</td>
<td>${movie.name}</td>
<td>${movie.yearOfRelease}</td>
<td>${movie.cast}  </td>
<td> ${movie.director}</td>
<td>  <a href="/deleteMovie/${movie.id}"> <button type="button" class="deletebtn"><b>Delete</b> </button>  </a> </td>
</tr>
</c:forEach>
</table> 
  <br/>
  <br/> <br/> <br/> <br/> <br/>
  </div>
<div class="footer">
  <p></p>
</div>

</body>

</html>