package br.com.srcsoftware.sistema.notafiscal.rateio.receita;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.financeiro.contareceber.ContaReceberServiceInterface;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.sistema.notafiscal.rateio.NotaFiscalRateioDTO;
import br.com.srcsoftware.sistema.notafiscal.rateio.itemnotafiscalrateio.ItemNotaFiscalRateioDTO;

public interface NotaFiscalRateioReceitaServiceInterface extends ContaReceberServiceInterface{

	List filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, NotaFiscalRateioDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalRateioDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalRateioDTO dto ) throws ApplicationException;

	void excluir( NotaFiscalRateioDTO dto ) throws ApplicationException;

	NotaFiscalRateioDTO filtrarPorId( String id ) throws ApplicationException;

	void adicionarItem( ItemNotaFiscalRateioDTO item, NotaFiscalRateioDTO nfr ) throws ApplicationException;

	void removerItem( NotaFiscalRateioDTO nfr, String idItem ) throws ApplicationException;

}
