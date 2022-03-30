package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

import dao.LocaisDAO;
import entidade.Locais;

@ManagedBean
public class LocaisBean {
	private Locais locais = new Locais();
	private List<Locais> lista;
	
	public String salvar() {
		try {
			LocaisDAO.salvar(locais);
			locais = new Locais();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Local cadastrado com sucesso!"));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "Local não foi cadastrado!"));
		}
		return null;
	}
	public String editar() {
		try {
			LocaisDAO.editar(locais);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Local editado com sucesso!"));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "Local não foi editado!"));
		}
		return "listagem";
	}
	public String apagar() {
		try {
			LocaisDAO.apagar(locais);
			lista.remove(locais);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Local excluído com sucesso!"));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "Local não foi excluído!"));
		}
		return null;
	}
	public String contar() {
		Integer count = LocaisDAO.contar();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", "Existem " + count.toString() + " registros!");
	    PrimeFaces.current().dialog().showMessageDynamic(message);
		return null;
		
	}
	
	public Locais getLocais() {
		return locais;
	}
	public void setLocais(Locais locais) {
		this.locais = locais;
	}
	public List<Locais> getLista() {
		if (lista == null)
			lista = LocaisDAO.listar();
		return lista;
	}
	public void setLista(List<Locais> lista) {
		this.lista = lista;
	}
	
}
