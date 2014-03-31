function sendComprobCliente(transacNr) {								
	$.ajax({
		type : "POST",
		url : "sendComprobCliente",
		data : {
			"transac.transacNr" : transacNr			
		},
		success : function(msg) {
			 $.blockUI({ 
		            message: msg, 
		            timeout: 3500 
		     }); 			
		}
	});		
}

function addProduct(url) {								
	 alert($('#idCantidadText').val());
	 alert(url);
	 //$('#idVerImagenesDiv').hide();
}

function deleteCookie( name, path, domain ) {
	alert(name + path + domain);
	if ( getCookie( name ) ) document.cookie = name + '=' +	( ( path ) ? ';path=' + path : '') +
			( ( domain ) ? ';domain=' + domain : '' ) +
			';expires=Thu, 01-Jan-1970 00:00:01 GMT';
}
var Cookie = {
		set: function(name,value,seconds){
			if(seconds){
				// alert(name);
				d = new Date();
				d.setTime(d.getTime() + (seconds * 1000));
				expiry = '; expires=' + d.toGMTString();
			}else
				alert(name);
				expiry = '';
			document.cookie = name + "=" + value + expiry + "; path=/";
		},
		get: function(name){
			nameEQ = name + "=";
			ca = document.cookie.split(';');
			for(i = 0; i < ca.length; i++){
				c = ca[i];
				while(c.charAt(0) == ' ')
					c = c.substring(1,c.length);
				if(c.indexOf(nameEQ) == 0)
					return c.substring(nameEQ.length,c.length);
			}
			return null
		},
		unset: function(name){
			Cookie.set(name,'',0);
		}
	}