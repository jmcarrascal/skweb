package jmc.skweb.core.model;

import java.util.ArrayList;
import java.util.List;

public class Booleano {

private String nombre;
private String valor;

public static List<Booleano> getListBooleano(){
	List<Booleano> listBooleano = new ArrayList<Booleano>();
	Booleano no = new Booleano(); 
	Booleano si = new Booleano();
	no.setNombre("false");
	no.setValor("No");
	si.setNombre("true");
	si.setValor("Sí");
	listBooleano.add(no);
	listBooleano.add(si);
	return listBooleano;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getValor() {
	return valor;
}
public void setValor(String valor) {
	this.valor = valor;
}


}
