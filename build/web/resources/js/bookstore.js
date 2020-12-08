function showCart(){
    document.getElementById("div_cart").style.display = "block";
    document.getElementById("div_chat").style.display = "none";
}

function hideCart(){
    document.getElementById("div_cart").style.display = "none";
}
function addToCart(id){
    var uri = "http://localhost:8080/BookstoreFrontend/controller/addToCart?pId=" + id;
    
    var ajax = new XMLHttpRequest();
    ajax.open("GET", uri, true);
    ajax.onload = function(){
        refreshCart();
    };
    ajax.send();
}
function removeFromCart(id){
    var uri = "http://localhost:8080/BookstoreFrontend/controller/removeFromCart?pId=" + id;
    
    var ajax = new XMLHttpRequest();
    ajax.open("GET", uri, true);
    ajax.onload = function(){
        refreshCart();
    };
    ajax.send();
}
function refreshCart(){
    var uri = "http://localhost:8080/BookstoreFrontend/controller/cart";
    
    var ajax = new XMLHttpRequest();
    ajax.open("GET", uri, true);
    ajax.onload = function (){
        var divCart = document.getElementById("div_cart");
        divCart.innerHTML = this.responseText;
    };
    ajax.send();
}

function checkout(){
    
}