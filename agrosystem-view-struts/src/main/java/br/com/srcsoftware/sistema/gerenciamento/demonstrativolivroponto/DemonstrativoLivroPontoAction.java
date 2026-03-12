package br.com.srcsoftware.sistema.gerenciamento.demonstrativolivroponto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import br.com.srcsoftware.modular.manager.abstracts.AbstractUsuarioAction;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioFacade;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.MensagensResourcesApplication;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.modular.manager.view.utilidades.Messages;
import br.com.srcsoftware.sistema.pessoa.funcionario.horaextra.HoraExtraFacade;
import br.com.srcsoftware.sistema.pessoa.funcionario.horaextra.SaldoHoraExtraPOJO;

public final class DemonstrativoLivroPontoAction extends AbstractUsuarioAction< DemonstrativoLivroPontoForm >{

	public ActionForward abrirPainelDemonstrativoLivroPonto( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			DemonstrativoLivroPontoForm meuForm = this.getCastingForm( form, request );

			meuForm.limparMensagens( request );

			meuForm.limparTudo();

		} catch ( ApplicationException | Exception e ) {
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		}

		return filtrarDemonstrativoLivroPonto( mapping, form, request, response );
	}

	public ActionForward filtrarDemonstrativoLivroPonto( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			DemonstrativoLivroPontoForm meuForm = this.getCastingForm( form, request );
			
			LocalDate dataInicialParam = Utilidades.isNuloOuVazio( meuForm.getDataInicial() ) ? DateUtil.getPrimeiroDiaMesFromData(LocalDate.now()) : DateUtil.parseLocalDate( meuForm.getDataInicial() );
			LocalDate dataFinalParam = Utilidades.isNuloOuVazio( meuForm.getDataFinal() ) ? DateUtil.getUltimoDiaMesFromData(LocalDate.now())  : DateUtil.parseLocalDate( meuForm.getDataFinal() );
			meuForm.setDataInicial(DateUtil.parseLocalDate(dataInicialParam));
			meuForm.setDataFinal(DateUtil.parseLocalDate(dataFinalParam));

			List< SaldoHoraExtraPOJO > saldosHoraExtra = HoraExtraFacade.getInstance().filtrarSaldo( meuForm.getIdColaborador(), meuForm.getDataInicial(), meuForm.getDataFinal() );
			meuForm.setSaldosHoraExtra( saldosHoraExtra );

			Collections.sort( meuForm.getSaldosHoraExtra() );

			if ( Utilidades.isNuloOuVazio( meuForm.getDataInicial() ) || Utilidades.isNuloOuVazio( meuForm.getDataFinal() ) ) {
				meuForm.setTextoIntervaloPesquisa( "Até ".concat( DateUtil.parseLocalDate( LocalDate.now() ) ) );
			} else {
				meuForm.setTextoIntervaloPesquisa( meuForm.getDataInicial().concat( " à ".concat( meuForm.getDataFinal() ) ) );
			}

			//BigDecimal totalPagar = BigDecimal.ZERO;
			//for ( SaldoHoraExtraPOJO saldoCorrente : meuForm.getSaldosHoraExtra() ) {
				//System.out.println("ID: "+saldoCorrente.getIdColaborador()+" Saldo: "+saldoCorrente.getSaldo());
				//totalPagar = totalPagar.add( saldoCorrente.getSaldo() );
			//}
			//meuForm.setValorTotalPagar( Utilidades.parseBigDecimal( totalPagar ) );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( MensagensResourcesApplication.getString( "falha", new String[ ] { this.getClass().getSimpleName() + "-" + e.getMessage(), e.toString() } ) );
			e.printStackTrace();

			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );

		}

		return mapping.findForward( "demonstrativoLivroPontoAjax" );
	}

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarColaboradorAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			DemonstrativoLivroPontoForm meuForm = this.getCastingForm( form, request );

			FuncionarioDTO tipoFiltrar = new FuncionarioDTO();
			tipoFiltrar.getPessoaFisica().setRazaoSocial( meuForm.getNomeColaborador() );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "pessoaFisica.razaoSocial", "ASC" );

			List< FuncionarioDTO > encontrados = FuncionarioFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, tipoFiltrar );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			for ( FuncionarioDTO dtoCorrente : encontrados ) {

				map = new JSONObject();
				map.put( "value", dtoCorrente.getPessoaFisica().getRazaoSocial() );
				map.put( "data", dtoCorrente.getId() );
				arr.add( map );
			}
			json.put( "suggestions", arr );

			response.setContentType( "application/json" );
			response.setHeader( "Cache-Control", "nocache" );
			response.getWriter().print( json.toString() );
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( MensagensResourcesApplication.getString( "falha", new String[ ] { this.getClass().getSimpleName() + "-" + e.getMessage(), e.toString() } ) );
			e.printStackTrace();
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[ ] { this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ActionForward abrirModalDadosExcel( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		return mapping.findForward( "excelTabelaModalHoraExtra" );
	}
}
