package de.thg.helidon.fotoanalyzer.services;

import de.thg.helidon.fotoanalyzer.model.Image;

import java.io.InputStream;

public interface ImageMetadataReader {

    Image readImageMetadata(InputStream inputStream, String originalName);

}
