package br.com.srcsoftware.exceptions;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * ¤ - para exibir o R$
 * Classe que padroniza a internacionalizacao de valores monetarios
 * 
 * @author Luiz Cavalcanti
 * @version 0.1
 * @see java.util.Locale
 * @see java.text.DecimalFormat
 * @see java.text.DecimalFormatSymbols
 */
public final class Moeda{

	/**
	 * Simbolos especificos do Dolar Americano
	 */
	private static final DecimalFormatSymbols DOLAR = new DecimalFormatSymbols( Locale.US );
	/**
	 * Mascara de dinheiro para Dolar Americano
	 */
	public static final DecimalFormat DINHEIRO_DOLAR = new DecimalFormat( "###,###,##0.00", DOLAR );
	/**
	 * Simbolos especificos do Euro
	 */
	private static final DecimalFormatSymbols EURO = new DecimalFormatSymbols( Locale.GERMANY );
	/**
	 * Mascara de dinheiro para Euro
	 */
	public static final DecimalFormat DINHEIRO_EURO = new DecimalFormat( "###,###,##0.00", EURO );
	/**
	 * Locale Brasileiro
	 */
	private static final Locale BRAZIL = new Locale( "pt", "BR" );
	/**
	 * Sï¿½mbolos especificos do Real Brasileiro
	 */
	private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols( BRAZIL );
	/**
	 * Mascara de dinheiro para Real Brasileiro
	 */
	public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat( "###,###,##0.00", REAL );

	public static final DecimalFormat NUMERICO_REAL = new DecimalFormat( "###,###0", REAL );

	/**
	 * Mascara texto com formatacao monetaria
	 * 
	 * @param valor Valor a ser mascarado
	 * @param moeda Padrao monetario a ser usado
	 * @return Valor mascarado de acordo com o padrao especificado
	 */
	public static String mascaraDinheiro( BigDecimal valor, DecimalFormat moeda ) {
		if ( valor == null ) {
			return "0,00";
		}
		return moeda.format( valor );
	}
}