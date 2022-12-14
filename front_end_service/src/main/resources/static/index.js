(function () {
    angular
        .module('market_front', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/edit_product/:productId', {
                templateUrl: 'edit_product/edit_product.html',
                controller: 'editProductController'
            })
            .when('/create_product', {
                templateUrl: 'create_product/create_product.html',
                controller: 'createProductController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartProductController'
            })
            .when('/registration', {
                templateUrl: 'registration/registration.html',
                controller: 'registrationController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'ordersController'
            })
            .otherwise({
                redirectTo: '/'
            })
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.webMarketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webMarketUser.token;
        }
    }

})();

angular.module('market_front').controller('indexController', function ($rootScope, $scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:5000/auth/api/v1/';
    const cartPath = 'http://localhost:5000/cart/api/v1/cart';
    const corePath = 'http://localhost:5000/core/api/v1';

    $scope.currentUser = {
        username: "Hi guest!",
        info: null
    }

    $scope.tryToAuth = function () {
        $http.post(contextPath + "auth", $scope.user)
            .then(function successCallback(response) {
                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = "Bearer " + response.data.token;
                        $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};

                        $scope.currentUser.username = "Welcome , " + $scope.user.username + "!";
                        $scope.user.username = null;
                        $scope.user.password = null;
                        $scope.sendGuestCart($localStorage.guestCart);
                    }
                },
                function errorCallback() {
                    alert("Failed login attempt!")
                }
            );
    }
    $scope.sendGuestCart = function (guestCart) {
        if ($localStorage.guestCart.items.length > 0) {
            $http.post(cartPath + "/guestCart", guestCart)
                .then(function successCallback() {
                        alert("Success merged carts!");
                        $localStorage.guestCart.items.length = 0;
                        $localStorage.guestCart.totalPrice = 0;
                        $rootScope.loadCartProducts();

                    },
                    function errorCallback() {
                        alert("Cart merging error! ")
                    })
        }
    }

    $scope.clearUser = function () {
        delete $localStorage.webMarketUser;
        $http.defaults.headers.common.Authorization = '';
    }

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
        $scope.currentUser.username = "Hi guest!";
        $location.path('/');
    }

    $rootScope.isUserLoggedIn = function () {
        return !!$localStorage.webMarketUser;
    }

    $scope.adminPoint = function () {
        $http.get(corePath + "/adminPoint")
            .then(function successCallback(response) {
                    alert(response.data.token);
                },
                function errorCallback() {
                    alert("Access denied! Not enough permissions!")
                })
    }


    if ($localStorage.webMarketUser) {
        try {
            let jwt = $localStorage.webMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.webMarketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }

        $http.defaults.headers.common.Authorization = "Bearer " + $localStorage.webMarketUser.token;
    }
    if (!$localStorage.guestCart) {
        let items = [];
        let totalPrice = 0;
        $localStorage.guestCart = {
            id: null,
            items,
            totalPrice
        }
    }

});