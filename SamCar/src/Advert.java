/*
Advert for SamCar
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
import java.util.*;

public class Advert implements Serializable {
    private String addName;

    public String getAddName() {
        return addName;
    }
    private UserProfil adCreator;
    private Event adEvent;
    private boolean sam;
    private int carSize;


    private List<UserProfil> adMembers;
    
    public boolean checkSize(){
        return adMembers.size() < carSize;
    }

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
        Advert tampon = new Advert();
        tampon = (Advert) is.readObject();
        this.adCreator = tampon.adCreator;
        this.adEvent = tampon.adEvent;
        this.adMembers = tampon.adMembers;
        this.carSize = tampon.carSize;
        this.sam = tampon.sam;
    }

    public UserProfil getAdCreator(){
        return adCreator;
    }

    public boolean isSam(){
        return sam;
    }

    public int getCarSize(){
        return carSize;
    }

    public List<UserProfil> getAdMembers() {
        return adMembers;
    }

}
