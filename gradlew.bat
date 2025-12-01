@echo off
@rem -----------------------------------------------------------------------------
@rem Gradle startup script for Windows
@rem -----------------------------------------------------------------------------

setlocal
set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.

set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

set DEFAULT_JVM_OPTS=-Xmx1024m -Xms256m

set JAVA_EXE=java.exe
if defined JAVA_HOME (
  if exist "%JAVA_HOME%\bin\java.exe" set JAVA_EXE=%JAVA_HOME%\bin\java.exe
)

:findMaxFD
call :findMaxFD2 16384
if errorlevel 1 goto :findMaxFDDone
call :findMaxFD2 8192
if errorlevel 1 goto :findMaxFDDone
call :findMaxFD2 4096
if errorlevel 1 goto :findMaxFDDone
set MAX_FD=2048
goto :findMaxFDDone

:findMaxFD2
ulimit -H -n > nul 2>&1 || exit /b 1
exit /b 0

:findMaxFDDone

set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

if exist "%JAVA_EXE%" goto executeJava
echo Could not find java executable.
exit /b 1

:executeJava
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
exit /b %ERRORLEVEL%
