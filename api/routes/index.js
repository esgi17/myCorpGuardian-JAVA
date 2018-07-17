const RouteManager = function() { };
const publicRouteManager = require('./public');
const generalRouteManger = require('./general');
const controlsRouteManger = require('./controls');

RouteManager.attach = function(app) {
    generalRouteManger.attach(app);
    controlsRouteManger.attach(app);
    publicRouteManager.attach(app);
    app.use(require('./authenticate'));
}

module.exports = RouteManager;
