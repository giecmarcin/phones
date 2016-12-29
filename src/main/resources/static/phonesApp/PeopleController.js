angular.module('phonesApp').controller('PeopleController', function ($scope, $resource, $http) {
    $scope.message = 'Hello from PeopleController';
    $scope.people;

    //$resource("../rest/api"}).get(); return an object.
    //$resource("../rest/api").query(); return an array.

    var loadAllPeopleFromDb = function () {
        var People = $resource('person/all', {}, {
            query: {method: 'get', isArray: true, cancellable: true}
        });

        People.query(function (response) {
            //alert(response); teraz w response masz to co bys widzial w postmanie takiego jsona
            $scope.people = response; // widoku będziesz używał teraz people
        });
    };
    loadAllPeopleFromDb();





    //$scope.people = $resource('/person/all', []).get(); //to da undefined bo nie zdazyl jeszcze pobrac
    //alert($scope.people.size);
    //scope.cos = dajesz wtedy gdy chcesz miec dostep do czegos w pliku html w widoku i zbindowac na przyklad



    //Zapis osoby do bazy danych
    $scope.savePerson = function () {
        var name = $scope.nameOfUser; //pobieramy imie z pola w html
        var lastName = $scope.lastNameOfUser;
        var age = $scope.ageOfUser;

        //alert(name); //to tylko dla testu czy dane sie pobieraja, w google chrome ctrl+shif j otwiera conosle do debuga
        //degug //tak sie wlacza debugger w js

        //Potrzebujemy stworzyc nasz obiekt, ktorego zadamy w Javie patrz RequestBody
        var userObject = {
            name: name,
            lastName: lastName,
            age: age
        };

        $http.post('/person/add',userObject).success(function () { //wywloujemy
            //alert('Thanks');
            loadAllPeopleFromDb();
        }).error(function () {
            alert('We have problem!');
        })
    };

});