package jmc.skweb.util;

import java.util.List;

import jmc.skweb.core.model.Preferencias;

public class PreferenciasUtil {

	public static Double comparePreferencia(List<Preferencias> listPreferencias, Integer idPreferencia){
		for(Preferencias preferencias : listPreferencias){
			if(preferencias.getNr().equals(idPreferencia)){
				return preferencias.getValor();
			}
		}
		return 0d;		
	}
}
