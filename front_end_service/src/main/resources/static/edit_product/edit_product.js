angular.module('market_front').controller('editProductController', function ($scope, $http, $routeParams, $location) {

    const productsPath = 'http://localhost:5000/products/api/v1/';

    $scope.prepareProductForUpdate = function () {
        $http.get(productsPath + 'products/' + $routeParams.productId)
            .then(
                function successCallback(response) {
                    $scope.updated_product = response.data;
                },
                function failCallback(response) {
                    alert(response.data.messages);
                    $location.path('/store');
                });
    }
    $scope.updateProduct = function () {
        $http.put(productsPath + 'products', $scope.updated_product)
            .then(
                function successCallback() {
                    $scope.updated_product = null;
                    alert("Продукт успешно обновлен!");
                    $location.path('/store');
                },
                function failCallback(response) {
                    alert(response.data.messages);
                });
    }

    $scope.prepareProductForUpdate();

});