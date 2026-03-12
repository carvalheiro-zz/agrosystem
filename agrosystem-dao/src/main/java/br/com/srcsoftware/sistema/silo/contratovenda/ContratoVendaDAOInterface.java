package br.com.srcsoftware.sistema.silo.contratovenda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface ContratoVendaDAOInterface{
	ContratoVendaPO inserir( HibernateConnection hibernate, ContratoVendaPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, ContratoVendaPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, ContratoVendaPO po ) throws ApplicationException;

	ContratoVendaPO filtrarPorId( Long id ) throws ApplicationException;

	List< ContratoVendaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, ContratoVendaPO poFilter ) throws ApplicationException;

	ContratoVendaPO filtrarPorNumero( String numero ) throws ApplicationException;

	BigDecimal filtrarQuantidadeRestante( Long idContratoVenda ) throws ApplicationException;
}
