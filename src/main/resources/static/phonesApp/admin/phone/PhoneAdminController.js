angular.module('phonesApp').controller('PhoneAdminController', function ($scope, $resource, $http, $routeParams, $rootScope) {
    $scope.message = 'adminController';
    $scope.allPhones;
    var urlToAllPhones = 'api/phone/all/quantity';
    var loadPhonesFromDb = function () {
        var phones = $resource(urlToAllPhones, {}, {
            query: {method: 'get', isArray: true, cancellable: true}
        });
        phones.query(function (response) {
            $scope.allPhones = response;
            $rootScope.allPhonesFromDb = response;
        });
    };
    loadPhonesFromDb();

    $scope.loadDataForPhone = function (id) {
        $scope.brand = $scope.allPhones[id].phone.brand,
            $scope.fullName = $scope.allPhones[id].phone.fullName,
            $scope.price = $scope.allPhones[id].phone.price,
            $scope.processor = $scope.allPhones[id].phone.processor,
            $scope.graphics = $scope.allPhones[id].phone.graphics,
            $scope.ram = $scope.allPhones[id].phone.ram,
            $scope.builtInMemory = $scope.allPhones[id].phone.builtInMemory,
            $scope.typeOfDisplay = $scope.allPhones[id].phone.typeOfDisplay,
            $scope.sizeOfDisplay = $scope.allPhones[id].phone.sizeOfDisplay,
            $scope.resolutionOfDisplay = $scope.allPhones[id].phone.resolutionOfDisplay,
            $scope.communication = createStringByArray($scope.allPhones[id].phone.communication);
        $scope.navigation = $scope.allPhones[id].phone.navigation,
            $scope.connectors = $scope.allPhones[id].phone.connectors,
            $scope.capacityOfBattery = $scope.allPhones[id].phone.capacityOfBattery;
        $scope.operatingSystem = $scope.allPhones[id].phone.operatingSystem,
            $scope.frontCameraMPX = $scope.allPhones[id].phone.frontCameraMPX,
            $scope.cameraMPX = $scope.allPhones[id].phone.cameraMPX,
            $scope.flashLamp = $scope.allPhones[id].phone.flashLamp,
            $scope.thickness = $scope.allPhones[id].phone.thickness,
            $scope.width = $scope.allPhones[id].phone.width,
            $scope.height = $scope.allPhones[id].phone.height,
            $scope.weight = $scope.allPhones[id].phone.weight,
            $scope.colour = $scope.allPhones[id].phone.colour,
            $scope.extraInfo = $scope.allPhones[id].phone.extraInfo,
            $scope.guarantee = $scope.allPhones[id].phone.guarantee,
            $scope.resolutionRecordingVideo = $scope.allPhones[id].phone.resolutionRecordingVideo;
        $scope.includedAccessories = $scope.allPhones[id].phone.includedAccessories,
            $scope.quantity = $scope.allPhones[id].quantity;
    }

    function createStringByArray(array) {
        var output = '';
        angular.forEach(array, function (object) {
            output += object.name + ', ';
        });
        output = output.substring(0, output.length - 1);
        return output;
    }

    $scope.removePhone = function (id) {
        $http({
            method: 'DELETE',
            url: '/api/phone/remove/' + id
        }).success(function (data) {
            alert('Telefon został usunięty' + id);
            var index;
            for (var i = 0; i < $scope.allPhones.length; i++) {
                if (angular.equals($scope.allPhones[i].phone.id, id)) {
                    index = i;
                    break;
                }
            }
            $scope.allPhones.splice(index, 1); //return removed object
        }).error(function (error) {
            //Showing error message
            alert('Nie udało się usunąć telefonu.');
        });
    }
});