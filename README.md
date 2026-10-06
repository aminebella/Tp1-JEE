# Tp1-JEE
# Introduction

Dans le cadre de ce TP, nous avons mis en place une application Java utilisant **Maven, JPA, Hibernate et la base de données H2**.

L'objectif est de comprendre le fonctionnement de la persistance des données avec JPA et Hibernate à travers une entité `Produit`. L'application permet d'insérer plusieurs produits dans la base de données, de les récupérer à l'aide d'une requête JPQL et de rechercher un produit à partir de son identifiant.

L'utilisation de **Lombok** permet également de réduire le code répétitif lié aux méthodes d'accès de l'entité.


## 1. Configuration du projet avec Maven
<img width="959" height="539" alt="A" src="https://github.com/user-attachments/assets/9fe5a9e4-bdff-48eb-bdc6-ad22e952f595" />

Le fichier `pom.xml` permet de gérer la configuration du projet et ses dépendances.

Les principales dépendances utilisées sont :

* **JPA 2.2** : fournit l'API de persistance.
* **Hibernate 5.6.5** : fournit l'implémentation de JPA.
* **H2** : permet d'utiliser une base de données légère en mémoire.
* **SLF4J** : permet la gestion des logs.
* **Lombok** : réduit le code répétitif, notamment pour générer automatiquement les getters et setters.

Le projet utilise également Java 8 comme version cible.


## 2. Configuration de JPA et Hibernate
<img width="959" height="539" alt="B" src="https://github.com/user-attachments/assets/740efc36-c6a0-41ba-8980-cb62165ae966" />

Le fichier `persistence.xml` contient la configuration de l'unité de persistance `hibernate-demo`.

Il définit Hibernate comme fournisseur JPA et configure la connexion à la base de données H2 avec l'URL :

`jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1`

La propriété `hibernate.hbm2ddl.auto` est configurée avec la valeur `update`, permettant à Hibernate de créer ou mettre à jour automatiquement la structure de la base de données à partir des entités.

L'option `hibernate.show_sql` permet également d'afficher les requêtes SQL générées par Hibernate dans la console.



## 3. Création de l'entité Produit
<img width="959" height="539" alt="C" src="https://github.com/user-attachments/assets/d1e148a5-0356-415b-bdd5-a4001d484485" />

La classe `Produit` représente l'entité persistée dans la base de données.

Elle est annotée avec `@Entity`, ce qui indique à JPA qu'il s'agit d'une entité persistante, et `@Table(name = "produit")` permet de définir le nom de la table correspondante.

L'attribut `id` est identifié avec `@Id` et sa valeur est générée automatiquement grâce à `@GeneratedValue`.

Les attributs `nom` et `prix` sont obligatoires grâce à l'annotation `@Column(nullable = false)`.

Lombok est utilisé avec `@Getter` et `@Setter` afin de générer automatiquement les méthodes d'accès.

Un constructeur sans paramètres est également présent, car il est nécessaire au fonctionnement de JPA, ainsi qu'un constructeur permettant de créer facilement un produit avec son nom et son prix.

## 4. Développement de l'application
<img width="959" height="539" alt="D" src="https://github.com/user-attachments/assets/5ce32131-e8ca-4322-b173-c2738acf1f7c" />
<img width="959" height="539" alt="3" src="https://github.com/user-attachments/assets/bb7aa4d1-031a-440c-9337-ac3bd6ae26ea" />

La classe `App` constitue le point d'entrée de l'application.

Au démarrage, une console Web H2 est lancée sur le port `8082`. Ensuite, une `EntityManagerFactory` est créée à partir de l'unité de persistance `hibernate-demo`.

L'application réalise ensuite deux opérations principales :

* **Insertion** de trois produits : Laptop, Smartphone et Tablette.
* **Lecture** des produits enregistrés à l'aide d'une requête JPQL.

Une recherche supplémentaire est réalisée avec `em.find()` afin de récupérer le produit ayant l'identifiant `2`.

Les opérations d'insertion sont exécutées dans une transaction. En cas d'erreur, la transaction est annulée avec `rollback()`, puis l'`EntityManager` est fermé dans le bloc `finally`.

## 5. Exécution et résultats
<img width="959" height="539" alt="2" src="https://github.com/user-attachments/assets/e65e61fd-fb94-4dc0-85ad-ea3b4c2a0540" />

Après l'exécution de l'application, les trois produits sont correctement insérés dans la base de données.

La requête JPQL permet ensuite de récupérer l'ensemble des produits enregistrés. La recherche par identifiant permet de vérifier qu'un produit précis peut également être retrouvé à partir de sa clé primaire.

Les résultats affichés dans la console confirment le bon fonctionnement de la persistance avec JPA et Hibernate.


## Conclusion

Ce TP nous a permis de mettre en œuvre une application Java basée sur Maven, JPA et Hibernate avec une base de données H2.

Nous avons appris à configurer les dépendances du projet, définir une entité JPA, effectuer des opérations d'insertion et de lecture à l'aide de `EntityManager`, ainsi qu'à utiliser JPQL pour interroger les données.

L'utilisation de Lombok permet également de simplifier la définition de l'entité en générant automatiquement certaines méthodes. Enfin, la console H2 nous a permis de vérifier directement les données persistées par l'application.

