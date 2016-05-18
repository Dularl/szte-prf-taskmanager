angular
		.module(
				"taskManager",
				[ "ngMaterial", "ngAnimate", "ngMessages", "ui.router",
						"loginModule","userModule","menuModule","resourceModule" ])
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

					$stateProvider.state('home', {
						url : "/projects",
						templateUrl : "./projects/projects.view.html"
					});

					$stateProvider.state('resources', {
						url : "/resources",
						templateUrl : "./resources/resource.view.html"
					});

					$urlRouterProvider.otherwise("/");
				});
