var app = angular.module('shoppingApplication', ['ngMaterial', 'ngCookies']);
app.controller('loginController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {

        $scope.showMessage = false;
        $scope.login = function (role) {
            
            var loginUrl = '/Shopping/LoginServlet';
            if(role==='employee'){
               
                loginUrl = '/Shopping/EmployeeLoginServlet';
            }
            $http({
                method: 'POST',
                url: loginUrl,
                data: "username=" + $scope.username + "&password=" + $scope.password,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).success(function (data, status, headers, config) {
                if (data.status === 1) {
                    if(role==='employee'){
                        window.location.href = "/Shopping/employeeHome.html";
                        
                        //$cookies.put('customer', JSON.stringify(data.data));
                    }else{
                        window.location.href = "/Shopping/customerHome.html";
                        $cookies.put('customer', JSON.stringify(data.data));
                    }
                    
                    
                } else {
                    $scope.showMessage = true;
                }
            }).error(function (data, status, headers, config) {
            });
        };
    }]);