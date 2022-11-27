angular.module('market_front').controller('createProductController', function ($scope, $http, $routeParams, $location) {

    const productsPath = 'http://localhost:5000/products/api/v1/';

    $scope.createNewProduct = function () {
        if ($scope.new_product == null){
            alert("Form not completed!");
            return;
        }
        $http.post(productsPath + 'products/', $scope.new_product)
            .then(
                function successCallback(response) {
                    console.log(response);
                    $scope.new_product = null;
                    alert("New product created!");
                    $location.path('/store');
                },
                function failCallback(response) {
                    alert(response.data.messages);
                });
    }

});