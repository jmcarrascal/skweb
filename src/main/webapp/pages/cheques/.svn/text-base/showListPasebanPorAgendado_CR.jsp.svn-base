<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec" %>
 

<script src="<%=request.getContextPath()%>/js/jquery.blockUI.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/tjpzoom.js" type="text/javascript"></script>


<div class="main">
		<div align="right"><a href="menuAgendado_Tran"> <img
	src="<%=request.getContextPath()%>/images/general/back.png" width="50px" alt="Volver" title="Volver"
	 border="0"></img> </a> <a href="<%=request.getContextPath()%>/home"> <img
	src="<%=request.getContextPath()%>/images/general/homev1.png" width="50px" alt="Inicio" title="Inicio"
	border="0"></img> </a>
	</div>
	
	
	<div class="panel_100 tab">
	    	<div class="space"></div>
		
           	<fieldset class="defaultFieldset">
    			<legend><b>Cheques Rechazados - Movimientos por Agendado</b></legend>
    			<div class="space"></div>										
						<table>					
				<tr>
					<td align="left">
	 					<s:label id="idRazonSocial" name="gente.descripC" cssClass="entidadGrande"/>		
					</td>										
				</tr>
				</table>
	 					
				<s:actionerror/>	
					<ec:table					
						tableId="notReg" 
				    	items="pasebanList"
						var = "paseban"
						locale="es_ES"
						action="findPasebanPorAgendado_CR"
						imagePath="images/table/*.gif"
						width="100%"
						rowsDisplayed="100" >
						<ec:exportPdf view="pdf" headerColor="white" headerTitle='Cheques Rechazados' fileName="chequesRechazados.pdf" tooltip="Exportar PDF" />
						<ec:exportXls view="xls"  fileName="chequesRechazados.xls" tooltip="Exportar Excel"/>
						
						<ec:row>
								<ec:column width="40px" title=" " filterable="false" sortable="false" alias="read" viewsAllowed="html">
	          						<a href="#">
	          							<img src="images/general/view.png" alt="Cheques Rechazados - Ver Imagen" title="Cheques Rechazados - Ver Imagen" onclick="viewImage('${paseban.serieNr}', '${paseban.cuentaNr}')" border="0" ></img>
									</a>
	          					</ec:column>								
								<ec:column width="40px" property="transacNr" title="TransacNr" />
								<ec:column width="80px" property="fecha" title="Fecha" sortable="false" viewsAllowed="html">
									<span style="white-space:nowrap;" >${paseban.formattedDate}</span>
								</ec:column>
								<ec:column width="auto" property="formattedDate" title="Fecha" viewsDenied="html"/>
								<ec:column width="auto" property="importeCalculado" title="Importe"/>
								<ec:column width="auto" property="importeCalculado" title="Importe"/>
								<ec:column width="auto" property="serieNr" title="Serie"/>
								<ec:column width="auto" property="nrInt" title="N. Interno"/>
								<ec:column width="auto" property="cuentaNr" title="Cuenta"/>
								<ec:column width="auto" property="instituNr" title="Banco Nr"/>
								<ec:column width="auto" property="bancoSucursal" title="Sucursal Nr"/>								
						</ec:row>
					</ec:table>
				<div class="space"></div> 
						<label for="totalSaldo" class="totales">Total:</label>	 					 					
	 					<b><s:label id="totalSaldo2" cssClass="totales" name="totalSaldo" /></b>
				
			</fieldset>		
			</div>		
</div>		
<div style="display: none" id = "divImage1" onclick="closeBlock();" v>
<div id="divImage" onclick="closeBlock();" >
</div>
</div>

<s:form action="findPasebanPorAgendado_CR" id="findPasebanPorAgendadoForm">
	<s:hidden key="gente.genteNr" id="idGente"/>
</s:form>

<script type="text/javascript">
$(document).ready(function(){
	$.ajaxSetup({
		cache: false
	});
});
function closeBlock(){	
	$.unblockUI();
}

function viewImage(serie, cuenta){	
	//Pregunto si la imagen existe
	var msg = '';
	$.ajax({ 
        type: "POST",
        url: "existeCheque",           
           data: {"serie": serie + cuenta},
           success: function(r){
        	     if (r == 'OK'){
        	    		imagenS = "<img src=\"recuperarCheque?serie="+ serie + cuenta+"\" style='width:900px; height: 600px;' id = 'imagenCheque' onmouseover= \"TJPzoom(this);\" onclick=\"closeBlock();\" />";
        	    		
        	    		$("#divImage").empty().append($(imagenS));
          	    	  $.blockUI({         	                       	               
        	              message:  "<img src=\"./images/icons/loadingGrayCircle.gif\">"
          	    	});
        	              setTimeout(function() { 
        	                  $.unblockUI({ 
        	                      onUnblock: function(){ 		        	    		
        	                    	  $.blockUI({		
		        	    			    
		        	    	        	message: $('#divImage'),
		        	    	        	css: { top: '1%',  left: '20%' }
		        	    	        
		        	    	        
		        	    		});
 } 
        	                  }); 
        	              }, 6000); 
        	               

        	              
        	          
			
        	      }else{
        	    	  $.blockUI({ 
        	              theme:     true, 
        	              title:    'Obtener Imagen', 
        	              message:  '<p>La imagen del cheque no se encuentra en la base de datos.</p>', 
        	              timeout:   2000 
        	          });
        	    }        	   
            }
      }); 	 
	//alert(msg);

		
}
</script>