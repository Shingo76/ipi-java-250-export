package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Transactional
public class InitData {

	@Autowired
	private EntityManager em;

	public void insertTestData() {

		Client alex = new Client();
		alex.setNom("PETRILLO");
		alex.setPrenom("Alexandre");
		alex.setSexe("H");
		alex.setAge(32L);
		alex.setAdresse("13 rue de lu marechal Leclerc");
		em.persist(alex);

		Client lucie = new Client();
		lucie.setNom("BONNET");
		lucie.setPrenom("Lucie");
		lucie.setSexe("F");
		lucie.setAge(23L);
		lucie.setAdresse("14 rue des Moulins");
		em.persist(lucie);

		Article article = new Article();
		article.setLibelle("Carte mère ASROCK 2345");
		article.setPrix(79.90);
		em.persist(article);

		Article carteGraphique = new Article();
		carteGraphique.setLibelle("NVIDIA 960GTX");
		carteGraphique.setPrix(419.90);
		em.persist(carteGraphique);

		Article wow = new Article();
		wow.setLibelle("World Of Warcraft");
		wow.setPrix(120d);
		em.persist(wow);

		Article sc2 = new Article();
		sc2.setLibelle("Starcraft 2");
		sc2.setPrix(100d);
		em.persist(sc2);

		Article binf = new Article();
		binf.setLibelle("Bioshock Infinite");
		binf.setPrix(30d);
		em.persist(binf);

		Article processeur = new Article();
		processeur.setLibelle("Intel Celeron Processor N389");
		processeur.setPrix(234.67);
		em.persist(processeur);

		Article pamplemousse = new Article();
		pamplemousse.setLibelle("Pamplemousse");
		pamplemousse.setPrix(0.90);
		em.persist(pamplemousse);

		Article tomates = new Article();
		tomates.setLibelle("Tomate");
		tomates.setPrix(0.30);
		em.persist(tomates);

		Article poireaux = new Article();
		poireaux.setLibelle("Poireaux");
		poireaux.setPrix(0.50);
		em.persist(poireaux);

		Article article4 = new Article();
		article4.setLibelle("Piston de voiture");
		article4.setPrix(146.40);
		em.persist(article4);

		Facture factureElectronique = new Facture();
		factureElectronique.setClient(alex);
		em.persist(factureElectronique);

		Facture factureJV = new Facture();
		factureJV.setClient(alex);
		em.persist(factureJV);

		Facture factureLucie = new Facture();
		factureLucie.setClient(lucie);
		em.persist(factureLucie);

		Facture factureLucie2 = new Facture();
		factureLucie2.setClient(lucie);
		em.persist(factureLucie2);

		LigneFacture ligneFacture1 = new LigneFacture();
		ligneFacture1.setFacture(factureElectronique);
		ligneFacture1.setArticle(article);
		ligneFacture1.setQuantite(2);
		em.persist(ligneFacture1);

		LigneFacture ligneFacture11 = new LigneFacture();
		ligneFacture11.setFacture(factureElectronique);
		ligneFacture11.setArticle(carteGraphique);
		ligneFacture11.setQuantite(5);
		em.persist(ligneFacture11);

		LigneFacture ligneFacture2 = new LigneFacture();
		ligneFacture2.setFacture(factureElectronique);
		ligneFacture2.setArticle(processeur);
		ligneFacture2.setQuantite(6);
		em.persist(ligneFacture2);

		LigneFacture factureTomatesLucie = new LigneFacture();
		factureTomatesLucie.setFacture(factureLucie);
		factureTomatesLucie.setArticle(tomates);
		factureTomatesLucie.setQuantite(12);
		em.persist(factureTomatesLucie);

		LigneFacture facturePampleLucie = new LigneFacture();
		facturePampleLucie.setFacture(factureLucie);
		facturePampleLucie.setArticle(pamplemousse);
		facturePampleLucie.setQuantite(4);
		em.persist(facturePampleLucie);

		LigneFacture facturePoireauxLucie = new LigneFacture();
		facturePoireauxLucie.setFacture(factureLucie);
		facturePoireauxLucie.setArticle(poireaux);
		facturePoireauxLucie.setQuantite(2);
		em.persist(facturePoireauxLucie);

		LigneFacture factureWow = new LigneFacture();
		factureWow.setFacture(factureJV);
		factureWow.setArticle(wow);
		factureWow.setQuantite(8);
		em.persist(factureWow);

		LigneFacture factureStarcraft2 = new LigneFacture();
		factureStarcraft2.setFacture(factureJV);
		factureStarcraft2.setArticle(sc2);
		factureStarcraft2.setQuantite(3);
		em.persist(factureStarcraft2);

		LigneFacture factureBio = new LigneFacture();
		factureBio.setFacture(factureJV);
		factureBio.setArticle(binf);
		factureBio.setQuantite(1);
		em.persist(factureBio);

		LigneFacture factureTomatesLucie2 = new LigneFacture();
		factureTomatesLucie2.setFacture(factureLucie2);
		factureTomatesLucie2.setArticle(tomates);
		factureTomatesLucie2.setQuantite(10);
		em.persist(factureTomatesLucie2);

		LigneFacture facturePampleLucie2 = new LigneFacture();
		facturePampleLucie2.setFacture(factureLucie2);
		facturePampleLucie2.setArticle(pamplemousse);
		facturePampleLucie2.setQuantite(7);
		em.persist(facturePampleLucie2);

		LigneFacture facturePoireauxLucie2 = new LigneFacture();
		facturePoireauxLucie2.setFacture(factureLucie2);
		facturePoireauxLucie2.setArticle(poireaux);
		facturePoireauxLucie2.setQuantite(3);
		em.persist(facturePoireauxLucie2);

	}
}
