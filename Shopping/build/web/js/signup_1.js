var app = angular.module('shoppingApplication', ['ngMaterial', 'ngCookies']);
app.controller('signupController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {

        $scope.showMessage = false;
        $scope.signup = function (role) {
            $http({
                method: 'POST',
                url: '/Shopping/SignUpServlet',
                data: "username=" + $scope.username + "&password=" + $scope.password+"&firstName="+$scope.firstName+"&lastName="+$scope.lastName+"&phone="+$scope.phoneNumber+"&creditCard="+$scope.creditCard,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).success(function (data, status, headers, config) {
                if (data.status === 1) {
                    $cookies.put('customer', JSON.stringify(data.data));
                    window.location.href = "/Shopping/signup_1.html";
                } else {
                    $scope.message=data.message;
                    $scope.showMessage = true;
                }
            }).error(function (data, status, headers, config) {
            });
        };
        
        $scope.confirm = function () {
            //alert($scope.selectedShipping);
            var shippingAddress={
                'address' : $scope.address,
                'city' : $scope.city,
                'stateName' : $scope.state,
                'zipCode' : $scope.zipCode
            }
            
            $http({
                method: 'POST',
                url: '/Shopping/SaveAddressServlet',
                data: JSON.stringify(shippingAddress),
                headers: {'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
                if (data.status === 1) {
                    
                    window.location.href = "/Shopping/signup_2.html";
                    
                } else {
                    $scope.orderError = data.message;
                    $scope.showOrderError = true;
                }
            }).error(function (data, status, headers, config) {
                $scope.message = 'Error while updatating, try again later!!';
                $scope.showMessage = true;
            });
            
        };
    }]);