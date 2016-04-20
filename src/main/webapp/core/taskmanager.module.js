angular
		.module(
				"taskManager",
				[ "ngMaterial", "ngAnimate", "ngMessages", "ui.router",
						"loginModule","userModule" ])
		.config(
				function($mdThemingProvider, $stateProvider, $urlRouterProvider) {
					$mdThemingProvider.theme('altTheme').primaryPalette(
							'blue-grey');
					$mdThemingProvider.setDefaultTheme('altTheme');

					$stateProvider.state('login', {
						url : "/",
						templateUrl : "./login/login.view.html"
					});

					$stateProvider.state('users', {
						url : "/users",
						templateUrl : "./user/user.view.html"
					});

					$urlRouterProvider.otherwise("/");
				});