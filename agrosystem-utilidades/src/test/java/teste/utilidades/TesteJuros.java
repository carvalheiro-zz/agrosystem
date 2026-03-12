package teste.utilidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public class TesteJuros{

	public static void main( String[ ] args ) {
		BigDecimal capital = new BigDecimal( "1000" );
		BigDecimal taxa = new BigDecimal( "1" );
		Integer tempo = Integer.valueOf( 3 );

		System.out.println( Utilidades.calcularJurosSimples( capital, taxa, tempo ) );
		System.out.println( Utilidades.calcularJurosComposto( capital, taxa, tempo ) );

		BigDecimal multa = new BigDecimal( "10" );
		BigDecimal mora = new BigDecimal( "0.033" );
		System.out.println( Utilidades.calcularJurosEMoraDia( LocalDate.now(), LocalDate.parse( "2018-01-13" ), capital, multa, mora, Long.valueOf( "0" ) ) );
	}

}
