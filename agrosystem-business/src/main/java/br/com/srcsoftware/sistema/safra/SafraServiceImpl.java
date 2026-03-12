package br.com.srcsoftware.sistema.safra;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.factory.ModelFactory;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.modular.manager.utilidades.MensagensResourcesApplication;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadeDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorFacade;
import br.com.srcsoftware.sistema.safra.setorsafra.SetorSafraDTO;
import br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO;

public class SafraServiceImpl implements SafraServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private SafraDAOInterface daoInterface;

	private ModelFactory< SafraPO, SafraDTO > modelFactory = new ModelFactory<>();

	public SafraServiceImpl(){
		this.daoInterface = new SafraDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, SafraDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			String usuarioOcorrencia = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataOcorrenciao = LocalDateTime.now();

			hibernate.iniciarTransacao();

			SafraPO po = this.modelFactory.getPO( SafraPO.class, dto );
			po.setarDadosAuditoria( usuarioOcorrencia, dataOcorrenciao );

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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, SafraDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			String usuarioOcorrencia = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataOcorrenciao = LocalDateTime.now();

			hibernate.iniciarTransacao();

			SafraPO po = this.modelFactory.getPO( SafraPO.class, dto );
			po.setarDadosAuditoria( usuarioOcorrencia, dataOcorrenciao );

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
	public void excluir( SafraDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			SafraPO po = this.modelFactory.getPO( SafraPO.class, dto );

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
	public ArrayList< SafraDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SafraDTO dto ) throws ApplicationException {

		try {
			SafraPO poFilter = this.modelFactory.getPO( SafraPO.class, dto );

			List< SafraPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, poFilter );

			ArrayList< SafraDTO > dtosRetorno = new ArrayList<>();

			for ( SafraPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( SafraDTO.class, poCorrente ) );
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
	public SafraDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			SafraPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			SafraDTO dto = this.modelFactory.getDTO( SafraDTO.class, encontrado );

			Collections.sort( dto.getSetoresSafras() );

			return dto;
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

	// Parei aqui... corrigir todos os AutoCompletes que usam resultTransform
	// ############# adicionar e remover setoresSafras ###############
	@Override
	public void adicionarSetor( ArrayList< SetorSafraDTO > setoresSafras, String idSetor, String nomeSetor, String area, VariedadeDTO variedade/* , String variedade */ ) {
		try {

			if ( Utilidades.isNuloOuVazio( idSetor ) ) {
				throw new ApplicationException( "Setor [" + nomeSetor + "] não cadastrada!" );
			}

			SetorDTO setor = SetorFacade.getInstance().filtrarPorId( idSetor );

			/*
			 * for ( SetorSafraDTO setorSafraCorrente : setoresSafras ) {
			 * if ( setorSafraCorrente.getSetor().equals( setor ) ) {
			 * return;
			 * }
			 * }
			 */

			SetorSafraDTO setorSafraAdicionar = new SetorSafraDTO();
			// setorSafraAdicionar.setId( "temp_" + Utilidades.generateId() );
			setorSafraAdicionar.getIdTemp();
			setorSafraAdicionar.setArea( area );
			setorSafraAdicionar.setVariedade( variedade );
			setorSafraAdicionar.setSetor( setor );
			// setorSafraAdicionar.setVariedade( variedade );

			setoresSafras.add( setorSafraAdicionar );

		} catch ( Exception e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
		} catch ( ApplicationException e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
		}
	}

	@Override
	public void removerSetor( ArrayList< SetorSafraDTO > setoresSafras, String idSetor ) {
		try {

			for ( SetorSafraDTO setorSafraCorrente : setoresSafras ) {
				if ( setorSafraCorrente.getIdTemp().equals( idSetor ) ) {
					setoresSafras.remove( setorSafraCorrente );
					break;
				}
			}

		} catch ( Exception e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
		}
	}

	@Override
	public LocalDate[ ] getDataInicioFimSafra( String idSafra ) throws ApplicationException {

		try {

			LocalDate[ ] encontrados = this.daoInterface.getDataInicioFimSafra( Long.valueOf( idSafra ) );

			return encontrados;
		} catch ( ApplicationException e ) {

			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw e;
		} catch ( Exception e ) {

			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		}
	}

	@Override
	public InformacoesProducaoSafraPOJO filtrarSaldoProducaoSafra( String idSafra, String idCultura ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( idCultura ) ) {
				throw new ApplicationException( "Cultura não informada!" );
			}

			if ( Utilidades.isNuloOuVazio( idSafra ) ) {
				throw new ApplicationException( "Safra não informada!" );
			}

			URL url = null;
			url = this.getClass().getResource( "/selects_demonstrativo/totalProducaoCulturaPorSafra.txt" );

			Path origem = Paths.get( url.toURI() );
			List< String > linhas = Files.readAllLines( origem, Charset.forName( "ISO-8859-1" ) );

			final StringBuffer SQL = new StringBuffer();

			for ( String linhaQueryCorrente : linhas ) {
				SQL.append( linhaQueryCorrente.concat( " " ) );
			}

			HashMap< String, Object > parametros = new HashMap<>();

			parametros.put( "idSafraParam", Long.valueOf( idSafra ) );
			parametros.put( "idCulturaParam", Long.valueOf( idCultura ) );

			InformacoesProducaoSafraPOJO encontrado = this.daoInterface.filtrarSaldoProducaoSafra( SQL, parametros );

			return encontrado;
		} catch ( ApplicationException e ) {

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}
}