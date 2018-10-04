
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hadrienjanicot
 */
public interface SerializableSC {
     byte[] serialize() throws IOException;
     void deserialize(byte[] data) throws IOException, ClassNotFoundException;  
}
