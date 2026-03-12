package br.com.srcsoftware.sistema.gerenciamento.relatorios.relacaosafrasetorculturavariedade;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractUsuarioForm;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.safra.relatorios.RelacaoSafraSetorCulturaVariedadePOJO;

public final class RelacaoSafraSetorCulturaVariedadeForm extends AbstractUsuarioForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private SafraDTO safra;
	private CulturaDTO cultura;

	private ArrayList< RelacaoSafraSetorCulturaVariedadePOJO > dados;

	public ArrayList< RelacaoSafraSetorCulturaVariedadePOJO > getDados() {
		if ( this.dados == null ) {
			this.dados = new ArrayList<>();
		}
		return dados;
	}

	public void setDados( ArrayList< RelacaoSafraSetorCulturaVariedadePOJO > dados ) {
		this.dados = dados;
	}

	public SafraDTO getSafra() {
		if ( safra == null ) {
			safra = new SafraDTO();
		}
		return safra;
	}

	public void setSafra( SafraDTO safra ) {
		this.safra = safra;
	}

	public CulturaDTO getCultura() {
		if ( cultura == null ) {
			cultura = new CulturaDTO();
		}
		return cultura;
	}

	public void setCultura( CulturaDTO cultura ) {
		this.cultura = cultura;
	}

	public void limparTudo() {
		setSafra( null );
		setCultura( null );
		getDados().clear();

		getSafra().setNome( String.valueOf( LocalDate.now().getYear() ) );
	}

	public void limparMensagens( HttpServletRequest request ) {
		request.getSession().removeAttribute( "erroAjax" );
		request.getSession().removeAttribute( "sucessoAjax" );
	}
}
