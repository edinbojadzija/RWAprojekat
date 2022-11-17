<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="service.QuizService" %>
<%@ page import="service.QuestionService" %>
<%@ page import="service.AnswerService" %>
<%@ page import="service.UserService" %>
<%@ page import="domain.Quiz" %>
<%@ page import="domain.Question" %>
<%@ page import="domain.Answer" %>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/quiz.css" >
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script>
<title>PLAY</title>
</head>


 <% 
 	QuizService quizService = new QuizService();
	QuestionService questionService = new QuestionService();
	AnswerService answerService = new AnswerService();
	UserService userService = new UserService();
	Integer quizId = (Integer) Integer.parseInt(request.getParameter("quizId"));
	Quiz quiz = quizService.getQuizById(quizId);
	List<String> questionList = quiz.getQuestionList();
	List<String> questionListId = quiz.getQuestionListId();
	
	//dodati u service funkcije getQuestionById
	//dodati u bazu questionId u quiz tabelu
	
	
	List<Question> questions = new ArrayList<Question>();
	List<Answer> answers = new ArrayList<Answer>();
	//List<Question> questionsId = new ArrayList<Question>();
	
	for(String q: questionListId){
		//questions.add(questionService.getQuestionByName(q));
		questions.add(questionService.getQuestionById(Integer.parseInt(q)));
	}
	
	for(Question q: questions){
		for(String a: q.getAnswerList()){
			answers.add(answerService.getAnswerByName(a));
		}
	}
	
	Gson gson = new Gson();    
    String quizJson = gson.toJson(quiz);
    System.out.println(quizJson); 
    
    String questionsJson = gson.toJson(questions);
    System.out.println(questionsJson);
    
    String answersJson = gson.toJson(answers);
    System.out.println(answersJson);
 %>

<body onload='init(<%=questionsJson%>,<%=answersJson%>)'>
<div>

        <div class="container">

            <div class="info-panel">
                <h1>SCORE : <span id="player-score"></span></h1>
                <h1>QUESTION : <span id="question-number"></span> / <%=quiz.getQuestionListId().size() %></h1>
           		<h3> <span id="time"></span></h3>
            </div>

            <div class="question-panel">
                <h1 id="display-question"></h1>
            </div>

            <div class="selectOption-panel">
				<div class="modal-container" id="option-modal">
			        <div class="modal-content-container">
                         <h1>PLEASE CHOOSE YOUR ANSWER!</h1>
			             <div class="finish-button">
                            <button onclick="selectAnswer()">BACK</button>
                        </div>
                    </div>
               </div>
                <span id="one">
                    <input type="radio" id="option-one" name="option" class="radio" value="a0" />
                    <label for="option-one" class="option" id="answer-one-label"></label>
                </span>
                <span id="two">
                    <input type="radio" id="option-two" name="option" class="radio" value="a1" />
                    <label for="option-two" class="option" id="answer-two-label"></label>
                </span>
                <span id="three">
                    <input type="radio" id="option-three" name="option" class="radio" value="a2" />
                    <label for="option-three" class="option" id="answer-three-label"></label>
                </span>
                <span id="four">
                    <input type="radio" id="option-four" name="option" class="radio" value="a3" />
                    <label for="option-four" class="option" id="answer-four-label"></label>
                </span>
            </div>
            <div class="next-button">
                <button onclick="handleNextQuestion()">NEXT QUESTION</button>
            </div>
        </div>
        
         <div class="modal-container" id="score-modal">
            <div class="modal-content-container">
                <h1>QUIZ DONE</h1>
	            <div class="results">
                    <p>QUESTIONS : <%=quiz.getQuestionList().size() %></p>
                    <p>WRONG ANSWERS : <span id="wrong-answers"></span></p>
                    <p>SCORE : <span id="right-answers"></span></p>
         <!--            <div class="user-data"> Your name: <input type='text' id='username' value=''></div>
                    <div class="user-data"> Your email: <input type='text' id='email' value=''></div>
          -->       </div>

         <!--         <div class="finish-button">
                    <button onclick="submitScore()">Submit</button>
                </div>  
          -->       <div class="finish-button">
                
  	               <button><a  class="link" href="index.jsp">MAIN MENU</a></button>
				</div>
            </div>
        </div>
</div>

</body>

<script type="text/javascript" >
	let quiz = null;
	let questions = null;
	let answers = null;
	let list = []
	let size = null
	let ansList = []
	let quizName = null
	
function init(questionList,answerList){
	questions = questionList;
	answers = answerList;
	console.log('Ans list:');
	console.log(answerList.length);
	
	size = questionList.length
	quizName = questions[0].quizName
	console.log(questions);
	console.log(answers);
	getNextQuestion(0);
}

let points = 0
let time = 0
let interval = null
let questionNumber = 1 
let playerScore = 0  
let wrongAttempt = 0 
let indexNumber = 0 
function refreshTime(){
	 interval = setInterval(() => {
     time--
     document.getElementById("time").innerHTML = time;
     if(time == 0){
    	      wrongAttempt++  
              indexNumber++
              questionNumber++
        handleNextQuestion()
     }
     }, 1000)	
}

function getNextQuestion(questionIndex){
	let questionName = null;
	ansList = [];
	let ans1 = {};
	let ans2 = {};
	let ans3 = {};
	let ans4 = {};
	document.getElementById('three').hidden = false;
	document.getElementById('four').hidden = false;
	
	questionName = questions[questionIndex].name;
	questionId = questions[questionIndex].id; //dodan je questionId
	points = questions[questionIndex].points
	time = questions[questionIndex].time
	for(let i = 0; i < answers.length ; ++i){
		if(answers[i] != null){
			console.log('Answer question id & question id')
			console.log(answers[i].questionId)
			console.log(answers[i].name)
			console.log(questionId)
			
			if(answers[i].questionId === questionId){
				ansList.push(answers[i]);
			}
		}
	}

	console.log('Answers')
	console.log(ansList[0],ansList[1],ansList[2],ansList[3])
	ans1 = ansList[0];
	ans2 = ansList[1];
	//ans3 = ansList[2];
	//ans4 = ansList[3];
	if(ansList.length == 2){
		ans3.name = '';
		ans4.name = '';
		//staviti polje 3 i 4 da ne mozes kliknut
		document.getElementById('three').hidden = true;
		document.getElementById('four').hidden = true;
		}
	else if(ansList.length == 3){
	//	if(ans3.name == undefined){
	//		ans3.name = '';
	//	}
		ans4.name = '';
		//polje 4 stavit da se ne moze kliknut
		document.getElementById('four').hidden = true;		
	}
	else{
	ans3 = ansList[2];
	ans4 = ansList[3];
	}
	
	console.log(questionName);
	console.log(ans1);
	console.log(ans2);
	console.log(ans3);
	console.log(ans4);
	document.getElementById("question-number").innerHTML = questionNumber
    document.getElementById("player-score").innerHTML = playerScore
    document.getElementById("display-question").innerHTML = questionName;
	document.getElementById("time").innerHTML = time;
    document.getElementById("answer-one-label").innerHTML = "<xmp>" + ans1.name +  "</xmp>" ;
    document.getElementById("answer-two-label").innerHTML =  "<xmp>" +  ans2.name  + "</xmp>" ;
    document.getElementById("answer-three-label").innerHTML =  "<xmp>" +  ans3.name  + "</xmp>";
    document.getElementById("answer-four-label").innerHTML = "<xmp>"  + ans4.name + "</xmp>" ;

    refreshTime()
}

function answerCheck() {
 const currentQuestion = questions[indexNumber]  
    let currentQuestionAnswer = null 
  	console.log('Answer list length');
	console.log(ansList.length);
    for(let i = 0; i < ansList.length; ++i){
    	if(ansList[i].correctStatus == true){
    		currentQuestionAnswer = 'a' + i;
    		console.log('current question answer:')
    		console.log(currentQuestionAnswer);
    	}
    }
    const options = document.getElementsByName("option"); 
    let correctOption = null

    options.forEach((option) => {
        if (option.value === currentQuestionAnswer) {
            correctOption = option.labels[0].id
        }
    })
	if(time != 0){
    if (options[0].checked === false && options[1].checked === false && options[2].checked === false && options[3].checked == false) {
        document.getElementById('option-modal').style.display = "flex"
    }
	}

    options.forEach((option) => {
        if (option.checked === true && option.value === currentQuestionAnswer) {
            document.getElementById(correctOption).style.backgroundColor = "green"
            playerScore += points; 
            indexNumber++ ;
            questionNumber++
           
        }

        else if (option.checked && option.value !== currentQuestionAnswer) {
            const wrongLabelId = option.labels[0].id
            console.log(correctOption);
            console.log(wrongLabelId);
            document.getElementById(wrongLabelId).style.backgroundColor = "red"
            document.getElementById(correctOption).style.backgroundColor = "green"
            wrongAttempt++  
            indexNumber++
            questionNumber++
            
        }
    })
}

function handleNextQuestion() {
	console.log('handle next question');
	clearInterval(interval)
    answerCheck() 
    uncheckButtons()
    setTimeout(()=> {
    if (indexNumber < size) {
         getNextQuestion(indexNumber)
    }
    else {
         handleEndGame()
    }
    resetBackgroundColor()},1000);
}

function resetBackgroundColor() {
    const options = document.getElementsByName("option");
    options.forEach((option) => {
        document.getElementById(option.labels[0].id).style.backgroundColor = ""
    })
}



function uncheckButtons() {
    const options = document.getElementsByName("option");
    for (let i = 0; i < options.length; i++) {
        options[i].checked = false;
    }
}

function handleEndGame() {
  
    document.getElementById('wrong-answers').innerHTML = wrongAttempt
    document.getElementById('right-answers').innerHTML = playerScore
    document.getElementById('score-modal').style.display = "flex"
   
}

function finishQuiz(){
	console.log(playerScore);
	console.log(quizName);
	
	let username =  document.getElementById('username').value
	let email =  document.getElementById('email').value
	console.log(username);
	console.log(email);
	$.ajax({
        type: 'POST',
    	url: 'FinishQuizServlet',
        data: {
        	'username' : username,
        	'email' : email,
            'score': playerScore,
            'quizName' : quizName
        },
        success: function(data) {
        },
		error: function(xhr, status, error){
		         var errorMessage = xhr.status + ': ' + xhr.statusText
		     }
	
    });
}

function submitScore() {
	 finishQuiz()
	}

function selectAnswer() {
    document.getElementById('option-modal').style.display = "none"
   
}


</script>

</html>