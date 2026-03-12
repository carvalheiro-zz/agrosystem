package br.com.srcsoftware.sistema.manutencao.veiculo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class VeiculoForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private VeiculoDTO veiculo;

	private ArrayList< VeiculoDTO > veiculos;

	public VeiculoDTO getVeiculo() {
		if ( veiculo == null ) {
			veiculo = new VeiculoDTO();
		}
		return veiculo;
	}

	public void setVeiculo( VeiculoDTO veiculo ) {
		this.veiculo = veiculo;
	}

	public ArrayList< VeiculoDTO > getVeiculos() {
		if ( veiculos == null ) {
			veiculos = new ArrayList<>();
		}
		return veiculos;
	}

	public void setVeiculos( ArrayList< VeiculoDTO > veiculos ) {
		this.veiculos = veiculos;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setVeiculo( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {

	}

	@Override
	public void limparLista() {
		this.getVeiculos().clear();
	}

}
