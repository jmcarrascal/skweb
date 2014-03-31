var text;

function Applet() 
{
    this.attr  = new Object(); // an object to keep the applet attributes
    this.param = new Object(); // an object to keep the applet parameters	
}

Applet.prototype.write = function() 
{
    document.write('<applet ');

    // write the attributes of the applet tag
    for (name in this.attr) 
	{
        var value = this.attr[name];
		document.write(name+'="'+value+'" ');
    }
    document.write('>');

    // write the applet parameters
    for (name in this.param) 
	{
        var value = this.param[name];
		document.write('<param name="'+name+'" value="'+value+'">');
    }
    document.write('</applet>');
}


function openPinSigner(){

	text='<form name="signerForm" action="abmDocumento.do?method=saveDocumentoPDF">'

	text+='<label class="label_33 field">Ingrese PIN:</label>'
			 				+'<input type="password" name="pin" id="pin" class="label_33" /> '
			 				
				
	text+='</form>'
	showSignerPinMessage(text,"openSelectorSigner()","mssId");
}

function openSelectorSigner(){
	
		document.getElementById('SignerApplet').cargarSelector();
}

function firmar(){
	document.getElementById('SignerApplet').firmar();
}