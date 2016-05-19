(function() {
    'use strict';

    var resourceCtrl = function($http) {
        console.log("resource module initialized");
        var ctrl = this;
        ctrl.editMode = false;

        ctrl.newresource = {
            name: "",
            description: ""
        };
        ctrl.getresourceList = function() {
            // Simple GET request example:
            $http({
                method: 'GET',
                url: '/rest/resources'
            }).then(function successCallback(response) {
                console.log("success")
                ctrl.resources = null;
                ctrl.resources = response.data;
                console.log(ctrl.resources);
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error", response);
            });
        }
        ctrl.getresourceList();
        ctrl.updateresource = function(resource) {
            console.log("PUT", resource);
            var data = {
                name: resource.name,
                description: resource.description,
                id: resource.id,
                editMode: false

            }
            $http.put("/rest/resources/" + resource.id, data).then(function(res) {
                ctrl.getresourceList();
            });
        }
        ctrl.deleteresource = function(resource) {
            console.log("DELETE", resource);
            $http.delete("/rest/resources/" + resource.id).then(function(res) {
                ctrl.getresourceList();
            });
        }

        ctrl.addresource = function(resource) {
            // Simple GET request example:

            $http({
                method: 'POST',
                url: '/rest/resources',
                data: {
                    name: ctrl.newresource.name,
                    description: ctrl.newresource.description
                }
            }).then(function successCallback(response) {
                console.log(response);
                ctrl.getresourceList();
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error", response);
            });
        }

        ctrl.toggleEdit = function(resource) {
          resource.editMode = !resource.editMode;
          console.log(ctrl.editMode);
        }
        return ctrl;
    }

    angular.module("resourceModule").controller('resourceCtrl', ["$http", resourceCtrl]);
})();
