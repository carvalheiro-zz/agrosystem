package br.com.srcsoftware.sistema.aplicacao.aplicacao;

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
import br.com.srcsoftware.enuns.ManutencaoEnum;
import br.com.srcsoftware.factory.ModelFactory;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.aplicacaoinsumo.AplicacaoInsumoReport;
import br.com.srcsoftware.sistema.pessoa.prestadorservico.PrestadorServicoPO;

public class AplicacaoServiceImpl implements AplicacaoServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private AplicacaoDAOInterface daoInterface;

	private ModelFactory< AplicacaoPO, AplicacaoDTO > modelFactory = new ModelFactory<>();

	public AplicacaoServiceImpl(){
		this.daoInterface = new AplicacaoDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, AplicacaoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			String usuarioAlteracao = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataAlteracao = LocalDateTime.now();

			hibernate.iniciarTransacao();

			AplicacaoPO po = this.modelFactory.getPO( AplicacaoPO.class, dto );
			po.setarDadosAuditoria( usuarioAlteracao, dataAlteracao );

			if ( po.getQuantidade().compareTo( BigDecimal.ZERO ) <= 0 ) {
				throw new ApplicationException( "O campo 'Quantidade' não pode ser ZERO!" );
			}

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

			gerenciarPrestador( hibernate, po, usuarioAlteracao, dataAlteracao );

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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, AplicacaoDTO dto ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();

		try {
			String usuarioAlteracao = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataAlteracao = LocalDateTime.now();

			hibernate.iniciarTransacao();

			AplicacaoPO po = this.modelFactory.getPO( AplicacaoPO.class, dto );
			po.setarDadosAuditoria( usuarioAlteracao, dataAlteracao );

			if ( po.getQuantidade().compareTo( BigDecimal.ZERO ) <= 0 ) {
				throw new ApplicationException( "O campo 'Quantidade' não pode ser ZERO!" );
			}

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

			gerenciarPrestador( hibernate, po, usuarioAlteracao, dataAlteracao );

			this.daoInterface.inserir( hibernate, po );

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
	public void excluir( AplicacaoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			AplicacaoPO po = this.modelFactory.getPO( AplicacaoPO.class, dto );

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
	public ArrayList< AplicacaoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, AplicacaoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			AplicacaoPO poFilter = this.modelFactory.getPO( AplicacaoPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< AplicacaoPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, camposBetween, poFilter );

			ArrayList< AplicacaoDTO > dtosRetorno = new ArrayList<>();

			for ( AplicacaoPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( AplicacaoDTO.class, poCorrente ) );
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
	public AplicacaoDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			AplicacaoPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			return this.modelFactory.getDTO( AplicacaoDTO.class, encontrado );
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

	private void gerenciarPrestador( HibernateConnection hibernateConnection, AplicacaoPO po, String usuario, LocalDateTime dataOcorrencia ) throws ApplicationException {
		if ( !Utilidades.isNuloOuVazio( po.getPrestador() ) && !Utilidades.isNuloOuVazio( po.getPrestador().getId() ) ) {
			po.setPrestador( (PrestadorServicoPO) new HibernateConnection().filtrarPorId( PrestadorServicoPO.class, po.getPrestador().getId() ) );
		} else {
			po.setPrestador( null );
		}
	}

	@Override
	public ArrayList< AplicacaoDTO > filtrarParaTotais( AplicacaoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			AplicacaoPO poFilter = this.modelFactory.getPO( AplicacaoPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< AplicacaoPO > encontrados = this.daoInterface.filtrarParaTotais( camposBetween, poFilter );

			ArrayList< AplicacaoDTO > dtosRetorno = new ArrayList<>();

			for ( AplicacaoPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( AplicacaoDTO.class, poCorrente ) );
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
	public void gerarPDF( HttpServletResponse response, HashMap< String, String > camposOrders, AplicacaoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			AplicacaoPO poFilter = this.modelFactory.getPO( AplicacaoPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< AplicacaoPO > encontrados = this.daoInterface.filtrar( Paginacao.NAO, camposOrders, camposBetween, poFilter );

			ArrayList< AplicacaoDTO > dtos = new ArrayList<>();

			for ( AplicacaoPO poCorrente : encontrados ) {
				dtos.add( this.modelFactory.getDTO( AplicacaoDTO.class, poCorrente ) );
			}

			String versaoSistema = Constantes.VERSAO_SISTEMA;

			AplicacaoInsumoReport report = new AplicacaoInsumoReport( "aplicacaoinsumo", null, versaoSistema );

			report.gerarRelatorio( response, dtos, getCamposPesquisa( dto, dataInicialParam, dataFinalParam ) );

		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}

	}

	private String getCamposPesquisa( AplicacaoDTO dto, String dataInicialParam, String dataFinalParam ) {
		StringBuilder campos = new StringBuilder();
		/*
		De 01/01/2019 a 31/01/2019 
		Aplicador: Claudio Correa Armando Dias 
		Almoxarife: Fernando Pedro Marqueil Filho 
		Produto: [Insumo] Adubo Foliar Kellus Inox Pct 3kg Produquimica (KG) 
		Categoria: Adubo Foliar 
		Tipo: Safra/Setor 
		Setor: 7-A 
		Prestador: Gabriel Damiani Carvalheiro
		*/
		if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
			campos.append( "De ".concat( dataInicialParam ).concat( " a " ).concat( dataFinalParam ).concat( " / " ) );
		}
		if ( !Utilidades.isNuloOuVazio( dto.getAplicador().getPessoaFisica().getRazaoSocial() ) ) {
			campos.append( "Aplicador: ".concat( dto.getAplicador().getPessoaFisica().getRazaoSocial() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getAlmoxarife().getPessoaFisica().getRazaoSocial() ) ) {
			campos.append( "Almoxarife: ".concat( dto.getAlmoxarife().getPessoaFisica().getRazaoSocial() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getProduto().getNomeCompleto() ) ) {
			campos.append( "Produto: ".concat( dto.getProduto().getNomeCompleto() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getProduto().getTipo().getNome() ) ) {
			campos.append( "Categoria: ".concat( dto.getProduto().getTipo().getNome() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getTipo() ) ) {
			campos.append( "Tipo: ".concat( dto.getTipo() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getSafra().getNome() ) ) {
			campos.append( "Safra: ".concat( dto.getSafra().getNome() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getSetor().getNome() ) ) {
			campos.append( "Setor: ".concat( dto.getSetor().getNome() ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( dto.getCultura().getNome() ) ) {
			campos.append( "Cultura: ".concat( dto.getCultura().getNome() ).concat( " / " ) );
		}
		if ( !Utilidades.isNuloOuVazio( dto.getPrestador().getNome() ) ) {
			campos.append( "Prestador: ".concat( dto.getPrestador().getNome() ).concat( " / " ) );
		}

		return campos.toString();
	}

}
