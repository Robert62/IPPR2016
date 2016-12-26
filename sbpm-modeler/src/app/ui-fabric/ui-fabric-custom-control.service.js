(function () {
    'use strict';

    angular.module('ui.fabric')
        .service('fabricCustomControl', fabricCustomControl);

    /** @ngInject */
    function fabricCustomControl($log, modeler, $compile, $rootScope) {
        const TAG = "ui-fabric-custom-service.service: ";

        var service = this;

        service.init = function () {

            $log.debug(TAG + 'init()');

        };

        var createId = function () {
            return '_' + Math.random().toString(36).substr(2, 9)
        };

        var createCustomControlDiv = function (customControlId) {
            return "<div id='" + customControlId + "' style='width: 100px; height: 100px; background-color: #00b3ee; " +
                "z-index: 9999; position: absolute;'><md-button class='md-icon-button md-primary' aria-label='Delete' ng-click='test()'><md-icon>delete</md-icon></md-button></div>";
        };

        var createCustomControl = function (subjectElement) {
            var customControlId = 'customControl' + createId();
            modeler.addCustomControl(subjectElement.get('id'), customControlId);

            var scope = $rootScope.$new();
            scope.test = function () {
              $log.debug("Test");
            };
            angular.element('#modeler-board').append($compile(createCustomControlDiv(customControlId))(scope));
            jQuery("#" + customControlId).hide();
            positionCustomControl(subjectElement, customControlId);
        };

        var positionCustomControl = function (subjectElement, customControlId) {
            jQuery("#" + customControlId).css({top: subjectElement.getTop() + 'px'});
            jQuery("#" + customControlId).css({left: (subjectElement.getLeft() + subjectElement.getWidth() + 20) + 'px'});
        };

        service.addCustomControl = function (subjectElement) {
            $log.debug(TAG + 'addCustomControl()');
            createCustomControl(subjectElement);
        };

        service.positionCustomControl = function (subjectElement) {
            $log.debug(TAG + 'positionCustomControl()');
            var customControlId = modeler.getCustomControlId(subjectElement.get('id'));
            positionCustomControl(subjectElement, customControlId);
        };

        service.setCustomControlVisibility = function (subjectElement, hide) {
            $log.debug(TAG + 'setCustomControlVisibility()');
            var customControlId = modeler.getCustomControlId(subjectElement.get('id'));

            if (hide) {
                jQuery('#' + customControlId).hide()
            } else {
                jQuery('#' + customControlId).show()
            }
        };

        service.init();

        return service;

    }

})();
