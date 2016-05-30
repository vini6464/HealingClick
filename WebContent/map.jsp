<!-- <!DOCTYPE html>
<html>
  <head>
    <title>Geolocation</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <style>
      html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>

    <script>
// Note: This example requires that you consent to location sharing when
// prompted by your browser. If you see a blank space instead of the map, this
// is probably because you have denied permission for location sharing.

var map;

function initialize() {
  var mapOptions = {
    zoom: 6
  };
  map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);

  // Try HTML5 geolocation
  if(navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      var pos = new google.maps.LatLng(position.coords.latitude,
                                       position.coords.longitude);

      var infowindow = new google.maps.InfoWindow({
        map: map,
        position: pos,
        content: 'Location found.'
      });

      map.setCenter(pos);
    }, function() {
      handleNoGeolocation(true);
    });
  } else {
    // Browser doesn't support Geolocation
    handleNoGeolocation(false);
  }
}

function handleNoGeolocation(errorFlag) {
  if (errorFlag) {
    var content = 'Error: The Geolocation service failed.';
  } else {
    var content = 'Error: Your browser doesn\'t support geolocation.';
  }

  var options = {
    map: map,
    position: new google.maps.LatLng(60, 105),
    content: content
  };

  var infowindow = new google.maps.InfoWindow(options);
  map.setCenter(options.position);
}

google.maps.event.addDomListener(window, 'load', initialize);

    </script>
  </head>
  <body>
    <div id="map-canvas"></div>
  </body>
</html> -->


 <html>
     <body>
      <p>Blah</p>
      <div id="map_canvas" style="width:425px; height:550px"></div> 
      <p>Blah</p>

      <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script> 
      <script type="text/javascript"> 

        var map;
        var markers = [];

        initialize();

        function initialize() {
          var myOptions = {
            zoom: 10,
            center: new google.maps.LatLng(100.090, -100.536),
            mapTypeId: google.maps.MapTypeId.ROADMAP
          }
          map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

          addMarker(new google.maps.LatLng(100.0747, -100.6274), "Alien");
          addMarker(new google.maps.LatLng(100.0758, -100.6254), "Zombie");
          addMarker(new google.maps.LatLng(100.0721, -100-6292), "Vampire");

        }

        function addMarker(latlng, myTitle) {
          markers.push(new google.maps.Marker({
            position: latlng, 
            map: map,
            title: myTitle,
            icon: "http://maps.google.com/mapfiles/marker" + String.fromCharCode(markers.length + 65) + ".png"
          }));    
        }

      </script> 
    </body>
  </html>