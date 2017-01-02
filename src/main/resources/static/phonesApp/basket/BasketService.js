angular.module('phonesApp').service('BasketService', function ($http, $resource) {
    /*
     data – {string|Object} – The response body transformed with the transform functions.
     status – {number} – HTTP status code of the response.
     headers – {function([headerName])} – Header getter function.
     config – {Object} – The configuration object that was used to generate the request.
     statusText – {string} – HTTP status text of the response.
     */

    this.confirmOrder = function (order) {
        return $http({
            method: "POST",
            url: 'api/basket/order/confirm',
            data: order
        }).then(function successCallback(response) {
            //return angular.toJson(response.data);
            return response;
        }, function errorCallback(response) {
            return response.status;
        });
    }
});