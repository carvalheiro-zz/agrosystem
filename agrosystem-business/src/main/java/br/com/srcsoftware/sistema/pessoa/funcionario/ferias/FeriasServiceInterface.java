package br.com.srcsoftware.sistema.pessoa.funcionario.ferias;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface FeriasServiceInterface{

	List< FeriasDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, FeriasDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, FeriasDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, FeriasDTO dto ) throws ApplicationException;

	void excluir( FeriasDTO dto ) throws ApplicationException;

	FeriasDTO filtrarPorId( String id ) throws ApplicationException;

	List< SaldoFeriasPOJO > filtrarSaldo( String idColaborador, String dataInicial, String dataFinal ) throws ApplicationException;

}
