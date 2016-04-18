(function() {
	'use strict';

	var loginCtrl = function($http) {
		console.log("login module initialized");
		var ctrl = this;
		
		ctrl.email = "";
		ctrl.password = "";
		ctrl.login = function() {
			console.log(ctrl.email, ctrl.password);
		};
		console.log(ctrl);

		return ctrl;
	}

	angular.module("loginModule").controller('loginCtrl',
			[ "$http", loginCtrl ]);
})();