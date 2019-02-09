$(document).ready(function(){
    $("#messageForm").submit(function(e){
    	e.preventDefault();
    	var message = $('.message').val();
        $.ajax({
    	    url: '/ThinkTowerExample/rest/myrestapp/post?message='+message,
    	    method: "POST",
    	    success: function( data, textStatus, jQxhr ){
    	        if(textStatus == "success"){
    	        	$(".message").val("");
    	        	$(".result").html("Message posted successfully").show();
    	        	setTimeout(function(){
    	        	    $(".result").hide(); 
    	        	}, 2000);
    	        } 
    	    },
    	    error: function( jqXhr, textStatus, errorThrown ){
    	        console.log(errorThrown);
    	        $(".message").val("");
	        	$(".result-danger").html("Sorry there is a problem in posting your message").show();
	        	setTimeout(function(){
	        	    $(".result-danger").hide(); 
	        	}, 2000);
    	    }
    	});
    });
    
    $(".get-message").click(function(e){
    	e.preventDefault();
        $.ajax({
    	    url: '/ThinkTowerExample/rest/myrestapp/home',
    	    method: "GET",
    	    dataType: "json",
			cache: false,
    	    success: function( data, textStatus, jQxhr ){
    	    	$('#messageContainer').html('<table class="table table-striped"><tbody class="message-table"></tbody></table>');
    	    	if(data.length > 0){
    	    		var str = "";
    	    		$.each(data, function(key,value) {
    	    			console.log(value);
    	    			str += "<tr>";
    	    			str += "<td>"+value+"</td>";
    	    			str += "</tr>";   
        	    	});
    	    		$('.message-table').html(str);
    	    	}else {
    	    		console.log("No Messages");
    	    		$('.message-table').html("No Messages");
    	    	}
    	    },
    	    error: function( jqXhr, textStatus, errorThrown ){
    	        console.log(errorThrown);
    	    }
    	});
    });
    
    $(".hide-message").click(function(e){
    	$('.message-table').html("");
    });
    
});

