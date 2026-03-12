package br.com.srcsoftware.gestao.sistema.aplicacao.safra;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;
import br.com.srcsoftware.gestao.sistema.aplicacao.safra.setosafra.SetorSafraMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "safras", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "nome" } ) )
public final class SafraMigracaoPO extends AuditoriaAbstractMigracaoPO implements Comparable< SafraMigracaoPO >{

	@Column( name = "nome", length = 100, nullable = false )
	private String nome;

	@OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true )
	@JoinColumn( name = "idSafra", nullable = false )
	private Set< SetorSafraMigracaoPO > setoresSafras = new HashSet<>();

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public Set< SetorSafraMigracaoPO > getSetoresSafras() {
		return this.setoresSafras;
	}

	public void setSetoresSafras( Set< SetorSafraMigracaoPO > setoresSafras ) {
		this.setoresSafras = setoresSafras;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.nome == null ) ? 0 : this.nome.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof SafraMigracaoPO ) ) {
			return false;
		}
		SafraMigracaoPO other = (SafraMigracaoPO) obj;
		if ( this.nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !this.nome.equals( other.nome ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "SafraPO [nome=" );
		builder.append( this.nome );
		builder.append( ", setoresSafras=" );
		builder.append( this.setoresSafras );
		builder.append( ", getNomeUsuarioCriacao()=" );
		builder.append( this.getNomeUsuarioCriacao() );
		builder.append( ", getNomeUsuarioAlteracao()=" );
		builder.append( this.getNomeUsuarioAlteracao() );
		builder.append( ", getDataOcorrenciaCriacao()=" );
		builder.append( this.getDataOcorrenciaCriacao() );
		builder.append( ", getDataOcorrenciaAlteracao()=" );
		builder.append( this.getDataOcorrenciaAlteracao() );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int compareTo( SafraMigracaoPO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

	// XXX Select do relatorio geral de Custo de Insumo

	/*# Situação 1 -  Traz o TOTAL em R$ de todas as Aplicaçães feitas especificamente na TUDO
	SELECT tbTotalCusto.nome nome,(tbTotalCusto.custoTotal*percentoCultura)/100 FROM
	
	(SELECT
		t.id, t.nome nome, sum(custoTotal) custoTotal
		FROM
		aplicacoes as a,
		produtos as p,
		tipos t,
		safras safra
		WHERE
		a.idProduto = p.id
		AND  t.id = p.id_tipo
		AND a.idSafra = safra.id
	
		and safra.nome = '2020'
		and a.tipo = 'Tudo'#FIXO
		group by t.nome) as tbTotalCusto,
	
	(SELECT (totalCultura*100)/totalAreaSafra as percentoCultura From
	
	(SELECT
	sum(ss.area) as totalCultura
	FROM
	setoressafras ss,
	safras safra,
	sistema_culturas c
	WHERE
	ss.idSafra = safra.id
	AND ss.idCultura = c.id
	AND safra.nome = '2020'
	and c.nome = 'Soja') as totalCultura,
	
	(SELECT
	sum(ss.area) as totalAreaSafra
	FROM
	setoressafras ss,
	safras safra
	WHERE
	ss.idSafra = safra.id
	AND safra.nome = '2020')as totalAreaSafra) as percentoCultura*/

	/*# Situação 2 -  Traz o TOTAL em R$ de todas as Aplicaçães feitas especificamente na CULTURA
		SELECT
			t.nome nome, sum(custoTotal) custoTotal
			FROM
			aplicacoes as a,
			produtos as p,
			tipos t,
			sistema_culturas c,
			safras safra
			WHERE
			a.idProduto = p.id
			AND  t.id = p.id_tipo
			AND a.idCultura = c.id
			AND a.idSafra = safra.id
		
			and safra.nome = '2020'
			and c.nome = 'Soja'
			and a.tipo = 'cultura'#FIXO
			group by t.nome*/

	/*# Situação 3 - Traz o TOTAL em R$ de todas as Aplicaçães feitas especificamente na CULTURA do Setor Informado
		SELECT
			t.nome nome, sum(custoTotal) custoTotal
			FROM
			aplicacoes as a,
			produtos as p,
			tipos t,
			sistema_culturas c,
			setoressafras ss,
			setores setor,
			safras safra
			WHERE
			a.idProduto = p.id
		
			AND  t.id = p.id_tipo
			AND a.idSafra = safra.id
			AND a.idSetor = setor.id
			AND ss.idSafra = safra.id
			AND ss.idSetor = setor.id
			AND ss.idCultura = c.id
			and a.tipo = 'Safra/Setor'#FIXO
			and safra.nome = '2020'
			and c.nome = (SELECT
			              c.nome
			            FROM
			              setoressafras ss,
			              setores setor,
			              safras safra,
		                sistema_culturas c
			            WHERE
			              ss.idSetor = setor.id
			              AND ss.idSafra = safra.id
		                AND ss.idCultura = c.id
			              and setor.nome = 'p1'
			              and safra.nome = '2020')
			group by t.nome*/

	/** SELECT FINAL */
	/*SELECT tbCustoInsumos.nome, sum(tbCustoInsumos.custoTotal) FROM
		(SELECT
			t.nome nome, sum(custoTotal) custoTotal
			FROM
			aplicacoes as a,
			produtos as p,
			tipos t,
			sistema_culturas c,
			safras safra
			WHERE
			a.idProduto = p.id
			AND  t.id = p.id_tipo
			AND a.idCultura = c.id
			AND a.idSafra = safra.id
		
			and safra.nome = '2020'
			and c.nome = 'Soja'
			and a.tipo = 'cultura'#FIXO
			group by t.nome
		
		UNION ALL
		
		SELECT
			t.nome nome, sum(custoTotal) custoTotal
			FROM
			aplicacoes as a,
			produtos as p,
			tipos t,
			sistema_culturas c,
			setoressafras ss,
			setores setor,
			safras safra
			WHERE
			a.idProduto = p.id
		
			AND  t.id = p.id_tipo
			AND a.idSafra = safra.id
			AND a.idSetor = setor.id
			AND ss.idSafra = safra.id
			AND ss.idSetor = setor.id
			AND ss.idCultura = c.id
			and a.tipo = 'Safra/Setor'#FIXO
			and safra.nome = '2020'
		  and c.nome = (SELECT
			                c.nome
			              FROM
			                setoressafras ss,
			                setores setor,
			                safras safra,
		                  sistema_culturas c
			              WHERE
			                ss.idSetor = setor.id
			                AND ss.idSafra = safra.id
		                  AND ss.idCultura = c.id
			                and setor.nome = 'p1'
			                and safra.nome = '2020')
			group by t.nome
		
		UNION ALL
		
		SELECT tbTotalCusto.nome nome, ((tbTotalCusto.custoTotal*percentoCultura)/100) custoTotal FROM
		
		(SELECT
			t.id, t.nome nome, sum(custoTotal) custoTotal
			FROM
			aplicacoes as a,
			produtos as p,
			tipos t,
			safras safra
			WHERE
			a.idProduto = p.id
			AND  t.id = p.id_tipo
			AND a.idSafra = safra.id
		
			and safra.nome = '2020'
			and a.tipo = 'Tudo'#FIXO
			group by t.nome) as tbTotalCusto,
		
		(SELECT (totalCultura*100)/totalAreaSafra as percentoCultura From
		
		(SELECT
		sum(ss.area) as totalCultura
		FROM
		setoressafras ss,
		safras safra,
		sistema_culturas c
		WHERE
		ss.idSafra = safra.id
		AND ss.idCultura = c.id
		AND safra.nome = '2020'
		and c.nome = 'Soja') as totalCultura,
		
		(SELECT
		sum(ss.area) as totalAreaSafra
		FROM
		setoressafras ss,
		safras safra
		WHERE
		ss.idSafra = safra.id
		AND safra.nome = '2020')as totalAreaSafra) as percentoCultura) tbCustoInsumos
		group by tbCustoInsumos.nome*/
}
