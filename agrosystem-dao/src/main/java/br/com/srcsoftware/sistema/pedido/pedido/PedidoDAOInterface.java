package br.com.srcsoftware.sistema.pedido.pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface PedidoDAOInterface{

	PedidoPO inserir( HibernateConnection hibernate, PedidoPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, PedidoPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, PedidoPO po ) throws ApplicationException;

	PedidoPO filtrarPorId( Long id ) throws ApplicationException;

	List< PedidoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, PedidoPO poFilter ) throws ApplicationException;

	List< PedidoPO > filtrarAbertosAndamentos( String numeroPedido, HashMap< String, String > camposOrders ) throws ApplicationException;

	BigDecimal calcularValoresRestantesEntregar( Long idProduto, Long idPedido ) throws ApplicationException;

	BigDecimal calcularValoresRestantesEntregar( Long idProduto, Long idPedido, Long idNotaFiscalVenda, boolean considerarFutura ) throws ApplicationException;

	BigDecimal calcularValoresRestantesEntregarSimplesRemessa( Long idProduto, Long idNotaFiscalVendaFutura ) throws ApplicationException;
}
