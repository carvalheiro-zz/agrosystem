package br.com.srcsoftware.sistema.pessoa.funcionario.horaextra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface HoraExtraDAOInterface{

	HoraExtraPO inserir( HibernateConnection hibernate, HoraExtraPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, HoraExtraPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, HoraExtraPO po ) throws ApplicationException;

	HoraExtraPO filtrarPorId( Long id ) throws ApplicationException;

	List< HoraExtraPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, HoraExtraPO poFilter ) throws ApplicationException;

	List< SaldoHoraExtraPOJO > filtrarSaldo( Long idColaborador, LocalDate dataInicial, LocalDate dataFinal ) throws ApplicationException;
}