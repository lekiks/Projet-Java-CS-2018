/*
Advert for SamCar
 */

/**
 *
 * @author hadrienjanicot
 */

//Imports list
import java.util.*;

public class Advert {
	private UserProfil adCreator;
    private Event adEvent;
    private boolean sam;
    private int carSize;
    private List<UserProfil> adMembers;
    public UserProfil getAdCreator() {
		return adCreator;
	}
	public void setAdCreator(UserProfil adCreator) {
		this.adCreator = adCreator;
	}
	public Event getAdEvent() {
		return adEvent;
	}
	public void setAdEvent(Event adEvent) {
		this.adEvent = adEvent;
	}
	public boolean isSam() {
		return sam;
	}
	public void setSam(boolean sam) {
		this.sam = sam;
	}
	public int getCarSize() {
		return carSize;
	}
	public void setCarSize(int carSize) {
		this.carSize = carSize;
	}
	public List<UserProfil> getAdMembers() {
		return adMembers;
	}
	public void setAdMembers(List<UserProfil> adMembers) {
		this.adMembers = adMembers;
	}
}
