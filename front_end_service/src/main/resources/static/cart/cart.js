angular.module('market_front').controller('cartProductController', function ($scope, $http, $rootScope, $localStorage) {

    const cartPath = 'http://localhost:5000/cart/api/v1/cart';

    const corePath = 'http://localhost:5000/core/api/v1';


    $scope.injectCart = function (response) {
        $scope.cartItems = response.data.items;
        $scope.sumOfOrder = response.data.totalPrice;
    }
    $scope.injectGuestCart = function () {
        $scope.cartItems = $localStorage.guestCart.items;
        $scope.sumOfOrder = $localStorage.guestCart.totalPrice;
    }

    $rootScope.recalculateGuestCart = function () {
        let cart = $localStorage.guestCart;
        let totalProductsPrice = 0;
        for (let i = 0; i < cart.items.length; i++) {
            totalProductsPrice = totalProductsPrice + cart.items[i].sum;
        }
        cart.totalPrice = totalProductsPrice;
    }

    $scope.guestIncAndDecProduct = function (item, number){
        let arr = $localStorage.guestCart.items;
        for (let i = 0; i < arr.length; i++) {
            if (arr[i].title === item.title) {
                arr[i].quantity = arr[i].quantity + number;
                if (arr[i].quantity < 1){
                   arr.splice(i,1);
                   return;
                }
                arr[i].sum = arr[i].price * arr[i].quantity;
                $rootScope.recalculateGuestCart();
                $scope.injectGuestCart();
                return;
            }
        }
    }

    $rootScope.loadCartProducts = function () {
        if ($rootScope.isUserLoggedIn()) {
            $http.get(cartPath)
                .then(function successCallback(response) {
                    $scope.injectCart(response);
                });
        } else {
            $scope.injectGuestCart();
        }
    }

    $scope.deleteProductFromCart = function (item) {
        if ($rootScope.isUserLoggedIn()){
            $http.delete(cartPath + '/item/' + item.productId)
                .then(function successCallback() {
                        $scope.loadCartProducts();
                        alert("Продукт удален!")
                    },
                    function failCallback(response) {
                        alert(response.data.messages);
                    });
        } else {
            let arrT = $localStorage.guestCart.items;
            for (let i = 0;  i < arrT.length; i++){
                if (arrT[i].title === item.title){
                    arrT.splice(i, 1);
                }
            }
        }
    }

    $scope.incCountOfProduct = function (item) {
        if ($rootScope.isUserLoggedIn()){
            $http.put(cartPath + '/item/inc/' + item.productId)
                .then(function successCallback(response) {
                        $scope.injectCart(response);
                    },
                    function failCallback(response) {
                        alert(response.data.messages);
                    });
        } else {
            $scope.guestIncAndDecProduct(item, 1);
        }

    }
    $scope.decCountOfProduct = function (item) {
        if ($rootScope.isUserLoggedIn()){
            $http.put(cartPath + '/item/dec/' + item.productId)
                .then(function successCallback(response) {
                        $scope.injectCart(response);
                    },
                    function failCallback(response) {
                        alert(response.data.messages);
                    });
        } else {
            $scope.guestIncAndDecProduct(item, -1);
        }

    }

    $scope.removeAllProductsInCart = function () {
        if ($rootScope.isUserLoggedIn()){
            $http.delete(cartPath)
                .then(function successCallback() {
                        alert("Корзина очищена!");
                        $scope.loadCartProducts();
                    },
                    function failCallback() {
                        alert("Невозможно очистить корзину!");
                    }
                );
        } else {
            $localStorage.guestCart.items.length = 0;
            $localStorage.guestCart.totalPrice = 0;
            $scope.injectGuestCart();
        }
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