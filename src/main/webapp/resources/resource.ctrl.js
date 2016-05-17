(function() {
	'use strict';

	var resourceCtrl = function($http) {
		console.log("user module initialized");
		var ctrl = this;

		console.log(ctrl);

		ctrl.getUserList = function() {
			// Simple GET request example:
			$http({
				method : 'GET',
				url : '/users/getResources'
			}).then(function successCallback(response) {
				console.log("success",response)
				ctrl.resources = null;
				ctrl.resources = response.data;
				console.log(ctrl.resources);
			}, function errorCallback(response) {
				// called asynchronously if an error occurs
				// or server returns response with an error status.
				console.log("error", response);
			});
		}
		ctrl.getUserList();
		return ctrl;
	}

	angular.module("resourceModule").controller('resourceCtrl', [ "$http", resourceCtrl ]);
})();
