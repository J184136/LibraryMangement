<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!Doctype html>
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
.btn{
    font-size: 50px;
    font-style: bold;
    color: black;
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
    </style>

    <title>Library System</title>
  </head>
  <body >
      <div class="body h-100 py-5">
    <div class="container  my-5 py-5" align="center">

        <h1 class="text-md-center my-5 py-5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Welcome&nbsp;&nbsp;&nbsp;to&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Library Management System </h1>
        <br>
        <button class="btn btn-lg btn-link my-5 py-5 text-center" ><a href="home">Click Here</a></button>

    </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</html>