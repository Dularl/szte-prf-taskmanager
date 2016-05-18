(function() {
    'use strict';

    var userCtrl = function($http) {
        console.log("user module initialized");
        var ctrl = this;

        console.log(ctrl);

        ctrl.getUserList = function() {
            // Simple GET request example:
            $http({
                method: 'GET',
                url: '/users/getUsers'
            }).then(function successCallback(response) {
                console.log("success")
                ctrl.users = null;
                ctrl.users = response.data;
                console.log(ctrl.users);
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error", response);
            });
        }
        ctrl.getUserList();
        ctrl.updateUser = function(user) {
            console.log("PUT", user);
            //call $http.put here
        }
        ctrl.deleteUser = function(user) {
            console.log("DELETE", user);
            $http.delete("/users/" + user.id).then(function(res) {
							  ctrl.getUserList();
            });
        }
        return ctrl;
    }

    angular.module("userModule").controller('userCtrl', ["$http", userCtrl]);
})();
