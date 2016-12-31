angular.module('phonesApp').controller('DetailPhoneController', function ($scope, $routeParams, $rootScope, PhoneService) {
    $scope.message = 'Telefony';
    $scope.phone = '';

    var loadPhoneData = function (id) {
        PhoneService
            .findOnePhone(id)
            .then(function (response) {
                if (response.status == 200) {
                    $scope.phone = response.data;
                }
            })
    }
    loadPhoneData($routeParams.id);
});