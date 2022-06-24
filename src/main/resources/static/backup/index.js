angular.module('market_front', []).controller('appController', function ($scope, $http) {

    const contextPath = 'http://localhost:8080/market/api/v1/';

    let currentPage = 1;

    $scope.loadProducts = function (pageIndex) {
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
                currentPage = pageIndex;
            });
    }

    $scope.deleteProduct = function (product) {
        $http.delete(contextPath + 'products/' + product.id)
            .then(function () {
                $scope.loadProducts(currentPage);
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

    $scope.prepareProductForUpdate = function (productId) {
        $http.get(contextPath + 'products/' + productId)
            .then(
                function successCallback(response) {
                    $scope.updated_product = response.data;
                },
                function failCallback() {
                    alert("Product cannot be find!");
                });
    }
    $scope.updateProduct = function () {
        $http.put(contextPath + 'products', $scope.updated_product)
            .then(
                function successCallback() {
                    $scope.loadProducts(currentPage);
                    $scope.updated_product = null;
                },
                function failCallback() {
                    alert("Product cannot be updated!");
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