angular.module('phonesApp').controller('DetailPhoneController', function ($scope, $http, $routeParams, $rootScope, PhoneService) {
    $scope.message = 'Telefony';
    $scope.phone = '';

    var loadPhoneData = function (id) {
        $scope.phone = PhoneService.findOnePhone(id);
    }
    loadPhoneData($routeParams.id);

    // var goData = function(){
    //     PhoneService.findOnePhone($routeParams.id).then(function(data){
    //         $scope.phone = data;
    //     });
    //
    // };
    // goData();
});