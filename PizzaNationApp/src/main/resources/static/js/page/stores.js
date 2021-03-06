//Step 1: initialize communication with the platform
var platform = new H.service.Platform({
    app_id: '4uBDFwxDej2pVxzIRZ9g',
    app_code: 'S7Z-EDOuFdxm9qjz9Tsy5w',
    useHTTPS: true
});
var pixelRatio = window.devicePixelRatio || 1;
var defaultLayers = platform.createDefaultLayers({
    tileSize: pixelRatio === 1 ? 256 : 512,
    ppi: pixelRatio === 1 ? undefined : 320
});

//Step 2: initialize a map  - not specificing a location will give a whole world view.
var map = new H.Map(document.getElementById('map'), defaultLayers.normal.map, {pixelRatio: pixelRatio});
var group = new H.map.Group();
map.addObject(group);

var exampleShopCoordinates = {lat: 42.1456, lng: 24.6722};
addMarkerToMap(exampleShopCoordinates);

//Step 4: make the map interactive
// MapEvents enables the event system
// Behavior implements default interactions for pan/zoom (also on mobile touch environments)
var behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(map));

// Create the default UI components
var ui = H.ui.UI.createDefault(map, defaultLayers);

moveMap(exampleShopCoordinates, 5);

function moveMap(markerJson, zoom) {
    map.setCenter(markerJson);
    map.setZoom(zoom);
}

function addMarkerToMap(markerJson) {
    group.removeAll();
    let markerObject = new H.map.Marker(markerJson);
    group.addObject(markerObject);
}