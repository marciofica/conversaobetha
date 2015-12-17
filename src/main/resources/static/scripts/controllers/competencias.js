'use strict';

angular.module('conversaoApp')
  .controller('CompetenciasCtrl', function ($scope,$http) {
    $scope.title = "Serviço de competências";
    $scope.url = "http://e-gov.betha.com.br/arrecadacao-conversao-service/rest/conversao/competencias";
    $scope.arquivo = "C:\\conversao\\competencias\\competencias.json";
    $scope.retorno = "Retorno do JSON";
    $scope.hash = "";

    $http({
        method: 'GET',
        url: '/competencias/enviar'
    }).then(function successCallback(response) {
        $scope.retorno = JSON.stringify(response.data);
        $scope.hash = response.data.data.hash;
    }, function errorCallback(response) {
        $scope.retorno = JSON.stringify(response.data);
    });

    $scope.consultarHash = function(hash){
        $http({
            method: 'GET',
            url: '/competencias/consultar/' + hash
        }).then(function successCallback(response) {
            $scope.retorno = JSON.stringify(response.data);
        }, function errorCallback(response) {
            $scope.retorno = JSON.stringify(response.data);
        });
    }

  });
