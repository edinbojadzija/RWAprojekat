<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0">
<title>Login</title>
</head>
<body>

<div class="login" align="center">
<h1 class="title">WELCOME TO PROGRAMMERS QUIZ</h1>
<h1 class="title">PLEASE LOGIN</h1>

<form action="/rwaprojekat/LoginServlet" method="post"  class="ui large form-margin">
<div>
   <div class="field vote-btn">
   <div class="ui left icon input">
   <i class="user icon"></i><input type="text" name="username" placeholder="Type your username">
   
   </div> 
   </div>
  
  <div class="field vote-btn">
  <div class="ui left icon input">
  <i class="lock icon"></i><input type="password" name="password" placeholder="Type your password">
  </div>
  </div> 
   
   <button class="btn vote-btn" type="submit">LOGIN</button>
   <button class="btn" type="submit"><a href='/rwaprojekat/'>BACK</a></button>
   
</div>
</form>


 <h1 id="error">${error}</h1>
 </div> 


</body>
</html>