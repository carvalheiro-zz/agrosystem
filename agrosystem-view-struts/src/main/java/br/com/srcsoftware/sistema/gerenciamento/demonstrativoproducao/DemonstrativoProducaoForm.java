package br.com.srcsoftware.sistema.gerenciamento.demonstrativoproducao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractUsuarioForm;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadeDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;
import br.com.srcsoftware.sistema.silo.contratovenda.InformacoesRestanteEntregarPOJO;
import br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO;
import br.com.srcsoftware.sistema.silo.silo.InformacoesSiloPOJO;

public final class DemonstrativoProducaoForm extends AbstractUsuarioForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private List< InformacoesProducaoSafraPOJO > demonstrativos = new ArrayList<>();

	private SafraDTO safra;
	private SetorDTO setor;
	private CulturaDTO cultura;
	private VariedadeDTO variedade;

	private ArrayList< InformacoesSiloPOJO > silos;
	private ArrayList< InformacoesProducaoSafraPOJO > safras;
	private ArrayList< InformacoesRestanteEntregarPOJO > restantes;

	public ArrayList< InformacoesRestanteEntregarPOJO > getRestantes() {
		if ( this.restantes == null ) {
			this.restantes = new ArrayList<>();
		}
		return restantes;
	}

	public void setRestantes( ArrayList< InformacoesRestanteEntregarPOJO > restantes ) {
		this.restantes = restantes;
	}

	public ArrayList< InformacoesProducaoSafraPOJO > getSafras() {
		if ( this.safras == null ) {
			this.safras = new ArrayList<>();
		}
		return safras;
	}

	public void setSafras( ArrayList< InformacoesProducaoSafraPOJO > safras ) {
		this.safras = safras;
	}

	public ArrayList< InformacoesSiloPOJO > getSilos() {
		if ( this.silos == null ) {
			this.silos = new ArrayList<>();
		}
		return silos;
	}

	public void setSilos( ArrayList< InformacoesSiloPOJO > silos ) {
		this.silos = silos;
	}

	public List< InformacoesProducaoSafraPOJO > getDemonstrativos() {
		if ( this.demonstrativos == null ) {
			this.demonstrativos = new ArrayList<>();
		}
		return demonstrativos;
	}

	public void setDemonstrativos( List< InformacoesProducaoSafraPOJO > demonstrativos ) {
		this.demonstrativos = demonstrativos;
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

	public SetorDTO getSetor() {
		if ( setor == null ) {
			setor = new SetorDTO();
		}
		return setor;
	}

	public void setSetor( SetorDTO setor ) {
		this.setor = setor;
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

	public VariedadeDTO getVariedade() {
		if ( variedade == null ) {
			variedade = new VariedadeDTO();
		}
		return variedade;
	}

	public void setVariedade( VariedadeDTO variedade ) {
		this.variedade = variedade;
	}

	public void limparTudo() {
		setSafra( null );
		setSetor( null );
		setCultura( null );
		setVariedade( null );

		getSilos().clear();
		getSafras().clear();

		getDemonstrativos().clear();
	}

	public void limparMensagens( HttpServletRequest request ) {
		request.getSession().removeAttribute( "erroAjax" );
		request.getSession().removeAttribute( "sucessoAjax" );
	}
}
