(function() {
    'use strict';

    var userCtrl = function($http) {
        console.log("user module initialized");
        var ctrl = this;
        ctrl.editMode = false;
        ctrl.selectedRole = {};
        ctrl.selectedProject = {};
        ctrl.selectedUser = {};
        console.log(ctrl);
        ctrl.roles = [{
            "name": "MANAGER"
        }, {
            "name": "EMPLOYEE"
        }, {
            "name": "ADMIN"
        }];
        ctrl.newUser = {
            name: "",
            email: "",
            role: ""
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
        ctrl.getUserList = function() {
            // Simple GET request example:
            $http({
                method: 'GET',
                url: '/rest/users'
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

        ctrl.getUserPerProject = function() {
            ///rest/projects/{projectId}/members
            // Simple GET request example:
            $http({
                method: 'GET',
                url: '/rest/projects/' + ctrl.selectedProject.id + '/members'
            }).then(function successCallback(response) {
                console.log("success")
                ctrl.projectMembers = null;
                ctrl.projectMembers = response.data;
                console.log(ctrl.projectMembers);
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error", response);
            });
        }

        ctrl.assignUser = function() {
            ///rest/projects/{projectId}/members
            // Simple GET request example:
            $http({
                method: 'POST',
                url: '/rest/projects/' + ctrl.selectedProject.id + '/members/' + ctrl.selectedUser.id
            }).then(function successCallback(response) {

                ctrl.getUserPerProject()
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error", response);
            });
        }

        ctrl.unassignUser = function() {
            ///rest/projects/{projectId}/members
            // Simple GET request example:
            $http({
                method: 'DELETE',
                url: '/rest/projects/' + ctrl.selectedProject.id + '/members/' + ctrl.selectedUser.id
            }).then(function successCallback(response) {

                ctrl.getUserPerProject()
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error", response);
            });
        }


        ctrl.updateUser = function(user) {
            console.log("PUT", user);
            var data = {
                name: user.name,
                email: user.email,
                role: user.role,
                id: user.id,
                editMode: false

            }
            $http.put("/rest/users/" + user.id, data).then(function(res) {
                ctrl.getUserList();
            });
        }
        ctrl.deleteUser = function(user) {
            console.log("DELETE", user);
            $http.delete("/rest/users/" + user.id).then(function(res) {
                ctrl.getUserList();
            });
        }

        ctrl.addUser = function(user) {
            // Simple GET request example:

            $http({
                method: 'POST',
                url: '/rest/users',
                data: {
                    name: ctrl.newUser.name,
                    email: ctrl.newUser.email,
                    role: ctrl.selectedRole.name
                }
            }).then(function successCallback(response) {
                console.log(response);
                ctrl.getUserList();
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error", response);
            });
        }

        ctrl.toggleEdit = function(user) {
            user.editMode = !user.editMode;
            console.log(ctrl.editMode);
        }

        return ctrl;
    }

    angular.module("userModule").controller('userCtrl', ["$http", userCtrl]);
})();
