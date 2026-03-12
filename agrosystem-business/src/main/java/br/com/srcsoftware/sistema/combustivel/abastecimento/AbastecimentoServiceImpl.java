package br.com.srcsoftware.sistema.combustivel.abastecimento;

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
import br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoPO;

public class AbastecimentoServiceImpl implements AbastecimentoServiceInterface{
	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private AbastecimentoDAOInterface daoInterface;

	private ModelFactory< AbastecimentoPO, AbastecimentoDTO > modelFactory = new ModelFactory<>();

	public AbastecimentoServiceImpl(){
		this.daoInterface = new AbastecimentoDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, AbastecimentoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			String loginUsuario = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataAtual = LocalDateTime.now();

			hibernate.iniciarTransacao();

			AbastecimentoPO po = this.modelFactory.getPO( AbastecimentoPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), dataAtual );

			if ( po.getQuantidade().compareTo( BigDecimal.ZERO ) <= 0 ) {
				throw new ApplicationException( "O campo 'Quantidade' não pode ser ZERO!" );
			}

			this.gerenciarVeiculo( po, loginUsuario, dataAtual );

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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, AbastecimentoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			String loginUsuario = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataAtual = LocalDateTime.now();

			hibernate.iniciarTransacao();

			AbastecimentoPO po = this.modelFactory.getPO( AbastecimentoPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), dataAtual );

			if ( po.getQuantidade().compareTo( BigDecimal.ZERO ) <= 0 ) {
				throw new ApplicationException( "O campo 'Quantidade' não pode ser ZERO!" );
			}

			this.gerenciarVeiculo( po, loginUsuario, dataAtual );

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
	public void excluir( AbastecimentoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			AbastecimentoPO po = this.modelFactory.getPO( AbastecimentoPO.class, dto );

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
	public ArrayList< AbastecimentoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, AbastecimentoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			AbastecimentoPO poFilter = this.modelFactory.getPO( AbastecimentoPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< AbastecimentoPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, camposBetween, poFilter );

			ArrayList< AbastecimentoDTO > dtosRetorno = new ArrayList<>();

			for ( AbastecimentoPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( AbastecimentoDTO.class, poCorrente ) );
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
	public AbastecimentoDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			AbastecimentoPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			return this.modelFactory.getDTO( AbastecimentoDTO.class, encontrado );
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

	private void gerenciarVeiculo( AbastecimentoPO po, String usuario, LocalDateTime dataOcorrencia ) throws ApplicationException {
		if ( !Utilidades.isNuloOuVazio( po.getVeiculo() ) && !Utilidades.isNuloOuVazio( po.getVeiculo().getId() ) ) {
			po.setVeiculo( (VeiculoPO) new HibernateConnection().filtrarPorId( VeiculoPO.class, po.getVeiculo().getId() ) );
		} else {
			if ( Utilidades.isNuloOuVazio( po.getVeiculo() ) || ( Utilidades.isNuloOuVazio( po.getVeiculo().getCodigo() ) && Utilidades.isNuloOuVazio( po.getVeiculo().getModelo() ) && Utilidades.isNuloOuVazio( po.getVeiculo().getNome() ) && Utilidades.isNuloOuVazio( po.getVeiculo().getTipo() ) ) ) {
				po.setVeiculo( null );
				return;
			}
			po.getVeiculo().setarDadosAuditoria( usuario, dataOcorrencia );
		}
	}
}
