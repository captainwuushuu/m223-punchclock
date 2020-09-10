# M223: Punchclock
Dies ist eine Beispielapplikation für das Modul M223.

## Loslegen
Folgende Schritte befolgen um loszulegen:
1. Sicherstellen, dass JDK 12 installiert und in der Umgebungsvariable `path` definiert ist.
1. Ins Verzeichnis der Applikation wechseln und über die Kommandozeile mit `./gradlew bootRun` oder `./gradlew.bat bootRun` starten
1. Unittest mit `./gradlew test` oder `./gradlew.bat test` ausführen.
1. Ein ausführbares JAR kann mit `./gradlew bootJar` oder `./gradlew.bat bootJar` erstellt werden.

Folgende Dienste stehen während der Ausführung im Profil `dev` zur Verfügung:
- REST-Schnittstelle der Applikation: http://localhost:8081
- Dashboard der H2 Datenbank: http://localhost:8081/h2-console

## Nutzung der Applikation
Folgende Schritte befolgen um loszulegen:
1. Programm starten (Run Punchclock)
2. über http://localhost:8081/index.html kann auf die Applikation zugegriffen werden
3. falls kein Konto bekannt, kann über das Sign-up-Formular ein neuer Benutzer erstellt werden
4. Bei erfolgreichem Sign up sich danach einloggen (Login Formular)
5. Über die Navigation kann auf verschiedene Bereiche zugegriffen werden: Departements, Categories, Users und Entries
6. Departements: Hier können bereits bestehende Departements verwaltet und neue erstellt werden
7. Categories: Hier können bereits bestehende Categories verwaltet und neue erstellt werden
8. Users: Hier können bereits bestehende User verwaltet und neue erstellt werden
9. Entries: Hier können bereits bestehende Entries verwaltet und neue erstellt werden.
CAVE: Die update-Funktion in den Entries funktioniert nicht richtig.
