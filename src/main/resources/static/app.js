angular.module('market_front', []).controller('appController', function ($scope, $http) {

    const contextPath = 'http://localhost:8080/market/api/v1/';

    let currentPage = 1;

    $scope.loadProducts = function (pageIndex) {
        currentPage = pageIndex;
        $http({
            url: contextPath + 'products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        })
            .then(function (response) {
                $scope.productsPage = response.data;
                $scope.paginationArray = $scope.generatePageIndexes (1, $scope.productsPage.totalPages);
            });
    }

    $scope.deleteProduct = function (product) {
        $http.delete(contextPath + 'products/' + product.id)
            .then(function (response) {
                console.log(response)
                $scope.loadProducts()
            });
    }

    $scope.createNewProduct = function () {
        $http.post(contextPath + 'products/', $scope.new_product)
            .then(
                function successCallback(response) {
                    console.log(response);
                    $scope.loadProducts(currentPage);
                    $scope.new_product = null;
                },
                function failCallback(response) {
                    console.log(response);
                    alert("New product cannot be created!");
                });
    }

    $scope.generatePageIndexes = function (startPage, endPage){
        let arr = [];
        for (let i = startPage; i <= endPage; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.nextPage = function (){
        currentPage++;
        if (currentPage > $scope.productsPage.totalPages){
            currentPage = $scope.productsPage.totalPages;
        }
        $scope.loadProducts(currentPage);
    }
    $scope.prevPage = function (){
        currentPage--;
        if (currentPage < 1 ){
            currentPage = 1;
        }
        $scope.loadProducts(currentPage);
    }

    $scope.loadProducts();

});