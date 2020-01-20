#!/usr/bin/env bash
set -xe

prepare() {
  echo "PREPARING"

  if [ -d "$testProjectDir" ]; then rm -rf "$testProjectDir"; fi

  mkdir "$testProjectDir"
  mkdir "$jacocoReportDir"
  mkdir "$katalonBundleClasses"
  mkdir "$kreDir"
  touch "$jacocoExecFile"
  touch "$jacocoReportXmlFile"
}

downloadKRE() {
  source "$webTestTools/download-kre.sh" "$kreDir" "$katalonVersion"
}

extractKatalonBundleClasses() {
  echo "EXTRACT KATALON BUNDLE CLASSES"

  jarDir="$kreDir/Katalon Studio Engine.app/Contents/Eclipse/plugins"
  outputDir=$katalonBundleClasses

  source "$webTestTools/extract-katalon-bundles.sh" "$jarDir" "$outputDir"
}

downloadTestProject() {
  echo "DOWNLOAD TEST PROJECT"

  source "$webTestTools/download-test-project.sh" "$testProjectDir"

  if [ -d "$testProjectDir/Reports"]; then rm -rf "$testProjectDir/Reports"; fi
}

injectJacocoAgentForExecution() {
  echo "INJECT JACOCO AGENT FOR EXECUTION"

  jacocoAgent="$jacocoLibDir/jacocoagent.jar"

  source "$webTestTools/inject-jacocoagent.sh" "$testProjectDir" "$jacocoAgent" "$jacocoExecFile"
}

executeTest() {
  echo "EXECUTE TEST"

  projectPath="$testProjectDir/web-samples.prj"
  katalonc="$kreDir/Katalon Studio Engine.app/Contents/MacOS/katalonc"

  source "$webTestTools/run-test.sh" "$projectPath" "$katalonc"
}

generateXmlCoverageReport() {
  echo "GENERATE XML COVERAGE REPORT"

  jacococli="$jacocoLibDir/jacococli.jar"
  
  source "$webTestTools/generate-xml-coverage-report.sh" "$jacococli" "$jacocoExecFile" "$katalonBundleClasses" "$jacocoReportXmlFile"
}

uploadResultToSonar() {
  echo "UPLOAD RESULT TO SONAR"

  sonarScanner="$tools/sonar-scanner/bin/sonar-scanner"
  sonarScannerPropertiesFile="$testProjectDir/sonar-project.properties"
  touch "$sonarScannerPropertiesFile"

  source "$webTestTools/upload-to-sonar.sh" "$sonarScanner" "$sonarScannerPropertiesFile" "$katalonDir" "$katalonBundleClasses" "$jacocoReportXmlFile"
}

katalonDir="$1"
workDir="$2"
katalonVersion="$3"

tools="$katalonDir/tools"
webTestTools="$tools/web-test"
jacocoLibDir="$tools/jacoco/lib"

testProjectDir="$workDir/web-ui-test"

jacocoReportDir="$testProjectDir/jacoco-report"
jacocoExecFile="$jacocoReportDir/jacoco.exec"
jacocoReportXmlFile="$jacocoReportDir/jacoco.xml"

katalonBundleClasses="$testProjectDir/katalon-bundle-classes"

kreDir="$testProjectDir/kre"

prepare
downloadKRE
extractKatalonBundleClasses
downloadTestProject
injectJacocoAgentForExecution
executeTest
generateXmlCoverageReport
uploadResultToSonar
