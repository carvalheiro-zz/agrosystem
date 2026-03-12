package br.com.srcsoftware.sistema.safra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadeDTO;
import br.com.srcsoftware.sistema.safra.setorsafra.SetorSafraDTO;
import br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO;

public interface SafraServiceInterface{

	List< SafraDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SafraDTO dto ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, SafraDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, SafraDTO dto ) throws ApplicationException;

	void excluir( SafraDTO dto ) throws ApplicationException;

	SafraDTO filtrarPorId( String id ) throws ApplicationException;

	void adicionarSetor( ArrayList< SetorSafraDTO > setoresSafras, String idSetor, String nomeSetor, String area, VariedadeDTO variedade/*, String variedade*/ );

	void removerSetor( ArrayList< SetorSafraDTO > setoresSafras, String idSetor );

	LocalDate[ ] getDataInicioFimSafra( String idSafra ) throws ApplicationException;

	InformacoesProducaoSafraPOJO filtrarSaldoProducaoSafra( String idSafra, String idCultura ) throws ApplicationException;

}
