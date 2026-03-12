package br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaDTO;

public interface NotaFiscalSimplesRemessaServiceInterface{

	List< NotaFiscalSimplesRemessaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, NotaFiscalSimplesRemessaDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalSimplesRemessaDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalSimplesRemessaDTO dto ) throws ApplicationException;

	void excluir( NotaFiscalSimplesRemessaDTO dto ) throws ApplicationException;

	NotaFiscalSimplesRemessaDTO filtrarPorId( String id ) throws ApplicationException;

	String getTotalFaltaEntregar( String idProduto, String idNotaFutura ) throws ApplicationException;

	void adicionarItemRecebido( NotaFiscalSimplesRemessaDTO notaFiscalSimplesRemessa, ArrayList< ItemNotaFiscalVendaDTO > itensNotaFiscalRestantes, String idItemNotaFiscalReceber, String quantidadeItensAdicionar ) throws ApplicationException;

	void removerItemRecebido( NotaFiscalSimplesRemessaDTO notaFiscalSimplesRemessa, ArrayList< ItemNotaFiscalVendaDTO > itensNotaFiscalRestantes, String idItemNotaFiscalRetornar, String quantidadeItensRetornar ) throws ApplicationException;

}
