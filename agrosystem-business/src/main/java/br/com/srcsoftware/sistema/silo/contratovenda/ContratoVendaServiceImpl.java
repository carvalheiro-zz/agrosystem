package br.com.srcsoftware.sistema.silo.contratovenda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.factory.ModelFactory;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public class ContratoVendaServiceImpl implements ContratoVendaServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private ContratoVendaDAOInterface daoInterface;

	private ModelFactory< ContratoVendaPO, ContratoVendaDTO > modelFactory = new ModelFactory<>();

	public ContratoVendaServiceImpl(){
		this.daoInterface = new ContratoVendaDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ContratoVendaDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			ContratoVendaPO po = this.modelFactory.getPO( ContratoVendaPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ContratoVendaDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			ContratoVendaPO po = this.modelFactory.getPO( ContratoVendaPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

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
	public void excluir( ContratoVendaDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			ContratoVendaPO po = this.modelFactory.getPO( ContratoVendaPO.class, dto );

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
	public ArrayList< ContratoVendaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ContratoVendaDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			ContratoVendaPO poFilter = this.modelFactory.getPO( ContratoVendaPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< ContratoVendaPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, camposBetween, poFilter );

			ArrayList< ContratoVendaDTO > dtosRetorno = new ArrayList<>();

			for ( ContratoVendaPO poCorrente : encontrados ) {
				BigDecimal quantidadeRestante = daoInterface.filtrarQuantidadeRestante( poCorrente.getId() );
				BigDecimal quantidadeEntregue = poCorrente.getQuantidade().subtract( quantidadeRestante );

				ContratoVendaDTO corrente = this.modelFactory.getDTO( ContratoVendaDTO.class, poCorrente );
				corrente.setQuantidadeRestante( Utilidades.parseBigDecimalOrZero( quantidadeRestante ) );
				corrente.setQuantidadeEntregue( Utilidades.parseBigDecimalOrZero( quantidadeEntregue ) );

				dtosRetorno.add( corrente );
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
	public ContratoVendaDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			ContratoVendaPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			BigDecimal quantidadeRestante = daoInterface.filtrarQuantidadeRestante( encontrado.getId() );
			BigDecimal quantidadeEntregue = encontrado.getQuantidade().subtract( quantidadeRestante );

			ContratoVendaDTO dto = this.modelFactory.getDTO( ContratoVendaDTO.class, encontrado );

			dto.setQuantidadeRestante( Utilidades.parseBigDecimalOrZero( quantidadeRestante ) );
			dto.setQuantidadeEntregue( Utilidades.parseBigDecimalOrZero( quantidadeEntregue ) );

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

	@Override
	public ContratoVendaDTO filtrarPorNumero( String numero ) throws ApplicationException {

		try {

			if ( !Utilidades.isNuloOuVazio( numero ) ) {
				new ContratoVendaDTO();
			}

			ContratoVendaPO encontrado = this.daoInterface.filtrarPorNumero( numero );

			if ( encontrado == null ) {
				return null;
			}

			BigDecimal quantidadeRestante = daoInterface.filtrarQuantidadeRestante( encontrado.getId() );
			BigDecimal quantidadeEntregue = encontrado.getQuantidade().subtract( quantidadeRestante );

			ContratoVendaDTO dto = this.modelFactory.getDTO( ContratoVendaDTO.class, encontrado );

			dto.setQuantidadeRestante( Utilidades.parseBigDecimalOrZero( quantidadeRestante ) );
			dto.setQuantidadeEntregue( Utilidades.parseBigDecimalOrZero( quantidadeEntregue ) );

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

	/*@Override
	public String filtrarQuantidadeRestantes( String numero ) throws ApplicationException {
	
		try {
	
			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}
	
			SaidaGraoPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );
	
			return this.modelFactory.getDTO( SaidaGraoDTO.class, encontrado );
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
	}*/
}
