angular.module('phonesApp').controller('PhoneAdminController', function ($scope, $resource, $http, $routeParams, $rootScope, $location, $anchorScroll) {
    $scope.message = 'adminController';
    $scope.allPhones;
    $scope.showForm = false;
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

    $scope.loadDataForPhone = function (phoneId, id) {
        var index;
        for (var i = 0; i < $scope.allPhones.length; i++) {
            if (angular.equals($scope.allPhones[i].phone.id, phoneId)) {
                index = i;
                break;
            }
        }

        $scope.pId = phoneId;
        $scope.brand = $scope.allPhones[index].phone.brand,
            $scope.fullName = $scope.allPhones[index].phone.fullName,
            $scope.price = $scope.allPhones[index].phone.price,
            $scope.processor = $scope.allPhones[index].phone.processor,
            $scope.graphics = $scope.allPhones[index].phone.graphics,
            $scope.ram = $scope.allPhones[index].phone.ram,
            $scope.builtInMemory = $scope.allPhones[index].phone.builtInMemory,
            $scope.typeOfDisplay = $scope.allPhones[index].phone.typeOfDisplay,
            $scope.sizeOfDisplay = $scope.allPhones[index].phone.sizeOfDisplay,
            $scope.resolutionOfDisplay = $scope.allPhones[index].phone.resolutionOfDisplay,
            $scope.communication = createStringByArray($scope.allPhones[index].phone.communication);
        $scope.navigation = $scope.allPhones[index].phone.navigation,
            $scope.connectors = $scope.allPhones[index].phone.connectors,
            $scope.capacityOfBattery = $scope.allPhones[index].phone.capacityOfBattery;
        $scope.operatingSystem = $scope.allPhones[index].phone.operatingSystem,
            $scope.frontCameraMPX = $scope.allPhones[index].phone.frontCameraMPX,
            $scope.cameraMPX = $scope.allPhones[index].phone.cameraMPX,
            $scope.flashLamp = $scope.allPhones[index].phone.flashLamp,
            $scope.thickness = $scope.allPhones[index].phone.thickness,
            $scope.width = $scope.allPhones[index].phone.width,
            $scope.height = $scope.allPhones[index].phone.height,
            $scope.weight = $scope.allPhones[index].phone.weight,
            $scope.colour = $scope.allPhones[index].phone.colour,
            $scope.extraInfo = $scope.allPhones[index].phone.extraInfo,
            $scope.guarantee = $scope.allPhones[index].phone.guarantee,
            $scope.resolutionRecordingVideo = $scope.allPhones[index].phone.resolutionRecordingVideo;
        $scope.includedAccessories = $scope.allPhones[index].phone.includedAccessories,
            $scope.quantity = $scope.allPhones[id].quantity;
        $scope.showForm = true;
        $scope.scrollTo('formForEdit');
    };

    $scope.scrollTo = function (id) {
        $location.hash(id);
        $anchorScroll();
    };

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

    $scope.editPhone = function () {
        //alert($scope.pId);
        var communicationTab = [];
        $scope.communication;
        if ($scope.communication != undefined) {
            communicationTab = $scope.communication.split(',');
        }
        var phone = {
            id: $scope.pId,
            brand: $scope.brand,
            fullName: $scope.fullName,
            price: $scope.price,
            processor: $scope.processor,
            graphics: $scope.graphics,
            ram: $scope.ram,
            builtInMemory: $scope.builtInMemory,
            typeOfDisplay: $scope.typeOfDisplay,
            sizeOfDisplay: $scope.sizeOfDisplay,
            resolutionOfDisplay: $scope.resolutionOfDisplay,
            communication: communicationTab,
            navigation: $scope.navigation,
            connectors: $scope.connectors,
            capacityOfBattery: $scope.capacityOfBattery,
            operatingSystem: $scope.operatingSystem,
            frontCameraMPX: $scope.frontCameraMPX,
            cameraMPX: $scope.cameraMPX,
            flashLamp: $scope.flashLamp,
            thickness: $scope.thickness,
            width: $scope.width,
            height: $scope.height,
            weight: $scope.weight,
            colour: $scope.colour,
            extraInfo: $scope.extraInfo,
            guarantee: $scope.guarantee,
            resolutionRecordingVideo: $scope.resolutionRecordingVideo,
            includedAccessories: $scope.includedAccessories
        };
        var phoneAndQuantity = {
            phone: phone,
            quantity: $scope.quantity
        };

        $http.post('/api/phone/edit', phoneAndQuantity).success(function () {
            loadPhonesFromDb();
            alert('Telfon został zmodyfikowany');
            $scope.showForm = false;
        }).error(function () {
            alert('Nie udało się zmienić danych!');
        })
    }


});