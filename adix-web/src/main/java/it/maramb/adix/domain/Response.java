package it.maramb.adix.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private Long id;
    private Boolean error;
    private String message;

    public static Response create(Message message) {
        return create(null, false, message);
    }

    public static Response create(Long id, Message message) {
        return create(id, false, message);
    }

    private static Response create(Long id, Boolean error, Message message) {
        return Response.builder()
                .id(id)
                .error(error)
                .message(message.toString())
                .build();
    }

    public static Response error(Message message) {
        return create(null, true, message);
    }

    public String toJson() throws JsonProcessingException {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(this);
    }

    public enum Message {
        LOGIN_USER_NOT_FOUND("L'utente non esiste"),
        LOGIN_USER_NOT_ENABLED("L'utente è disabilitato"),
        LOGIN_USER_BAD_CREDENTIALS("Credenziali errate"),
        LOGIN_USER_APPLICATION_RELOADED("Applicazione riavviata"),
        LOGIN_USER_SUCCESS("Accesso autorizzato"),

        NO_HANDLER_FOR_METHOD("La risorsa richiesta non esiste"),
        ACCESS_DENIED("L'utente non ha i permessi per accedere o utilizzare questa risorsa"),
        REQUEST_VALIDATION_FAILED("La richiesta non è valida"),

        RECORD_SAVED("Dati salvati correttamente"),
        RECORD_UPDATED("Dati aggiornati con successo"),
        RECORD_NOT_FOUND("I dati richiesti non esitono"),
        RECORD_DELETED("Dati eliminati"),


        USER_CREATED("Utente salvato"),
        USER_UPDATED("Utente modificato con successo"),
        USER_NOT_FOUND("L'utente selezionato non esiste o è stato eliminato"),
        USER_DELETED("Utente eliminato con successo"),
        CUSTOMER_CREATED("Azienda salvata"),
        CUSTOMER_UPDATED("Azienda modificata con successo"),
        CUSTOMER_NOT_FOUND("L'azienda selezionata non esiste o è stata eliminata"),
        CUSTOMER_DELETED("Azienda eliminata con successo"),
        YEAR_CREATED("Anno creato"),
        YEAR_UPDATED("Anno modificato con successo"),
        YEAR_NOT_FOUND("L'anno selezionato non esiste o è stato eliminato"),
        YEAR_DELETED("Anno eliminato con successo");

        private String message;

        Message(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return this.message;
        }
    }
}
