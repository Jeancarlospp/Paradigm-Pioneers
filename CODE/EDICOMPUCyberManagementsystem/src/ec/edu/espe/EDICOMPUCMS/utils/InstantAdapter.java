package ec.edu.espe.EDICOMPUCMS.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.Instant;
/**
 *
 * @author Andrea Raura,Paradigm Pioneers Squad, DCCO-ESPE
 */

<<<<<<< HEAD

public class InstantAdapter extends TypeAdapter<Instant> {
    @Override
    public void write(JsonWriter out, Instant value) throws IOException {
        out.value(value.toString());
=======
public class InstantAdapter extends TypeAdapter<Instant> {

    @Override
    public void write(JsonWriter out, Instant value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value.toString());
        }
>>>>>>> c5beda7e217edd8fffcf33a829bc5d067edfd558
    }

    @Override
    public Instant read(JsonReader in) throws IOException {
<<<<<<< HEAD
        return Instant.parse(in.nextString());
=======
        String value = in.nextString();
        if (value == null || value.isEmpty()) {
            return null;
        }
        return Instant.parse(value);
>>>>>>> c5beda7e217edd8fffcf33a829bc5d067edfd558
    }
}