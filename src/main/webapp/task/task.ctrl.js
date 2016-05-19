(function() {
    'use strict';

    var taskCtrl = function($http) {
        console.log("task module initialized");
        var ctrl = this;
        ctrl.editMode = false;
        ctrl.selectedUser = {
            name: "",
            email: "",
            role: "",
            id: ""
        };
        ctrl.selectedProject = {
            name: "",
            description: ""
        };

        ctrl.selectedTaskType = {}

        ctrl.selectedStatus = {}


        ctrl.gettaskTypeList = function() {
            // Simple GET request example:
            $http({
                method: 'GET',
                url: '/rest/taskTypes'
            }).then(function successCallback(response) {
                console.log("success")
                ctrl.TaskTypes = null;
                ctrl.TaskTypes = response.data;
                console.log(ctrl.TaskTypes);
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error", response);
            });
        }

        ctrl.statuses = [{
            "name": "ASSIGNED"
        }, {
            "name": "IN_PROGRESS"
        }, {
            "name": "DONE"
        }];
        ctrl.gettaskTypeList();

        ctrl.getUserList = function() {
            // Simple GET request example:
            console.log("asd")
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

        ctrl.getProjectList = function() {
            // Simple GET request example:
            console.log("asd")
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
        ctrl.newTask = {
            name: "",
            description: "",
            status: "",
            assignee: "",
            type: "",
            deadline: "",
            project: "",
            creationDate: "",
            modificationDate: "",
            progress: ""
        };
        ctrl.getUserList();
        ctrl.getProjectList();
        ctrl.gettaskList = function() {
            // Simple GET request example:
            $http({
                method: 'GET',
                url: '/rest/tasks'
            }).then(function successCallback(response) {
                console.log("success", response);
                ctrl.tasks = response.data;

            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error", response);
            });
        }

        ctrl.gettaskList();
        ctrl.updatetask = function(task) {
            console.log("PUT", task);
            var data = {
                name: task.name,
                description: task.description,
                status: task.status,
                assignee: task.assignee,
                type: task.selectedTaskType,
                deadline: task.deadline,
                project: task.project,
                creationDate: task.creationDate,
                modificationDate: task.modificationDate,
                progress: task.progress
            }
            $http.put("/rest/tasks/" + task.id, data).then(function(res) {
                ctrl.gettaskList();
            });
        }
        ctrl.deletetask = function(task) {
            console.log("DELETE", task);
            $http.delete("/rest/tasks/" + task.id).then(function(res) {
                ctrl.gettaskList();
            });
        }

        ctrl.addtask = function(task) {
            console.log(ctrl.selectedUser);
            $http({
                method: 'POST',
                url: '/rest/tasks',
                data: {
                    name: ctrl.newTask.name,
                    description: ctrl.newTask.description,
                    status: ctrl.selectedStatus.name,
                    assignee: ctrl.selectedUser,
                    type: ctrl.selectedTaskType,
                    deadline: ctrl.newTask.deadline,
                    project: ctrl.selectedProject,
                    creationDate: ctrl.newTask.creationDate,
                    modificationDate: ctrl.newTask.modificationDate,
                    progress: ctrl.newTask.progress
                }
            }).then(function successCallback(response) {
                console.log(response);
                ctrl.gettaskList();
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error", response);
            });
        }

        ctrl.toggleEdit = function(task) {
            task.editMode = !task.editMode;
            console.log(ctrl.editMode);
        }

        return ctrl;
    }

    angular.module("taskModule").controller("taskCtrl", ["$http", taskCtrl]);
})();
