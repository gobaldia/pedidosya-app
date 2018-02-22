/*$(document).ready(function() {

});*/

$(function(){
    $(window).load(function(){
        setLoggedUser();
        getRestaurants(getCookie("userToken"));
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

function getRestaurants(userToken) {
    $.ajax({
        type: "GET",
        url: "get-restaurants",
        beforeSend: function(xhr){xhr.setRequestHeader('Authorization', userToken);},
        success: function (data) {
            for (i = 0; i < data["data"].length; i++) {
                alert(JSON.stringify(data["data"][i]["name"]));
            }
        },
        error: function () {
            alert("error");
        }
    })
}