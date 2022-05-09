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

    font-style: bold;
    color: black;
  
}
h2{
  color: white;
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
   .table{
   font-size:25px;
   
   } 
   th,td {
  padding: 10px;
}
footer{
    background-color: Black;
   }
    </style>

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
                <a class="nav-link active" aria-current="page" href="homeLogin"><h2 class="mx-5">Home</h2></a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="issueBook"><h2 class="mx-5">Issue Books</h2></a>
              </li>
            </ul>
            <form class="d-flex">
            <h2 class="mx-5">${Display3}</h2>
             <button type="button" class="btn btn-light btn-lg mx-3 px-5" ><a href="logout">LogOut</a></button>         
            </form>
          </div>
        </div>
      </nav>

      <br>
	<div class="container h-100 my-5 py-5 mx-7 px-7" width="75%">
			<h1>Issue Books</h1>
			<hr>
			<table class="table table-striped text-center">
				<thead>
					<tr>
						<th scope="col">Book Name</th>
						<th scope="col">Author</th>
						<th scope="col">IssueBook Date</th>
						<th scope="col">Return/Renew Date</th>
						<th scope="col">Price to Pay</th>
						<th scope="col">Status</th>
					</tr>
				</thead>

				<tbody id="tableBody">
					<c:forEach items="${list}" var="item">
						<tr>
							<td>${item.bookname}</td>
							<td>${item.author}</td>
							<td>${item.todayDate}</td>
							<td>${item.returnDate}</td>
							<td>${item.price}</td>
							<td>${item.status}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
</div>
</div>
<br> <br> <br>
<footer class="fixed-bottom mt-auto py-3">
		<p class="text-center text-muted">© 2022 Library Management System</p>
	  </footer>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</html>