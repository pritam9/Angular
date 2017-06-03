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
    }]);
app.directive('myDirective', function() {
    return {
        require: 'ngModel',
        link: function(scope, element, attr, mCtrl) {
            function myValidation(value) {
                //alert();
                if (value.toString().length === 16) {
                    
                    mCtrl.$setValidity('lengthC', true);
                } else {
                    mCtrl.$setValidity('lengthC', false);
                }
                return value;
            }
            mCtrl.$parsers.push(myValidation);
        }
    };
});