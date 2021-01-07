# Installatie voor Windows

De eenvoudigste manier om alles onder Mac OSX te installeren is met `brew`.

    brew install adoptopenjdk11 git maven jenkins

# Jenkins configuratie

De eerste keer dat je Jenkins start moet je een wachtwoord invoeren om Jenkins te ontsluiten. Start Jenkins door in de browser naar http://localhost:8080 te gaan. Er verschijnt nu een pagina die vraagt om een wachtwoord. Kopieer het wachtwoord uit het genoemde bestand en ga verder.

Nu moeten we een aantal plug-ins installeren. Kies 'Install suggested plugins'. Eventuele extra plug-ins kunnen we later nog installeren.

Hierna dien je een **Admin** gebruiker in te voeren. Het maakt niet uit hoe de gebruiker heet. Je kan dit overslaan maar dan moet je elke keer als admin inloggen en het wachtwoord gebruiken dat je op de vorige pagina ingevoerd hebt.

De volgende pagina **Instance Configuration** kun je de voorgestelde URL (http://localhost:8080) accepteren.

Hierna moeten we een aantal tools in Jenkins instellen:

Kies **Manage Jenkins** > **Global Tool Configuration**.

Je hebt een aantal paden nodig, de volgende commando's laten die zien:

```bash
which mvn
which git
echo $JAVA_HOME
```

Als eerste de JDK. Kies de knop **Add JDK** en voer als naam `JDK11` in. Verwijder de Oracle installer (gebruik de rode knop **Delete installer**), uncheck de checkbox **Install automatically** en er verschijnt nu een invoerveld voor **JAVA_HOME**. Geef hier het pad naar de geïnstalleerde JDK in.

Nu moeten we **Git** installeren als tool, klik op **Add Git** en voer als naam `Git` in. Het veld _Path to Git executable_ kan als waarde `/usr/local/bin/git` krijgen.

Als laatste nog Maven. Klik op **Add Maven** en geef als naam `Maven`. Uncheck de checkbox **Install automatically** en voer het pad naar de geïnstalleerde `maven` directory in (waarschijnlijk `/usr/local/bin/mvn`.
