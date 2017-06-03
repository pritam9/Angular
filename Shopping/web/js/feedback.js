var app = angular.module('shoppingApplication', ['ngMaterial', 'ngCookies']);
var cartItems = [];
app.controller('feedbackController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {

        $scope.showMessage = false;
        $scope.confirm = function () {
            $http({
                method: 'POST',
                url: '/Shopping/SaveFeedbackServlet',
                data: "rating=" + $scope.rating + "&feedback=" + $scope.feedback,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).success(function (data, status, headers, config) {
                if (data.status === 1) {
                    window.location.href = "/Shopping/customerHome.html";
                    
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

app.controller('addressController', ['$scope', '$http', '$cookies', '$rootScope', function ($scope, $http, $cookies, $rootScope) {

        $scope.orderSuccess = false;
        $scope.orderError = false;
        $scope.loadDetails = function () {
            $http({
                method: 'GET',
                url: '/Shopping/ShippingDetailsServlet',
            }).success(function (data, status, headers, config) {
                if (data.status === 1) {

                    $scope.shippingTypes = data.data;
                    //alert($scope.shippingTypes[0].shippingType);

                } else {
                    $scope.productError = data.message;
                    $scope.showProductError = true;
                }


            }).error(function (data, status, headers, config) {
                $scope.message = 'Error while updatating, try again later!!';
                $scope.showMessage = true;
            });

            $http({
                method: 'GET',
                url: '/Shopping/GetShippingAddressServlet'
            }).success(function (data, status, headers, config) {
                if (data.status === 1) {

                    $scope.address = data.data.address;
                    $scope.city = data.data.city;
                    $scope.state = data.data.stateName;
                    $scope.zipCode = data.data.zipCode;

                } else {
                    $scope.productError = data.message;
                    $scope.showProductError = true;
                }


            }).error(function (data, status, headers, config) {
                $scope.message = 'Error while updatating, try again later!!';
                $scope.showMessage = true;
            });

            $http({
                method: 'GET',
                url: '/Shopping/GetCardServlet'
            }).success(function (data, status, headers, config) {
                if (data.status === 1) {

                    $scope.cardNumber = data.data.creditCardNumber;
                    $scope.holderName = data.data.cardHolderName;
                    $scope.cvv = data.data.cvv;
                    $scope.validFrom = data.data.validFromDate;
                    $scope.validTill = data.data.expiryDate;

                } else {
                    $scope.productError = data.message;
                    $scope.showProductError = true;
                }


            }).error(function (data, status, headers, config) {
                $scope.message = 'Error while updatating, try again later!!';
                $scope.showMessage = true;
            });
        };

        $scope.confirm = function () {
            //alert($scope.selectedShipping);
            var orderDetails = {
                'address': $scope.address,
                'city': $scope.city,
                'state': $scope.state,
                'zip': $scope.zipCode,
                'shippingType': $scope.selectedShipping,
                'creditCard': $scope.cardNumber,
                'holderName': $scope.holderName,
                'cvv': $scope.cvv,
                'validFrom': $scope.validFrom,
                'validTill': $scope.validTill
            };

            $http({
                method: 'POST',
                url: '/Shopping/ConfirmOrderServlet',
                data: JSON.stringify(orderDetails),
                headers: {'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
                if (data.status === 1) {
                    $scope.productList = data.data;
                    window.location.href = "/Shopping/feedback.html";
                    $cookies.remove('cart');
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
            //alert(JSON.stringify($scope.myCartItems));
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