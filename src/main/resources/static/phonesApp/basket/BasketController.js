angular.module('phonesApp').controller('BasketController', function ($scope, $resource, $http, $routeParams, $rootScope, $localStorage, BasketService, UserService) {
    $scope.phonesInBasket = [];
    $scope.message;
    $scope.cost = 0;
    $scope.numberOfItems = 0;
    $scope.customerEmail = '';
    $scope.customerFirstName = '';
    $scope.customerLastName = '';
    var loadAllItems = function () {
        if ($localStorage.email != undefined) {
            $scope.customerEmail = $localStorage.email;
            $scope.customerFirstName = $localStorage.first_name;
            $scope.customerLastName = $localStorage.last_name;
        }
        $scope.cost = 0;
        $scope.numberOfItems = 0;
        $scope.phonesInBasket = $localStorage.phoneItems;
        if ($localStorage.phoneItems != undefined) {
            for (var i = 0; i < $localStorage.phoneItems.length; i++) {
                $scope.cost += $localStorage.phoneItems[i].phone.price * $localStorage.phoneItems[i].quantity;
                $scope.numberOfItems += $localStorage.phoneItems[i].quantity;
            }
        }
    }
    loadAllItems();

    $scope.removeItem = function (phone) {
        var index = $localStorage.phoneItems.indexOf(phone);
        $localStorage.phoneItems.splice(index, 1);
        loadAllItems();
    }

    $scope.removeAllItems = function () {
        delete $localStorage.phoneItems;
        loadAllItems();
    }

    $scope.confirmOrder = function () {
        UserService
            .getCurrentUser()
            .then(function (response) {
                if (response.status == 200) {
                    if ($localStorage.email == undefined) {
                        $scope.message = "Proszę się zalogować."
                    }
                    var order = {
                        customerEmail: $scope.customerEmail,
                        products: $scope.phonesInBasket
                    }

                    BasketService
                        .confirmOrder(order)
                        .then(function (response) {
                            if (response.status == 200) {
                                alert('Zamówienie zostało przyjęte.');
                                $scope.removeAllItems();
                            } else {
                                alert('Nie udało się zrealizować.');
                            }
                        })
                } else {
                    alert('Proszę się zalogować.');
                }
            })
    }

    $scope.addOne = function (p) {
        p.quantity += 1;
        loadAllItems();
    }

    $scope.subtractOne = function (p) {
        if (p.quantity > 1) {
            p.quantity -= 1;
        }
        loadAllItems();
    }
});