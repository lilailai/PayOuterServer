cd ..
for %%i in ("%cd%") do set jarName=%%~ni
set WORKDIR=%cd%
set classpath=.
set WORK=.
cd %WORKDIR%\lib
java -Xms256m -Xmx512m -DWORKDIR=%WORKDIR% -jar %jarName%.jar
pause
