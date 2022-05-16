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
    .modal-footer{
    background-color: black;
     color:white;
   }
   footer{
    background-color: Black;
   }
    </style>
     <script>
     function SearchFuntion() {
  	   let filter = document.getElementById('myInput').value.toUpperCase();
  	   let rowContain = document.getElementById('cardbody');
  	   let colContain = rowContain.getElementsByClassName('colbody');
  	   for(var i=0;i<colContain.length;i++){
  		   let val=colContain[i].querySelector(".card-title");
  		   if(val.innerText.toUpperCase().indexOf(filter) > -1){
  			colContain[i].style.display='';
  		   }
  		   else{
  			colContain[i].style.display='none';
  		   }
  	   }
     }
     window.onload = function(){
   	  document.getElementById('clickButton').click();
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

   

      <div class="body h-100 py-5">
      <br>
      <br>
    <div class="container  my-5 py-5" align="center">
        <h1 class="text-md-center my-5 py-5">Library Management System </h1>
    </div>
    </div>
    <br>
    <br>
    <br>
    <div class="container h-100 pt-5">
        <div class="row justify-content-center mb-5 pb-5">  
            <div class="col-12">  
                <form class="card card-sm">  
                    <div class="card-body row no-gutters align-items-center">  
                        <div class="col-auto">  
                            <i class="fas fa-search h4 text-body"> </i>  
                        </div>  
                             <div class="col">  
                            <input class="form-control form-control-lg form-control-borderless" id="myInput" type="search" onkeyup="SearchFuntion()" placeholder="Search topics or book name  here..."?>  
                        </div>  
                                   <div class="col-auto">  
                            <button class="btn btn-lg btn-dark " onclick="return SearchFuntion()" type="button"> Search </button>  
                        </div>  
                         
                    </div>  
                </form>  
            </div>  
              </div>
  
     <!-- Return model -->
<!-- Modal -->
  <div id="returnModel" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
        <div class="modal-body">
        <br>
          <h4>Return Request has been sent to Admin!!  go and Return Book manually</h4>
          <br>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-lg btn-light px-5" data-bs-dismiss="modal">Ok</button>
        </div>
      </div>
    </div>
  </div>
  
    <!-- Renew model -->
<!-- Modal -->
  <div id="renewModel" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
        <div class="modal-body">
        <br>
          <h4>You have some fine on book. Renew Request has been sent to Admin!!  go and pay the fine then Book will Renewed automatically</h4>
          <br>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-lg btn-light px-5" data-bs-dismiss="modal">Ok</button>
        </div>
      </div>
    </div>
  </div>

<!-- RenewSuccess model -->
<!-- Modal -->
  <div id="renewSuccessModel" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
        <div class="modal-body">
        <br>
          <h4>Book has been Renewed successfully</h4>
          <br>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-lg btn-light px-5" data-bs-dismiss="modal">Ok</button>
        </div>
      </div>
    </div>
  </div>
  
		<div class="row" id="cardbody">
			<c:forEach items="${list}" var="item">
				<div class="col col-4 colbody">
					<div class="card text-white bg-dark m-3 ">
						<div class="card-body">
						<form action="issueBook1">
							<h2 class="card-title"><input type="hidden" name="bookName" value="${item.bookname}">${item.bookname}</input></h2>
							<h4 class="card-subtitle mb-2 text-muted"><input type="hidden" name="author" value="${item.author}">${item.author}</input></h4>
							<p class="card-text">${item.description}</p>
							<br>
							<input type="hidden" name="bookstatus"  value="${item.status}"></input>
							<button class="btn btn-lg btn-light "type="submit" ${item.buttonstatus}>${item.status}</button>
							${item.renew}
							<a data-bs-target="${CallModel}" id="${item.bookalert}" data-bs-toggle="modal" role="button" class="btn"></a>
						</form>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	</div>
	<br> <br> <br>
	<footer class="mt-auto py-3">
		<p class="text-center text-muted">© 2022 Library Management System</p>
	  </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</html>
