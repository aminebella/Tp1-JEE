import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import org.h2.tools.Server;

import model.Produit;

public class App {
    public static void main(String[] args){
        try {
            Server.createWebServer("-web", "-webPort", "8082").start();
            System.out.println("Console H2 disponible sur : http://localhost:8082");
        } catch (Exception e) {
            System.out.println("Erreur lors du démarrage de la console H2");
            e.printStackTrace();
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
        // Insertion de produits
        insererProduits(emf);
        // Lecture des produits
        lireProduits(emf);
        emf.close();
    }

    public static void insererProduits(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            // Création de quelques produits
            Produit p1 = new Produit("Laptop", new BigDecimal("999.99"));
            Produit p2 = new Produit("Smartphone", new BigDecimal("499.99"));
            Produit p3 = new Produit("Tablette", new BigDecimal("299.99"));
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.getTransaction().commit();
            System.out.println("Produits insérés avec succès !");
        }
        catch(Exception e){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void lireProduits(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        try{
            // Requête JPQL pour récupérer tous les produits
            List<Produit> produits = em.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
            System.out.println("\nListe des produits :");
            for(Produit produit : produits){
                System.out.println(produit);
            }

            // Recherche d'un produit par ID
            System.out.println("\nRecherche du produit avec ID=2 :");
            Produit produitRecherche = em.find(Produit.class, 2L);
            if(produitRecherche != null){
                System.out.println(produitRecherche);
            } else{
                System.out.println("Produit non trouvé !");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
