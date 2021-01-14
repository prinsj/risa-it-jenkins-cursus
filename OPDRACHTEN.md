# Opdrachten

Hieronder volgen een aantal opdrachten om je wegwijs te maken in Jenkins. De nadruk ligt meer op Jenkins dan op het programmeren. De bedoeling is dat je hierna vertrouwd bent met het configureren van Jenkins, het configureren van jobs, etc.

## Downloaden van de code

Ga naar [de Jenkins cursus voor Risa-IT](https://github.com/prinsj/risa-it-jenkins-cursus) op GitHub en download de code. Gebruik hiervoor de groene knop met Code rechtsboven en kies `Download ZIP`. Pak het bestand uit in een directory. De bestanden zullen nu in een directory `risa-it-jenkins-cursus-main` staan. We gaan nu van de directory een `git`-repository maken:

```
cd risa-it-jenkins-cursus-main
git init
git add .
git commit --message 'Eerste commit' .
```

## Aanmaken van een job

Het allereerste wat we gaan doen is het aanmaken van een job. Ga naar het menu item `New Item` linksboven en vul een naam voor je job in. Kies dan `Multibranch Pipeline` en klik op `OK`.

Je krijgt nu een scherm te zien waarmee je job verder kan configureren. Hieronder worden de stappen beschreven:

1. **Branch sources**

   Klik op `Add source` en kies **Git**. Vul bij de _Project Repository_ de zojuist aangemaakte directory met de code in (/pad/naar/workspace/risa-it-jenkins-cursus-main).

2. **Build configuration**
   
   Verifieer dat de _Mode_ is *by Jenkinsfile* en dat het _Script path_ *Jenkinsfile* is. Hiermee geef je aan dat de build gedefinieerd wordt het bestand `Jenkinsfile` in je code branch.
   
3. **Scan Multibranch Pipeline Triggers**
   
   Stel dit in op 1 minuut. Dit betekent dat Jenkins elke minuut zal controleren of er wijzigingen in je Git-repository zijn.
   
2. **Orphaned Item Strategy**

   Stel het _Max # of old items to keep_ in op 10. Zoals het label al aanduidt, kun je hiermee aangeven hoeveel jobs je wil bewaren.

Klik op `Save`. Na maximaal één minuut zal de job starten.

## Aanpassen Jenkinsfile

Je ziet nu dat er een fout is opgetreden, om te zien wat het probleem is klik je op de naam van je job en dan op `Master`. Links zie je een overzicht van de gedraaide jobs, klik op de bovenste en dan op `Console output`. Je ziet dat er staat `No steps specified for branch ...`. Dat klopt, want er staat het volgende in de Jenkinsfile:

```groovy
        stage('Initialisation') {
            steps {
            }
        }
```

Hier moet staan dat Jenkins de Git-repository moet uitchecken. Dus dan gaan we dan maar eens doen:

1. Klik op `Back to Project` (links boven) en dan op `Pipeline Syntax`. Er opent nu **Snippet Generator** waarmee je heel handig pipeline script syntax kan genereren voor gebruik in je `Jenkinsfile`. Als je een nieuwe plugin installeert wordt dit automatisch hierin bijgewerkt. Maar nu gaan we eerst zorgen dat we onze branch kunnen uitchecken in Jenkins.

   Je ziet trouwens links een aantal interessante links staan. Het is nuttig om daar eens op door te klikken als je dieper op Jenkins en pipelines wilt ingaan.

2. Kies bij _Sample Step_ voor `Checkout` en gebruik de Git repository die je al een keer hebt ingevuld, onder stap 1 van de vorige paragraaf.
3. Bij _Branches to build_ vul je in `*/${BRANCH_NAME}`, waarbij `${BRANCH_NAME}` een Jenkins variabele is die je kan gebruiken en door Jenkins op de juiste waarde wordt gezet (in dit geval de naam van de branch die actief is).
4. Klik nu op `Generate Pipeline Script` en er wordt een stukje code gegenereerd dat je kan kopiëren. Plak het in de Jenkinsfile op de juiste plek, zodat bovenstaande code er nu ongeveer als volgt uit ziet:

   ```groovy
        stage('Initialisation') {
            steps {
               checkout([$class: 'GitSCM', 
                         branches: [[name: '*/${BRANCH_NAME}']], 
                         doGenerateSubmoduleConfigurations: false, 
                         extensions: [], 
                         submoduleCfg: [], 
                         userRemoteConfigs: [[url: 'D:/workspace/risa-it-jenkins-cursus-main']]
               ])
            }
        }
   ```

5. Als laatste moeten we nog zorgen dat de aangepaste `Jenkinsfile` wordt verwerkt door **Jenkins**. Ga naar de Git repository en geef het volgende commando:

   ```
   git commit --message 'Jenkinsfile aangepast' .
   ```
 
Als alles klopt zal er nu een Job starten, wordt de brnach uitgecheckt en worden er de stappen `Declarative: Checkout SCM`, `Initialisation`,  `Compile`, `Test` en `Package` doorlopen. Alles zou nu groen moeten zijn.

## Installeren van plugins.

In deze stap gaan we eerst een plugin installeren en die vervolgens gebruiken in de `Jenkisnfile`.

Plugins zijn belangrijk in Jenkins, ze leveren de functionaliteit die Jenkins niet zelf heeft. We gaan de volgende plugin installeren:

- xUnit plugin: deze plugin publiceert test resultaten (zie https://plugins.jenkins.io/xunit/ voor meer informatie).

Klik linksboven op `Dashboard` en dan `Beheer Jenkins`. Kies nu `Beheer plugins` en dan de tab `Beschikbaar`. Type bovenaan in bij het zoek filter `xunit` en selecteer de `xUnit` plugin. Klik op `Nu downloaden en installeren tijdens herstart` en selecteer onderin de checkbox met als label `Jenkins herstarten zodra de installatie voltooid is en er geen taken draaien`. Jenkins zal nu de plugin installeren en herstarten, dit kan even duren.

## Plugin gebruiken

We gaan nu de zojuist geladen plugin gebruiken. Ga terug naar de job en kies weer `Pipeline Syntax`. Selecteer de `xunit`-stap.

Onder `Report type` kies `JUnit` en vul bij _Includes Pattern_ dit in: `target/surefire-reports/*.xml`. Hiermee geven we aan waar onze test rapportages zijn, deze zullen door de plugin worden gelezen en gepubliceerd in Jenkins. Klik nu op `Generate Pipeline Script`.

Kopieer de gegenereerde code en ga naar de `Jenkinsfile` in het project. Maak onderaan een nieuwe `stage` genaamd `Report` aan. De `stage` heeft 1 step waarin we de zojuist gekopieerde code plakken. Commit de code en kijk na afloop in de zojuist gedraaide job. Je ziet nu een nieuw menu-item verschijnen aan de linkerkant met als naam `Testresultaat`.

## Nieuwe branch aanmaken

We hebben hierboven een `Multibranch Pipeline` aangemaakt, dus is het ook wel interessant om te zien wat er gebeurt als we meerdere branches hebben. Het is ook een reële situatie die in veel projecten voorkomt.

Ga naar het project en geef de volgende commando's:

```bash
git status
git checkout -b branch-1
git status
```

Het eerste commando toont dat je in de `master`-branch zit. Met het volgende commando maak je een nieuwe branch, genaamd `branch-1` aan en checked deze uit. Met het derde commando verifiëren we dit.

In **Jenkins** zie je nu dat er ineens een 2e branch in het overzicht onder onze job. Alles wat je in deze branch doet wordt alleen in die branch gedraaid en heeft geen invloed op de andere branches. Als je een branch verwijderd wordt, zal deze ook hier verdwijnen. 
