// main app module
var app =angular.module('main',['ngRoute']);

// module configuration
app.config(function ($routeProvider,$locationProvider){

  $routeProvider.when('/',{

    templateUrl:'./view/login.html',
    controller:'loginController'

  }).when('/signup',{

    templateUrl:'./view/signup.html',
    controller:'signupController'

  }).otherwise({

    template:'404'

  });

  $locationProvider.html5Mode(true);

});

// signupController controller for signup.html
app.controller('signupController',function($scope, $location, $http){
  $scope.signup = function() {
    var name = $scope.name;
    var email = $scope.email;
    var password = $scope.password;

    console.log(name);
    console.log(email);
    console.log(password);

    $http({
      url: 'http://localhost:8080/server/signup',
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data: 'name='+name+'&email='+email+'&password='+password
    }).then(function(response) {
      console.log(response.data);
    });
  }
  $scope.goToLogin = function() {
    $location.path('/');
  }
});

// loginController controller for login.html
app.controller('loginController',function ($scope, $location, $http){

  $scope.login = function() {

    var email = $scope.email;
    var password = $scope.password;

    console.log(email);
    console.log(password);

    $http({
    		url: 'http://localhost:8080/server/login',
    		method: 'POST',
    		headers: {
    			'Content-Type': 'application/x-www-form-urlencoded'
    		},
    		data: 'email='+email+'&password='+password
    }).then(function(response) {
        console.log(response.data);
    })
  }

  $scope.goToSignup = function () {
    $location.path('/signup');
  }
});
