angular.module('phonesApp').controller('NavbarController', function ($scope, $resource, $localStorage) {
    $scope.message = 'Hello from navbarController';
    $scope.email;
    $scope.admin;
    var loadCurrentUser = function () {
        //debugger;
        var User = $resource('api/user/current.json', {}, {
            query: {method: 'get', isArray: false, cancellable: true}
        });

        User.query(function (response) {
            $scope.email = response.email;
            $localStorage.email = response.email;
            $localStorage.first_name = response.first_name;
            $localStorage.last_name = response.last_name;
            $localStorage.role = response.role;

            if (angular.equals(response.role, 'ROLE_ADMIN')) {
                $scope.admin = true;
                $localStorage.isAdmin = true;
            } else {
                $scope.admin = false;
                $localStorage.isAdmin = false;
            }
            //alert($scope.admin);
        });
    };
    loadCurrentUser();

    $scope.removeUserFromLocalStorage = function () {
        delete $localStorage.email;
        delete $localStorage.first_name;
        delete $localStorage.last_name;
        delete $localStorage.isAdmin;
        $localStorage.$reset();
    }
});