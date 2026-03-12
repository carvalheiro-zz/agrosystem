package br.com.srcsoftware.sistema.pessoa.funcionario.ferias;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface FeriasDAOInterface{

	FeriasPO inserir( HibernateConnection hibernate, FeriasPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, FeriasPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, FeriasPO po ) throws ApplicationException;

	FeriasPO filtrarPorId( Long id ) throws ApplicationException;

	List< FeriasPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, FeriasPO poFilter ) throws ApplicationException;

	List< SaldoFeriasPOJO > filtrarSaldo( Long idColaborador, LocalDate dataInicial, LocalDate dataFinal ) throws ApplicationException;
}