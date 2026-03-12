package br.com.srcsoftware.sistema.demonstrativoproducao;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.srcsoftware.modular.manager.empresa.EmpresaDTO;
import br.com.srcsoftware.modular.manager.empresa.EmpresaFacade;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.safra.SafraDAOImpl;
import br.com.srcsoftware.sistema.safra.SafraDAOInterface;
import br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO;
import br.com.srcsoftware.sistema.silo.resumoproducao.ResumoProducaoGraoReport;

public class DemonstrativoProducaoServiceImpl implements DemonstrativoProducaoServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private SafraDAOInterface daoInterface;

	public DemonstrativoProducaoServiceImpl(){
		this.daoInterface = new SafraDAOImpl();
	}

	@Override
	public List< InformacoesProducaoSafraPOJO > filtrarSaldoProducao( String idSafra, String idSetor, String idCultura, String idVariedade ) throws ApplicationException {

		try {

			Long idSafraLong = null;
			if ( !Utilidades.isNuloOuVazio( idSafra ) ) {
				idSafraLong = Long.valueOf( idSafra );
			}
			Long idSetorLong = null;
			if ( !Utilidades.isNuloOuVazio( idSetor ) ) {
				idSetorLong = Long.valueOf( idSetor );
			}
			Long idCulturaLong = null;
			if ( !Utilidades.isNuloOuVazio( idCultura ) ) {
				idCulturaLong = Long.valueOf( idCultura );
			}
			Long idVariedadeLong = null;
			if ( !Utilidades.isNuloOuVazio( idVariedade ) ) {
				idVariedadeLong = Long.valueOf( idVariedade );
			}

			List< InformacoesProducaoSafraPOJO > encontrados = this.daoInterface.filtrarSaldoProducao( idSafraLong, idSetorLong, idCulturaLong, idVariedadeLong );

			return encontrados;
		} catch ( ApplicationException e ) {

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void gerarPDF( HttpServletResponse response, String idEmpresa, List< InformacoesProducaoSafraPOJO > encontrados, String safra, String setor, String cultura, String variedade ) throws ApplicationException {

		try {

			EmpresaDTO empresa = EmpresaFacade.getInstance().filtrarPorId( idEmpresa );

			String versaoSistema = Constantes.VERSAO_SISTEMA;

			ResumoProducaoGraoReport report = new ResumoProducaoGraoReport( "silo/resumo", null, versaoSistema );

			report.gerarRelatorio( response, empresa, encontrados, getCamposPesquisa( safra, setor, cultura, variedade ) );

		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}

	}

	private String getCamposPesquisa( String safra, String setor, String cultura, String variedade ) {
		StringBuilder campos = new StringBuilder();

		if ( !Utilidades.isNuloOuVazio( safra ) ) {
			campos.append( "Safra: ".concat( safra ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( setor ) ) {
			campos.append( "Setor: ".concat( setor ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( cultura ) ) {
			campos.append( "Cultura: ".concat( cultura ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( variedade ) ) {
			campos.append( "Variedade: ".concat( variedade ).concat( " / " ) );
		}

		return campos.toString();
	}
}