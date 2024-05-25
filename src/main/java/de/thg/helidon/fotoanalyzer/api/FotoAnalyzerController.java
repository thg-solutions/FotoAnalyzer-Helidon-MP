/*
 * FastAPI
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 0.1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package de.thg.helidon.fotoanalyzer.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jakarta.ws.rs.*;


import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaHelidonServerCodegen", date = "2024-05-24T05:07:51.083708Z[Etc/UTC]")
public class FotoAnalyzerController {

    @POST
    @Path("/analyze_image")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    public String analyzeImageAnalyzeImagePost( @FormDataParam(value = "file") InputStream fileInputStream) {
        try(fileInputStream) {
            return String.format("%s\n", fileInputStream.readAllBytes().length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/rename_images")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public void renameImagesRenameImagesPost(@Valid @NotNull List<String> requestBody,@QueryParam("rename") Boolean rename) {
        System.out.println("rename_images");
    }
}
