package m.schuermann.weiterbildungskatalog;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

// Klasse Dozent als Tabelle in der Datenbank erstellen
@Entity(name = "Dozent")
public class Dozent {

	// Attribute als Spalten in der Datenbank anlegen

	@Id
	@SequenceGenerator(name = "dozent_sequence", sequenceName = "dozent_sequence", allocationSize = 1, initialValue = 1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dozent_sequence")

	@Column(name = "dozentID", updatable = false)
	private Long dozentID;

	@Column(name = "vorname", nullable = false, columnDefinition = "VARCHAR(30)")
	private String vorname;

	@Column(name = "nachname", nullable = false, columnDefinition = "VARCHAR(30)")
	private String nachname;

	@OneToMany(mappedBy = "dozent")
	private Set<Weiterbildungsangebot> weiterbildungsangebote = new HashSet<>();

	// Konstruktoren
	public Dozent() {

	}

	public Dozent(String vorname, String nachname) {
		this.vorname = vorname;
		this.nachname = nachname;
	}

	// Getter und Setter
	public Long getDozentID() {
		return dozentID;
	}

	public void setDozentID(Long dozentID) {
		this.dozentID = dozentID;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public Set<Weiterbildungsangebot> getWeiterbildungsangebote() {
		return weiterbildungsangebote;
	}

	public void setWeiterbildungsangebote(Set<Weiterbildungsangebot> weiterbildungsangebote) {
		this.weiterbildungsangebote = weiterbildungsangebote;
	}

	// toString-Methode
	@Override
	public String toString() {
		return "Dozent [dozentID=" + dozentID + ", vorname=" + vorname + ", nachname=" + nachname + "]";
	}

}
