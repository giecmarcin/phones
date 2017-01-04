angular.module('phonesApp').controller('NavbarController', function ($scope, $resource, $localStorage, UserService) {
    $scope.message = 'Hello from navbarController';
    $scope.email;
    $scope.admin;
    var loadCurrentUser = function () {
        UserService
            .getCurrentUser()
            .then(function (response) {
                if (response.status == 200) {
                    $scope.email = response.data.email;
                    $localStorage.email = response.data.email;
                    $localStorage.first_name = response.data.first_name;
                    $localStorage.last_name = response.data.last_name;
                    $localStorage.role = response.data.role;

                    if (angular.equals(response.data.role, 'ROLE_ADMIN')) {
                        $scope.admin = true;
                        $localStorage.isAdmin = true;
                    } else {
                        $scope.admin = false;
                        $localStorage.isAdmin = false;
                    }
                }
            })
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