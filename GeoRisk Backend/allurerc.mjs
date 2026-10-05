const environment = process.env.TEST_ENVIRONMENT || "unbekannt";
const geoServerVersion = process.env.RESOLVED_GEOSERVER_VERSION || "unbekannt";
const lwasVersion = process.env.RESOLVED_LWAS_VERSION || "unbekannt";

export default {
    name: "OGC Integrationstests",
    
    plugins: {
        awesome: {
            options: {
                reportName: `OGC Tests | GS ${geoServerVersion} | ` + `LWAS ${lwasVersion} | ${environment}`,
                reportLanguage: "de"
            }
        }
    }
};