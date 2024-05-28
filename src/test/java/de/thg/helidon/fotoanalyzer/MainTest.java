
package de.thg.helidon.fotoanalyzer;

import de.thg.helidon.fotoanalyzer.model.Image;
import io.helidon.metrics.api.MetricsFactory;
import io.helidon.microprofile.testing.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@HelidonTest
class MainTest {

    @Inject
    private MetricRegistry registry;

    @Inject
    private WebTarget target;


    @Test
    void testHealth() {
        Response response = target
                .path("health")
                .request()
                .get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    void testMicroprofileMetrics() {
        Message message = target.path("simple-greet/Joe")
                .request()
                .get(Message.class);

        assertThat(message.getMessage(), is("Hello Joe"));
        Counter counter = registry.counter("personalizedGets");
        double before = counter.getCount();

        message = target.path("simple-greet/Eric")
                .request()
                .get(Message.class);

        assertThat(message.getMessage(), is("Hello Eric"));
        double after = counter.getCount();
        assertEquals(1d, after - before, "Difference in personalized greeting counter between successive calls");
    }

    @AfterAll
    static void clear() {
        MetricsFactory.closeAll();
    }

    @Test
    void testAnalyzeImage() {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("testdata/PHOTO0021.JPG");
        MultiPart multiPart = new MultiPart();
        BodyPart bodyPart = new StreamDataBodyPart("file", inputStream, "PHOTO0021.JPG");
        multiPart.bodyPart(bodyPart);
        Image image = target.path("analyze_image")
                .request()
                .post(Entity.entity(multiPart, MediaType.MULTIPART_FORM_DATA_TYPE), Image.class);
        Image expected = new Image();
        expected.setCreationDate(LocalDateTime.of(2015, 11, 22, 11, 04, 37));
        expected.setLatitude(48.15315222222222);
        expected.setLongitude(11.591775555555555);
        expected.setFilename("PHOTO0021.JPG");
        assertThat(image, equalTo(expected));
    }
                
}
