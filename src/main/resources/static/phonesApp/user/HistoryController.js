angular.module('phonesApp').controller('HistoryController', function ($scope, $routeParams, $rootScope, UserService) {
    $scope.allData;
    $scope.currentUser;

    var loadData = function () {
        UserService.getCurrentUser()
            .then(function (response) {
                if (response.status == 200) {
                    $scope.currentUser = response.data;

                    UserService.findAllOrdersByUser($scope.currentUser.email)
                        .then(function (response2) {
                            if (response2.status == 200) {
                                $scope.allData = response2.data;
                                alert($scope.allData[0].phoneAndQuantityInOrderList[0].quantity);
                                //console.log(angular.toJson($scope.allData));
                            }
                        })
                }
            })
    }
    loadData();
});