# SPARADRAP — TP Maven (AFPA / CDA)

Application **Swing** de démonstration (achats, clients, médecins, médicaments, mutuelles) pilotée par **Maven**.

## Prérequis
- **Java 11** et **Maven 3.9.x**
- Vérifier (PowerShell) :
  ```powershell
  mvn -v
  
---

## Compiler
mvn clean package

## Lancer l’application Swing
mvn exec:java

## Générer le site Maven (rapports, Javadoc, XRef)
mvn site

## Ouvrir la page d’accueil du site
Start-Process ".\target\site\index.html"

