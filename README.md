# M223 Punchclock

Folgende Schritte sind notwendig um die Applikation zu erstellen und zu starten: 
1. Stellen Sie sicher, dass OpenJDK 11 oder höher installiert und JAVA_HOME korrekt gesetzt ist.  
2. Installieren Sie (falls noch nicht vorhanden) Apache Maven 3.8.1 oder höher
3. Wechseln Sie auf der Kommandozeile in den Ordner dieser Appliation. 
`cd punchclockM223/`
4. Starten Sie die Applikation mit 
```shell script
./mvnw compile quarkus:dev
```

Folgende Dienste stehen während der Ausführung im Profil dev zur Verfügung:

Swagger API: http://localhost:8080/q/swagger-ui/

H2 Console: http://localhost:8080/h2/ 
Datenquelle: jdbc:h2:mem:punchclock
Benutzername: zli
Passwort: zli

In der Applikation kann man seine eigenen Zeiten erfassen. Diese Zeiten kommen zusammen mit einer Kategorie und einem Arbeitsort, in welchen man angibt, was man in dieser Zeit erledigt hat und wo man dies gemacht hat.
Die erstellten Erfassungen können ebenfalls gelöscht werden.

Man kann sich registrieren und einloggen. Ebenfalls kann man sich als eingeloggter User ausloggen. 

Beispieldaten sind im Verzeichnis ./src/main/resources/import.sql gespeichert und werden automatisch beim Start der Applikation in die Datenbank gespeichert.

