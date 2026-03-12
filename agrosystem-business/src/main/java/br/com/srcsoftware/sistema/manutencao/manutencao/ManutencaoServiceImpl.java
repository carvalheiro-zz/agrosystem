package br.com.srcsoftware.sistema.manutencao.manutencao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.enuns.ManutencaoEnum;
import br.com.srcsoftware.factory.ModelFactory;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.manutencao.imovel.ImovelPO;
import br.com.srcsoftware.sistema.manutencao.implemento.ImplementoPO;
import br.com.srcsoftware.sistema.manutencao.servico.ServicoPO;
import br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;
import br.com.srcsoftware.sistema.pessoa.prestadorservico.PrestadorServicoPO;
import br.com.srcsoftware.sistema.safra.SafraPO;
import br.com.srcsoftware.sistema.safra.setor.SetorPO;

public class ManutencaoServiceImpl implements ManutencaoServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private ManutencaoDAOInterface daoInterface;

	private ModelFactory< ManutencaoPO, ManutencaoDTO > modelFactory = new ModelFactory<>();

	public ManutencaoServiceImpl(){
		this.daoInterface = new ManutencaoDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ManutencaoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			String usuarioAlteracao = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataAlteracao = LocalDateTime.now();

			hibernate.iniciarTransacao();

			ManutencaoPO po = this.modelFactory.getPO( ManutencaoPO.class, dto );
			po.setarDadosAuditoria( usuarioAlteracao, dataAlteracao );

			if ( po.getTipo().equals( ManutencaoEnum.TIPO_CULTURA.getValor() ) ) {
				po.setSetor( null );
			} else if ( po.getTipo().equals( ManutencaoEnum.TIPO_TUDO.getValor() ) ) {
				po.setSetor( null );
				po.setCultura( null );
			} else {
				if ( Utilidades.isNuloOuVazio( po.getSafra() ) || Utilidades.isNuloOuVazio( po.getSafra().getId() ) ) {
					throw new ApplicationException( "Safra não informada!" );
				}
				if ( Utilidades.isNuloOuVazio( po.getSetor() ) || Utilidades.isNuloOuVazio( po.getSetor().getId() ) ) {
					throw new ApplicationException( "Setor não informado!" );
				}
				po.setCultura( null );
			}

			this.gerenciarVeiculo( hibernate, po, usuarioAlteracao, dataAlteracao );
			this.gerenciarImplemento( hibernate, po, usuarioAlteracao, dataAlteracao );
			this.gerenciarImovel( hibernate, po, usuarioAlteracao, dataAlteracao );
			this.gerenciarFornecedor( hibernate, po, usuarioAlteracao, dataAlteracao );
			this.gerenciarServico( hibernate, po, usuarioAlteracao, dataAlteracao );
			this.gerenciarSetor( hibernate, po, usuarioAlteracao, dataAlteracao );
			this.gerenciarSafra( hibernate, po, usuarioAlteracao, dataAlteracao );
			gerenciarPrestador( hibernate, po, usuarioAlteracao, dataAlteracao );

			if ( Utilidades.isNuloOuVazio( po.getNotaFiscal() ) ) {
				po.setReciboOuNotaFiscal( ManutencaoEnum.RECIBO.getValor() );
			} else {
				po.setReciboOuNotaFiscal( ManutencaoEnum.NOTA_FISCAL.getValor() );
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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ManutencaoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			String usuarioAlteracao = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataAlteracao = LocalDateTime.now();

			hibernate.iniciarTransacao();

			ManutencaoPO po = this.modelFactory.getPO( ManutencaoPO.class, dto );
			po.setarDadosAuditoria( usuarioAlteracao, dataAlteracao );

			if ( po.getTipo().equals( ManutencaoEnum.TIPO_CULTURA.getValor() ) ) {
				po.setSetor( null );
			} else if ( po.getTipo().equals( ManutencaoEnum.TIPO_TUDO.getValor() ) ) {
				po.setSetor( null );
				po.setCultura( null );
			} else {
				if ( Utilidades.isNuloOuVazio( po.getSafra() ) || Utilidades.isNuloOuVazio( po.getSafra().getId() ) ) {
					throw new ApplicationException( "Safra não informada!" );
				}
				if ( Utilidades.isNuloOuVazio( po.getSetor() ) || Utilidades.isNuloOuVazio( po.getSetor().getId() ) ) {
					throw new ApplicationException( "Setor não informado!" );
				}
				po.setCultura( null );
			}

			this.gerenciarVeiculo( hibernate, po, usuarioAlteracao, dataAlteracao );
			this.gerenciarImplemento( hibernate, po, usuarioAlteracao, dataAlteracao );
			this.gerenciarImovel( hibernate, po, usuarioAlteracao, dataAlteracao );
			this.gerenciarFornecedor( hibernate, po, usuarioAlteracao, dataAlteracao );
			this.gerenciarServico( hibernate, po, usuarioAlteracao, dataAlteracao );
			this.gerenciarSetor( hibernate, po, usuarioAlteracao, dataAlteracao );
			this.gerenciarSafra( hibernate, po, usuarioAlteracao, dataAlteracao );
			gerenciarPrestador( hibernate, po, usuarioAlteracao, dataAlteracao );

			if ( Utilidades.isNuloOuVazio( po.getNotaFiscal() ) ) {
				po.setReciboOuNotaFiscal( ManutencaoEnum.RECIBO.getValor() );
			} else {
				po.setReciboOuNotaFiscal( ManutencaoEnum.NOTA_FISCAL.getValor() );
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
	public void excluir( ManutencaoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			ManutencaoPO po = this.modelFactory.getPO( ManutencaoPO.class, dto );

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
	public ArrayList< ManutencaoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ManutencaoDTO dto, String dataInicialParam, String dataFinalParam, String tipoManutencaoPesquisa ) throws ApplicationException {

		try {
			ManutencaoPO poFilter = this.modelFactory.getPO( ManutencaoPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< ManutencaoPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, camposBetween, poFilter, tipoManutencaoPesquisa );

			ArrayList< ManutencaoDTO > dtosRetorno = new ArrayList<>();

			for ( ManutencaoPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( ManutencaoDTO.class, poCorrente ) );
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
	public ManutencaoDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			ManutencaoPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			return this.modelFactory.getDTO( ManutencaoDTO.class, encontrado );
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

	private void gerenciarServico( HibernateConnection hibernateConnection, ManutencaoPO po, String usuario, LocalDateTime dataOcorrencia ) throws ApplicationException {
		if ( !Utilidades.isNuloOuVazio( po.getServico().getId() ) ) {
			po.setServico( (ServicoPO) new HibernateConnection().filtrarPorId( ServicoPO.class, po.getServico().getId() ) );
		} else {
			po.getServico().setarDadosAuditoria( usuario, dataOcorrencia );
		}
	}

	private void gerenciarFornecedor( HibernateConnection hibernateConnection, ManutencaoPO po, String usuario, LocalDateTime dataOcorrencia ) throws ApplicationException {
		if ( !Utilidades.isNuloOuVazio( po.getFornecedor().getId() ) ) {
			po.setFornecedor( (FornecedorJuridicoPO) new HibernateConnection().filtrarPorId( FornecedorJuridicoPO.class, po.getFornecedor().getId() ) );
		} else {
			po.setFornecedor( null );
		}
	}

	private void gerenciarImovel( HibernateConnection hibernateConnection, ManutencaoPO po, String usuario, LocalDateTime dataOcorrencia ) throws ApplicationException {
		if ( !Utilidades.isNuloOuVazio( po.getImovel() ) && !Utilidades.isNuloOuVazio( po.getImovel().getId() ) ) {
			po.setImovel( (ImovelPO) new HibernateConnection().filtrarPorId( ImovelPO.class, po.getImovel().getId() ) );
		} else {

			if ( Utilidades.isNuloOuVazio( po.getImovel() ) || Utilidades.isNuloOuVazio( po.getImovel().getNome() ) ) {
				po.setImovel( null );
				return;
			}

			po.getImovel().setarDadosAuditoria( usuario, dataOcorrencia );
		}
	}

	private void gerenciarVeiculo( HibernateConnection hibernateConnection, ManutencaoPO po, String usuario, LocalDateTime dataOcorrencia ) throws ApplicationException {
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

	private void gerenciarImplemento( HibernateConnection hibernateConnection, ManutencaoPO po, String usuario, LocalDateTime dataOcorrencia ) throws ApplicationException {
		if ( !Utilidades.isNuloOuVazio( po.getImplemento() ) && !Utilidades.isNuloOuVazio( po.getImplemento().getId() ) ) {
			po.setImplemento( (ImplementoPO) new HibernateConnection().filtrarPorId( ImplementoPO.class, po.getImplemento().getId() ) );
		} else {
			if ( Utilidades.isNuloOuVazio( po.getImplemento() ) || ( Utilidades.isNuloOuVazio( po.getImplemento().getCodigo() ) && Utilidades.isNuloOuVazio( po.getImplemento().getModelo() ) && Utilidades.isNuloOuVazio( po.getImplemento().getNome() ) ) ) {
				po.setImplemento( null );
				return;
			}
			po.getImplemento().setarDadosAuditoria( usuario, dataOcorrencia );
		}
	}

	private void gerenciarSetor( HibernateConnection hibernateConnection, ManutencaoPO po, String usuario, LocalDateTime dataOcorrencia ) throws ApplicationException {
		if ( !Utilidades.isNuloOuVazio( po.getSetor() ) && !Utilidades.isNuloOuVazio( po.getSetor().getId() ) ) {
			po.setSetor( (SetorPO) new HibernateConnection().filtrarPorId( SetorPO.class, po.getSetor().getId() ) );
		} else {
			po.setSetor( null );
		}
	}

	private void gerenciarSafra( HibernateConnection hibernateConnection, ManutencaoPO po, String usuario, LocalDateTime dataOcorrencia ) throws ApplicationException {
		if ( !Utilidades.isNuloOuVazio( po.getSafra().getId() ) ) {
			po.setSafra( (SafraPO) new HibernateConnection().filtrarPorId( SafraPO.class, po.getSafra().getId() ) );
		} else {
			po.setSafra( null );
		}
	}

	private void gerenciarPrestador( HibernateConnection hibernateConnection, ManutencaoPO po, String usuario, LocalDateTime dataOcorrencia ) throws ApplicationException {
		if ( !Utilidades.isNuloOuVazio( po.getPrestador() ) && !Utilidades.isNuloOuVazio( po.getPrestador().getId() ) ) {
			po.setPrestador( (PrestadorServicoPO) new HibernateConnection().filtrarPorId( PrestadorServicoPO.class, po.getPrestador().getId() ) );
		} else {
			po.setPrestador( null );
		}
	}
}