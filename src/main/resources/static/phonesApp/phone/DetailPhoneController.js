angular.module('phonesApp').controller('DetailPhoneController', function ($scope, $http, $routeParams, $rootScope, PhoneService) {
    $scope.message = 'Telefony';
    $scope.phone = '';

    var loadPhoneData = function (id) {
        PhoneService
            .findOnePhone(id)
            .then(function (response) {
                $scope.phone = response;
            })
    }
    loadPhoneData($routeParams.id);
});