/**
 * Created by Marcin on 02.12.2016.
 */
var phonesApp = angular.module('phonesApp', ['ngRoute', 'ngResource', 'ui.bootstrap', 'rzModule', 'ngStorage', 'angularUtils.directives.dirPagination']);


phonesApp.config(function ($routeProvider) {
    $routeProvider

        .when('/', {
            templateUrl: 'views/phone/phones.html',
            controller: 'PhoneController'
        })
        .when('/phone/detail/:id', {
            templateUrl: 'views/phone/detail.html',
            controller: 'DetailPhoneController'
        })
        .when('/basket', {
            templateUrl: 'views/basket/basket.html',
            controller: 'BasketController'
        })
        .when('/phone/add', {
            templateUrl: 'views/phone/add.html',
            controller: 'AddPhoneController',
            resolve: {
                msg: function ($location, $localStorage, UserService) {
                    UserService
                        .getCurrentUser()
                        .then(function (response) {
                            //$localStorage.isAdmin=false;
                            if (response.status == 200) {
                                if (angular.equals(response.data.role, 'ROLE_ADMIN')) {
                                    $localStorage.isAdmin = true;
                                } else {
                                    $location.path("/");
                                }
                            } else {
                                $location.path("/");
                            }
                        })
                }
            }
        })
        .when('/admin/phone/all', {
            templateUrl: 'views/admin/phone/phones.html',
            controller: 'PhoneAdminController',
            resolve: {
                msg: function ($location, $localStorage, UserService) {
                    UserService
                        .getCurrentUser()
                        .then(function (response) {
                            //$localStorage.isAdmin=false;
                            if (response.status == 200) {
                                if (angular.equals(response.data.role, 'ROLE_ADMIN')) {
                                    $localStorage.isAdmin = true;
                                } else {
                                    $location.path("/");
                                }
                            } else {
                                $location.path("/");
                            }
                        })
                }
            }
        })
        .when('/login', {
            templateUrl: 'views/login.html',
            controller: 'LoginController'
        })
        .when('/people', {
            templateUrl: 'views/people/people.html',
            controller: 'PeopleController'
        })
        .when('/about', {
            templateUrl: 'views/about.html',
            controller: 'AboutController'
        })
        .otherwise({redirectTo: '/'});
});

phonesApp.run(function ($localStorage) {
    if ($localStorage.isAdmin == undefined) {
        $localStorage.isAdmin = false;
    }
});