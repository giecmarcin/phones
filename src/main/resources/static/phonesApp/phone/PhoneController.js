angular.module('phonesApp').controller('PhoneController', function ($scope, $rootScope, $localStorage, PhoneService) {
    $scope.message = 'Telefony';
    $scope.phones;
    $scope.allBrandsFromDb;

    var loadBrandsFromDb = function () {
        PhoneService
            .findAll('api/phone/brand/all')
            .then(function (response) {
                if (response.status == 200) {
                    $scope.allBrandsFromDb = response.data;
                    $scope.brands = $scope.allBrandsFromDb;
                    $scope.selectionBrand = $scope.allBrandsFromDb.slice(); // slice() operation clones the array and returns the reference to the new array
                }
            })
    };
    loadBrandsFromDb();

    $scope.allMemoryFromDb;
    var loadBuiltInMemorySizesFromDb = function () {
        PhoneService
            .findAll('api/phone/builtInMemory/all')
            .then(function (response) {
                if (response.status == 200) {
                    $scope.allMemoryFromDb = response.data;
                    $scope.maxMemory = 0;
                    for (var i = 0; i < $scope.allMemoryFromDb.length; i++) {
                        if ($scope.allMemoryFromDb[i] > $scope.maxMemory)
                            $scope.maxMemory = $scope.allMemoryFromDb[i];
                    }

                    $scope.memorySlider = {
                        minValue: 0,
                        maxValue: $scope.maxMemory,
                        options: {
                            floor: 0,
                            ceil: $scope.maxMemory,
                            step: 1,
                            noSwitching: true
                        }
                    };
                }
            })
    };
    loadBuiltInMemorySizesFromDb();


    $scope.allRamSizesFromDb;
    var loadAllRamSizesFromDb = function () {
        PhoneService.findAll('api/phone/ram/all')
            .then(function (response) {
                if (response.status == 200) {
                    $scope.allRamSizesFromDb = response.data;
                    $scope.maxRam = 0;
                    for (var i = 0; i < $scope.allRamSizesFromDb.length - 1; i++) {
                        if ($scope.allRamSizesFromDb[i] > $scope.maxRam)
                            $scope.maxRam = $scope.allRamSizesFromDb[i];
                    }
                    $scope.ramSlider = {
                        minValue: 0,
                        maxValue: $scope.maxRam,
                        options: {
                            floor: 0,
                            ceil: $scope.maxRam,
                            step: 1,
                            noSwitching: true
                        }
                    };
                }
            })
    };
    loadAllRamSizesFromDb();


    $scope.allDisplaySizesFromDb;
    var loadAllDisplaySizesFromDb = function () {

        PhoneService
            .findAll('api/phone/displaySizes/all')
            .then(function (response) {
                if (response.status == 200) {
                    $scope.allDisplaySizesFromDb = response.data;
                    $scope.maxSizeOfDisplay = 0;
                    for (var i = 0; i < $scope.allDisplaySizesFromDb.length; i++) {
                        if ($scope.allDisplaySizesFromDb[i] > $scope.maxSizeOfDisplay)
                            $scope.maxSizeOfDisplay = $scope.allDisplaySizesFromDb[i];
                    }
                    $scope.displaySlider = {
                        minValue: 1,
                        maxValue: $scope.maxSizeOfDisplay,
                        options: {
                            floor: 1,
                            ceil: $scope.maxSizeOfDisplay,
                            step: 1,
                            noSwitching: true
                        }
                    };
                }
            })
    };
    loadAllDisplaySizesFromDb();


    $scope.minPrice = 0;
    $scope.maxPrice = 0;
    $scope.minRam = 0.5;
    $scope.maxRam = 0;

    var findMaxPrice = function (phones) {
        var max = 0;
        for (var i = 0; i < phones.length; i++) {
            if (phones[i].price > max)
                max = phones[i].price;
        }
        return max;
    };


    var urlToAllPhones = 'api/phone/all';
    var urlToPhones = 'api/phone/all?pageNumber=1&pageSize=50';
    var loadPhonesFromDb = function () {
        PhoneService
            .findAll(urlToAllPhones)
            .then(function (response) {
                if (response.status == 200) {
                    $scope.phones = response.data;
                    $rootScope.allPhonesFromDb = response.data;

                    $scope.maxPrice = findMaxPrice($scope.phones);
                    $scope.priceSlider = {
                        minValue: 100,
                        maxValue: $scope.maxPrice,
                        options: {
                            floor: 100,
                            ceil: $scope.maxPrice,
                            step: 100,
                            noSwitching: true
                        }
                    };
                }
            })
    };
    loadPhonesFromDb();

    // toggle selection for a given brand by name
    $scope.toggleSelection = function toggleSelection(brandName) {
        var idx = $scope.selectionBrand.indexOf(brandName);
        if (idx > -1) {
            $scope.selectionBrand.splice(idx, 1);
        }
        else {
            $scope.selectionBrand.push(brandName);
        }
    };


    $scope.filterByBrands = function (p) {
        if ($scope.selectionBrand.length == 0) {
            return p;
        }
        return ($scope.selectionBrand.indexOf(p.brand) !== -1);
    };

    $scope.filterByPrice = function (lOfPhones) {
        $scope.minPrice = $scope.priceSlider.minValue;
        $scope.maxPrice = $scope.priceSlider.maxValue;
        if (lOfPhones.price >= $scope.minPrice && lOfPhones.price <= $scope.maxPrice) {
            return lOfPhones;
        }
    }

    $scope.filterByRam = function (lOfPhones) {
        var minRam = $scope.ramSlider.minValue;
        var maxRam = $scope.ramSlider.maxValue;
        if (lOfPhones.ram >= minRam && lOfPhones.ram <= maxRam) {
            return lOfPhones;
        }
    }

    $scope.filterByDisplay = function (lOfPhones) {
        var minSizeOfDisplay = $scope.displaySlider.minValue;
        var maxSizeOfDisplay = $scope.displaySlider.maxValue;
        if (lOfPhones.sizeOfDisplay >= minSizeOfDisplay && lOfPhones.sizeOfDisplay <= maxSizeOfDisplay) {
            return lOfPhones;
        }
    }

    $scope.filterByMemory = function (lOfPhones) {
        var minMemory = $scope.memorySlider.minValue;
        var maxMemory = $scope.memorySlider.maxValue;
        if (lOfPhones.builtInMemory >= minMemory && lOfPhones.builtInMemory <= maxMemory) {
            return lOfPhones;
        }
    }

    $scope.uncheckedBrands = function () {
        var index = $scope.selectionBrand.length;
        if (index > 1) {
            $scope.selectionBrand.splice(1, index);
        } else {
            $scope.selectionBrand = $scope.allBrandsFromDb.slice(); // slice() operation clones the array and returns the reference to the new array
        }
    }

    $scope.showDetail = function (event) {
        //console.log($(event.target).attr("data-id"));
        $rootScope.idSelectedPhone = ($(event.target).attr("data-id"));
    }


    //Basket
    var existInBasket = function (id) {
        for (var i = 0; i < $localStorage.phoneItems.length; i++) {
            if (angular.equals($localStorage.phoneItems[i].phone.id, id)) {
                return i;
            }
        }
        return -1;
    }

    if ($localStorage.phoneItems === undefined) {
        //$localStorage.myFaves = [];
        $localStorage.phoneItems = [];
    }
    $scope.addItemToBasket = function (phone) {
        var phoneObject = {
            phone: phone,
            quantity: 1,
        };
        var indexOfSelectedObj = existInBasket(phone.id);
        if (indexOfSelectedObj != -1) {
            $localStorage.phoneItems[indexOfSelectedObj].quantity += 1;
            alert('Zwiększono ilość zamówionych telefonów o 1.');
        } else {
            $localStorage.phoneItems.push(phoneObject);
            alert('Dododano do koszyka');
        }
        phoneObject = null;
    }
});