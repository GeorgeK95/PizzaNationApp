function initMap() {

    /*<![CDATA[*/
    var lat = /*[[${lat}]]*/ 36.114757;
    var long = /*[[${long}]]*/ -115.172937;
    /*]]>*/

    var myCenter = new google.maps.LatLng(42.161177, 24.737454);
    var mapCanvas = document.getElementById("map");
    var mapOptions = {center: myCenter, zoom: 10};
    var map = new google.maps.Map(mapCanvas, mapOptions);
    var marker = new google.maps.Marker({position: myCenter});
    marker.setMap(map);
    google.maps.event.addListener(marker, 'click', function () {
        var infowindow = new google.maps.InfoWindow({
            content: "Pari nema deistvaite!!!"
        });
        infowindow.open(map, marker);
    });
}