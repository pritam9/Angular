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
			if (data.status === 1) {
				$scope.quizes=data.data.quizes;
				$scope.groupId = data.data.groupId;
			} else {
				$scope.alertError("Something went wrong!! Try agin later!");
				window.location.href = projectName+"/";
			}
		}).error(function (data, status, headers, config) {
			$scope.alertError("Something went wrong!! Try agin later!");
		});
	};
	
	
	$scope.participate = function(quizId){
		if($scope.groupId>0){
			var getDetailsUrl = projectName+'/quizService/quizRegistration';
			var jwt=$cookies.get('jwt');
			$http({
				method: 'POST',
				url: getDetailsUrl,
				data : "groupId="+$scope.groupId+"&quizId="+quizId,
				headers: {'Authorization': 'jwt '+jwt,'Content-Type': 'application/x-www-form-urlencoded'}
			}).success(function (data, status, headers, config) {
				if (data.status === 1) {
					$scope.alertSuccess(data.message);
				} else {
					$scope.alertError(data.message);
					//window.location.href = projectName+"/";
				}
			}).error(function (data, status, headers, config) {
				$scope.alertError("Something went wrong!! Try agin later!");
			});
		}else{
			$scope.alertError("Its a group exam. You need to be part of a group to register.")
		}
		
	};
	
	$scope.startQuiz=function(quizId){
		var getDetailsUrl = projectName+'/quizService/checkIfEnrolled';
		var jwt=$cookies.get('jwt');
		$http({
			method: 'POST',
			url: getDetailsUrl,
			data : "groupId="+$scope.groupId+"&quizId="+quizId,
			headers: {'Authorization': 'jwt '+jwt,'Content-Type': 'application/x-www-form-urlencoded'}
		}).success(function (data, status, headers, config) {
			if (data.status === 1) {
				
				$cookies.put("quizId",quizId);
				$cookies.put("questionNum",0);
				window.location.href=projectName+"/ongoingQuiz";
			} else {
				$scope.alertError("You need to participate in the quiz first");
				//window.location.href = projectName+"/";
			}
		}).error(function (data, status, headers, config) {
			$scope.alertError("Something went wrong!! Try agin later!");
		});
		
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