<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec"%>
<link href="<%=request.getContextPath()%>/css/popup.css" rel="stylesheet" type="text/css" />

<script src="<%=request.getContextPath()%>/js/jquery.blockUI.js" type="text/javascript"></script>


<link href="<%=request.getContextPath()%>/css/hints.css"
	rel="stylesheet" type="text/css" />


<div class="main"><jsp:include page="add_on/botones_basicos.jsp" />


<div class="panel_100 tab">
	    	<div class="space"></div>
		
<fieldset class="defaultFieldset"><legend><b>Resumen
Cuenta Corriente - Agendados</b></legend>
<div class="space"></div>
<s:actionerror /> <ec:table tableId="notReg" items="genteList"
	var="gente" locale="es_ES" action="findAgendadoPorNombre_Resumen_CC"
	imagePath="images/table/*.gif" width="100%" rowsDisplayed="100">
	<ec:row>
		<ec:column width="40px" title=" " filterable="false" sortable="false"
			alias="read">
			<a href="#"> <img src="images/general/view.png"
				alt="Resumen Cuenta Corriente - Reporte"
				title="Resumen Cuenta Corriente - Reporte"
				onclick="selectDate(${gente.genteNr},' ${gente.descripC}' )"
				border="0" id="read${gente.genteNr}"></img> </a>
		</ec:column>
		<ec:column width="40px" property="genteNr" title="Numero"
			sortable="false" filterable="false" />
		<ec:column width="auto" property="razonSocial" title="Razon Social" />
		<ec:column width="auto" property="cuit" title="Cuit" />

	</ec:row>
</ec:table>
<div style="display: none" align="center" >
<s:form action="resumen_CC" id="resumen_CC_Form">
	<s:hidden key="gente.genteNr" id="idGenteR" />
		<div class="separator"></div>
		<div class="space"></div>
	<table align="center">
		<tr>
<!--			   		<s:textfield id="apellido" name="usuarioWeb.apellido" size="50" maxlength="50" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all"/>-->
<!--			   		<s:textfield label="Fecha Desde" id="fechaDesde" name="fechaDesde" size="12" maxlength="12" cssClass="ui-widget-content ui-corner-all"  zindex="2002" />-->
					<div class="space" ></div>
			   		<sj:datepicker  label="Fecha Desde" id="fechaDesde" name="fechaDesde" displayFormat="dd/mm/yy" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" buttonImageOnly="true" changeMonth="true" changeYear="true" maxDate="0" readonly="false" zindex="2002" cssStyle="display:inline" yearRange="-100:100" showOn="button"/> 
			   		<sj:datepicker label="Fecha Hasta"  id="fechaHasta" name="fechaHasta" displayFormat="dd/mm/yy" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" buttonImageOnly="true" changeMonth="true" changeYear="true" maxDate="0" readonly="false" cssStyle="display:inline" zindex="2008" yearRange="-100:100" showOn="button"/> 
		</tr>
	</table>
	
	<div class="buttonPanel" id="buttonDown" align="center">
	<ul class="content_1x128">
		<img src="images/general/icon_buscar.png" alt="Buscar"
			border="0" style="float: center; margin-top: 0px;" onclick="resumenCC();"/>
		<a href="#" onclick="closeBlock();"> <img
			src="images/general/icon_cancelar.png" alt="Cancelar"
			border="0" style="float: center; margin-top: 0px;"></img> </a>
	</ul>
	</div>
</div>
</s:form>


</fieldset>
</div>

</div>


<script type="text/javascript">

$(document).ready(function(){
	 	$("#fechaDesde").mask("99/99/9999");
	 	$("#fechaHasta").mask("99/99/9999");
});

function resumenCC(){		
	document.getElementById("resumen_CC_Form").submit();
}

function closeBlock(){	
	document.getElementById("fechaDesde").value = '';
	document.getElementById("fechaHasta").value = '';
	$.unblockUI();
}

function selectDate(idGente,nombre ) {
	var title = "Agendado: " + nombre;
	//document.getElementById("titleSelectDate").innerHTML = title;
	document.getElementById("idGenteR").value = idGente;
	//showPopup();
	$("#fechaDesde").
	fechaDesde
	$.blockUI({
		theme:   true, 
		title:   title, 
        message: $('#resumen_CC_Form'), 
        opacity: .1, 
        color: 'black' 
        
	});
	
}
jQuery(function($){
	   $.datepicker.regional['es'] = {
	      closeText: 'Cerrar',
	      prevText: '<Ant',
	      nextText: 'Sig>',
	      currentText: 'Hoy',
	      monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
	      monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
	      dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
	      dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
	      dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
	      weekHeader: 'Sm',
	      dateFormat: 'dd/mm/yy',
	      firstDay: 1,
	      isRTL: false,
	      showMonthAfterYear: false,
	      yearSuffix: ''};
	   $.datepicker.setDefaults($.datepicker.regional['es']);
	});

</script>