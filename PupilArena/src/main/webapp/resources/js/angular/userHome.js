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

app.controller('updateProfileController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {

	$scope.showMessage = false;
	var projectName="/controller";
	$scope.loadUserDetails = function () {
		var getDetailsUrl = projectName+'/userService/getUserDetails';
		var jwt=$cookies.get('jwt');
		$http({
			method: 'GET',
			url: getDetailsUrl,
			headers: {'Authorization': 'jwt '+jwt}
		}).success(function (data, status, headers, config) {
			if (data.status === 1) {
				
				$scope.firstname=data.data.firstname;
				$scope.middlename=data.data.middlename;
				$scope.lastname=data.data.lastname;
				$scope.email=data.data.email;
				$scope.school_name=data.data.school_name;
				$scope.sex = data.data.sex;
				$scope.role = data.data.role;
				$scope.gpa = data.data.gpa;		
			} else {
				
				$scope.message=data.message;
				$scope.showMessage = true;
				window.location.href = projectName+"/";
			}
		}).error(function (data, status, headers, config) {
			
		});
	};
	
	$scope.updateProfile = function(){
		if($scope.myForm.$valid){
			var userDetails = {
		    		firstname: $scope.firstname,
		    		middlename: $scope.middlename,
		    		lastname: $scope.lastname,
		    		email: $scope.email,
		    		school_name:$scope.school_name,
		    		sex:$scope.sex,
		    		role:$scope.role,
		    		gpa:$scope.gpa
		    	};
			alert($scope.lastname+" - "+userDetails.role);
			var getDetailsUrl = projectName+'/userService/updateUserDetails';
			$http({
				method: 'POST',
				url: getDetailsUrl,
				data: userDetails,
				headers: {'Authorization': 'jwt '+$cookies.get('jwt')}
			}).success(function (data, status, headers, config) {
				if (data.status === 1) {
					$scope.alertSuccess(data.message);
				} else {
					$scope.alertError(data.message);
				}
			}).error(function (data, status, headers, config) {
			});
			}else{
				$scope.alertError("Oops!! Something went wrong!!");
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
		
		$scope.changeRole=function(){
			alert($scope.role);
			if($scope.role==='Leader'){
				$scope.role='Member';
			}else{
				$scope.role='Leader';
			}
		};
		
}]);