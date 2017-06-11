var app = angular.module('pupilArenaApplication', ['ngMaterial', 'ngCookies']);
app.controller('loginController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {

	$scope.showMessage = false;
	$scope.isLeader='Member';
	var projectName="/controller";
	$scope.signUp = function () {
		
		alert($scope.sex+" - "+$scope.isLeader);
		if($scope.myForm.$valid){
		var userDetails = {
		    		firstname: $scope.firstname,
		    		middlename: $scope.middlename,
		    		lastname: $scope.lastname,
		    		email: $scope.username,
		    		password: $scope.password,
		    		school_name:$scope.school_name,
		    		sex:$scope.sex,
		    		role:$scope.isLeader,
		    		gpa:0.0
		    	};
		alert(userDetails.lastname+" - "+userDetails.sex+" - "+ userDetails.role);
		var loginUrl = projectName+'/userService/addUser';
		$http({
			method: 'POST',
			url: loginUrl,
			data: userDetails,
			headers: {'Content-Type': 'application/json'}
		}).success(function (data, status, headers, config) {
			if (data.status === 1) {
				window.location.href = projectName+"/home?token="+data.data;
				$cookies.put('jwt', data.data);
			} else {
				$scope.message=data.message;
				$scope.showMessage = true;
			}
		}).error(function (data, status, headers, config) {
		});
		}else{
			$scope.message="All fields are required!!";
			$scope.showMessage = true;
		}
	};
}]);