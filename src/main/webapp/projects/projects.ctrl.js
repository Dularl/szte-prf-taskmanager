(function() {
	'use strict';

	var projectsCtrl = function($http) {
		console.log("project module initialized");
		var ctrl = this;
		console.log(ctrl);

		return ctrl;
	}

	angular.module("projectsModule").controller('projectsCtrl',
			[ "$http", projectsCtrl ]);
})();
