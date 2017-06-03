var app = angular.module('shoppingApplication', ['ngMaterial', 'ngCookies']);
var cartItems = [];
app.controller('profileController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {

        $scope.showMessage = false;
        $scope.updateProfile = function () {
            $http({
                method: 'POST',
                url: '/Shopping/UpdateProfileServlet',
                data: "username=" + $scope.username + "&firstName=" + $scope.firstName + "&lastName=" + $scope.lastName + "&phone=" + $scope.phoneNumber + "&creditCard=" + $scope.creditCard,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).success(function (data, status, headers, config) {
                if (data.status === 1) {
                    $cookies.put('customer', JSON.stringify(data.data));
                    $scope.success = data.message;
                    $scope.showSuccess = true;
                } else {
                    $scope.error = data.message;
                    $scope.showError = true;
                }


            }).error(function (data, status, headers, config) {
                $scope.message = 'Error while updatating, try again later!!';
                $scope.showMessage = true;
            });
        };
        $scope.loadProfile = function () {
            var object = $cookies.get('customer');
            var customer = JSON.parse(object);
            $scope.username = customer.email;
            $scope.password = customer.email;
            $scope.firstName = customer.firstName;
            $scope.lastName = customer.lastName;
            $scope.phoneNumber = customer.phoneNumber;
            $scope.creditCard = customer.creditcard_number;
        };
    }]);

app.controller('productController', ['$scope', '$http', '$cookies', '$rootScope', function ($scope, $http, $cookies, $rootScope) {

        $scope.orderSuccess = false;
        $scope.orderError = false;
        $scope.loadProducts = function () {
            $http({
                method: 'GET',
                url: '/Shopping/ProductServlet',
            }).success(function (data, status, headers, config) {
                if (data.status === 1) {
                    $scope.productList = data.data;

                } else {
                    $scope.productError = data.message;
                    $scope.showProductError = true;
                }


            }).error(function (data, status, headers, config) {
                $scope.message = 'Error while updatating, try again later!!';
                $scope.showMessage = true;
            });
        };

        $scope.addProduct = function () {
            var product = {
                'productID': $scope.productId,
                'productName': $scope.productName,
                'description': $scope.description,
                'productPrice': $scope.price,
                'quantityInStock': $scope.quantity
            }

            $http({
                method: 'POST',
                url: '/Shopping/AddProductServlet',
                data: JSON.stringify(product),
                headers: {'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
                if (data.status === 1) {
                    $scope.loadProducts();
                    $scope.orderSuccessMsg = data.message;
                    $scope.orderSuccess = true;
                } else {
                    $scope.orderErrorMsg = data.message;
                    $scope.showOrderError = true;
                }
            }).error(function (data, status, headers, config) {
                $scope.message = 'Error while updatating, try again later!!';
                $scope.showMessage = true;
            });
        }

        $scope.addToCart = function (product) {
            $scope.productId = product.productID;
            $scope.productName = product.productName;
            $scope.description = product.description;
            $scope.price = product.productPrice;
            $scope.quantity = product.quantityInStock;
        };
        
        $scope.deleteProduct=function (productId){
            //alert(productId);
            $http({
                method: 'POST',
                url: '/Shopping/DeleteProductServlet',
                data:'productId='+productId,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                 
            }).success(function (data, status, headers, config) {
                if (data.status === 1) {
                    //alert(data.data);
                    $scope.loadProducts();
                    $scope.orderSuccessMsg = data.message;
                    $scope.orderSuccess = true;
                } else {
                    $scope.orderErrorMsg = data.message;
                    $scope.showOrderError = true;
                }
            }).error(function (data, status, headers, config) {
                $scope.message = 'Error while updatating, try again later!!';
                $scope.showMessage = true;
            });
        };

    }]);

app.controller('empDetailsController', ['$scope', '$http', '$cookies', '$rootScope', function ($scope, $http, $cookies, $rootScope) {

        
        $scope.getEmpDetails = function () {
           
            $http({
                method: 'GET',
                url: '/Shopping/GetEmployeeServlet'
            }).success(function (data, status, headers, config) {
                if (data.status === 1) {
                    $scope.employee = data.data;
                    
                } else {
                    window.location.href = "/Shopping/index.html";
                }
            }).error(function (data, status, headers, config) {
                $scope.message = 'Error while updatating, try again later!!';
                $scope.showMessage = true;
            });
        };



    }]);
app.controller('logOutController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {
        $scope.logout = function () {
            //alert('logout');
            $http({
                method: 'GET',
                url: '/Shopping/LogOutServlet',
            }).success(function (data, status, headers, config) {
                if (data.status === 1) {
                    window.location.href = "/Shopping/index.html";

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