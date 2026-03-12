package br.com.srcsoftware.sistema.silo.saida;

import java.math.BigDecimal;
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
import br.com.srcsoftware.sistema.silo.contratovenda.ContratoVendaDAOImpl;
import br.com.srcsoftware.sistema.silo.saidagrao.SaidaGraoReport;

public class SaidaGraoServiceImpl implements SaidaGraoServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private SaidaGraoDAOInterface daoInterface;

	private ModelFactory< SaidaGraoPO, SaidaGraoDTO > modelFactory = new ModelFactory<>();

	public SaidaGraoServiceImpl(){
		this.daoInterface = new SaidaGraoDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, SaidaGraoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			SaidaGraoPO po = this.modelFactory.getPO( SaidaGraoPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

			if ( Utilidades.isNuloOuVazio( po.getSafra01() ) || Utilidades.isNuloOuVazio( po.getSafra01().getId() ) ) {
				throw new ApplicationException( "Safra não informada!" );
			}

			if ( po.getLocalArmazenagem01().getId() == null ) {
				po.setLocalArmazenagem01( null );
			}
			if ( po.getLocalArmazenagem02().getId() == null ) {
				po.setLocalArmazenagem02( null );
			}
			if ( po.getSafra01().getId() == null ) {
				po.setSafra01( null );
			}
			if ( po.getSafra02().getId() == null ) {
				po.setSafra02( null );
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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, SaidaGraoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			SaidaGraoPO po = this.modelFactory.getPO( SaidaGraoPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

			if ( Utilidades.isNuloOuVazio( po.getSafra01() ) || Utilidades.isNuloOuVazio( po.getSafra01().getId() ) ) {
				throw new ApplicationException( "Safra não informada!" );
			}

			if ( po.getLocalArmazenagem01().getId() == null ) {
				po.setLocalArmazenagem01( null );
			}
			if ( po.getLocalArmazenagem02().getId() == null ) {
				po.setLocalArmazenagem02( null );
			}
			if ( po.getSafra01().getId() == null ) {
				po.setSafra01( null );
			}
			if ( po.getSafra02().getId() == null ) {
				po.setSafra02( null );
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
	public void excluir( SaidaGraoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			SaidaGraoPO po = this.modelFactory.getPO( SaidaGraoPO.class, dto );

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
	public ArrayList< SaidaGraoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SaidaGraoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			SaidaGraoPO poFilter = this.modelFactory.getPO( SaidaGraoPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< SaidaGraoPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, camposBetween, poFilter );

			ArrayList< SaidaGraoDTO > dtosRetorno = new ArrayList<>();

			for ( SaidaGraoPO poCorrente : encontrados ) {
				SaidaGraoDTO saidaDTO = this.modelFactory.getDTO( SaidaGraoDTO.class, poCorrente );
				//BigDecimal quantidadeRestante = new ContratoVendaDAOImpl().filtrarQuantidadeRestante( poCorrente.getContratoVenda().getId() );
				//saidaDTO.getContratoVenda().setQuantidadeRestante( Utilidades.parseBigDecimalOrZero( quantidadeRestante ) );

				dtosRetorno.add( saidaDTO );
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
	public SaidaGraoDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			SaidaGraoPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			SaidaGraoDTO dto = this.modelFactory.getDTO( SaidaGraoDTO.class, encontrado );

			BigDecimal quantidadeRestante = new ContratoVendaDAOImpl().filtrarQuantidadeRestante( encontrado.getContratoVenda().getId() );
			BigDecimal quantidadeEntregue = encontrado.getContratoVenda().getQuantidade().subtract( quantidadeRestante );
			dto.getContratoVenda().setQuantidadeRestante( Utilidades.parseBigDecimalOrZero( quantidadeRestante ) );
			dto.getContratoVenda().setQuantidadeEntregue( Utilidades.parseBigDecimalOrZero( quantidadeEntregue ) );

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
	public void gerarPDF( HttpServletResponse response, HashMap< String, String > camposOrders, SaidaGraoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			SaidaGraoPO poFilter = this.modelFactory.getPO( SaidaGraoPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< SaidaGraoPO > encontrados = this.daoInterface.filtrar( Paginacao.NAO, camposOrders, camposBetween, poFilter );

			ArrayList< SaidaGraoDTO > dtos = new ArrayList<>();

			for ( SaidaGraoPO poCorrente : encontrados ) {
				dtos.add( this.modelFactory.getDTO( SaidaGraoDTO.class, poCorrente ) );
			}

			String versaoSistema = Constantes.VERSAO_SISTEMA;

			SaidaGraoReport report = new SaidaGraoReport( "silo/saida", null, versaoSistema );

			report.gerarRelatorio( response, dtos, getCamposPesquisa( dto, dataInicialParam, dataFinalParam ) );

		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}

	}

	private String getCamposPesquisa( SaidaGraoDTO dto, String dataInicialParam, String dataFinalParam ) {
		StringBuilder campos = new StringBuilder();

		if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
			campos.append( "De ".concat( dataInicialParam ).concat( " a " ).concat( dataFinalParam ).concat( " / " ) );
		}
		if ( !Utilidades.isNuloOuVazio( dto.getEstoquista().getPessoaFisica().getRazaoSocial() ) ) {
			campos.append( "Estoquista: ".concat( dto.getEstoquista().getPessoaFisica().getRazaoSocial() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getCliente().getNome() ) ) {
			campos.append( "Cliente: ".concat( dto.getCliente().getNome() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getMotorista() ) ) {
			campos.append( "Motorista: ".concat( dto.getMotorista() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getLocalArmazenagem01().getNome() ) ) {
			campos.append( "Armazenagem: ".concat( dto.getLocalArmazenagem01().getNome() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getSafra01().getNome() ) ) {
			campos.append( "Safra: ".concat( dto.getSafra01().getNome() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getSafra01().getNome() ) ) {
			campos.append( "Cultura: ".concat( dto.getCultura().getNome() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getPlaca() ) ) {
			campos.append( "Placa: ".concat( dto.getPlaca() ).concat( " / " ) );
		}

		return campos.toString();
	}
}
