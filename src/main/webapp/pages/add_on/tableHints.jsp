<link href="<%=request.getContextPath()%>/css/hints.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      oldonload();
      func();
    }
  }
}

function prepareInputsForHints() {
  var inputs = document.getElementsByTagName("input");
  for (var i=0; i<inputs.length; i++){
    if (inputs[i].name.substr(0,5)=='ec_f_'){
    	newHint=document.createElement('span');
    	newHint.className="hint";
	   	newHint.id="HINT_"+inputs[i].name;
    	newHint.innerHTML='filtro de busqueda,<br>escriba el texto<br>y presione filtrar.<span class="hint-pointer">&nbsp;</span>';
		inputs[i].parentNode.appendChild(newHint);
	    if (inputs[i].parentNode.getElementsByTagName("span")[0]) {
	      inputs[i].onmouseover= function () {
	        this.parentNode.getElementsByTagName("span")[0].style.display = "inline";
	      }
	      inputs[i].onmouseout = function () {
	        this.parentNode.getElementsByTagName("span")[0].style.display = "none";
	      }
	    }
	}
  }
  var selects = document.getElementsByTagName("select");
  for (var k=0; k<selects.length; k++){
    if (selects[k].name.substr(0,5)=='ec_f_'){
	   	newHint=document.createElement('span');
	   	newHint.className="hint";
	   	newHint.id="HINT_"+selects[k].name;
	   	newHint.innerHTML='filtro de busqueda,<br>seleccione la opción deseada.<span class="hint-pointer">&nbsp;</span>';
		selects[k].parentNode.appendChild(newHint);
	    if (selects[k].parentNode.getElementsByTagName("span")[0]) {
	      selects[k].onmouseover = function () {
	        this.parentNode.getElementsByTagName("span")[0].style.display = "inline";
	      }
	      selects[k].onmouseout = function () {
	        this.parentNode.getElementsByTagName("span")[0].style.display = "none";
	      }
	    }
	}
  }
}
addLoadEvent(prepareInputsForHints);


</script>
