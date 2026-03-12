package br.com.srcsoftware.migracao;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

/**
 * 
 * Classe que representa
 * 
 * @param <PO> - Tipo do Objeto PO que sera utilizado
 * @param <PO2> - Tipo do Objeto DTO que sera utilizado
 * 
 * @author Gabriel Damiani Carvalheiro <gabriel.carvalheiro@gmail.com.br>
 * @since 14 de ago de 2015 12:42:55
 * @version 1.0
 */
public final class ModelFactoryMigracao< PO, PO2 > {

	public PO getPO( Class< PO > tipoDestinoPO, PO2 objetoOrigemDTO ) throws ModelFactoryException {

		try {
			if ( objetoOrigemDTO == null ) {
				return null;
			}
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter( String.class, new StringAdapter() ).serializeNulls();

			/**
			 * Use serializeNulls method if you want To serialize null values
			 * By default, Gson does not serialize null values
			 */
			Gson gson = builder.serializeNulls().create();

			/** Passando o Objeto para o JSon */
			String dtoJSON = gson.toJson( objetoOrigemDTO );

			/** Pegando o Objeto do JSon e convertendo para PO */
			JsonParser parse = new JsonParser();
			JsonObject object = parse.parse( dtoJSON ).getAsJsonObject();
			PO po = ( gson.fromJson( object, tipoDestinoPO ) );

			return po;
		} catch ( com.google.gson.JsonParseException e ) {
			if ( !Utilidades.isNuloOuVazio( e.getCause() ) ) {
				if ( e.getCause() instanceof java.text.ParseException ) {
					if ( e.getCause().getMessage().contains( "date" ) ) {
						throw new ModelFactoryException( e.getMessage().replace( "\"", " " ).replace( "Unparseable date", "Data inválida" ), e );
					}
				} else if ( e.getCause() instanceof java.lang.NumberFormatException ) {
					throw new ModelFactoryException( e.getMessage().replace( "\"", " " ).replace( "For input string", "Número inválido" ), e );
				}
			}
			throw new ModelFactoryException( e.getMessage(), e );
		}
	}

	
	/**
	 * Classe responsavel definir a postura que o GSON deverá assumir ao
	 * se deparar com uma String vazia.
	 * 
	 * Neste caso foi definido que todas Strings VAZIAS deverão ser alteradas
	 * para NULL.
	 * 
	 * @author Gabriel Damiani Carvalheiro <gabriel.carvalheiro@gmail.com.br>
	 * @since 19 de ago de 2015 14:56:17
	 * @version 1.0
	 */
	public class StringAdapter extends TypeAdapter< String >{
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
}