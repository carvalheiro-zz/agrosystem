package br.com.srcsoftware.sistema.pessoa.funcionario.horaextra;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class HoraExtraForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private HoraExtraDTO horaExtra;

	private ArrayList< HoraExtraDTO > horaExtras;

	private String dataInicial;
	private String dataFinal;

	public String getDataInicial() {
		return this.dataInicial;
	}

	public void setDataInicial( String dataInicial ) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return this.dataFinal;
	}

	public void setDataFinal( String dataFinal ) {
		this.dataFinal = dataFinal;
	}

	public HoraExtraDTO getHoraExtra() {
		if ( horaExtra == null ) {
			horaExtra = new HoraExtraDTO();
		}
		return horaExtra;
	}

	public void setHoraExtra( HoraExtraDTO horaExtra ) {
		this.horaExtra = horaExtra;
	}

	public ArrayList< HoraExtraDTO > getHoraExtras() {
		if ( horaExtras == null ) {
			horaExtras = new ArrayList<>();
		}
		return horaExtras;
	}

	public void setHoraExtras( ArrayList< HoraExtraDTO > horaExtras ) {
		this.horaExtras = horaExtras;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setHoraExtra( null );
		//getHoraExtra().setTipo( HoraExtraDTO.TIPO_LANCAMENTO );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		setHoraExtra( null );
		setDataInicial( null );
		setDataFinal( null );
	}

	@Override
	public void limparLista() {
		this.getHoraExtras().clear();
	}

}
