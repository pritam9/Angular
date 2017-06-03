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
            var cardDetails={
                'creditCardNumber' : $scope.cardNumber,
                'cardHolderName' : $scope.holderName,
                'cvv' : $scope.cvv,
                'validFromDate' : $scope.validFrom,
                'expiryDate' : $scope.validTill 
            }
            
            $http({
                method: 'POST',
                url: '/Shopping/SaveCardServlet',
                data: JSON.stringify(cardDetails),
                headers: {'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
                if (data.status === 1) {
                   
                    window.location.href = "/Shopping/customerHome.html";
                   
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