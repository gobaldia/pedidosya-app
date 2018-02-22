$(document).ready(function() {
    setLoggedUser();
    //getRestaurants(getCookie("userToken"), getUrlParameter("lat"), getUrlParameter("lng"));
    initializeMap();
});

function setLoggedUser() {
    var name = getCookie("loggedUserName");
    var lastName = getCookie("loggedUserLastName");

    $("#loggedUser").html("Bienvenido " + name + " " + lastName);
}

function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=");
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1) {
                c_end = document.cookie.length;
            }
            return unescape(document.cookie.substring(c_start, c_end));
        }
    }
    return "";
}

function getRestaurants(userToken, lat, lng) {
    var restaurants;
    var url;
    if (lat && lng) {
        url = "get-restaurants?lat=" + lat + "&lng=" + lng;
    }
    else {
        url = "get-restaurants";
    }

    $.ajax({
        async: false,
        type: "GET",
        url: url,
        beforeSend: function(xhr){xhr.setRequestHeader('Authorization', userToken);},
        success: function (data) {
            restaurants = data["data"];
        },
        error: function () {
            alert("error");
        }
    });
    return restaurants;
}


var geocoder = new google.maps.Geocoder();

function geocodePosition(pos) {
    geocoder.geocode({
        latLng: pos
    }, function(responses) {
        if (responses.length > 0) {
            updateMarkerAddress(responses[0].formatted_address);
        } else {
            updateMarkerAddress('Cannot determine address at this location.');
        }
    });
}

function updateMarkerStatus(str) {
    document.getElementById('markerStatus').innerHTML = str;
}

function updateMarkerPosition(latLng) {
    document.getElementById('info').innerHTML = [
        latLng.lat(),
        latLng.lng()
    ].join(', ');
}

function updateMarkerAddress(str) {
    document.getElementById('address').innerHTML = str;
}

function initializeMap() {
    var icon = {
        url: "https://lh3.googleusercontent.com/qmpu9eiTI5kWjySD8ShgjrNE77SZohGBqpbLNm90AS1ETvxICyhyKvNgRf8f98TYhQ=w300",
        scaledSize: new google.maps.Size(50, 50)
    };
    restaurants = getRestaurants(getCookie("userToken"), getUrlParameter("lat"), getUrlParameter("lng"));
    var locations = [];
    for (i = 0; i < restaurants.length; i++) {
        coord = parseCoordinates(restaurants[i]["coordinates"]);
        var latLng = new google.maps.LatLng(coord[0], coord[1]);
        locations.push(latLng)
    }

    var map = new google.maps.Map(document.getElementById('mapCanvas'), {
        zoom: 12,
        center: latLng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    for (i = 0; i < locations.length; i++) {
        var date = new Date(Date.now());
        var restaurantName = restaurants[i]["name"];
        var restaurantTopCategories = restaurants[i]["topCategories"];
        var restaurantRating = restaurants[i]["ratingScore"];
        var restaurantLogo = "https://d1v73nxuzaqxgd.cloudfront.net/restaurants/" + restaurants[i]["logo"];
        var maxArrivalTime = new Date(date.setMinutes(date.getMinutes() + parseInt(restaurants[i]["deliveryTimeMaxMinutes"])));
        var restaurantProfile = "http://www.pedidosya.com.uy/restaurantes/montevideo/" + restaurants[i]["link"] + "-menu";
        var content = '<div id="content">'+
            '<div id="siteNotice">'+
            '</div>'+
            '<img src="' + restaurantLogo + '">'+
            '<h4 id="firstHeading" class="firstHeading">' + restaurantName + '</h4>'+
            '<p id="firstHeading" class="firstHeading"> Categorías: ' + restaurantTopCategories + '</p>'+
            '<p id="firstHeading" class="firstHeading"> Hora máxima de llegada: ' + maxArrivalTime.getHours() + ":" + maxArrivalTime.getMinutes() + '</p>'+
            '<p id="firstHeading" class="firstHeading"> Calificación: ' + restaurantRating + '</p>'+
            '<a href="' + restaurantProfile + '">Ver restaurante</a>' +
            '</div>'+
            '</div>';

        // var infowindow = new google.maps.InfoWindow({
        //     content: contentString
        // });
        var marker = new google.maps.Marker({
            position: locations[i],
            icon: icon,
            map: map
        });

        /*google.maps.event.addListener('click', (function(marker, i) {
            return function() {
                infowindow.setContent("x");
                infowindow.open(map, marker);
            }
        })(marker, i));*/

        /*marker.addListener('click', function() {
            infowindow.open(map, marker);
        });*/

        var infowindow = new google.maps.InfoWindow();

        google.maps.event.addListener(marker,'click', (function(marker,content,infowindow){
            return function() {
                infowindow.setContent(content);
                infowindow.open(map,marker);
            };
        })(marker,content,infowindow));
    }

    /*for (i = 0; i < 2; i++) {
        var marker = new google.maps.Marker({
            position: arr[i],
            title: 'Point A',
            map: map,
            draggable: true
        });

        // Update current position info.

        updateMarkerPosition(arr[i]);
        geocodePosition(arr[i]);

        // Add dragging event listeners.

        google.maps.event.addListener(marker, 'dragstart', function() {
            updateMarkerAddress('Dragging...');
        });

        google.maps.event.addListener(marker, 'drag', function() {
            updateMarkerStatus('Dragging...');
            updateMarkerPosition(marker.getPosition());
        });

        google.maps.event.addListener(marker, 'dragend', function() {
            updateMarkerStatus('Drag ended');
            geocodePosition(marker.getPosition());
        });
    }*/

}

// Onload handler to fire off the app.

google.maps.event.addDomListener(window, 'load', initialize);

function parseCoordinates(coordinatesString) {
    var splitedCoordinates = coordinatesString.split(",");
    return [parseFloat(splitedCoordinates[0]), parseFloat(splitedCoordinates[1])];
}

function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
}