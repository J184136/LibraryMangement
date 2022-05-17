<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">  
    
    <style>
      
h1{
    font-size: 100px;
    font-style: bold;
    color: black;
    background-color: yellow;
    border-radius: 50px;
}
h2{
  color: white;
}
#Stu{
font-size: 70px;
}
 a{
    color: black;
    text-decoration: none;
   }
.body {
    background: rgba(102, 73, 73, 0.5);
    background-image: url("${pageContext.request.contextPath}/resources/images/library.jpg");
    background-color: #cccccc;
    opacity: 0.9;
    height:100%;
   }
   img{
       border-radius: 10px;
   }
   .container{
   height:600px;
   }
   tbody, td, tfoot, th, thead, tr {
   font-size: 30px;
   }
   footer{
    background-color: Black;
   }
    </style>

    <script>
        function SearchFuntion(){
	   let filter = document.getElementById('myInput').value;
	   let filter2 = document.getElementById('myInput').value.toUpperCase();
	   let tableContain = document.getElementById('tableBody');
       let trContain = tableContain.getElementsByTagName('tr');
       let count=0;
	   for(var i=0;i<trContain.length;i++){
        let tdContain=trContain[i].getElementsByTagName('td');
        for(var j=0;j<tdContain.length;j++){
			let val=trContain[i].querySelector(".studentId").value;
			let val2=trContain[i].querySelector(".bookName").value.toUpperCase();
			if((val.indexOf(filter) > -1) || (val2.indexOf(filter2) > -1)){
			trContain[i].style.display='';
			count=count+1;
		   }
		   else{
			trContain[i].style.display='none';
		   }
        }
	   }
	   if(count==0){
			document.getElementById("SearchFind").style.visibility = "visible"
			document.getElementById("Studentdetials").style.visibility = "hidden"
		   }
		   else{
			document.getElementById("SearchFind").style.visibility = "hidden"
			document.getElementById("Studentdetials").style.visibility = "visible"
		   }
   }
    </script>
    
    <title>Library System</title>
  </head>
  <body >
    <nav class="navbar navbar-expand-lg navbar-dark  bg-dark navbar-fixed-top py-3 ">
        <div class="container-fluid">
          <a class="navbar-brand" href="#">
            <img src="${pageContext.request.contextPath}/resources/images/logoo.jpg" alt="Library Logoo" width="70" height="70">
          </a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="homeAdmin"><h2 class="mx-5">Home</h2></a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="booklist"><h2 class="mx-5">Book List</h2></a>
              </li>
            </ul>
            <form class="d-flex">
            <h2 class="mx-5">${Display4}</h2>
             <button type="button" class="btn btn-light btn-lg mx-3 px-5" ><a href="logout">LogOut</a></button>         
            </form>
          </div>
        </div>
      </nav>



	<div class="body h-100 py-5">
		<br> <br>
		<div class="container  my-5 py-5" align="center">
			<h1 class="text-md-center my-5 py-5">Library Management System</h1>
		</div>
	</div>
	<br>
	<br>
	<br>
	<div class="container h-100  pt-5">
		<div class="row justify-content-center mb-5 pb-5">
			<div class="col-12">
				<form class="card card-sm">
					<div class="card-body row no-gutters align-items-center">
						<div class="col-auto">
							<i class="fas fa-search h4 text-body"> </i>
						</div>
						<div class="col">
							<input
								class="form-control form-control-lg form-control-borderless"
								type="search" id="myInput" onkeyup="SearchFuntion()" onsearch="SearchFuntion()" placeholder="Search Student Id or BookName here..."?>
						</div>
						<div class="col-auto">
							<button class="btn btn-lg btn-dark" onclick="SearchFuntion()" type="button">
								Search</button>
						</div>

					</div>
				</form>
			</div>
		</div>
		<div id="SearchFind" style="visibility: hidden" class="text-center my-2 py-2">
			<h3>No Records Found</h3>
		</div>
		<div  id="Studentdetials">
			<h3 id="Stu" >Student Details</h3>
			<hr>
			<table class="table table-striped text-center">
				<thead>
					<tr>
					    <th scope="col" width="15%">Student Id</th>
						<th scope="col" width="15%">Book Name</th>
						<th scope="col" width="15%">Author</th>
						<th scope="col" width="15%">Price to Pay</th>
						<th scope="col" width="15%">Status</th>
						<th scope="col" width="15%">Requests</th>
					</tr>
				</thead>

				<tbody id="tableBody">
					<c:forEach items="${list}" var="item">
					<form action="actionBook">
						<tr>
						    <td><input type="hidden" class="studentId" name="studentId" value="${item.studentid}">${item.studentid}</td>
							<td><input type="hidden" class="bookName" name="bookName" value="${item.bookname}">${item.bookname}</td>
							<td><input type="hidden" name="author" value="${item.author}">${item.author}</td>
							<td>${item.price}</td>
							<td>${item.status}</td>
							<td>${item.request}</td>
							</form>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
	</div>
	<br> <br> <br>
	<footer class="mt-auto py-3">
		<p class="text-center text-muted">© 2022 Library Management System</p>
	  </footer>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</html>
