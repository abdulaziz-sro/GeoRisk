$resultsDir = if ($env:ALLURE_RESULTS_DIR) {
    $env:ALLURE_RESULTS_DIR
} else {
    "target/allure-results"
}

$testEnvironment = if ($env:TEST_ENVIRONMENT) {
    $env:TEST_ENVIRONMENT
} else {
    "lokal"
}

$geoServerUrl = if ($env:GEOSERVER_URL) {
    $env:GEOSERVER_URL
} else {
    "http://localhost:8080/geoserver"
}

$geoServerVersion = if ($env:GEOSERVER_VERSION) {
    $env:GEOSERVER_VERSION
} else {
    "lokal"
}

$lwasUrl = if ($env:LWAS_URL) {
    $env:LWAS_URL
} else {
    "https://localhost:8443"
}

$lwasVersion = if ($env:LWAS_VERSION) {
    $env:LWAS_VERSION
} else {
    "lokal"
}

$buildNumber = if ($env:BUILD_NUMBER) {
    [int]$env:BUILD_NUMBER
} else {
    0
}

$buildName = if ($env:JOB_NAME) {
    $env:JOB_NAME
} else {
    "Lokale Ausführung"
}

$buildUrl = if ($env:BUILD_URL) {
    $env:BUILD_URL
} else {
    ""
}

$gitCommit = if ($env:GIT_COMMIT) {
    $env:GIT_COMMIT
} else {
    "lokal"
}

New-Item `
    -ItemType Directory `
    -Force `
    -Path $resultsDir | Out-Null

$environmentProperties = @"
Testumgebung=$testEnvironment
GeoServer_URL=$geoServerUrl
GeoServer_Version=$geoServerVersion
LWAS_URL=$lwasUrl
LWAS_Version=$lwasVersion
Git_Commit=$gitCommit
"@

$environmentProperties |
    Set-Content `
        -Path "$resultsDir/environment.properties" `
        -Encoding UTF8

$executor = @{
    name        = "Lokal"
    type        = "jenkins"
    buildOrder  = $buildNumber
    buildName   = "$buildName #$buildNumber"
    buildUrl    = $buildUrl
    reportName  = "OGC Tests | GS $geoServerVersion | LWAS $lwasVersion | $testEnvironment"
}

$executor |
    ConvertTo-Json |
    Set-Content `
        -Path "$resultsDir/executor.json" `
        -Encoding UTF8

Write-Host "Allure-Metadaten wurden unter $resultsDir erzeugt."