@REM ----------------------------------------------------------------------------
@REM Copyright 2001-2004 The Apache Software Foundation.
@REM
@REM Licensed under the Apache License, Version 2.0 (the "License");
@REM you may not use this file except in compliance with the License.
@REM You may obtain a copy of the License at
@REM
@REM      http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS,
@REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM See the License for the specific language governing permissions and
@REM limitations under the License.
@REM ----------------------------------------------------------------------------
@REM

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\org\apache\camel\camel-core\3.0.0-M2\camel-core-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-util\3.0.0-M2\camel-util-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-api\3.0.0-M2\camel-api-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-management-api\3.0.0-M2\camel-management-api-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-base\3.0.0-M2\camel-base-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-support\3.0.0-M2\camel-support-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-browse\3.0.0-M2\camel-browse-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-controlbus\3.0.0-M2\camel-controlbus-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-dataformat\3.0.0-M2\camel-dataformat-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-direct\3.0.0-M2\camel-direct-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-directvm\3.0.0-M2\camel-directvm-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-file\3.0.0-M2\camel-file-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-language\3.0.0-M2\camel-language-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-log\3.0.0-M2\camel-log-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-properties\3.0.0-M2\camel-properties-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-ref\3.0.0-M2\camel-ref-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-rest\3.0.0-M2\camel-rest-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-saga\3.0.0-M2\camel-saga-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-scheduler\3.0.0-M2\camel-scheduler-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-seda\3.0.0-M2\camel-seda-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-stub\3.0.0-M2\camel-stub-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-timer\3.0.0-M2\camel-timer-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-validator\3.0.0-M2\camel-validator-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-vm\3.0.0-M2\camel-vm-3.0.0-M2.jar;"%REPO%"\org\apache\camel\camel-xslt\3.0.0-M2\camel-xslt-3.0.0-M2.jar;"%REPO%"\com\github\ben-manes\caffeine\caffeine\2.7.0\caffeine-2.7.0.jar;"%REPO%"\org\checkerframework\checker-qual\2.6.0\checker-qual-2.6.0.jar;"%REPO%"\com\google\errorprone\error_prone_annotations\2.3.3\error_prone_annotations-2.3.3.jar;"%REPO%"\org\apache\camel\camel-util-json\3.0.0-M2\camel-util-json-3.0.0-M2.jar;"%REPO%"\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar;"%REPO%"\org\apache\logging\log4j\log4j-api\2.11.1\log4j-api-2.11.1.jar;"%REPO%"\org\apache\logging\log4j\log4j-core\2.11.1\log4j-core-2.11.1.jar;"%REPO%"\org\apache\logging\log4j\log4j-slf4j-impl\2.11.1\log4j-slf4j-impl-2.11.1.jar;"%REPO%"\org\telegram\telegrambots\3.5\telegrambots-3.5.jar;"%REPO%"\org\telegram\telegrambots-meta\3.5\telegrambots-meta-3.5.jar;"%REPO%"\com\google\inject\guice\4.1.0\guice-4.1.0.jar;"%REPO%"\javax\inject\javax.inject\1\javax.inject-1.jar;"%REPO%"\aopalliance\aopalliance\1.0\aopalliance-1.0.jar;"%REPO%"\com\google\guava\guava\19.0\guava-19.0.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-annotations\2.9.8\jackson-annotations-2.9.8.jar;"%REPO%"\com\fasterxml\jackson\jaxrs\jackson-jaxrs-json-provider\2.9.8\jackson-jaxrs-json-provider-2.9.8.jar;"%REPO%"\com\fasterxml\jackson\jaxrs\jackson-jaxrs-base\2.9.8\jackson-jaxrs-base-2.9.8.jar;"%REPO%"\com\fasterxml\jackson\module\jackson-module-jaxb-annotations\2.9.8\jackson-module-jaxb-annotations-2.9.8.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-databind\2.9.8\jackson-databind-2.9.8.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-core\2.9.8\jackson-core-2.9.8.jar;"%REPO%"\org\glassfish\jersey\media\jersey-media-json-jackson\2.25.1\jersey-media-json-jackson-2.25.1.jar;"%REPO%"\org\glassfish\jersey\core\jersey-common\2.25.1\jersey-common-2.25.1.jar;"%REPO%"\org\glassfish\jersey\bundles\repackaged\jersey-guava\2.25.1\jersey-guava-2.25.1.jar;"%REPO%"\org\glassfish\hk2\osgi-resource-locator\1.0.1\osgi-resource-locator-1.0.1.jar;"%REPO%"\org\glassfish\jersey\ext\jersey-entity-filtering\2.25.1\jersey-entity-filtering-2.25.1.jar;"%REPO%"\org\glassfish\jersey\containers\jersey-container-grizzly2-http\2.25.1\jersey-container-grizzly2-http-2.25.1.jar;"%REPO%"\org\glassfish\hk2\external\javax.inject\2.5.0-b32\javax.inject-2.5.0-b32.jar;"%REPO%"\org\glassfish\grizzly\grizzly-http-server\2.3.28\grizzly-http-server-2.3.28.jar;"%REPO%"\org\glassfish\grizzly\grizzly-http\2.3.28\grizzly-http-2.3.28.jar;"%REPO%"\org\glassfish\grizzly\grizzly-framework\2.3.28\grizzly-framework-2.3.28.jar;"%REPO%"\javax\ws\rs\javax.ws.rs-api\2.0.1\javax.ws.rs-api-2.0.1.jar;"%REPO%"\org\glassfish\jersey\core\jersey-server\2.25.1\jersey-server-2.25.1.jar;"%REPO%"\org\glassfish\jersey\core\jersey-client\2.25.1\jersey-client-2.25.1.jar;"%REPO%"\org\glassfish\jersey\media\jersey-media-jaxb\2.25.1\jersey-media-jaxb-2.25.1.jar;"%REPO%"\javax\annotation\javax.annotation-api\1.2\javax.annotation-api-1.2.jar;"%REPO%"\org\glassfish\hk2\hk2-api\2.5.0-b32\hk2-api-2.5.0-b32.jar;"%REPO%"\org\glassfish\hk2\hk2-utils\2.5.0-b32\hk2-utils-2.5.0-b32.jar;"%REPO%"\org\glassfish\hk2\external\aopalliance-repackaged\2.5.0-b32\aopalliance-repackaged-2.5.0-b32.jar;"%REPO%"\org\glassfish\hk2\hk2-locator\2.5.0-b32\hk2-locator-2.5.0-b32.jar;"%REPO%"\org\javassist\javassist\3.20.0-GA\javassist-3.20.0-GA.jar;"%REPO%"\javax\validation\validation-api\1.1.0.Final\validation-api-1.1.0.Final.jar;"%REPO%"\org\json\json\20160810\json-20160810.jar;"%REPO%"\org\apache\httpcomponents\httpclient\4.5.6\httpclient-4.5.6.jar;"%REPO%"\org\apache\httpcomponents\httpcore\4.4.10\httpcore-4.4.10.jar;"%REPO%"\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;"%REPO%"\commons-codec\commons-codec\1.12\commons-codec-1.12.jar;"%REPO%"\org\apache\httpcomponents\httpmime\4.5.6\httpmime-4.5.6.jar;"%REPO%"\commons-io\commons-io\2.6\commons-io-2.6.jar;"%REPO%"\com\vdurmont\emoji-java\3.2.0\emoji-java-3.2.0.jar;"%REPO%"\org\jsoup\jsoup\1.11.3\jsoup-1.11.3.jar;"%REPO%"\org\jetbrains\annotations\17.0.0\annotations-17.0.0.jar;"%REPO%"\kz\zangpro\QazTBot\1.0-SNAPSHOT\QazTBot-1.0-SNAPSHOT.jar
set EXTRA_JVM_ARGUMENTS=
goto endInit

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS% %EXTRA_JVM_ARGUMENTS% -classpath %CLASSPATH_PREFIX%;%CLASSPATH% -Dapp.name="workerBot" -Dapp.repo="%REPO%" -Dbasedir="%BASEDIR%" kz.zangpro.MainApp %CMD_LINE_ARGS%
if ERRORLEVEL 1 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=1

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@endlocal

:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
