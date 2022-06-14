angular.module('market_front', []).controller('appController', function ($scope, $http) {

    const contextPath = 'http://localhost:8080/market/';
    let page = 1;
    // $scope.loadProducts = function () {
    //     $http.get(contextPath + 'products')
    //         .then(function (response) {
    //             $scope.products = response.data;
    //         });
    // }
    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url:contextPath + 'products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        })
            .then(function (response) {
                $scope.productsPage = response.data;
            });
    }

    $scope.deleteProduct = function (product) {
        $http.get(contextPath + 'products/delete/' + product.id).
            then(function (response){
                console.log(response)
                $scope.loadProducts()
            });
    }

       $scope.loadProducts();

});