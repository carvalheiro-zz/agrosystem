package br.com.srcsoftware.gestao;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

/**
 * Classe que representa
 * 
 * @author Gabriel Damiani Carvalheiro <gabriel.carvalheiro@sigma.com.br>
 * @since 10/05/2012 17:15:49
 * @version 1.0
 */
@MappedSuperclass
// Garante que os atributos padr�es desta classe sejam mapeados em cada subclasse.
public abstract class AuditoriaAbstractMigracaoPO extends AbstractPO{

	@Column( name = "usuario_criacao", nullable = false, length = 100 )
	private String nomeUsuarioCriacao;

	@Column( name = "usuario_alteracao", nullable = true, length = 100 )
	private String nomeUsuarioAlteracao;

	@Column( name = "data_criacao", nullable = false, length = 10 )
	private LocalDateTime dataOcorrenciaCriacao;

	@Column( name = "data_alteracao", nullable = true, length = 10 )
	private LocalDateTime dataOcorrenciaAlteracao;

	/**
	 * M�todo construtor respos�vel por inicializar os atributos e/ou
	 * executar qualquer ação no momento da criação da classe.
	 * 
	 * @author Gabriel Damiani Carvalheiro <gabriel.carvalheiro@sigma.com.br>
	 * @since 10/05/2012 17:15:49
	 * @version 1.0
	 */
	public AuditoriaAbstractMigracaoPO(){}

	public void setarDadosAuditoria( String nomeUsuario, LocalDateTime dataOcorrencia ) {
		if ( Utilidades.isNuloOuVazio( getDataOcorrenciaCriacao() ) ) {
			setNomeUsuarioCriacao( nomeUsuario );
			setDataOcorrenciaCriacao( dataOcorrencia );
		} else {
			setNomeUsuarioAlteracao( nomeUsuario );
			setDataOcorrenciaAlteracao( dataOcorrencia );
		}
	}

	/**
	 * M�todo respons�vel por atribuir o valor ao atributo nomeUsuarioCriacao.
	 * 
	 * @param String nomeUsuarioCriacao - nomeUsuarioCriacao do objeto a ser atribuido.
	 */
	public final void setNomeUsuarioCriacao( String nomeUsuarioCriacao ) {
		this.nomeUsuarioCriacao = nomeUsuarioCriacao;
	}

	/**
	 * M�todo respons�vel por atribuir o valor ao atributo nomeUsuarioAlteracao.
	 * 
	 * @param String nomeUsuarioAlteracao - nomeUsuarioAlteracao do objeto a ser atribuido.
	 */
	public final void setNomeUsuarioAlteracao( String nomeUsuarioAlteracao ) {
		this.nomeUsuarioAlteracao = nomeUsuarioAlteracao;
	}

	/**
	 * M�todo respons�vel por atribuir o valor ao atributo dataOcorrenciaCriacao.
	 * 
	 * @param LocalDateTime dataOcorrenciaCriacao - dataOcorrenciaCriacao do objeto a ser atribuido.
	 */
	public final void setDataOcorrenciaCriacao( LocalDateTime dataOcorrenciaCriacao ) {
		this.dataOcorrenciaCriacao = dataOcorrenciaCriacao;
	}

	/**
	 * M�todo respons�vel por atribuir o valor ao atributo dataOcorrenciaAlteracao.
	 * 
	 * @param LocalDateTime dataOcorrenciaAlteracao - dataOcorrenciaAlteracao do objeto a ser atribuido.
	 */
	public final void setDataOcorrenciaAlteracao( LocalDateTime dataOcorrenciaAlteracao ) {
		this.dataOcorrenciaAlteracao = dataOcorrenciaAlteracao;
	}

	/**
	 * M�todo respons�vel por retornar o valor do atributo nomeUsuarioCriacao.
	 * 
	 * @return String nomeUsuarioCriacao - nomeUsuarioCriacao do objeto a ser retornado.
	 */
	public final String getNomeUsuarioCriacao() {
		return nomeUsuarioCriacao;
	}

	/**
	 * M�todo respons�vel por retornar o valor do atributo nomeUsuarioAlteracao.
	 * 
	 * @return String nomeUsuarioAlteracao - nomeUsuarioAlteracao do objeto a ser retornado.
	 */
	public final String getNomeUsuarioAlteracao() {
		return nomeUsuarioAlteracao;
	}

	/**
	 * M�todo respons�vel por retornar o valor do atributo dataOcorrenciaCriacao.
	 * 
	 * @return LocalDateTime dataOcorrenciaCriacao - dataOcorrenciaCriacao do objeto a ser retornado.
	 */
	public final LocalDateTime getDataOcorrenciaCriacao() {
		return dataOcorrenciaCriacao;
	}

	/**
	 * M�todo respons�vel por retornar o valor do atributo dataOcorrenciaAlteracao.
	 * 
	 * @return LocalDateTime dataOcorrenciaAlteracao - dataOcorrenciaAlteracao do objeto a ser retornado.
	 */
	public final LocalDateTime getDataOcorrenciaAlteracao() {
		return dataOcorrenciaAlteracao;
	}
}
