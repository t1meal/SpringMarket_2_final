angular.module('market_front').controller('editProductController', function ($scope, $http, $routeParams, $location) {

    const contextPath = 'http://localhost:5555/core/api/v1/';

    $scope.prepareProductForUpdate = function () {
        $http.get(contextPath + 'products/' + $routeParams.productId)
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
        $http.put(contextPath + 'products', $scope.updated_product)
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