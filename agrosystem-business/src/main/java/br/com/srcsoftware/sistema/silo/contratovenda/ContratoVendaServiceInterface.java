package br.com.srcsoftware.sistema.silo.contratovenda;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface ContratoVendaServiceInterface{

	List< ContratoVendaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ContratoVendaDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ContratoVendaDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ContratoVendaDTO dto ) throws ApplicationException;

	void excluir( ContratoVendaDTO dto ) throws ApplicationException;

	ContratoVendaDTO filtrarPorId( String id ) throws ApplicationException;

	ContratoVendaDTO filtrarPorNumero( String numero ) throws ApplicationException;
}
