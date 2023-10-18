package m.schuermann.weiterbildungskatalog;

import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Erzeugung einer Basisliste von Dozent*innen und Weiterbildungsangeboten
@Configuration
public class WeiterbildungskatalogStorage {
	@Bean
	CommandLineRunner commandLineRunner(DozentRepository dozentRepository, WeiterbildungsangebotRepository weiterbildungsangebotRepository) {
		return args -> {
			
			//Anlegen von Dozent*innen
			Dozent wagner = new Dozent("Ulrich","Wagner");
			dozentRepository.save(wagner);
			Dozent ebeling = new Dozent("Sofia","Ebeling");
			dozentRepository.save(ebeling);
			Dozent copur = new Dozent("Burak","Copur");
			dozentRepository.save(copur);
			Dozent becker = new Dozent("Natalie","Becker");
			dozentRepository.save(becker);
			Dozent hakimi = new Dozent("Achraf","Hakimi");
			dozentRepository.save(hakimi);
			

			//Erstellung von Weiterbildungsangeboten
			weiterbildungsangebotRepository.save(new Weiterbildungsangebot("Biologie", "Allgemeine Einführung", new BigDecimal(35), wagner));
			weiterbildungsangebotRepository.save(new Weiterbildungsangebot("Genomik", "Aktuelle Methoden der praktischen Genomik", new BigDecimal(85), wagner));
			weiterbildungsangebotRepository.save(new Weiterbildungsangebot("Genetik", "Vorstellung des Modellorganismus Drosophila Melanogaster", new BigDecimal(70), wagner));
			weiterbildungsangebotRepository.save(new Weiterbildungsangebot("Sozialrechtecht", "Einführung in die wirtschaftliche Jugendhilfe", new BigDecimal(70), ebeling));
			weiterbildungsangebotRepository.save(new Weiterbildungsangebot("Crashkurs Betriebswirtschaft", "Basiswissen in Rechnungswesen und Kostenmanagement", new BigDecimal(45), copur));
			weiterbildungsangebotRepository.save(new Weiterbildungsangebot("Mathematik I", "Grundlagen der Mathematik", new BigDecimal(30), hakimi));
			weiterbildungsangebotRepository.save(new Weiterbildungsangebot("Mathematik II", "Vertiefungskurs, aufbauend auf Mathematik I", new BigDecimal(35), hakimi));
			
		};
	}
}
