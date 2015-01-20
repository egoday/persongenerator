var personGeneratorApp = angular.module('personGeneratorApp', ['ui.bootstrap', 'ngStorage']);

personGeneratorApp.controller('MainController', function($scope, $http, $localStorage) {
	$scope.query = $localStorage.$default({
		"gender" : "",
		"dniPattern" : "",
		"namesPercentage" : 10,
		"appsPercentage" : 10
	});
	
	$scope.persons = [];
	
	$scope.next = function(query) {
		var url = 'rest/generator/next';
		var sep = '?';

		if (query.gender != '') {
			url = url + "?gender=" + query.gender;
			sep = '&';
		}

		if (query.dniPattern != '' && query.dniPattern != null) {
			url = url + sep + "dniPattern=" + query.dniPattern;
			sep = '&';
		}

		url = url + sep + "namesPercentage=" + query.namesPercentage;
		sep = '&';

		url = url + sep + "appsPercentage=" + query.appsPercentage; 

		$http.get(url).success(function(data) {
			$scope.person = data;
			$scope.archive(data);
		}).error(function(data) {
			alert(url + " " + data);
		});
	}
	
	$scope.copy = function(person) {
		var text = person.name + " " + person.app1 + " " + person.app2 + " " + person.dni;
		window.prompt("Copy to clipboard: Ctrl+C, Enter", text);
	}

	$scope.remove = function(person) {
		$scope.person = null;
	}

	$scope.archive = function(person) {
		var index = $scope.persons.indexOf(person);

		if (index == -1) {
			$scope.persons.push(person);
		}

		if ($scope.persons.length > 5) {
			$scope.persons.splice(0, 1);
		}
	}

	$scope.unarchive = function(person) {
		var index = $scope.persons.indexOf(person);
		$scope.persons.splice(index, 1);
		$scope.person = person;
	}
});