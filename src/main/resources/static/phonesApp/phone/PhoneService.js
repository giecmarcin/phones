angular.module('phonesApp').service('PhoneService', function ($http, $resource) {
    /*
     data – {string|Object} – The response body transformed with the transform functions.
     status – {number} – HTTP status code of the response.
     headers – {function([headerName])} – Header getter function.
     config – {Object} – The configuration object that was used to generate the request.
     statusText – {string} – HTTP status text of the response.
     */
    this.findOnePhone = function (id, phone) {
        var url = 'api/phone/id/' + id;
        $http({
            method: "GET",
            url: url
        }).then(function successCallback(response) {
            //alert(angular.toJson(response.data)); //są dane
            return response.data;
        }, function errorCallback(response) {
            return response.status;
        });
    }

    this.method2 = function () {
        //..
    }
});