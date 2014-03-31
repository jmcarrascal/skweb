package jmc.skweb.core.dao;

import java.util.List;

import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Paseban;
import jmc.skweb.core.model.Stock;
import jmc.skweb.core.model.Transac;



public interface PasebanDAO extends GenericDAO<Paseban> {

	List<Paseban> getPasebanPorAgendado(Integer genteNr);

	Double getPasebanTotalPorAgendado(Integer genteNr);



}
