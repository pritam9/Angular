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

app.controller('createTeamController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {
	
	$scope.isMember=false;
	 
	
	$scope.loadMyTeamDetails = function () {
		var getDetailsUrl = projectName+'/userService/getMyTeamDetails';
		var jwt=$cookies.get('jwt');
		$http({
			method: 'GET',
			url: getDetailsUrl,
			headers: {'Authorization': 'jwt '+jwt}
		}).success(function (data, status, headers, config) {
			if (data.status === 1 || data.status === 0) {
				$scope.isMember = data.data.isMember;
				$scope.teams=data.data.groups;		
				$scope.memberList=data.data.memberList;
				$scope.myGroupId=data.data.myGroupId;
				
				
			} else {
				$scope.alertError("Something went wrong!! Try agin later! <a href='/controller/'>Click here to Login again!</a>");
				window.location.href = projectName+"/";
			}
		}).error(function (data, status, headers, config) {
			$scope.alertError("Something went wrong!! Try agin later! <a href='/'>Click here to Login again!</a>");
		});
	};
	
	
	$scope.createGroup = function(){
		if($scope.myForm.$valid){
			var getDetailsUrl = projectName+'/userService/createTeam';
			var jwt=$cookies.get('jwt');
			var groupName = $scope.groupName;
			var capacity = $scope.capacity;
			$http({
				method: 'POST',
				url: getDetailsUrl,
				data : "groupName="+groupName+"&capacity="+capacity,
				headers: {'Authorization': 'jwt '+jwt,'Content-Type': 'application/x-www-form-urlencoded'}
			}).success(function (data, status, headers, config) {
				if (data.status === 1) {					
					$scope.loadMyTeamDetails();
					$scope.alertSuccess("Group created Succesfully!!");
					
				} else {
					$scope.alertError(data.message);
					//window.location.href = projectName+"/";
				}
			}).error(function (data, status, headers, config) {
				$scope.alertError("Something went wrong!! Try agin later! <a href='/'>Click here to Login again!</a>");
			});
		}else{
			$scope.alertError("All fields are required!!");
		}
	};
	
	$scope.deleteGroup = function(groupId){
		var getDetailsUrl = projectName+'/userService/deleteGroup';
		var jwt=$cookies.get('jwt');
		$http({
			method: 'POST',
			url: getDetailsUrl,
			data:"groupId="+groupId,
			headers: {'Authorization': 'jwt '+jwt,'Content-Type': 'application/x-www-form-urlencoded'}
		}).success(function (data, status, headers, config) {
			if (data.status === 1) {
				$scope.alertSuccess(data.message);
				$scope.loadMyTeamDetails();
			} else {
				$scope.alertError(data.message);
				//window.location.href = projectName+"/";
			}
		}).error(function (data, status, headers, config) {
			$scope.alertError("Something went wrong!! Try agin later! <a href='/'>Click here to Login again!</a>");
		});
	};
	
	

	$scope.addMember = function(memberEmail){
		var getDetailsUrl = projectName+'/userService/addMember';
		var jwt=$cookies.get('jwt');
		$http({
			method: 'POST',
			url: getDetailsUrl,
			data:"groupId="+$scope.myGroupId+"&studentId="+memberEmail,
			headers: {'Authorization': 'jwt '+jwt,'Content-Type': 'application/x-www-form-urlencoded'}
		}).success(function (data, status, headers, config) {
			if (data.status === 1) {
				$scope.alertSuccess(data.message);
				$scope.loadMyTeamDetails();
			} else {
				$scope.alertError(data.message);
				//window.location.href = projectName+"/";
			}
		}).error(function (data, status, headers, config) {
			$scope.alertError("Something went wrong!! Try agin later! <a href='/'>Click here to Login again!</a>");
		});
	};
	
	$scope.removeMember = function(memberEmail){
		var getDetailsUrl = projectName+'/userService/removeMember';
		var jwt=$cookies.get('jwt');
		$http({
			method: 'POST',
			url: getDetailsUrl,
			data:"groupId="+$scope.myGroupId+"&studentId="+memberEmail,
			headers: {'Authorization': 'jwt '+jwt,'Content-Type': 'application/x-www-form-urlencoded'}
		}).success(function (data, status, headers, config) {
			if (data.status === 1) {
				$scope.alertSuccess(data.message);
				$scope.loadMyTeamDetails();
			} else {
				$scope.alertError(data.message);
				//window.location.href = projectName+"/";
			}
		}).error(function (data, status, headers, config) {
			$scope.alertError("Something went wrong!! Try agin later! <a href='/'>Click here to Login again!</a>");
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