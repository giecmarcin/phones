angular.module('phonesApp').controller('DetailPhoneController', function ($scope, $resource, $http, $routeParams, $rootScope) {
    $scope.message = 'Telefony';
    $scope.phone;

    var loadOnePhoneFromDb = function () {
        var url = 'api/phone/id/' + $routeParams.id;
        var onePhone = $resource(url, {}, {
            query: {method: 'get', isArray: false, cancellable: true}
        });

        onePhone.query(function (response) {
            $scope.phone = response;
        });
    };
    loadOnePhoneFromDb();
});