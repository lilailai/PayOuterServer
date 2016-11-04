pwd=$PWD
cd ..
prjName=${PWD##*/}
echo $prjName

WORK=$PWD
cd $WORK/lib
ps -ef|grep `whoami`|grep java|grep $prjName |awk '{ printf "kill -9 %s\n",$2}'|sh
sum=`ps -ef|grep $prjName.jar|grep $LOGNAME|grep -v grep |wc -l`
if [ $sum -ne 0 ]
then
        echo "$prjName is running now,down fail"
        exit
else
        echo "$prjName down success"
fi

cd $pwd
tail -n 10 $WORK/logs/DEFAULT-INFO.log
