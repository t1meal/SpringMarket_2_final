angular.module('market_front').controller('cartProductController', function ($scope, $http) {

    const cartPath = 'http://localhost:5000/cart/api/v1/cart';

    const corePath = 'http://localhost:5000/core/api/v1';

    $scope.injectCart = function (r) {
        $scope.cartItems = r.data.items;
        $scope.sumOfOrder = r.data.totalPrice;
    }

    $scope.loadCartProducts = function () {
        $http.get(cartPath)
            .then(function (response) {
                $scope.injectCart(response);
            });
    }

    $scope.deleteProductFromCart = function (item) {
        $http.delete(cartPath + '/item/' + item.productId)
            .then(function successCallback(response) {
                    $scope.loadCartProducts();
                    alert("Продукт удален!")
                },
                function failCallback(response) {
                    alert(response.data.messages);
                });
    }

    $scope.incCountOfProduct = function (item) {
        $http.put(cartPath + '/item/inc/' + item.productId)
            .then(function successCallback(response) {
                    $scope.injectCart(response);
                },
                function failCallback(response) {
                    alert(response.data.messages);
                });
    }
    $scope.decCountOfProduct = function (item) {
        $http.put(cartPath + '/item/dec/' + item.productId)
            .then(function successCallback(response) {
                    $scope.injectCart(response);
                },
                function failCallback(response) {
                    alert(response.data.messages);
                });
    }

    $scope.removeAllProductsInCart = function () {
        $http.delete(cartPath)
            .then(function successCallback() {
                    alert("Корзина очищена!");
                    $scope.loadCartProducts();
                },
                function failCallback() {
                    alert("Невозможно очистить корзину!");
                }
            );
    }

    $scope.sendOrder = function () {
        $http.get(corePath + '/order')
            .then(function successCallback() {
                    alert("Заказ успешно размещен!");
                    $scope.removeAllProductsInCart();
                    $scope.loadCartProducts();
                },
                function failCallback(response) {
                    alert(response.data.messages);
                });
    }

    $scope.loadCartProducts();
});