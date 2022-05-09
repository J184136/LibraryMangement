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
   .modal-header{
     background-color: black;
     color:white;
   }
   .modal-footer{
    background-color: black;
     color:white;
   }
    .modal-body{
    font-weight: bold;
   }
   footer{
    background-color: Black;
   }
    </style>
     <script type="text/javascript">
      function Validate() {
          var password = document.getElementById("password").value;
          var confirmPassword = document.getElementById("cpassword").value;
          if (password != confirmPassword) {
            document.getElementById("visi").style.visibility = "visible"
              return false;
          }
          return true;
      }
      
      function Validate1() {
          var password = document.getElementById("passwordf").value;
          var confirmPassword = document.getElementById("cpasswordf").value;
          if (password != confirmPassword) {
            document.getElementById("visi1").style.visibility = "visible"
              return false;
          }
          return true;
      }
      function SearchFuntion() {
   	   let filter = document.getElementById('myInput').value.toUpperCase();
   	   let rowContain = document.getElementById('cardbody');
   	   let cardContain = rowContain.getElementsByClassName('card');
   	   for(var i=0;i<cardContain.length;i++){
   		   let val=cardContain[i].querySelector(".card-title");
   		   if(val.innerText.toUpperCase().indexOf(filter) > -1){
   			cardContain[i].style.display='';
   		   }
   		   else{
   			cardContain[i].style.display='none';
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
                <a class="nav-link active" aria-current="page"  href="home"><h2 class="mx-5">Home</h2></a>
              </li>
            </ul>
            <form class="d-flex">
              <button type="button" id="${CallModel}" class="btn btn-light btn-lg mx-3 px-5" data-bs-toggle="modal" data-bs-target="#loginModal">Log In</button>
              <button type="button" class="btn btn-light btn-lg mx-3 " data-bs-toggle="modal" data-bs-target="#signupModal">Create Account</button>              
            </form>
          </div>
        </div>
      </nav>

      <!-- Login model -->
<!-- Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="loginModalLabel">Login</h3>
        <button type="button" class="btn-close btn-close-white " data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="index2" method="post">
          <div class="mb-3">
            <label for="StudentLogin" class="form-label">User Id</label>
            <input type="text" name="userid" class="form-control" id="StudentLogin" aria-describedby="StudentLoginHelp" required>
            <h5 class="text-danger">${Display2}</h5>
          </div>
          <div class="mb-3">
            <label for="Pass" class="form-label">Password</label>
            <input type="password" name="pass" class="form-control" id="Pass" required>
            <h5 class="text-danger">${Display1}</h5>
          </div>
          <br>
          <div class="row">
            <div class="col-12 col-md-8">
          <button type="submit" class="btn btn-primary px-5">Log in</button>
        </div>
        <div class="col-6 col-md-4 pt-3">
          <a href="#" type="button"  data-bs-target="#pwdModal" data-bs-toggle="modal">ForgotPassword</a>
        </div>
        </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- Sign Up model  -->
<!-- Modal -->
<div class="modal fade" id="signupModal" tabindex="-1" aria-labelledby="signupModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h3 class="modal-title" id="signupModalLabel">Create Account</h3>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form  action="index" method="post">        
            <div class="mb-3">
              <label for="FirstName" class="form-label">First Name</label>
              <input type="text"  name="firstname" class="form-control" id="FirstName" aria-describedby="FirstNameHelp" required>
            </div>
            <div class="mb-3">
              <label for="LastName" class="form-label">Last Name</label>
              <input type="text" name="lastname" class="form-control" id="LastName" aria-describedby="emailHelp" required>
            </div>
            <div class="mb-3">
              <label for="CourseName" class="form-label">Course Name</label>
              <input type="text" name="coursename" class="form-control" id="CourseName" aria-describedby="emailHelp" required>
            </div>
            <div class="mb-3">
              <label for="StudentId" class="form-label">Student Id</label>
              <input type="number" name="studentid" value="${StudentId}" class="form-control" id="StudentId" aria-describedby="emailHelp" readonly>
            </div>
            <div class="mb-3">
              <label for="Password" class="form-label">Password</label>
              <input type="password" id="password" name="password" class="form-control" id="Password" minlength="6" required>
            </div>
            <div class="mb-3">
              <label for="CPassword" class="form-label">Confirm Password</label>
              <input type="password" id="cpassword" name="cpassword" class="form-control" id="CPassword" required>
              <h5 class="text-danger" id ="visi" style="visibility: hidden">Password and Confirm Password should be same</h5>
              <br>
            </div>
            <button type="submit" onclick="return  Validate()" class="btn btn-primary">Create Account</button>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
  
 <!--- Forget Password model-->
 <!--modal-->
<div id="pwdModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="signupModalLabel">Forgot Password</h3>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form  action="index3" method="post">
          <h5 class="text-danger"  id ="visi1" style="visibility: hidden">New Password and Confirm Password should be same</h5>
          <div class="mb-3">
              <label for="StudentId" class="form-label">Student Id</label>
              <input type="text" name="studentidf" class="form-control" id="StudentIdF" aria-describedby="StudentIdHelp" required>
            </div>
          <div class="mb-3">
            <label for="Passwordf" class="form-label">New Password</label>
            <input type="password" id="passwordf" name="passwordf" class="form-control" id="Passwordf" minlength="6" required>
          </div>
          <div class="mb-3">
            <label for="CPasswordf" class="form-label">Confirm Password</label>
            <input type="password" id="cpasswordf" name="cpasswordf" class="form-control" id="CPasswordf" required>
          </div>
          <button type="submit"  onclick="return Validate1()" class="btn btn-primary">Reset Password</button>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
 


 <!---Can't issue  model-->
 <!--modal-->
 <div id="issueModel" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
        <div class="modal-body">
        <br>
          <h4>You Can't Issue a Book till You didn't Login!!</h4>
          <br>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-lg btn-light px-5" data-bs-dismiss="modal">Ok</button>
        </div>
      </div>
    </div>
  </div>

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
    <div class="container h-100  pt-5">
        <div class="row justify-content-center mb-5 pb-5">  
            <div class="col-12 ">  
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

		<div class="row" id="cardbody">
			<c:forEach items="${list}" var="item">
				<div class="col col-4">
					<div class="card text-white bg-dark m-3 ">
						<div class="card-body">
							<h2 class="card-title">${item.bookname}</h2>
							<h4 class="card-subtitle mb-2 text-muted">${item.author}</h4>
							<p class="card-text">${item.description}</p>
							<br>
							<button class="btn btn-lg btn-light " type="button"
								data-bs-toggle="modal" data-bs-target="#issueModel">Issue Book</button>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	</div>
	<br> <br> <br>
	<footer class="mt-auto py-3">
		<p class="text-center text-muted">� 2022 Library Management System</p>
	  </footer>
	
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</html>