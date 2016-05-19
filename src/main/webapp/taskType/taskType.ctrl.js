(function() {
    'use strict';

    var taskTypeCtrl = function($http) {
        console.log("taskType module initialized");
        var ctrl = this;
        ctrl.editMode = false;
        ctrl.selectedPriority = {};
        ctrl.priorities = [{
            "name": "BELOW_NORMAL"
        }, {
            "name": "NORMAL"
        }, {
            "name": "ABOVE_NORMAL"
        }, {
            "name": "URGENT"
        }];
        ctrl.newtaskType = {
            name: "",
            priority: "",
        };
        ctrl.gettaskTypeList = function() {
            // Simple GET request example:
            $http({
                method: 'GET',
                url: '/rest/taskTypes'
            }).then(function successCallback(response) {
                console.log("success")
                ctrl.taskTypes = null;
                ctrl.taskTypes = response.data;
                console.log(ctrl.taskTypes);
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error", response);
            });
        }
        ctrl.gettaskTypeList();
        ctrl.updatetaskType = function(taskType) {
            console.log("PUT", taskType);
            var data = {
                name: taskType.name,
                priority: taskType.priority,
                id: taskType.id,
                editMode: false

            }
            $http.put("/rest/taskTypes/" + taskType.id, data).then(function(res) {
                ctrl.gettaskTypeList();
            });
        }
        ctrl.deletetaskType = function(taskType) {
            console.log("DELETE", taskType);
            $http.delete("/rest/taskTypes/" + taskType.id).then(function(res) {
                ctrl.gettaskTypeList();
            });
        }

        ctrl.addtaskType = function(taskType) {
            // Simple GET request example:
            console.log(ctrl.selectedPriority);
            $http({
                method: 'POST',
                url: '/rest/taskTypes',
                data: {
                    name: ctrl.newtaskType.name,
                    priority: ctrl.selectedPriority.name
                }
            }).then(function successCallback(response) {
                console.log(response);
                ctrl.gettaskTypeList();
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error", response);
            });
        }

        ctrl.toggleEdit = function(taskType) {
            taskType.editMode = !taskType.editMode;
            console.log(ctrl.editMode);
        }
        return ctrl;
    }

    angular.module("taskTypeModule").controller('taskTypeCtrl', ["$http", taskTypeCtrl]);
})();
