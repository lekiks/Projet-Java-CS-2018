/*
Event on which ad can be done
 */

/**
 *
 * @author hadrienjanicot
 */

//Imports list
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;

public class Event implements SerializableSC{
    private String eventName;
    private String adress;
    private String date;
    private ImageIcon eventPicture;    

    @Override
    public byte[] serialize() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(this);
        return out.toByteArray();
    }

    @Override
    public void deserialize(byte[] data) throws IOException, ClassNotFoundException {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        Event tampon = new Event();
        tampon = (Event) is.readObject();
        this.adress = tampon.adress;
        this.date = tampon.date;
        this.eventName = tampon.eventName;
        this.eventPicture = tampon.eventPicture; 
    }
}
