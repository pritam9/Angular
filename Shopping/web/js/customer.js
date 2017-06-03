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

        $scope.addToCart = function (product) {
            if (product.quantityInStock > 0) {
                $rootScope.$emit('updateCart', product);
                $scope.orderSuccessMsg = 'Product ' + product.productName + ' added to cart!!';
                $scope.orderSuccess = true;
                $scope.orderError = false;
            } else {
                $scope.orderErrorMsg = 'Product ' + product.productName + ' out of stock!!';
                $scope.orderError = true;
                $scope.orderSuccess = false;
            }
        };
        $rootScope.$on('deleteProd', function (event, productId, quantity) {
            angular.forEach($scope.productList, function (prod, key) {
                if (prod.productID == productId) {
                    prod.quantityInStock = prod.quantityInStock + quantity;
                }
            });


        });
    }]);

app.controller('cartController', ['$scope', '$http', '$cookies', '$rootScope', function ($scope, $http, $cookies, $rootScope) {

        $scope.showMessage = false;
        try {
            $scope.myCartItems = JSON.parse($cookies.get('cart'));
            $scope.myCartItemIds = JSON.parse($cookies.get('cartProductIds'));
        } catch (error) {
            $scope.myCartItems = cartItems;
            $scope.myCartItemIds = [];
        }
        $scope.size = $scope.myCartItems.length;
        $rootScope.$on('updateCart', function (event, product) {
            if ($scope.myCartItemIds.includes(product.productID)) {

                var index = $scope.myCartItemIds.indexOf(product.productID);
                if (product.quantityInStock > 0) {
                    $scope.myCartItems[index].quantityInStock = $scope.myCartItems[index].quantityInStock + 1;
                    $scope.myCartItems[index].productPrice = $scope.myCartItems[index].productPrice + product.productPrice;
                    product.quantityInStock = product.quantityInStock - 1;
                }
            } else {
                var cartProduct = {
                    'productID': product.productID,
                    'productName': product.productName,
                    'productPrice': product.productPrice,
                    'quantityInStock': 1

                };
                $scope.myCartItems.push(cartProduct);
                $scope.myCartItemIds.push(product.productID);
                product.quantityInStock = product.quantityInStock - 1;
            }

            $scope.size = $scope.myCartItems.length;
            $cookies.put('cart', JSON.stringify($scope.myCartItems));
            $cookies.put('cartProductIds', JSON.stringify($scope.myCartItemIds));
        });
        $scope.deleteProduct = function (productId) {
            var index = $scope.myCartItemIds.indexOf(productId);
            var quantity = $scope.myCartItems[index].quantityInStock;
            $scope.myCartItems.splice(index, 1);
            $scope.myCartItemIds.splice(index, 1);
            $rootScope.$emit('deleteProd', productId, quantity);
            $cookies.put('cart', JSON.stringify($scope.myCartItems));
            $cookies.put('cartProductIds', JSON.stringify($scope.myCartItemIds));
            $scope.size = $scope.myCartItems.length;
        };
        $scope.placeOrder = function () {
            alert(JSON.stringify($scope.myCartItems));
            $http({
                method: 'POST',
                url: '/Shopping/PlaceOrderServlet',
                data: JSON.stringify($scope.myCartItems),
                headers: {'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
                if (data.status === 1) {
                    $scope.productList = data.data;
                    window.location.href = "/Shopping/confirmOrder.html";

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
app.controller('logOutController',['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {
        $scope.logout=function(){
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