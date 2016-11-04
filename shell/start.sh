cd ..
prjName=${PWD##*/}
echo $prjName
sum=`ps -ef|grep $prjName.jar|grep $LOGNAME|grep -v grep |wc -l`

if [ $sum -ne 0 ]
then
        echo "Services is up"
        exit
else
        echo "Services  will up"
fi
WORK=$PWD
cd lib/
#echo $WORK
nohup java -Dfile.encoding="utf8"  -Xms512m -Xmx512m -DWORKDIR=$WORK  -jar $prjName.jar &
cd $pwd
sleep 3
ps -ef|grep $prjName
cd $WORK/logs
tail -f DEFAULT-INFO.log 
