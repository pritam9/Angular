var app = angular.module('pupilArenaApplication', ['ngMaterial', 'ngCookies']);
app.controller('loginController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {

	$scope.showMessage = false;
	var projectName="/controller";
	$scope.login = function () {
		alert("Hey");
		var loginUrl = projectName+'/loginService/auth';
		$http({
			method: 'POST',
			url: loginUrl,
			data: "username=" + $scope.username + "&password=" + $scope.password,
			headers: {'Content-Type': 'application/x-www-form-urlencoded'}
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
	};
}]);