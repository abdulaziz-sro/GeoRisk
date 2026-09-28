package com.example.georisk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Lovion OGC Services")
@Feature("WMS")
@Owner("Abdulaziz Srour")
class WmsDemoTest {

    @Test
    @Story("GetCapabilities")
    @Severity(SeverityLevel.BLOCKER)
	@Description("Demonstriert einen WMS-GetCapabilities-Test mit Schritten und Attachments.")
	@DisplayName("WMS GetCapabilities veröffentlicht den erwarteten Layer")
    void getCapabilitiesPublishesExpectedLayer() {
        String requestUrl =
                DemoEnvironment.geoServerUrl()
                        + "/wms?SERVICE=WMS"
                        + "&VERSION=1.1.1"
                        + "&REQUEST=GetCapabilities";

        String response = """
                <WMT_MS_Capabilities version="1.1.1">
                    <Layer>
                        <Name>Lovion:Wasser</Name>
                    </Layer>
                </WMT_MS_Capabilities>
                """;

		Allure.step("GetCapabilities-Request vorbereiten");

		Allure.addAttachment("Request URL", "text/plain", requestUrl);

        Allure.step(
                "Capabilities-Antwort auswerten",
                () -> assertTrue(
                        response.contains("Lovion:Wasser"),
                        "Der erwartete Layer fehlt."
                )
        );

		Allure.addAttachment("Response Body", "application/xml", response);
    }

    @Test
    @Story("GetMap")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("WMS GetMap liefert ein PNG-Bild")
    void getMapReturnsPngImage() {
        String contentType = "image/png";
        int width = 1024;
        int height = 1024;

        Allure.step(
                "GetMap-Request für Testumgebung "
                        + DemoEnvironment.environment()
                        + " ausführen"
        );

        Allure.step(
                "Content-Type prüfen",
                () -> assertEquals(
                        "image/png",
                        contentType
                )
        );

        Allure.step(
                "Bildgröße prüfen",
                () -> {
                    assertEquals(1024, width);
                    assertEquals(1024, height);
                }
        );
    }
}