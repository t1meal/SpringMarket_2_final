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
            .otherwise({
                redirectTo: '/'
            })
    }

    function run($rootScope, $http, $localStorage) {
        if($localStorage.webMarketUser){
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webMarketUser.token;
        }
    }

})();

angular.module('market_front').controller('indexController', function ($rootScope, $scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:8080/market/api/v1/';

    $scope.tryToAuth = function () {
        $http.post(contextPath + "auth", $scope.user)
            .then(function successCallback(response) {
                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = "Bearer " + response.data.token;
                        $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};

                        $scope.user.username = null;
                        $scope.user.password = null;
                    }
                },
                function errorCallback(response) {
                }
            );
    }

    $scope.clearUser = function () {
        delete $localStorage.webMarketUser;
        $http.defaults.headers.common.Authorization = '';
    }
    $scope.tryToLogout = function (){
        $scope.clearUser();
        if($scope.user.username){
            $scope.user.username = null;
        }
        if($scope.user.password){
            $scope.user.password = null;
        }
        $location.path('/');
    }

    $rootScope.isUserLoggedIn = function () {
        return !!$localStorage.webMarketUser;
    }

});