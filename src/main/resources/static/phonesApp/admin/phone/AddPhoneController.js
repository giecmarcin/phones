angular.module('phonesApp').controller('AddPhoneController', function ($scope, PhoneService) {
    $scope.message = 'Dodawnie telefonu';

    //Zapis osoby do bazy danych
    $scope.savePhone = function () {
        var communicationTab = [];
        var images = [];

        var strBase64 = $scope.data.replace('data:image/jpeg;base64,', '');
        var obj = {
            image: strBase64
        }
        images.push(obj);

        $scope.communication;
        if ($scope.communication != undefined) {
            communicationTab = $scope.communication.split(',');
        }
        var phone = {
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
            //     private List<ImageUrl> imageUrl = new ArrayList<>(),
            imageUrl: images,
            imageUrlXKom: "",
            extraInfo: $scope.extraInfo,
            guarantee: $scope.guarantee,
            resolutionRecordingVideo: $scope.resolutionRecordingVideo,
            includedAccessories: $scope.includedAccessories,
        };

        var phoneAndQuantity = {
            phone: phone,
            quantity: $scope.quantity
        }
        //alert(phone.resolutionOfDisplay);
        //alert(phone.capacityOfBattery);
        //alert(phoneAndQuantity.quantity);

        if (angular.equals($scope.data, 'none')) {
            alert("Ładuje zdjęcie. Spróbuj za chwilę jeszcze raz.");
        } else {
            PhoneService
                .addPhone(phoneAndQuantity)
                .then(function (response) {
                    if (response.status == 200) {
                        alert('Telefon został dodany');
                    } else {
                        alert('Nie udało się dodać telefonu.');
                    }
                })
        }
    };

    $scope.data = 'none';
    $scope.addImage = function () {
        var f = document.getElementById('file').files[0],
            r = new FileReader();
        r.onloadend = function (e) {
            $scope.data = e.target.result;
        }
        r.readAsDataURL(f)
        //alert($scope.data);
        //console.log($scope.data);
        alert('Ok');
    }

    $scope.loadTestData = function () {
        var x = Math.floor((Math.random() * 1000) + 1);
        $scope.brand = 'Marka' + x,
            $scope.fullName = 'Telefon' + x,
            $scope.price = 899,
            $scope.processor = 'Apple A7 (2 rdzenie, 1.30 GHz)',
            $scope.graphics = 'Adreno 505',
            $scope.ram = 3,
            $scope.builtInMemory = 32,
            $scope.typeOfDisplay = 'Pojemnościowy, 10-punktowy, IPS',
            $scope.sizeOfDisplay = 5,
            $scope.resolutionOfDisplay = '1280 x 720',
            $scope.communication = 'Bluetooth, Wi-Fi, 4G, (LTE)',
            $scope.navigation = 'GPS',
            $scope.connectors = 'Gniazdo kart microSIM - 1 szt. Gniazdo kart nanoSIM - 1 szt. (wspólne z czytnikiem kart pamięci) Wyjście słuchawkowe/głośnikowe - 1 szt. Micro USB - 1 szt.',
            $scope.capacityOfBattery = 4100,
            $scope.operatingSystem = 'Android 6.0 Marshmallow',
            $scope.frontCameraMPX = 5,
            $scope.cameraMPX = 13,
            $scope.flashLamp = 'Wbudowana',
            $scope.thickness = 8.5,
            $scope.width = 69,
            $scope.height = 139,
            $scope.weight = 143,
            $scope.colour = 'Szary',
            $scope.extraInfo = 'Czytnik linii papilarnych Obsługa dwóch kart SIM (Dual SIM) Akcelerometr Żyroskop Magnetometr',
            $scope.guarantee = '24 miesiące (gwarancja producenta)',
            $scope.resolutionRecordingVideo = '1920 x 1080 (FullHD)',
            $scope.includedAccessories = 'Ładowarka Kabel USB',
            $scope.quantity = 1000;
    }
});