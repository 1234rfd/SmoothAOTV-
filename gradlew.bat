@echo off
REM ---------------------------------------------------------------------------
REM Gradle startup script for Windows
REM ---------------------------------------------------------------------------
SETLOCAL
SET DIRNAME=%~dp0
IF "%DIRNAME%"=="" SET DIRNAME=.

SET APP_BASE_NAME=%~n0
SET APP_HOME=%DIRNAME%

SET DEFAULT_JVM_OPTS=-Xmx1024m -Xms256m

SET JAVA_EXE=java.exe
IF DEFINED JAVA_HOME (
    IF EXIST "%JAVA_HOME%\bin\java.exe" SET JAVA_EXE=%JAVA_HOME%\bin\java.exe
)

SET CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
EXIT /B %ERRORLEVEL%
