# Installatie voor Windows

De volgende componenten dienen geïnstalleerd te worden:

## Java

Je dient een Java JDK geïnstalleerd te hebben, zowel versie 8 als 11 zijn goed. Als je die al hebt hoef je niets te doen en kun je doorgaan met de volgende stap.

Download de [AdoptOpenJDK 11](https://github.com/AdoptOpenJDK/openjdk11-binaries/releases/download/jdk-11.0.9.1%2B1/OpenJDK11U-jdk_x64_windows_hotspot_11.0.9.1_1.ms) en voer het gedownloade programma uit.

## Git

Ga naar [Git](https://git-scm.com/downloads) en kies Windows. Voer het gedownloade bestand uit.

## Maven

Download Maven van de [Maven download pagina](http://maven.apache.org/download.cgi) en installeer het. Zie de [installatie pagina](http://maven.apache.org/install.html) voor meer informatie.

## Jenkins

Ga naar de [Jenkins download pagina](https://www.jenkins.io/download/#downloading-jenkins) en kies de **Stable (LTS)** Windows versie. Voer het gedownloade programma uit em Jenkins wordt automatisch gestart.

# Jenkins configuratie

De eerste keer dat je Jenkins start moet je een wachtwoord invoeren om Jenkins te ontsluiten. Start Jenkins door in de browser naar http://localhost:8080 te gaan. Er verschijnt nu een pagina die vraagt om een wachtwoord. Kopieer het wachtwoord uit het genoemde bestand en ga verder.

Nu moeten we een aantal plug-ins installeren. Kies 'Install suggested plugins'. Eventuele extra plug-ins kunnen we later nog installeren.

Hierna dien je een **Admin** gebruiker in te voeren. Het maakt niet uit hoe de gebruiker heet. Je kan dit overslaan maar dan moet je elke keer als admin inloggen en het wachtwoord gebruiken dat je op de vorige pagina ingevoerd hebt.

De volgende pagina **Instance Configuration** kun je de voorgestelde URL (http://localhost:8080) accepteren.

Hierna moeten we een aantal tools in Jenkins instellen:

Kies **Manage Jenkins** > **Global Tool Configuration**.

Als eerste de JDK. Kies de knop **Add JDK** en voer als naam `JDK11` in. Verwijder de Oracle installer (gebruik de rode knop **Delete installer**), uncheck de checkbox **Install automatically** en er verschijnt nu een invoerveld voor **JAVA_HOME**. Geef hier het pad naar de geïnstalleerde JDK in (onder Windows is dit waarschijnlijk `C:\Program Files\AdoptOpenJDK\jdk-11.0.6.10-hotspot`).

Nu moeten we **Git** installeren als tool, klik op **Add Git** en voer als naam `Git` in. Het veld _Path to Git executable_ kan als waarde `git.exe` krijgen.

Als laatste nog Maven. Klik op **Add Maven** en geef als naam `Maven`. Uncheck de checkbox **Install automatically** en voer het pad naar de geïnstalleerde `maven` directory in.
