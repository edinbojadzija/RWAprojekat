<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="domain.Quiz" %> 
<%@ page import="service.QuizService" %> 
<%@ page import ="java.util.List" %>


<%

   QuizService quiz = new QuizService();
   List<Quiz> lista = quiz.getActiveQuizzes();
  // System.out.println(lista.get(1).getImagePath());
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8"/>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/mycss.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<title>Home page</title>
</head>
<body>
<div class="Main"> 
      <div class="navbar">
           <h1> PROGRAMMERS QUIZ </h1>
           <div class="buttonmenu">
            <button id="rankingsButton" onclick="location.href='/rwaprojekat/login.jsp'"><i class="fa fa-bars"></i>LOGIN</button>
       
        </div>
      </div>         
<%  for(Quiz k : lista) {  %>      
     <div class="row">
     
      <div class="column">
      <img src='<%= k.getImagePath()%>' class="thumbnail"/>
      </div>
     

      <div class="column column-vote">
       <a href="quiz.jsp?quizId=<%=k.getId()%>">  <button class="btnn" id="startQuiz">PLAY QUIZ</button> </a>
      </div>
   </div> 
  <% } %>
 
</body>

<script>
function playQuiz(id) {
    $.ajax({
        type: 'GET',
        url: './quiz.jsp/quizId=' + id
    });
}

</script>
</html>