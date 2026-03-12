package br.com.srcsoftware.sistema.manutencao.veiculo;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface VeiculoServiceInterface{

	List< VeiculoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, VeiculoDTO dto ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, VeiculoDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, VeiculoDTO dto ) throws ApplicationException;

	void excluir( VeiculoDTO dto ) throws ApplicationException;

	VeiculoDTO filtrarPorId( String id ) throws ApplicationException;

}
