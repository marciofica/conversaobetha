'use strict';

/**
 * @ngdoc overview
 * @name conversaoApp
 * @description
 * # conversaoApp
 *
 * Main module of the application.
 */
angular
  .module('conversaoApp', [
    'ngAnimate',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/servicos.html',
        controller: 'ServicosCtrl'
      })
      .when('/competencias', {
        templateUrl: 'views/competencias.html',
        controller: 'CompetenciasCtrl'
      })
        .when('/prestadores', {
          templateUrl: 'views/prestadores.html',
          controller: 'PrestadoresCtrl'
        })
      .otherwise({
        redirectTo: '/'
      });
  });
