'use strict';

angular.module('conversaoApp')
  .controller('PrestadoresCtrl', function ($scope,$http) {
    $scope.title = "Serviço de prestadores";
    $scope.url = "http://e-gov.betha.com.br/arrecadacao-conversao-service/rest/conversao/prestadores";
    $scope.arquivo = "C:\\conversao\\competencias\\prestadores.json";
    $scope.retorno = "Retorno do JSON";
    $scope.hash = "";

    $http({
        method: 'GET',
        url: '/prestadores/enviar'
    }).then(function successCallback(response) {
        $scope.retorno = JSON.stringify(response.data);
        $scope.hash = response.data.data.hash;
    }, function errorCallback(response) {
        $scope.retorno = JSON.stringify(response.data);
    });

    $scope.consultarHash = function(hash){
        $http({
            method: 'GET',
            url: '/prestadores/consultar/' + hash
        }).then(function successCallback(response) {
            $scope.retorno = JSON.stringify(response.data);
        }, function errorCallback(response) {
            $scope.retorno = JSON.stringify(response.data);
        });
    }

  });
