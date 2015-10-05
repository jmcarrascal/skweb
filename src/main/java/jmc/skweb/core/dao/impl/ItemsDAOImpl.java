package jmc.skweb.core.dao.impl;


import jmc.skweb.core.dao.ItemsDAO;
import jmc.skweb.core.model.Items;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class ItemsDAOImpl extends GenericDAOImpl<Items> implements ItemsDAO {

		public ItemsDAOImpl() {
			super(Items.class);		 
		}

			}
