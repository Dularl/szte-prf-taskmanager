angular.module("taskManager",
		[ "ngMaterial", "ngAnimate", "ngMessages", "loginModule" ]).config(
		function($mdThemingProvider) {
			$mdThemingProvider.theme('altTheme').primaryPalette('blue-grey');
			$mdThemingProvider.setDefaultTheme('altTheme');
		});