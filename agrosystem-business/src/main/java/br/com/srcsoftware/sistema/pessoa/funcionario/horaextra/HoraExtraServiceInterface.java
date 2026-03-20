package br.com.srcsoftware.sistema.pessoa.funcionario.horaextra;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface HoraExtraServiceInterface{

	List< HoraExtraDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HoraExtraDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, HoraExtraDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, HoraExtraDTO dto ) throws ApplicationException;

	void excluir( HoraExtraDTO dto ) throws ApplicationException;

	HoraExtraDTO filtrarPorId( String id ) throws ApplicationException;

	List< SaldoHoraExtraPOJO > filtrarSaldo( String idColaborador, String dataInicial, String dataFinal ) throws ApplicationException;

	void gerarPDF( HttpServletResponse response, HashMap< String, String > camposOrders, HoraExtraDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;
}
