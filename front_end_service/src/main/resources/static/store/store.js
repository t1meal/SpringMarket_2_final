angular.module('market_front').controller('storeController', function ($scope, $http, $location, $localStorage) {

    const corePath = 'http://localhost:5000/core/api/v1/';
    const cartPath = 'http://localhost:5000/cart/api/v1';

    let currentPage = 1;


    $scope.loadProducts = function (pageIndex) {
        $http({
            url: corePath + 'products',
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
        $http.delete(corePath + 'products/' + product.id)
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
    $scope.navToCartPage = function () {
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

    $scope.addProductToCart = function (p) {
        $http.post(cartPath + '/cart', p)
            .then(
                function successCallback() {
                    alert('ОК');
                },
                function failCallback(response) {
                    alert(response.data.messages);
                });
    }
    // $scope.checkEmptyCart = function () {
    //     $http.get(corePath + 'products/cart/empty')
    //
    // }

    $scope.loadProducts();
    // $scope.checkEmptyCart();

});