package br.com.srcsoftware.migracao;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.migracao.conexao.HibernateConnectionMigracao;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public class MigracaoManager{
	/**
	 * Classe responsavel definir a postura que o GSON dever� assumir ao
	 * se deparar com uma String vazia.
	 * 
	 * Neste caso foi definido que todas Strings VAZIAS dever�o ser alteradas
	 * para NULL.
	 * 
	 * @author Gabriel Damiani Carvalheiro <gabriel.carvalheiro@gmail.com.br>
	 * @since 19 de ago de 2015 14:56:17
	 * @version 1.0
	 */
	public static class StringAdapter extends TypeAdapter< String >{
		@Override
		public String read( JsonReader reader ) throws IOException {
			if ( reader.peek() == JsonToken.NULL ) {
				reader.nextNull();
				return null;
			}
			return Utilidades.normalizeString( reader.nextString() );
		}

		@Override
		public void write( JsonWriter writer, String value ) throws IOException {
			if ( ( value == null ) || value.isEmpty() ) {
				writer.nullValue();
				return;
			}
			writer.value( Utilidades.normalizeString( value ) );
		}
	}

	public static class LocalDateTypeAdapter implements JsonSerializer< LocalDate >, JsonDeserializer< LocalDate >{

		@Override
		public JsonElement serialize( LocalDate localDate, Type typeOfSrc, JsonSerializationContext context ) {
			String dateFormatAsString;

			dateFormatAsString = localDate.format( DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) );

			return new JsonPrimitive( dateFormatAsString );
		}

		@Override
		public LocalDate deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context ) throws JsonParseException {
			if ( !( json instanceof JsonPrimitive ) ) {
				throw new JsonParseException( "The date should be a string value" );
			}

			return LocalDate.parse( json.getAsString(), DateTimeFormatter.ofPattern( "dd/MM/yyyy", new Locale( "pt", "BR" ) ).withResolverStyle( ResolverStyle.SMART ) );

		}
	}

	public static class LocalDateTimeTypeAdapter implements JsonSerializer< LocalDateTime >, JsonDeserializer< LocalDateTime >{

		@Override
		public JsonElement serialize( LocalDateTime localDateTime, Type typeOfSrc, JsonSerializationContext context ) {
			String dateFormatAsString;

			dateFormatAsString = localDateTime.format( DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" ) );

			return new JsonPrimitive( dateFormatAsString );
		}

		@Override
		public LocalDateTime deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context ) throws JsonParseException {
			if ( !( json instanceof JsonPrimitive ) ) {
				throw new JsonParseException( "The date should be a string value" );
			}

			return LocalDateTime.parse( json.getAsString(), DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss", new Locale( "pt", "BR" ) ).withResolverStyle( ResolverStyle.SMART ) );

		}
	}

	public static class LocalTimeTypeAdapter implements JsonSerializer< LocalTime >, JsonDeserializer< LocalTime >{

		@Override
		public JsonElement serialize( LocalTime localTime, Type typeOfSrc, JsonSerializationContext context ) {
			String dateFormatAsString;

			dateFormatAsString = localTime.format( DateTimeFormatter.ofPattern( "HH:mm:ss" ) );

			return new JsonPrimitive( dateFormatAsString );
		}

		@Override
		public LocalTime deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context ) throws JsonParseException {
			if ( !( json instanceof JsonPrimitive ) ) {
				throw new JsonParseException( "The date should be a string value" );
			}

			String horaDefinida = json.getAsString();
			if ( horaDefinida.length() == 5 ) {
				horaDefinida = horaDefinida.concat( ":00" );
			}

			return LocalTime.parse( horaDefinida, DateTimeFormatter.ofPattern( "HH:mm:ss", new Locale( "pt", "BR" ) ).withResolverStyle( ResolverStyle.SMART ) );

		}
	}

	public static class BigDecimalTypeAdapterToPO implements JsonSerializer< BigDecimal >, JsonDeserializer< BigDecimal >{

		@Override
		public JsonElement serialize( BigDecimal valor, Type typeOfSrc, JsonSerializationContext context ) {

			BigDecimal valorBigDecimal = valor;

			return new JsonPrimitive( valorBigDecimal );

		}

		@Override
		public BigDecimal deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context ) throws JsonParseException {
			if ( !( json instanceof JsonPrimitive ) ) {
				throw new JsonParseException( "The date should be a string value" );
			}

			BigDecimal valorBigDecimal = new BigDecimal( json.getAsString() );

			return ( valorBigDecimal );
		}
	}

	public static class BigDecimalTypeAdapterToDTO implements JsonSerializer< BigDecimal >, JsonDeserializer< String >{

		@Override
		public JsonElement serialize( BigDecimal valor, Type typeOfSrc, JsonSerializationContext context ) {

			String valorBigDecimal = Utilidades.parseBigDecimal( valor );

			return new JsonPrimitive( valorBigDecimal );
		}

		@Override
		public String deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context ) throws JsonParseException {
			if ( !( json instanceof JsonPrimitive ) ) {
				throw new JsonParseException( "The date should be a string value" );
			}

			String valorBigDecimal = json.getAsString();

			return ( valorBigDecimal );
		}
	}

	@SuppressWarnings( "unchecked" )
	public static void migrar( Class clazzAgro, Class clazzEsafra, HibernateConnection hibernate, HibernateConnectionMigracao hibernateConnectionMigracao ) throws ApplicationException {

		try {
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter( String.class, new StringAdapter() ).registerTypeAdapter( LocalDate.class, new LocalDateTypeAdapter() ).registerTypeAdapter( LocalDateTime.class, new LocalDateTimeTypeAdapter() ).registerTypeAdapter( LocalTime.class, new LocalTimeTypeAdapter() ).registerTypeAdapter( BigDecimal.class, new BigDecimalTypeAdapterToPO() ).serializeNulls();

			/**
			 * Use serializeNulls method if you want To serialize null values
			 * By default, Gson does not serialize null values
			 */
			Gson gson = builder.serializeNulls().create();

			System.out.println( "\nINICIO MIGRAçãO ".concat( clazzEsafra.getSimpleName() ) );

			System.out.println( "#### AGROSYSTEM: Pegando de" );
			List marcas = hibernateConnectionMigracao.filtrarTodos( clazzAgro );

			System.out.println( "#### AGROSYSTEM: Encontrados : ".concat( String.valueOf( marcas.size() ) ) );

			String marcasGSON = gson.toJson( marcas );

			System.out.println( "#### ESAFRA: Convertendo para " );

			List migrados = new ArrayList<>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse( marcasGSON ).getAsJsonArray();

			for ( int i = 0; i < array.size(); i++ ) {
				migrados.add( gson.fromJson( array.get( i ), clazzEsafra ) );

			}

			System.out.println( "#### ESAFRA: Convertidos: ".concat( String.valueOf( migrados.size() ) ) );
			System.out.println( "#### ESAFRA: Inserindo " );

			//hibernate.iniciarTransacao();

			for ( Object po : migrados ) {
				hibernate.inserir( ( (AbstractPO) po ) );
			}

			//hibernate.commitTransacao();

			System.out.println( "FIM MIGRAçãO ".concat( clazzEsafra.getSimpleName() ) );

		} catch ( ApplicationException e ) {
			//hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			//hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

}
