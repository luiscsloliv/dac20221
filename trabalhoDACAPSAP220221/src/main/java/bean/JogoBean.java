package bean;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

import dao.JogoDAO;
import entidade.Jogo;

@ManagedBean
public class JogoBean {
	private Jogo jogo = new Jogo();
	private List<Jogo> lista;
	
	public String salvar() {
		try {
			JogoDAO.salvar(jogo);
			jogo = new Jogo();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo criado com sucesso!"));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "Jogo não foi criado!"));
		}
		return null;
	}
	public String editar() {
		try {
			JogoDAO.editar(jogo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo editado com sucesso!"));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "Jogo não foi editado!"));
		}
		return "listagem";
	}
	public String apagar() {
		try {
			JogoDAO.apagar(jogo);
			lista.remove(jogo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo excluído com sucesso!"));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "Jogo não foi excluído!"));
		}
		return null;
	}
	public String buscaPares() {
		List<Integer> l = JogoDAO.buscaPares(jogo);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", "Os números pares são: " + l + " !");
	    PrimeFaces.current().dialog().showMessageDynamic(message);
		return null;
		
	}
	public Jogo getJogo() {
		return jogo;
	}
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	public List<Jogo> getLista() {
		if (lista == null)
			lista = JogoDAO.listar();
		return lista;
	}
	public void setLista(List<Jogo> lista) {
		this.lista = lista;
	}
	
}
