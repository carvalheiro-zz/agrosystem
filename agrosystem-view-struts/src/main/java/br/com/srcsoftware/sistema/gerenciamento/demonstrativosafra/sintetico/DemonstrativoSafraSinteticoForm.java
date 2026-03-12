package br.com.srcsoftware.sistema.gerenciamento.demonstrativosafra.sintetico;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractUsuarioForm;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.CustoDiretoInsumoPOJO;
import br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.sintetico.CustoDiretoInsumoSinteticoPOJO;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;

public final class DemonstrativoSafraSinteticoForm extends AbstractUsuarioForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private List< CustoDiretoInsumoSinteticoPOJO > sinteticos = new ArrayList<>();
	private String tipo;
	private SafraDTO safra;
	private SetorDTO setor;
	private CulturaDTO cultura;
	private String informativoResultado;

	private String totalArea;
	private String totalPorAlqueire;
	private String totalPorArea;

	public String getTotalArea() {
		return totalArea;
	}

	public void setTotalArea( String totalArea ) {
		this.totalArea = totalArea;
	}

	public String getTotalPorAlqueire() {
		return totalPorAlqueire;
	}

	public void setTotalPorAlqueire( String totalPorAlqueire ) {
		this.totalPorAlqueire = totalPorAlqueire;
	}

	public String getTotalPorArea() {
		return totalPorArea;
	}

	public void setTotalPorArea( String totalPorArea ) {
		this.totalPorArea = totalPorArea;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
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

	public List< CustoDiretoInsumoSinteticoPOJO > getSinteticos() {
		if ( sinteticos == null ) {
			sinteticos = new ArrayList<>();
		}
		return sinteticos;
	}

	public void setSinteticos( List< CustoDiretoInsumoSinteticoPOJO > sinteticos ) {
		this.sinteticos = sinteticos;
	}

	public CustoDiretoInsumoSinteticoPOJO calcularTotaisSintetico( List< CustoDiretoInsumoPOJO > encontradosParaTotais ) {

		CustoDiretoInsumoSinteticoPOJO sintetico = new CustoDiretoInsumoSinteticoPOJO();

		BigDecimal totalPorAlqueire = BigDecimal.ZERO;
		BigDecimal totalPorArea = BigDecimal.ZERO;

		for ( CustoDiretoInsumoPOJO corrente : encontradosParaTotais ) {
			totalPorArea = totalPorArea.add( corrente.getCustoTotal() );

			totalPorAlqueire = totalPorAlqueire.add( corrente.getCustoPorAlqueire() );
		}

		sintetico.setTotalPorAlqueire( totalPorAlqueire );
		sintetico.setTotalPorArea( totalPorArea );

		return sintetico;
	}

	public void calcularTotais( List< CustoDiretoInsumoSinteticoPOJO > encontradosParaTotais ) {

		BigDecimal totalArea = BigDecimal.ZERO;
		BigDecimal totalPorAlqueire = BigDecimal.ZERO;
		BigDecimal totalPorArea = BigDecimal.ZERO;

		for ( CustoDiretoInsumoSinteticoPOJO corrente : encontradosParaTotais ) {
			totalArea = totalArea.add( Utilidades.parseBigDecimal( corrente.getAreaTotal() ) );
			totalPorArea = totalPorArea.add( corrente.getTotalPorArea() );

			totalPorAlqueire = totalPorAlqueire.add( corrente.getTotalPorAlqueire() );
		}
		// XXX Parei aqui, terminei... testar mais um pouco o sistema em relação ao setor e cultura
		BigDecimal custoPorAlqueire = totalPorArea.divide( totalArea, Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_HALF_EVEN ).setScale( Utilidades.SCALE_EXIBICAO, BigDecimal.ROUND_HALF_EVEN );

		setTotalArea( Utilidades.parseBigDecimal( totalArea ) );
		setTotalPorAlqueire( Utilidades.parseBigDecimal( custoPorAlqueire /*totalPorAlqueire.setScale( Utilidades.SCALE_EXIBICAO, BigDecimal.ROUND_HALF_EVEN )*/ ) );
		setTotalPorArea( Utilidades.parseBigDecimal( totalPorArea.setScale( Utilidades.SCALE_EXIBICAO, BigDecimal.ROUND_HALF_EVEN ) ) );

	}

	public String getInformativoResultado() {
		return informativoResultado;
	}

	public void setInformativoResultado( String informativoResultado ) {
		this.informativoResultado = informativoResultado;
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

	public void limparTudo() {
		setInformativoResultado( null );
		setSafra( null );
		setSetor( null );
		setCultura( null );
		setTipo( null );

		getSinteticos().clear();
	}

	public void limparMensagens( HttpServletRequest request ) {
		request.getSession().removeAttribute( "erroAjax" );
		request.getSession().removeAttribute( "sucessoAjax" );
	}
}
