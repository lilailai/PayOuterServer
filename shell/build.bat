cd ..
for %%i in ("%cd%") do set jarName=%%~ni

set WORK=%cd%
set classpath=.;
cd %WORK%\target\classes
jar -cvfm %jarName%.jar MANIFEST.MF com version.txt 
copy %jarName%.jar %WORK%\lib
del -f %jarName%.jar

cd ../../
set ph=%cd%

cd ../PayIFramework
set jarName=PayIFramework

set WORK=%cd%
set classpath=.;
cd %WORK%\target\classes
jar -cvfm %jarName%.jar MANIFEST.MF com version.txt 
copy %jarName%.jar %ph%\lib
del -f %jarName%.jar




