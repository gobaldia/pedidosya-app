/*$(document).ready(function() {

});*/

$(function(){
    $(window).load(function(){
        setLoggedUser();
        //initMap();
    });
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

function initMap() {
    var montevideo = {lat: -34.892349, lng: -56.160892};
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15,
        center: montevideo
    });

    var contentString = '<div id="content">'+
        '<div id="siteNotice">'+
        '</div>'+
        '<h2 id="firstHeading" class="firstHeading">Nombre Restaurante</h2>'+
        '<p id="firstHeading" class="firstHeading">Categorias</p>'+
        '<p id="firstHeading" class="firstHeading">Rating</p>'+
        '<img src="https://img.pystatic.com/social_image.png">'+
        '</div>'+
        '</div>';

    var infowindow = new google.maps.InfoWindow({
        content: contentString
    });

    var marker = new google.maps.Marker({
        position: montevideo,
        map: map,
        title: 'Uluru (Ayers Rock)'
    });
    marker.addListener('click', function() {
        infowindow.open(map, marker);
    });

    //src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA3DdxEl0GSUT8q5H456mnun4X1sgEzIec&callback=initMap";
}