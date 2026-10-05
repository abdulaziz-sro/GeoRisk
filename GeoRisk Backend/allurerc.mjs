const testEnvironment = process.env.TEST_ENVIRONMENT || "unbekannt";
const geoServerVersion = process.env.RESOLVED_GEOSERVER_VERSION || "unbekannt";
const lwasVersion = process.env.RESOLVED_LWAS_VERSION || "unbekannt";
const geoServerUrl = process.env.GEOSERVER_URL || "unbekannt";
const lwasUrl = process.env.LWAS_URL || "unbekannt";
const jenkinsJob = process.env.JOB_NAME || "unbekannt";
const jenkinsBuild = process.env.BUILD_NUMBER || "unbekannt";

const reportName =
    `OGC Tests | GS ${geoServerVersion} | ` +
    `LWAS ${lwasVersion} | Test Environment ${testEnvironment}`;

export default {
    name: reportName,
    plugins: {
        awesome: {
            options: {
                reportName: reportName,
                reportLanguage: "de"
            }
        }
    },

    variables: {
        Testumgebung: testEnvironment,
        "GeoServer.URL": geoServerUrl,
        "GeoServer.Version": geoServerVersion,
        "LWAS.URL": lwasUrl,
        "LWAS.Version": lwasVersion,
        "Jenkins.Job": jenkinsJob,
        "Jenkins.Build": jenkinsBuild,
    }
};