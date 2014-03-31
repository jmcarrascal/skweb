package jmc.skweb.jasper.core.jasper;

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public abstract class AbstractDataSource implements JRDataSource {

	private Iterator it;
	private Object actual;

	public AbstractDataSource(List datos) {
		it = datos.iterator();
	}

	public AbstractDataSource() {
	}

	public final boolean next() throws JRException {
		boolean sig = it.hasNext();
		if (sig) {
			actual = it.next();
		}
		return sig;
	}

	public final Object getFieldValue(JRField arg0) throws JRException {
		String fieldName = arg0.getName();
		return this.getFieldValue(fieldName, this.actual);
	}

	public abstract Object getFieldValue(String fieldName, Object obj) throws JRException;
}
