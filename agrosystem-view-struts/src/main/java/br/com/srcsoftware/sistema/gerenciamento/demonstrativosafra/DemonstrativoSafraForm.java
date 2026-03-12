package br.com.srcsoftware.sistema.gerenciamento.demonstrativosafra;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractUsuarioForm;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.CustoDiretoInsumoPOJO;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;

public final class DemonstrativoSafraForm extends AbstractUsuarioForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private List< CustoDiretoInsumoPOJO > demonstrativos = new ArrayList<>();
	private String tipo;
	private SafraDTO safra;
	private SetorDTO setor;
	private CulturaDTO cultura;

	private String informativoResultado;

	private String totalPorAlqueire;
	private String totalPorArea;

	/*public String getCustoTotalEncontrado() {
		BigDecimal soma = BigDecimal.ZERO;
	
		for ( CustoDiretoInsumoPOJO corrente : demonstrativos ) {
			soma = soma.add( corrente.getCustoTotal() );
		}
	
		return Utilidades.parseBigDecimal( soma );
	}*/

	public void calcularTotais( List< CustoDiretoInsumoPOJO > encontradosParaTotais ) {

		BigDecimal totalPorAlqueire = BigDecimal.ZERO;
		BigDecimal totalPorArea = BigDecimal.ZERO;

		for ( CustoDiretoInsumoPOJO corrente : demonstrativos ) {
			totalPorArea = totalPorArea.add( corrente.getCustoTotal() );

			totalPorAlqueire = totalPorAlqueire.add( corrente.getCustoPorAlqueire() );
		}

		setTotalPorAlqueire( Utilidades.parseBigDecimal( totalPorAlqueire.setScale( Utilidades.SCALE_EXIBICAO, BigDecimal.ROUND_HALF_EVEN ) ) );
		setTotalPorArea( Utilidades.parseBigDecimal( totalPorArea.setScale( Utilidades.SCALE_EXIBICAO, BigDecimal.ROUND_HALF_EVEN ) ) );

	}

	public String getTotalPorAlqueire() {
		return totalPorAlqueire;
	}

	public void setTotalPorAlqueire( String totalPorAlqueire ) {
		this.totalPorAlqueire = totalPorAlqueire;
	}

	/*public String getTotalPorAlqueireToString() {
		if ( !Utilidades.isNuloOuVazio( getTotalPorAlqueire() ) ) {
			return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( getTotalPorAlqueire() ) );
		}
		return getTotalPorAlqueire();
	}
	
	public String getTotalPorAlqueireViewToString() {
		if ( !Utilidades.isNuloOuVazio( getTotalPorAlqueire() ) ) {
			return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( getTotalPorAlqueire() ).setScale( Utilidades.SCALE_EXIBICAO, BigDecimal.ROUND_HALF_EVEN ) );
		}
		return getTotalPorAlqueire();
	}*/

	public String getTotalPorArea() {
		return totalPorArea;
	}

	public void setTotalPorArea( String totalPorArea ) {
		this.totalPorArea = totalPorArea;
	}

	public String getTotalPorAreaToString() {
		if ( !Utilidades.isNuloOuVazio( getTotalPorArea() ) ) {
			return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( getTotalPorArea() ) );
		}
		return getTotalPorArea();
	}

	public String getTotalPorAreaViewToString() {
		if ( !Utilidades.isNuloOuVazio( getTotalPorArea() ) ) {
			return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( getTotalPorArea() ).setScale( Utilidades.SCALE_EXIBICAO, BigDecimal.ROUND_HALF_EVEN ) );
		}
		return getTotalPorArea();
	}

	public String getInformativoResultado() {
		return informativoResultado;
	}

	public void setInformativoResultado( String informativoResultado ) {
		this.informativoResultado = informativoResultado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
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

	public List< CustoDiretoInsumoPOJO > getDemonstrativos() {
		if ( demonstrativos == null ) {
			demonstrativos = new ArrayList<>();
		}
		return demonstrativos;
	}

	public void setDemonstrativos( List< CustoDiretoInsumoPOJO > demonstrativos ) {
		this.demonstrativos = demonstrativos;
	}

	public void limparTudo() {
		setCultura( null );
		setInformativoResultado( null );
		setSafra( null );
		setSetor( null );
		setTipo( null );

		setTotalPorAlqueire( null );
		setTotalPorArea( null );

		getDemonstrativos().clear();
	}

	public void limparMensagens( HttpServletRequest request ) {
		request.getSession().removeAttribute( "erroAjax" );
		request.getSession().removeAttribute( "sucessoAjax" );
	}
}
