angular.module('phonesApp').controller('RegisterController', function ($scope, $routeParams, $rootScope, UserService, $window) {
    $scope.email;
    $scope.password;
    $scope.name;
    $scope.last_name;

    $scope.saveUser = function () {
        var user = {
            first_name:$scope.name,
            last_name :$scope.last_name,
            email: $scope.email,
            password: $scope.password,
        }
        UserService
            .addUser(user)
            .then(function (response) {
                if (response.status == 200) {
                    alert('Możesz teraz się zalogować.');
                    $window.location.href = '/login';
                } else {
                    alert('Wystąpił problem podczas rejestracji');
                }
            })
    }
});