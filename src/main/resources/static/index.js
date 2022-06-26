(function (){
    angular
        .module ('market_front', ['ngRoute'])
        .config (config)
        .run (run);

    function config ($routeProvider){
        $routeProvider
            .when('/',{
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/edit_product/:productId',   {
                templateUrl: 'edit_product/edit_product.html',
                controller: 'editProductController'
            })
            .when('/create_product',   {
                templateUrl: 'create_product/create_product.html',
                controller: 'createProductController'
            })
            .when('/cart',   {
                templateUrl: 'cart/cart.html',
                controller: 'cartProductController'
            })
            .otherwise ({
                redirectTo: '/'
            })
    }

    function run ($rootScope, $http){

    }

})();

angular.module('market_front').controller('indexController', function ($rootScope, $scope, $http) {
    const contextPath = 'http://localhost:8080/market/api/v1/';
});