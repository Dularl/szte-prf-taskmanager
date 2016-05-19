angular
    .module(
        "taskManager", ["ngMaterial", "ngAnimate", "ngMessages", "ui.router",
            "loginModule", "userModule", "menuModule", "resourceModule", "projectModule","taskModule","taskTypeModule"
        ])
    .config(
        function($mdThemingProvider, $stateProvider, $urlRouterProvider) {
            $mdThemingProvider.theme('altTheme').primaryPalette(
                'blue-grey');
            $mdThemingProvider.setDefaultTheme('altTheme');

            $stateProvider.state('login', {
                url: "/",
                templateUrl: "./login/login.view.html"
            });

            $stateProvider.state('users', {
                url: "/users",
                templateUrl: "./user/user.view.html"
            });

            $stateProvider.state('projects', {
                url: "/projects",
                templateUrl: "./projects/projects.view.html"
            });

            $stateProvider.state('resources', {
                url: "/resources",
                templateUrl: "./resources/resource.view.html"
            });

            $stateProvider.state('membership', {
                url: "/membership-user",
                templateUrl: "./user/user-member.view.html"
            });


            $stateProvider.state('tasks', {
                url: "/tasks",
                templateUrl: "./task/task.view.html"
            });

            $stateProvider.state('taskType', {
                url: "/taskTypes",
                templateUrl: "./taskType/taskType.view.html"
            });

          /*  $stateProvider.state('tasks', {
                url: "/tasks",
                templateUrl: "./task/task.view.html"
            });*/

            $urlRouterProvider.otherwise("/");
        });
