<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="jmc.skweb.core.model.Usuario"%>
<%@page import="jmc.skweb.util.PreferenciasUtil"%>
<%@page import="jmc.skweb.util.Constants"%>
<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec"%>

<link href="<%=request.getContextPath()%>/css/hints.css"
	rel="stylesheet" type="text/css" />

<link href="<%=request.getContextPath()%>/css/styles.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/popup.css"
	rel="stylesheet" type="text/css" />

<link href="<%=request.getContextPath()%>/css/jquery.autocomplete.css"
	rel="stylesheet" type="text/css" />

<script src="<%=request.getContextPath()%>/js/popup.js"
	type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/jquery.blockUI.js"
	type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/jquery.autocomplete.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.alphanumeric.js?n=2"></script>

<%
	//Tomar el usuario desde el Request
	Usuario usuarioActual = (Usuario) request.getSession()
			.getAttribute("usuario");
	int idRol = usuarioActual.getRol();
%>

<style>

/* 

	TableCloth	
	by Alen Grakalic, brought to you by cssglobe.com
	
*/

/* general styles */

table, td{
	font:100% Arial, Helvetica, sans-serif; 
}
table{width:100%;border-collapse:collapse;margin:1em 0;}
th, td{text-align:left;padding:.5em;border:1px solid #fff;}
th{background:#328aa4 url(tr_back.gif) repeat-x;color:#fff;}
td{background:#e5f1f4;}

/* tablecloth styles */

tr.even td{background:#e5f1f4;}
tr.odd td{background:#f8fbfc;}

th.over, tr.even th.over, tr.odd th.over{background:#4a98af;}
th.down, tr.even th.down, tr.odd th.down{background:#bce774;}
th.selected, tr.even th.selected, tr.odd th.selected{}

td.over, tr.even td.over, tr.odd td.over{background:#ecfbd4;}
td.down, tr.even td.down, tr.odd td.down{background:#bce774;color:#fff;}
td.selected, tr.even td.selected, tr.odd td.selected{background:#bce774;color:#555;}

/* use this if you want to apply different styleing to empty table cells*/
td.empty, tr.odd td.empty, tr.even td.empty{background:#fff;}


a{
	text-decoration:none;
	color:#057fac;
}
a:hover{
	text-decoration:none;
	color:#999;
}
h1{
	font-size:140%;
	margin:0 20px;
	line-height:80px;	
}
h2{
	font-size:120%;
}
#container{
	margin:0 auto;
	width:680px;
	background:#fff;
	padding-bottom:20px;
}
#content{margin:0 20px;}
p.sig{	
	margin:0 auto;
	width:680px;
	padding:1em 0;
}
form{
	margin:1em 0;
	padding:.2em 20px;
	background:#eee;
}
</style>


<div class="main">
<div class="body">
	<div id="listaSaloImpago">
	</div>

</div>
</div>



<script type="text/javascript">
	$(document).ready(function() {
		var initTable = "<table ><caption>@caption</caption><tr><th>TransacNr</th><th>Fecha</th><th>TipoComprob</th><th>Letra</th><th>Prefijo</th><th>NrComprob</th><th>Total Comprob</th><th>Saldo Comprob</th><th>Saldo Arrastre</th></tr>";
		var result ="";
		$.ajax({
			type : "GET",
			url : "getSoloImpagoClientes",
			success : function(msg) {
				var msgJson = JSON.parse(msg);
				var resultfinal = ""
				var result = ""
				$.each(msgJson, function(id_valor, saldoReport) {
					var tienedatos = false;
					if (result == ""){
						result = initTable.replace("@caption","Cliente: " + saldoReport['gente']);						
						$.each(saldoReport['listTransac'], function(id_valor_transac, transacReport) {							
							var tienedatos = true;
							var transac_ = buildTr(transacReport);
							result =  result + transac_;
						});
						result =  result + "</table><h2>Total Saldo:	"+ saldoReport['finAcumulado']+" </h2>";
					}
					
					resultfinal = resultfinal + result;
					result = "";
				});
				$('#listaSaloImpago').html(resultfinal);
			}
		});
	});
	
	function buildTr(obj){			
		var result = "<tr><td>@transacNr</td><td>@fecha</td><td>@tipoComprob</td><td>@letra</td><td>@prefijo</td><td>@numeroComprob</td><td>@totalAcumulado</td><td>@saldoComprob</td><td>@saldoArrastre</td></tr>";
		result = result.replace("@transacNr",obj['transacNr']);
		result = result.replace("@fecha",obj['fecha']);
		result = result.replace("@tipoComprob",obj['tipoComprob']);
		result = result.replace("@letra",obj['letra']);
		result = result.replace("@prefijo",obj['prefijo']);
		result = result.replace("@numeroComprob",obj['nrComprob']);
		result = result.replace("@totalAcumulado",obj['totalComprob']);
		result = result.replace("@saldoComprob",obj['saldoComprob']);
		result = result.replace("@saldoArrastre",obj['saldoArrastre']);
		//console.log(result);
		return result;
		
	}

	
</script>