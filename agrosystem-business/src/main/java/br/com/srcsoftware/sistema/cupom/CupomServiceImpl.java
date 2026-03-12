package br.com.srcsoftware.sistema.cupom;

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
import br.com.srcsoftware.modular.manager.utilidades.MensagensResourcesApplication;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.cupom.item.ItemCupomDTO;
import br.com.srcsoftware.sistema.cupom.item.ItemCupomPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoDTO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoFacade;

public final class CupomServiceImpl implements CupomServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private CupomDAOInterface daoInterface;

	private ModelFactory< CupomPO, CupomDTO > modelFactory = new ModelFactory<>();

	public CupomServiceImpl(){
		this.daoInterface = new CupomDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, CupomDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			String usuarioAlteracao = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataAlteracao = LocalDateTime.now();

			hibernate.iniciarTransacao();

			CupomPO po = this.modelFactory.getPO( CupomPO.class, dto );
			po.setarDadosAuditoria( usuarioAlteracao, dataAlteracao );
			po.setNotaEmitida( Boolean.valueOf( "false" ) );

			dto.setId( this.daoInterface.inserir( hibernate, po ).getId().toString() );

			this.gerenciarFornecedor( hibernate, po );

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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, CupomDTO dto ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();

		try {
			String usuarioAlteracao = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataAlteracao = LocalDateTime.now();

			hibernate.iniciarTransacao();

			CupomPO po = this.modelFactory.getPO( CupomPO.class, dto );
			po.setarDadosAuditoria( usuarioAlteracao, dataAlteracao );

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
	public void excluir( CupomDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			CupomPO po = this.modelFactory.getPO( CupomPO.class, dto );

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
	public ArrayList< CupomDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, CupomDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			CupomPO poFilter = this.modelFactory.getPO( CupomPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< CupomPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, camposBetween, poFilter );

			ArrayList< CupomDTO > dtosRetorno = new ArrayList<>();

			for ( CupomPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( CupomDTO.class, poCorrente ) );
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
	public CupomDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			CupomPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			return this.modelFactory.getDTO( CupomDTO.class, encontrado );
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

	private ArrayList< ItemCupomPO > getItensEntregaRemovidos( CupomPO compraProduto ) throws ApplicationException {
		CupomPO notaEncontrada = this.daoInterface.filtrarPorId( compraProduto.getId() );
		ArrayList< ItemCupomPO > itensRemovidos = new ArrayList<>();

		for ( ItemCupomPO itemEntregaCorrente : notaEncontrada.getItens() ) {
			boolean achei = false;
			for ( ItemCupomPO itemCorrente : compraProduto.getItens() ) {
				if ( itemEntregaCorrente.getId().equals( itemCorrente.getId() ) ) {
					achei = true;
					break;
				}
			}

			if ( !achei ) {
				ItemCupomPO itemRemovido = new ItemCupomPO();
				itemRemovido.setId( itemEntregaCorrente.getId() );
				itemRemovido.setProduto( itemEntregaCorrente.getProduto() );
				itemRemovido.setPrecoCusto( itemEntregaCorrente.getPrecoCusto() );
				itemRemovido.setQuantidade( itemEntregaCorrente.getQuantidade() );

				itensRemovidos.add( itemRemovido );
			}
		}

		return itensRemovidos;
	}

	private void gerenciarFornecedor( HibernateConnection hibernate, CupomPO cupom ) throws ApplicationException {
		if ( Utilidades.isNuloOuVazio( cupom.getFornecedor().getNome() ) ) {

			FornecedorJuridicoPO fornecedorEncontrado = (FornecedorJuridicoPO) new HibernateConnection().filtrarSinglePorCampo( FornecedorJuridicoPO.class, "Fornecedor padrão", "nome", HibernateConnection.COMITA_TRANSACAO );

			if ( Utilidades.isNuloOuVazio( fornecedorEncontrado ) ) {
				FornecedorJuridicoPO fornecedorInserir = new FornecedorJuridicoPO();
				fornecedorInserir.setNome( "Fornecedor padrão" );
				/*fornecedorInserir.setCelular( "(00)0000-00000" );
				fornecedorInserir.setFixo( "(00)0000-00000" );*/
				fornecedorInserir.setTelefone( "(00)0000-00000" );
				fornecedorInserir.setEndereco( "Endereão padrão" );
				cupom.setFornecedor( (FornecedorJuridicoPO) hibernate.inserir( fornecedorInserir ) );
			} else {
				cupom.setFornecedor( fornecedorEncontrado );
			}
		}
	}

	@Override
	public void adicionarItem( ItemCupomDTO item, CupomDTO cupom ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( item.getQuantidade() ) ) {
				item.setQuantidade( "1" );
			}

			ProdutoDTO produtoAdicionar = ProdutoFacade.getInstance().filtrarPorId( item.getProduto().getId() );
			item.setProduto( produtoAdicionar );

			cupom.getItens().add( item );
		} catch ( ApplicationException e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		}
	}

	@Override
	public void removerItem( CupomDTO cupom, String idItem ) throws ApplicationException {
		try {

			for ( ItemCupomDTO itemCupomCorrente : cupom.getItens() ) {
				if ( idItem.equals( itemCupomCorrente.getIdTemp() ) ) {
					cupom.getItens().remove( itemCupomCorrente );
					break;
				}
			}

		} catch ( Exception e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		}
	}
}
