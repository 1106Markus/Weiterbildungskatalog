package m.schuermann.weiterbildungskatalog;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

//Klasse Weiterbildungsangebot als Tabelle in der Datenbank erstellen
@Entity
public class Weiterbildungsangebot {
	
	// Attribute als Spalten in der Datenbank anlegen
	
    @Id
    @SequenceGenerator(name = "weiterbildungsangebot_sequence", sequenceName = "weiterbildungsangebot_sequence", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weiterbildungsangebot_sequence")
    
    @Column
    private Long angebotID;
    
    @Column
    private String titel;
    
    @Column
    private String beschreibung;
    
    @Column
    private BigDecimal teilnahmegebuhr;
    
    @ManyToOne
    private Dozent dozent;
    
	// Konstruktoren
    public Weiterbildungsangebot() {
    	
    };
    
    public Weiterbildungsangebot(String titel, String beschreibung, BigDecimal teilnahmegebuhr, Dozent dozent) {
		super();
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.teilnahmegebuhr = teilnahmegebuhr;
		this.dozent = dozent;
	}
    
	//Getter und Setter
	public Long getAngebotID() {
		return angebotID;
	}
	public void setAngebotID(Long angebotID) {
		this.angebotID = angebotID;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public BigDecimal getTeilnahmegebuhr() {
		return teilnahmegebuhr;
	}
	public void setTeilnahmegebuhr(BigDecimal teilnahmegebuhr) {
		this.teilnahmegebuhr = teilnahmegebuhr;
	}
	public Dozent getDozent() {
		return dozent;
	}
	public void setDozent(Dozent dozent) {
		this.dozent = dozent;
	}
	
	// toString-Methode
	@Override
	public String toString() {
		return "Weiterbildungsangebot [angebotID=" + angebotID + ", titel=" + titel + ", beschreibung=" + beschreibung
				+ ", teilnahmegebuhr=" + teilnahmegebuhr + ", dozent=" + dozent + "]";
	}
    
    
}
