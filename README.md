# FotoAnalyzer-Helidon-MP
FotoAnalyzer as Helidon MicroProfile Application

## Anwendung

### rename_images
```
curl -X 'POST' \
  'http://localhost:8080/rename_images' \
  -H 'accept: application/json' \
  -H 'Content-Type: multipart/form-data' \
  -F 'files=@/home/tom/Bilder/IMG_2336.JPG' \
  -F 'files=@/home/tom/Bilder/Alfred.jpg'
```

### analyze_image
```
curl -X 'POST' \
  'http://localhost:8080/analyze_image' \
  -H 'accept: application/json' \
  -H 'Content-Type: multipart/form-data' \
  -F 'file=@/home/tom/Bilder/IMG_2336.JPG;type=image/jpeg'
```
