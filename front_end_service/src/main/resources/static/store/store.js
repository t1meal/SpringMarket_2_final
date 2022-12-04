angular.module('market_front').controller('storeController', function ($scope, $http, $location, $localStorage, $rootScope) {

    const productsPath = 'http://localhost:5000/products/api/v1/';
    const cartPath = 'http://localhost:5000/cart/api/v1';

    let currentPage = 1;


    $scope.loadProducts = function (pageIndex) {
        $http({
            url: productsPath + 'products',
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

    $scope.addProductToCart = function (product) {
        if ($rootScope.isUserLoggedIn()) {
            $http.post(cartPath + '/cart', product)
                .then(
                    function successCallback() {
                        alert('ОК');
                    },
                    function failCallback(response) {
                        alert(response.data.messages);
                    });
        } else {
            $scope.tempCartItem = {
                productId:product.id,
                title: product.title,
                price: product.price,
                quantity: 1,
                sum: product.price,
            }
            let arr = $localStorage.guestCart.items;
            if (arr.length > 0){
                for (let i = 0; i < arr.length; i++) {
                    if (arr[i].title === $scope.tempCartItem.title) {
                        arr[i].quantity = arr[i].quantity + 1;
                        arr[i].sum = arr[i].price * arr[i].quantity;
                        $rootScope.recalculateGuestCart();
                        // $scope.tempCartItem = null;
                        return;
                    }
                }
            }
            $localStorage.guestCart.items.push($scope.tempCartItem);
            $rootScope.recalculateGuestCart();
            // $scope.tempCartItem = null;
        }

    }

    $scope.deleteProduct = function (product) {
        $http.delete(productsPath + 'products/' + product.id)
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


    // $scope.checkEmptyCart = function () {
    //     $http.get(productsPath + 'products/cart/empty')
    //
    // }

    $scope.loadProducts();
    // $scope.checkEmptyCart();

});