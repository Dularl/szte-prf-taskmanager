(function() {
    'use strict';

    var menuCtrl = function($location) {
        var ctrl = this;
        console.log("menu module initialized");

				ctrl.goToUsersPage = function() {
          console.log("LF");
            $location.path("users");
        }

        ctrl.goToResourcesPage = function() {
          console.log("LF");
            $location.path("resources");
        }

        ctrl.navigate = function(path) {
          console.log("LF");
            $location.path(path);
        }

    

    }

    angular.module("menuModule").controller('menuCtrl', ["$location", menuCtrl]);
})();
