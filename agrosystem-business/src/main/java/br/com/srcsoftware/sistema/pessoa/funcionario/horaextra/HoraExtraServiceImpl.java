package br.com.srcsoftware.sistema.pessoa.funcionario.horaextra;

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
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDAOImpl;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDAOInterface;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioPO;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public class HoraExtraServiceImpl implements HoraExtraServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private HoraExtraDAOInterface daoInterface;

	private ModelFactory< HoraExtraPO, HoraExtraDTO > modelFactory = new ModelFactory<>();

	public HoraExtraServiceImpl(){
		this.daoInterface = new HoraExtraDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, HoraExtraDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			HoraExtraPO po = this.modelFactory.getPO( HoraExtraPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

			// Fraciona as horas informadas em 50% - 100% - Domingo/Feriado
			detalharHorasExtra(po);
			
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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, HoraExtraDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			HoraExtraPO po = this.modelFactory.getPO( HoraExtraPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

			// Fraciona as horas informadas em 50% - 100% - Domingo/Feriado
			detalharHorasExtra(po);
						
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
	public void excluir( HoraExtraDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			HoraExtraPO po = this.modelFactory.getPO( HoraExtraPO.class, dto );

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
	public ArrayList< HoraExtraDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HoraExtraDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			HoraExtraPO poFilter = this.modelFactory.getPO( HoraExtraPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< HoraExtraPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, camposBetween, poFilter );

			ArrayList< HoraExtraDTO > dtosRetorno = new ArrayList<>();

			for ( HoraExtraPO poCorrente : encontrados ) {				
				dtosRetorno.add( this.modelFactory.getDTO( HoraExtraDTO.class, poCorrente ) );
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
	public HoraExtraDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			HoraExtraPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			return this.modelFactory.getDTO( HoraExtraDTO.class, encontrado );
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
	public List< SaldoHoraExtraPOJO > filtrarSaldo( String idColaborador, String dataInicial, String dataFinal ) throws ApplicationException {

		try {

			Long idColaboradorParam = Utilidades.isNuloOuVazio( idColaborador ) ? null : Long.valueOf( idColaborador );
			LocalDate dataInicialParam = Utilidades.isNuloOuVazio( dataInicial ) ? DateUtil.getPrimeiroDiaMesFromData(LocalDate.now()) : DateUtil.parseLocalDate( dataInicial );
			LocalDate dataFinalParam = Utilidades.isNuloOuVazio( dataFinal ) ? DateUtil.getUltimoDiaMesFromData(LocalDate.now())  : DateUtil.parseLocalDate( dataFinal );

			List< SaldoHoraExtraPOJO > encontrados = this.daoInterface.filtrarSaldo( idColaboradorParam, dataInicialParam, dataFinalParam );

			// Buscando o Colaborador relacionado ao Saldo de Horas Extras
			FuncionarioDAOInterface daoFuncionario = new FuncionarioDAOImpl();
			ModelFactory< FuncionarioPO, FuncionarioDTO > modelFactoryFuncionario = new ModelFactory<>();

			for ( SaldoHoraExtraPOJO saldoCorrente : encontrados ) {
				FuncionarioPO colaborador = ( daoFuncionario.filtrarPorId( Long.valueOf( saldoCorrente.getIdColaborador() ) ) );
				saldoCorrente.setColaborador( modelFactoryFuncionario.getDTO( FuncionarioDTO.class, colaborador ) );
			}

			return encontrados;
		} catch ( ApplicationException e ) {

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
		
		/*try {
			
			LocalDate dataInicialParam = Utilidades.isNuloOuVazio( dataInicial ) ? DateUtil.getPrimeiroDiaMesFromData(LocalDate.now()) : DateUtil.parseLocalDate( dataInicial );
			LocalDate dataFinalParam = Utilidades.isNuloOuVazio( dataFinal ) ? DateUtil.getUltimoDiaMesFromData(LocalDate.now())  : DateUtil.parseLocalDate( dataFinal );

			HoraExtraDTO dto = new HoraExtraDTO();
			dto.setColaborador(new FuncionarioDTO());
			dto.getColaborador().setId(idColaborador);	
			dto.setTipo(HoraExtraDTO.TIPO_LANCAMENTO);
			
			ArrayList< HoraExtraDTO > encontrados = filtrar(Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, dto, DateUtil.parseLocalDate(dataInicialParam), DateUtil.parseLocalDate(dataFinalParam));//( idColaboradorParam, dataInicialParam, dataFinalParam );

			ArrayList< SaldoHoraExtraPOJO > saldosGerados = new ArrayList<>();
			
			for ( HoraExtraDTO dtoCorrente : encontrados ) {
				HoraExtraPO poCorrente = this.modelFactory.getPO( HoraExtraPO.class, dtoCorrente );
				
				SaldoHoraExtraPOJO saldo = new SaldoHoraExtraPOJO();
				
				if(dtoCorrente.getColaborador().getId().equals("17")) {
					System.out.println("Achei!");
				}
				detalharHorasExtra(poCorrente, saldo);	
				saldo.setColaborador(dtoCorrente.getColaborador());
				saldo.setIdColaborador(dtoCorrente.getColaborador().getId());
				
				saldosGerados.add(saldo);
			}
			
			

			return saldosGerados;
		} catch ( ApplicationException e ) {

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}*/
	}

	private void detalharHorasExtra(HoraExtraPO po) {
		System.out.println("Tipo: " + po.getTipo());
		// TODO Parei aqui, fazer um método para por essa regra e aplicar os Feriados na regra.
		// TODO Criar uma tela de cadastro de feriados
		// TODO Fazer com que as telas de Aplicações de Insumos e Abastecimentos iniciem a pesquisa com a data atual
		if(HoraExtraDTO.TIPO_LANCAMENTO.equalsIgnoreCase(po.getTipo())) {
			BigDecimal quantidadeHoras = po.getQuantidadeHoras();
			BigDecimal quantidade50Porcento = BigDecimal.ZERO;
			BigDecimal quantidade100Porcento = BigDecimal.ZERO;
			BigDecimal quantidadeDomingoFeriado = BigDecimal.ZERO;		

			if( DateUtil.isDomingo(po.getData()) || po.getFeriado().booleanValue() ) {
				if(quantidadeHoras.compareTo(new BigDecimal("8")) >= 0) {
					quantidade100Porcento = quantidadeHoras.subtract(new BigDecimal("8"));
					quantidadeDomingoFeriado = new BigDecimal("1");// Esse campo é em dias
				}else {
					quantidade100Porcento = quantidadeHoras;
				}
			}else {
				if(quantidadeHoras.compareTo(new BigDecimal("2")) > 0) {
					quantidade50Porcento = new BigDecimal("2");
					quantidade100Porcento = quantidadeHoras.subtract(quantidade50Porcento);
				}else {
					quantidade50Porcento = quantidadeHoras;
				}
			}
			
			po.setQuantidade50Porcento(quantidade50Porcento);
			po.setQuantidade100Porcento(quantidade100Porcento);
			po.setQuantidadeDomingoFeriado(quantidadeDomingoFeriado);
			//System.out.println("quantidadeHoras = " + quantidadeHoras);
			//System.out.println("quantidade50Porcento = " + quantidade50Porcento);
			//System.out.println("quantidade100Porcento = " + quantidade100Porcento);
			//System.out.println("quantidadeDomingoFeriado = " + quantidadeDomingoFeriado);
			System.out.println("Data = "+ DateUtil.parseLocalDate(po.getData()) +" "+ DateUtil.getDiaSemana(po.getData()) +"\t\t\tHoras = "+quantidadeHoras+"\t\t50% = "+quantidade50Porcento+"\t\t100% = "+quantidade100Porcento+"\t\tDom/Fer. ="+quantidadeDomingoFeriado);
		}
	}
}