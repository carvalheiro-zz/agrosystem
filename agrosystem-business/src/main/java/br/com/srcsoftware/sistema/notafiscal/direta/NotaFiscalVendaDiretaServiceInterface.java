package br.com.srcsoftware.sistema.notafiscal.direta;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.sistema.notafiscal.direta.item.ItemNotaFiscalVendaDiretaDTO;

public interface NotaFiscalVendaDiretaServiceInterface{

	List< NotaFiscalVendaDiretaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, NotaFiscalVendaDiretaDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalVendaDiretaDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalVendaDiretaDTO dto ) throws ApplicationException;

	void excluir( NotaFiscalVendaDiretaDTO dto ) throws ApplicationException;

	NotaFiscalVendaDiretaDTO filtrarPorId( String id ) throws ApplicationException;

	void adicionarItem( ItemNotaFiscalVendaDiretaDTO item, NotaFiscalVendaDiretaDTO nf ) throws ApplicationException;

	void removerItem( NotaFiscalVendaDiretaDTO nf, String idItem ) throws ApplicationException;

}
