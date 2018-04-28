// main app module
var app =angular.module('main',['ngRoute']);

// module configuration
app.config(function ($routeProvider){

  $routeProvider.when('/',{

    resolve: {
      // check if user is already logged in than user can't login again
      check: function($location, user){
        if(!!user.isUserLoggedIn()){
          $location.path('/dashboard');
        }
      },

    },
    // if user is not login than only login page will be accessible
    templateUrl:'./view/login.html',
    controller:'loginController'

  }).when('/signup',{

    resolve: {

      check: function($location, user){
        if(!!user.isUserLoggedIn()){
          $location.path('/dashboard');
        }
      },

    },
    templateUrl:'./view/signup.html',
    controller:'signupController'

  }).when('/dashboard', {

    resolve: {
      // check if user is loggedin than only can access to dashboard
      check: function($location, user){
        if(!user.isUserLoggedIn()){
          $location.path('/');
        }
      },

    },

    templateUrl:'./view/dashboard.html',
    controller:'dashboardController'

  }).when('/logout', {
    resolve: {
      deadResolve:function($location, user){
        user.clearProfile();
        $location.path('/');
      }
    }
  }).otherwise({

        templateUrl:'./view/404.html',
        controller:'errorPageController'

  });

  // $locationProvider.html5Mode(true);

});

// service for user login
app.service('user', function($http){
  var name;
  var loggedin = false;
  var email;
  var id;

  this.getName = function () {
    return name;
  };

  this.getEmail = function () {
    return email;
  };

  this.getId = function () {
    return id;
  };

  this.isUserLoggedIn = function () {
    if(!!localStorage.getItem('login')) {
      loggedin = true;
      var profile = JSON.parse(localStorage.getItem('login'));
      name = profile.name;
      email = profile.email;
      id = profile.id;
    }
    return loggedin;
  };

  this.saveProfile = function(profile_){
    loggedin = true;
    name = profile_.name;
    email = profile_.email;
    id = profile_.id;

    localStorage.setItem('login',JSON.stringify({
      name: name,
      id: id,
      email: email,
    }));

  };

  this.clearProfile = function () {
    localStorage.removeItem('login');
    name = "";
    email = "";
    id = "";
    loggedin = false;
  };
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
      if(response.data.status == "true") {
        // signup success
        $location.path('/');
      } else if (response.data.status == "false") {
        // user already exist
        $location.path('/signup');
        alert('User already exist.');
      } else {
        //server error
      }
    });
  }
  $scope.goToLogin = function() {
    $location.path('/');
  }
});

// loginController controller for login.html
app.controller('loginController',function ($scope, $location, $http, user){

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
      if(response.data.status == "true") {
        var profile =response.data.profile;

        user.saveProfile(profile);

        $location.path('/dashboard');
      } else if(response.data.status == "false") {
        // Invalid login
        $location.path('/');
        alert('Invalid login');
      } else {
        // Server error
      }
    })
  }

  $scope.goToSignup = function () {
    $location.path('/signup');
  }
});

// dashboardController for dashboard.html
app.controller('dashboardController',function($scope, $location, $http, $interval, user){
  $scope.name = user.getName();
  $scope.email = user.getEmail();
  $scope.id = user.getId();
  $scope.favourites=[];
  $interval(function(){
    $location.path('/');
  },300000);
  $http.get('https://api.coinmarketcap.com/v1/ticker/?limit=0').then(function(response){
    $scope.allCoin =  response.data;
  });

  $http.get('http://localhost:8080/server/favourites?email='+$scope.email).then(function(response) {
    var dump = response.data.favourites;
    for(i = 0; i< dump.length; i++) {

      $http.get('https://api.coinmarketcap.com/v1/ticker/'+dump[i]+'/').then(function(resp){
        $scope.favourites.push(resp.data[0]);
      });

    }
  });

  $http.get('https://api.coinmarketcap.com/v1/global/').then(function(response) {
    $scope.global = response.data;
  });

  $scope.addFavourite = function() {

    $http({
      url:'http://localhost:8080/server/favourites',
      method:'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data: 'email='+$scope.email+'&item='+$scope.favCoin
    }).then(function(response) {

    $location.path('/');

    });
  };

  $scope.logout = function () {
    $location.path('/logout');
  };

});

app.controller('errorPageController', function($scope, $location) {
  $scope.goBack = function() {
    $location.path('/');
  }
});


// END of Script
