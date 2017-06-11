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

	$scope.loadQuizDetails = function () {
		var getDetailsUrl = projectName+'/userService/getQuizDetails';
		var jwt=$cookies.get('jwt');
		$http({
			method: 'GET',
			url: getDetailsUrl,
			headers: {'Authorization': 'jwt '+jwt}
		}).success(function (data, status, headers, config) {
			if (data.status === 1 && data.data.role==='Admin') {
				$scope.quizes=data.data.quizes;
				$scope.groupId = data.data.groupId;
			} else {
				$scope.alertError("Something went wrong!! Try agin later!");
				window.location.href = projectName+"/error";
			}
		}).error(function (data, status, headers, config) {
			$scope.alertError("Something went wrong!! Try agin later!");
		});
	};
	
	
	$scope.lock = function(quizId){
		
			var getDetailsUrl = projectName+'/quizService/lockQuiz';
			var jwt=$cookies.get('jwt');
			$http({
				method: 'POST',
				url: getDetailsUrl,
				data : "lock="+true+"&quizId="+quizId,
				headers: {'Authorization': 'jwt '+jwt,'Content-Type': 'application/x-www-form-urlencoded'}
			}).success(function (data, status, headers, config) {
				if (data.status === 1) {
					$scope.alertSuccess(data.message);
					$scope.loadQuizDetails();
				} else {
					$scope.alertError(data.message);
					//window.location.href = projectName+"/";
				}
			}).error(function (data, status, headers, config) {
				$scope.alertError("Something went wrong!! Try agin later!");
			});
		
		
	};
	
	$scope.unlock = function(quizId){
		var getDetailsUrl = projectName+'/quizService/lockQuiz';
		var jwt=$cookies.get('jwt');
		$http({
			method: 'POST',
			url: getDetailsUrl,
			data : "lock="+false+"&quizId="+quizId,
			headers: {'Authorization': 'jwt '+jwt,'Content-Type': 'application/x-www-form-urlencoded'}
		}).success(function (data, status, headers, config) {
			if (data.status === 1) {
				$scope.alertSuccess(data.message);
				$scope.loadQuizDetails();
			} else {
				$scope.alertError(data.message);
				//window.location.href = projectName+"/";
			}
		}).error(function (data, status, headers, config) {
			$scope.alertError("Something went wrong!! Try agin later!");
		});
		
	};
	
	$scope.scorecard = function(quizId){
		$cookies.put("scoreForQuiz",quizId);
		var token = $cookies.get("jwt");
		window.location.href=projectName+"/scorecard?token="+token;
	}
	
	
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