# FotoAnalyzer-Helidon-MP
FotoAnalyzer als Helidon-MicroProfile-Applikation

* [Helidon Homepage](/https://helidon.io/)
* [MicroProfile Homepage](https://microprofile.io/)

Die Tests laufen in einer Helidon-spezifischen Umgebung (`@HelidonTest`), in der u.a. ein `WebTarget`-Objekt injiziert wird.

## Anwendung

### rename_images
Um den Service im Container betreiben zu können wurde darauf verzichtet, dass der Service selbst Änderungen an Dateien vornimmt. Stattdessen wird eine Liste von Binär-Dateien hochgeladen. Zurückgegeben wird ein Json-Objekt, dass die ursprünglichen Dateinamen auf geänderte Dateinamen mappt.

Das Hochladen kann über Curl erfolgen (s.u.). Es wird aber auch ein sehr einfacher Web-Client zur Verfügung gestellt, der alle Jpg-Dateien aus einem gegebenen Verzeichnis überträgt und anschließend die übergebenen Dateien umbenennt (`WebClient.class`). Dieser Client kann ausgeführt werden, wenn der Helido-Server gestartet ist.

Beispiel für den Aufruf per `curl`:

```
curl -X 'POST' \
  'http://localhost:8080/rename_images' \
  -H 'accept: application/json' \
  -H 'Content-Type: multipart/form-data' \
  -F 'files=@/home/tom/Bilder/IMG_2336.JPG' \
  -F 'files=@/home/tom/Bilder/Alfred.jpg'
```

### analyze_image
Übergibt dem Server eine einzelne Bild-Datei, die daraufhin auch Metadaten untersucht wird. Der Aufrug kann per `curl`erfolgen (s.u.), aber auch über Postman.

```
curl -X 'POST' \
  'http://localhost:8080/analyze_image' \
  -H 'accept: application/json' \
  -H 'Content-Type: multipart/form-data' \
  -F 'file=@/home/tom/Bilder/IMG_2336.JPG;type=image/jpeg'
```
