package co.edu.uniquindio.grid.util;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

import co.edu.uniquindio.grid.ejbs.AdminEJB;
import co.edu.uniquindio.grid.entidades.Familia;

@FacesConfig(version = Version.JSF_2_3)
@Named(value = "familiaConverter")
@ApplicationScoped
public class FamiliaConverter implements Converter<Familia> {

	@EJB
	private AdminEJB adminEJB;

	@Override
	public Familia getAsObject(FacesContext context, UIComponent component, String id) {
		Familia familia = null;
		if (id != null && !"".equals(id)) {
			try {
				Long idFamilia = Long.parseLong(id);
				familia = adminEJB.buscarFamilia(idFamilia);
			} catch (Exception e) {
				throw new ConverterException(new FacesMessage(component.getClientId() + ":ID no valido"));
			}
		}
		return familia;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Familia familia) {
		if (familia != null) {
			return familia.getId().toString();
		}
		return "";
	}

}
