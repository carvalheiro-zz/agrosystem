package br.com.srcsoftware.sistema.silo.entrada;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.factory.ModelFactory;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.silo.entradagrao.EntradaGraoReport;

public class EntradaGraoServiceImpl implements EntradaGraoServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private EntradaGraoDAOInterface daoInterface;

	private ModelFactory< EntradaGraoPO, EntradaGraoDTO > modelFactory = new ModelFactory<>();

	public EntradaGraoServiceImpl(){
		this.daoInterface = new EntradaGraoDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, EntradaGraoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			EntradaGraoPO po = this.modelFactory.getPO( EntradaGraoPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

			if ( Utilidades.isNuloOuVazio( po.getSafra() ) || Utilidades.isNuloOuVazio( po.getSafra().getId() ) ) {
				throw new ApplicationException( "Safra não informada!" );
			}
			if ( Utilidades.isNuloOuVazio( po.getSetor() ) || Utilidades.isNuloOuVazio( po.getSetor().getId() ) ) {
				throw new ApplicationException( "Setor não informado!" );
			}

			dto.setId( this.daoInterface.inserir( hibernate, po ).getId().toString() );

			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, EntradaGraoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			EntradaGraoPO po = this.modelFactory.getPO( EntradaGraoPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

			if ( Utilidades.isNuloOuVazio( po.getSafra() ) || Utilidades.isNuloOuVazio( po.getSafra().getId() ) ) {
				throw new ApplicationException( "Safra não informada!" );
			}
			if ( Utilidades.isNuloOuVazio( po.getSetor() ) || Utilidades.isNuloOuVazio( po.getSetor().getId() ) ) {
				throw new ApplicationException( "Setor não informado!" );
			}

			this.daoInterface.alterar( hibernate, po );

			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( EntradaGraoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			EntradaGraoPO po = this.modelFactory.getPO( EntradaGraoPO.class, dto );

			this.daoInterface.excluir( hibernate, po );

			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public ArrayList< EntradaGraoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, EntradaGraoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			EntradaGraoPO poFilter = this.modelFactory.getPO( EntradaGraoPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< EntradaGraoPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, camposBetween, poFilter );

			ArrayList< EntradaGraoDTO > dtosRetorno = new ArrayList<>();

			for ( EntradaGraoPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( EntradaGraoDTO.class, poCorrente ) );
			}

			return dtosRetorno;
		} catch ( ApplicationException e ) {

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( ModelFactoryException e ) {
			this.logger.error( "Erro no ModelFactory" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro no ModelFactory" + System.lineSeparator() + e.getMessage().trim(), e );
		} catch ( Exception e ) {

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public EntradaGraoDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			EntradaGraoPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			return this.modelFactory.getDTO( EntradaGraoDTO.class, encontrado );
		} catch ( ApplicationException e ) {

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( ModelFactoryException e ) {
			this.logger.error( "Erro no ModelFactory" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro no ModelFactory" + System.lineSeparator() + e.getMessage().trim(), e );
		} catch ( Exception e ) {

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void gerarPDF( HttpServletResponse response, HashMap< String, String > camposOrders, EntradaGraoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			EntradaGraoPO poFilter = this.modelFactory.getPO( EntradaGraoPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< EntradaGraoPO > encontrados = this.daoInterface.filtrar( Paginacao.NAO, camposOrders, camposBetween, poFilter );

			ArrayList< EntradaGraoDTO > dtos = new ArrayList<>();

			for ( EntradaGraoPO poCorrente : encontrados ) {
				dtos.add( this.modelFactory.getDTO( EntradaGraoDTO.class, poCorrente ) );
			}

			String versaoSistema = Constantes.VERSAO_SISTEMA;

			EntradaGraoReport report = new EntradaGraoReport( "silo/entrada", null, versaoSistema );

			report.gerarRelatorio( response, dtos, getCamposPesquisa( dto, dataInicialParam, dataFinalParam ) );

		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}

	}

	private String getCamposPesquisa( EntradaGraoDTO dto, String dataInicialParam, String dataFinalParam ) {
		StringBuilder campos = new StringBuilder();

		if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
			campos.append( "De ".concat( dataInicialParam ).concat( " a " ).concat( dataFinalParam ).concat( " / " ) );
		}
		if ( !Utilidades.isNuloOuVazio( dto.getEstoquista().getPessoaFisica().getRazaoSocial() ) ) {
			campos.append( "Estoquista: ".concat( dto.getEstoquista().getPessoaFisica().getRazaoSocial() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getLocalArmazenagem().getNome() ) ) {
			campos.append( "Armazenagem: ".concat( dto.getLocalArmazenagem().getNome() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getSafra().getNome() ) ) {
			campos.append( "Safra: ".concat( dto.getSafra().getNome() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getSetor().getNome() ) ) {
			campos.append( "Setor: ".concat( dto.getSetor().getNome() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getVariedade().getNome() ) ) {
			campos.append( "Variedade: ".concat( dto.getVariedade().getNome() ).concat( " / " ) );
		}

		return campos.toString();
	}

}