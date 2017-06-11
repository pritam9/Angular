var app = angular.module('pupilArenaApplication', ['ngMaterial', 'ngCookies']);
var projectName="/controller";
app.controller('sidebarController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {

	$scope.showMessage = false;
	$scope.isLeader=false;
	$scope.isMember=false;
	$scope.loadDetails = function () {
		//alert("load details");
		var getDetailsUrl = projectName+'/userService/getUserDetails';
		var jwt=$cookies.get('jwt');
		$http({
			method: 'GET',
			url: getDetailsUrl,
			headers: {'Authorization': 'jwt '+jwt}
		}).success(function (data, status, headers, config) {
			if (data.status === 1) {
				
				$scope.fullName=data.data.firstname+" "+data.data.middlename+" "+data.data.lastname;
				if(data.data.role==="Leader"){
					$scope.isLeader=true;
				}else{
					$scope.isMember=true;
				}				
			} else {
				
				$scope.message=data.message;
				$scope.showMessage = true;
				window.location.href = projectName+"/";
			}
		}).error(function (data, status, headers, config) {
			
		});
		
	};
	
	$scope.logout=function(){
		$cookies.remove('jwt');
		window.location.href = projectName+"/";
	};
}]);

app.controller('quizController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {
	
	$scope.questionNumber=1;
	
	$scope.loadQuizQuestion = function(){
		var quizId = $cookies.get("quizId");
		var questionId = Number($cookies.get("questionNum"));
		alert(questionId+1);
		alert("Question Number = "+questionId);
		if(questionId===0){
			$scope.loadAllQuestions(quizId);
		}else{
			$scope.loadNextQuestion();
		}
	}
	
	$scope.loadAllQuestions = function (quizId) {
		var getDetailsUrl = projectName+'/quizService/getQuizQuestions/'+quizId;
		var jwt=$cookies.get('jwt');
		$http({
			method: 'GET',
			url: getDetailsUrl,
			headers: {'Authorization': 'jwt '+jwt}
		}).success(function (data, status, headers, config) {
			if (data.status === 1 || data.status === 0) {
				$scope.currentQuestion= data.data[0];
				$cookies.put("questions",JSON.stringify(data.data));
				//$cookies.put("questionNum",1);
				$scope.questionNumber = 1;
				$scope.isLastQuestion = (questions.length===$scope.questionNumber);
				$scope.alertSuccess("Quiz has started!! All the Best!");
				
			} else {
				$scope.alertError("Something went wrong!! Try agin later! <a href='/controller/'>Click here to Login again!</a>");
				window.location.href = projectName+"/";
			}
		}).error(function (data, status, headers, config) {
			$scope.alertError("Something went wrong!! Try agin later! <a href='/'>Click here to Login again!</a>");
		});
	};
	
	$scope.loadNextQuestion=function(){
		var questions = JSON.parse($cookies.get("questions"));
		var questionNumber = Number($cookies.get("questionNum"));
		$scope.questionNumber = questionNumber+1;
		
		$scope.currentQuestion = questions[questionNumber];
		$scope.isLastQuestion = (questions.length===$scope.questionNumber);
		//$cookies.put("questionNum",$scope.questionNumber);  // add on answer submission
	};
	
	$scope.submitAnswer = function(questionId){
		if($scope.answer===undefined){
			$scope.alertError("Please select an option before submitting.");
		}else{
			var postAnswerUrl = projectName+'/quizService/submitAnswer';
			var quizId = $cookies.get("quizId");
			var jwt=$cookies.get('jwt');
			$http({
				method: 'POST',
				url: postAnswerUrl,
				data:"quizId="+quizId+"&questionId="+questionId+"&answer="+$scope.answer,
				headers: {'Authorization': 'jwt '+jwt,'Content-Type': 'application/x-www-form-urlencoded'}
			}).success(function (data, status, headers, config) {
				if (data.status === 1) {
					$scope.alertSuccess(data.message);
					var questions = JSON.parse($cookies.get("questions"));
					var questionNumber = Number($cookies.get("questionNum"));
					$scope.questionNumber = questionNumber+1;					
					$scope.currentQuestion = questions[questionNumber];
					$cookies.put("questionNum",$scope.questionNumber);
					$scope.loadNextQuestion();
					$scope.answer=undefined;
					$scope.isLastQuestion = (questions.length===$scope.questionNumber);
					
				} else {
					$scope.alertError(data.message);
					//window.location.href = projectName+"/";
				}
			}).error(function (data, status, headers, config) {
				$scope.alertError("Something went wrong!! Try agin later! <a href='/'>Click here to Login again!</a>");
			});
		}
	};
	
	$scope.submitLastAnswer = function(questionId){
		if($scope.answer===undefined){
			$scope.alertError("Please select an option before submitting.");
		}else{
			var postAnswerUrl = projectName+'/quizService/submitLastAnswer';
			var quizId = $cookies.get("quizId");
			var jwt=$cookies.get('jwt');
			$http({
				method: 'POST',
				url: postAnswerUrl,
				data:"quizId="+quizId+"&questionId="+questionId+"&answer="+$scope.answer,
				headers: {'Authorization': 'jwt '+jwt,'Content-Type': 'application/x-www-form-urlencoded'}
			}).success(function (data, status, headers, config) {
				if (data.status === 1) {
					$scope.alertSuccess(data.message);
					alert(data.message);
					$cookies.remove("questionNum");
					$cookies.remove("questions");
					var jwt1=String($cookies.get('jwt'));
					var myUrl = projectName+"/home?token="+jwt1;
					alert(jwt1+" is ");
					window.location.href=myUrl;
					
				} else {
					$scope.alertError(data.message);
					//window.location.href = projectName+"/";
				}
			}).error(function (data, status, headers, config) {
				$scope.alertError("Something went wrong!! Try agin later! <a href='/'>Click here to Login again!</a>");
			});
		}
	};
	
	
	$scope.alertSuccess = function(message){
			$.notify({
	        	icon: "pe-7s-bell",
	        	message: "<b>"+message+"</b>"

	        },{
	            type: 'success',
	            timer: 4000,
	            placement: {
	                from: 'top',
	                align: 'center'
	            }
	        });
		};
		
		$scope.alertError = function(message){
			$.notify({
	        	icon: "pe-7s-bell",
	        	message: "<b>"+message+"</b>"

	        },{
	            type: 'danger',
	            timer: 4000,
	            placement: {
	                from: 'top',
	                align: 'center'
	            }
	        });
		};
}]);