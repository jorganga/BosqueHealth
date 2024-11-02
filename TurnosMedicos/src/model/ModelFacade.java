package model;

import model.persistence.TurnoDAO;

public class ModelFacade {

	private TurnoDAO mdao;

	public ModelFacade() {
		mdao = new TurnoDAO();
	}

	public TurnoDAO getMdao() {
		return mdao;
	}

	public void setMdao(TurnoDAO mdao) {
		this.mdao = mdao;
	}

}
