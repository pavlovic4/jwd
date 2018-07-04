var wafepaApp = angular.module("wafepa", ['ngRoute']);

wafepaApp.controller("ctrl", function($scope){
	
	$scope.text = "WELCOME TO RENT A CAR APPLICATION!"
	
});

wafepaApp.controller("automobiliCtrl", function($scope, $http, $location){
	
	var baseUrl = "/api/automobili";
	var baseUrlKompanije = "/api/kompanije";
	
	$scope.kompanije = [];
	$scope.automobili = [];
	
	$scope.noviAutomobil = {};
	$scope.noviAutomobil.model = "";
	$scope.noviAutomobil.registracija = "";
	$scope.noviAutomobil.godiste = "";
	$scope.noviAutomobil.potrosnja = "";
	$scope.noviAutomobil.iznajmljen = false;
	$scope.noviAutomobil.kompanijaId = 0;
	
	$scope.search = {};
	$scope.search.model = "";
	$scope.search.minGod = "";
	$scope.search.maxPot = "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	var getAutomobili = function(){
		
		var config = { params : {}};
		
		if ($scope.search.model != "") {
			config.params.model = $scope.search.model;
		}
		if ($scope.search.minGod != "") {
			config.params.minGod = $scope.search.minGod;
		}
		if ($scope.search.maxPot != "") {
			config.params.maxPot = $scope.search.maxPot;
		}
		
		config.params.page = $scope.pageNum;
		
		$http.get(baseUrl, config).then(
			function success(res){
				$scope.automobili = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(res){
				alert("Error in function getAutomobili!");
			}
		);
	}
	
	var getKompanije = function(){
		$http.get(baseUrlKompanije).then(
			function success(res){
				$scope.kompanije = res.data;
			},
			function error(res){
				alert("Error in function getKompanije!");
			}
		);
	}
	
	getKompanije();
	getAutomobili();
	
	$scope.add = function(){
		var promise = $http.post(baseUrl, $scope.noviAutomobil);
		promise.then(
			function success(res){
				getAutomobili();
				$scope.noviAutomobil.model = "";
				$scope.noviAutomobil.registracija = "";
				$scope.noviAutomobil.godiste = "";
				$scope.noviAutomobil.potrosnja = "";
				$scope.noviAutomobil.kompanijaId = 0;
			},
			function error(res){
				alert("Error in function add!");
			}
		);
	}
	
	$scope.iznajmi = function(automobil_id){
		var promise = $http.post(baseUrl + "/" + automobil_id);
		promise.then(
			function success(res){
				getAutomobili();
			},
			function error(res){
				alert("Error in function iznajmi!");
			}
		);
	}
	
	$scope.deleteAutomobil = function(id){
		var promise = $http.delete(baseUrl + "/" + id);
		promise.then(
			function success(res){
				getAutomobili();
			},
			function error(res){
				alert("Error in function delete!");
			}
		)
	}
	
	$scope.goToEdit = function(id){
		$location.path("/automobili/edit/" + id);
	}
	
	$scope.go = function(direction){
		$scope.pageNum += direction;
		getAutomobili();
	}
	
	$scope.search2 = function(){
		$scope.pageNum = 0;
		getAutomobili();
	}
	
	$scope.empty = function(){
		$scope.search.model = "";
		$scope.search.minGod = "";
		$scope.search.maxPot = "";
		getAutomobili();
	}
	
	$scope.goToInfo = function(id){
		$location.path("/kompanije/" + id);
	}
});

wafepaApp.controller("kompanijeCtrl", function($scope, $http){
	
	var baseUrl = "/api/kompanije";
	
	$scope.kompanije = [];
	
	var getKompanije = function(){
		$http.get(baseUrl).then(
			function success(res){
				$scope.kompanije = res.data;
			},
			function error(res){
				alert("Error in function getKompanije!");
			}
		);
	}
	
	getKompanije();

});

wafepaApp.controller("kompanijaCtrl", function($scope, $http, $routeParams){
	
	var baseUrl = "/api/kompanije/";
	var id = $routeParams.kid;
	
	$scope.kompanija = {};
	
	var getKompanija = function(){
		$http.get(baseUrl + id).then(
			function success(res){
				$scope.kompanija = res.data;
			},
			function error(res){
				alert("Error in function getKompanija!");
			}
		);
	}
	
	getKompanija();

});

wafepaApp.controller("editAutomobilCtrl", function($scope, $routeParams, $http, $location){
	
	var baseUrl = "/api/automobili/";
	var id = $routeParams.aid;
	
	$scope.stariAutomobil = {};
	$scope.stariAutomobil.model = "";
	$scope.stariAutomobil.registracija = "";
	$scope.stariAutomobil.godiste = "";
	$scope.stariAutomobil.potrosnja = "";
	$scope.stariAutomobil.iznajmljen = false;
	$scope.stariAutomobil.kompanijaId = 0;
	
	var getAutomobil = function(){
		$http.get(baseUrl + id).then(
			function success(res){
				$scope.stariAutomobil = res.data;
			},
			function error(res){
				alert("Error in function getAutomobili!");
			}
		);
	}
	
	getAutomobil();
	
	$scope.edit = function(){
		$http.put(baseUrl + id, $scope.stariAutomobil).then(
			function success(res){
				$location.path('/automobili');
			},
			function error(res){
				alert("Error in function edit!");
			}
		);
	}
});


wafepaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl:'/app/html/home.html'
		})
		.when('/automobili', {
			templateUrl:'/app/html/automobili.html'
		})
		.when('/kompanije', {
			templateUrl:'/app/html/kompanije.html'
		})
		.when('/kompanije/:kid', {
			templateUrl:'/app/html/kompanija.html'
		})
		.when('/automobili/edit/:aid', {
			templateUrl:'/app/html/edit-automobil.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);