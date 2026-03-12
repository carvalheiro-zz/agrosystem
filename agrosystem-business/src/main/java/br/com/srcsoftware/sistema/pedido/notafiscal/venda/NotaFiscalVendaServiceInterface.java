package br.com.srcsoftware.sistema.pedido.notafiscal.venda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoDTO;

public interface NotaFiscalVendaServiceInterface{

	List< NotaFiscalVendaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, NotaFiscalVendaDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalVendaDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalVendaDTO dto ) throws ApplicationException;

	void excluir( NotaFiscalVendaDTO dto ) throws ApplicationException;

	NotaFiscalVendaDTO filtrarPorId( String id ) throws ApplicationException;

	String getTotalFaltaEntregar( String idProduto, String idPedido ) throws ApplicationException;

	List< NotaFiscalVendaDTO > filtrarAbertosAndamentos( String numeroNF, String tipoNF, HashMap< String, String > camposOrders ) throws ApplicationException;

	void adicionarItemRecebido( NotaFiscalVendaDTO notaFiscalVenda, ArrayList< ItemPedidoDTO > itensPedidosRestantes, String idItemPedidoReceber, String quantidadeItensAdicionar ) throws ApplicationException;

	void removerItemRecebido( String tipoNF, NotaFiscalVendaDTO notaFiscalVenda, ArrayList< ItemPedidoDTO > itensPedidosRestantes, String idItemPedidoReceber, String quantidadeItensRetornar ) throws ApplicationException;
}
