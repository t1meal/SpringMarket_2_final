angular.module('market_front').controller('storeController', function ($scope, $http, $location) {

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
                $scope.paginationArray = $scope.generatePageIndexes(1, $scope.productsPage.totalPages);
                currentPage = pageIndex;
            });
    }

    $scope.deleteProduct = function (product) {
        $http.delete(contextPath + 'products/' + product.id)
            .then(function () {
                $scope.loadProducts(currentPage);
            });
    }

    $scope.generatePageIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i <= endPage; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.navToEditProductPage = function (productId) {
        $location.path('edit_product/' + productId);
    }
    $scope.navToCartPage = function (productId) {
        $location.path('cart/');
    }

    $scope.nextPage = function () {
        currentPage++;
        if (currentPage > $scope.productsPage.totalPages) {
            currentPage = $scope.productsPage.totalPages;
        }
        $scope.loadProducts(currentPage);
    }
    $scope.prevPage = function () {
        currentPage--;
        if (currentPage < 1) {
            currentPage = 1;
        }
        $scope.loadProducts(currentPage);
    }

    $scope.addProductToCart = function (product) {
        $http.post(contextPath + 'products/cart', product)
            .then(
                function successCallback() {
                    alert('ОК');
                },
                function failCallback(response) {
                    alert(response.data.messages);
                });
    }

    $scope.loadProducts();

});