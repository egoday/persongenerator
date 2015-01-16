var personGeneratorApp = angular.module('personGeneratorApp', []);

personGeneratorApp.controller('MainController', function($scope, $http) {
	$scope.query = {
		"gender" : "",
		"dniPattern" : "",
		"namesPercentage" : 10,
		"appsPercentage" : 10
	};

	$scope.next = function(query) {
		var url = 'rest/generator/next';
		var sep = '?';

		if (query.gender != '') {
			url = url + "?gender=" + query.gender;
			sep = '&';
		}
		if (query.dniPattern != 'undefined') {
			url = url + sep + "dniPattern=" + query.dniPattern;
			sep = '&';
		}

		url = url + sep + "namesPercentage=" + query.namesPercentage; 

		url = url + sep + "appsPercentage=" + query.appsPercentage; 

		$http.get(url).success(function(data) {
			$scope.person = data;
		});
	}
	
	$scope.copy = function(person) {
		var text = person.name + " " + person.app1 + " " + person.app2 + " " + person.dni;
		window.prompt("Copy to clipboard: Ctrl+C, Enter", text);
	}
});