angular.module('phonesApp').service('UserService', function ($http) {
    /*
     data – {string|Object} – The response body transformed with the transform functions.
     status – {number} – HTTP status code of the response.
     headers – {function([headerName])} – Header getter function.
     config – {Object} – The configuration object that was used to generate the request.
     statusText – {string} – HTTP status text of the response.
     */
    this.getCurrentUser = function () {
        var url = 'api/user/current';
        return $http({
            method: "GET",
            url: url
        }).then(function successCallback(response) {
            //return angular.toJson(response.data);
            return response;
        }, function errorCallback(response) {
            return response.status;
        });
    }

    this.findAllOrdersByUser = function (email) {
        var url = 'api/orders/all/' + email + '.json';
        return $http({
            method: "GET",
            url: url
        }).then(function successCallback(response) {
            //return angular.toJson(response.data);
            return response;
        }, function errorCallback(response) {
            return response.status;
        });
    }

    this.addUser = function (user) {
        return $http({
            method: "POST",
            url: '/api/user/add',
            data: user
        }).then(function successCallback(response) {
            //return angular.toJson(response.data);
            return response;
        }, function errorCallback(response) {
            return response.status;
        });
    }
});