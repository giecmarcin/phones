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
            controller: 'AddPhoneController'
        })
        .when('/admin/phone/all', {
            templateUrl: 'views/admin/phone/phones.html',
            controller: 'PhoneAdminController'
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