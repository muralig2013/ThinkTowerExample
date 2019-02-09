$(document).ready(function(){
    $("button").click(function(){
        $.ajax({
        	url: "http://localhost:8080/ThinkTowerExample/rest/myrestapp/post/" + $('#message').val(), 
		success: function(result){ssssss
			$("#div1").html(result);
        }});
    });
});

