package com.example.georisk;

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
@Feature("WFS")
@Owner("Abdulaziz Srour")
class WfsDemoTest {

	@Test
	@Story("GetFeature")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Demonstriert einen WFS-GetFeature-Test mit FeatureCollection-Prüfung.")
	@DisplayName("WFS GetFeature liefert eine FeatureCollection")
    void getFeatureReturnsFeatureCollection() {
        String requestUrl =
                DemoEnvironment.geoServerUrl()
                        + "/wfs?SERVICE=WFS"
                        + "&VERSION=1.1.0"
                        + "&REQUEST=GetFeature"
                        + "&TYPENAME=Lovion:test_flaechen"
                        + "&MAXFEATURES=1";

        String response = """
                <wfs:FeatureCollection
                    xmlns:wfs="http://www.opengis.net/wfs"
                    numberOfFeatures="1">
                    <gml:featureMembers
                        xmlns:gml="http://www.opengis.net/gml">
                        <Lovion:test_flaechen
                            xmlns:Lovion="http://www.lovion.de">
                            <Lovion:id>1</Lovion:id>
                            <Lovion:name>Testfläche</Lovion:name>
                        </Lovion:test_flaechen>
                    </gml:featureMembers>
                </wfs:FeatureCollection>
                """;

		Allure.addAttachment("Request URL", "text/plain", requestUrl);
		Allure.addAttachment("Response Body", "application/xml", response);

        Allure.step(
                "FeatureCollection prüfen",
                () -> assertTrue(
                        response.contains("FeatureCollection"),
                        "Die Antwort enthält keine "
                                + "FeatureCollection."
                )
        );

        Allure.step(
                "Feature-ID prüfen",
                () -> assertTrue(
                        response.contains(
                                "<Lovion:id>1</Lovion:id>"
                        ),
                        "Das erwartete Feature fehlt."
                )
        );
    }

	@Test
	@Story("DescribeFeatureType")
	@Severity(SeverityLevel.NORMAL)
	@DisplayName("WFS DescribeFeatureType liefert ein XML-Schema")
    void describeFeatureTypeReturnsSchema() {
        String response = """
                <xsd:schema
                    xmlns:xsd="http://www.w3.org/2001/XMLSchema">
                    <xsd:element
                        name="test_flaechen"
                        type="xsd:string"/>
                </xsd:schema>
                """;

        Allure.step(
                "XML-Schema prüfen",
                () -> assertTrue(
                        response.contains("<xsd:schema"),
                        "Die Antwort enthält kein XML-Schema."
                )
        );
    }
}