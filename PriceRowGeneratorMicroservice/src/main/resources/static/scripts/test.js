jQuery( document ).ready(function() {
    var href = document.location.href;
    var queryString = href.split("=")[1]
    $.ajax({
    	url: "/updateVisit?id=" + queryString, 
    	success: function(data) {
    		jQuery("#pageVisitsCount").html(data);
    	}
    });
});