(function() {
    'use strict';

    var projectCtrl = function($http) {
        console.log("project module initialized");
        var ctrl = this;
        ctrl.editMode = false;
        
        ctrl.newProject = {
            name: "",
            description: ""
        };
        ctrl.getprojectList = function() {
            // Simple GET request example:
            $http({
                method: 'GET',
                url: '/rest/projects'
            }).then(function successCallback(response) {
                console.log("success")
                ctrl.projects = null;
                ctrl.projects = response.data;
                console.log(ctrl.projects);
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error", response);
            });
        }
        ctrl.getprojectList();
        ctrl.updateproject = function(project) {
            console.log("PUT", project);
            var data = {
                name: project.name,
                description: project.description,
                id: project.id,
                editMode: false

            }
            $http.put("/rest/projects/" + project.id, data).then(function(res) {
                ctrl.getprojectList();
            });
        }
        ctrl.deleteproject = function(project) {
            console.log("DELETE", project);
            $http.delete("/rest/projects/" + project.id).then(function(res) {
                ctrl.getprojectList();
            });
        }

        ctrl.addproject = function(project) {
            // Simple GET request example:

            $http({
                method: 'POST',
                url: '/rest/projects',
                data: {
                    name: ctrl.newProject.name,
                    description: ctrl.newProject.description
                }
            }).then(function successCallback(response) {
                console.log(response);
                ctrl.getprojectList();
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error", response);
            });
        }

        ctrl.toggleEdit = function(project) {
          project.editMode = !project.editMode;
          console.log(ctrl.editMode);
        }
        return ctrl;
    }

    angular.module("projectModule").controller('projectCtrl', ["$http", projectCtrl]);
})();
