#!/bin/sh:
maxsize=$((1024*1024*20))
keep_days=2
log_path=/usr/home/softwares/tomcat/logs
ymd=`date +%Y-%m-%d`
hms=`date +%H-%M-%S`

#rm -rf ${log_path}/manager*.log
#rm -rf ${log_path}/host-manager*.log
#rm -rf ${log_path}/localhost*.log

# start delete the expired files  
j=0

cd $log_path

declare -a files

for i in `ls -l|awk '{print $9}'`

do
   files[j]=$i
   #echo $i
   j=`expr $j + 1`

done

for file in ${files[@]}
do  
    cols=`echo $file | awk -F"." '{print NF}'`
    echo $file

    if [ $cols -eq 3 ]
    then
       time=`echo $file|awk -F"." '{print $2}'`
       #echo $time
       file_time=`date -d "${time}" +%s`
       now_time=`date -d "${ymd}" +%s`
       stampDiff=`expr $now_time - $file_time`
       dayDiff=`expr $stampDiff / 86400`
       if [ $dayDiff -gt $keep_days ]
       then
         echo $file 'expired, is being deleted '
         rm -rf $file
       fi
    fi
done

# end delete the expired files  


#start splash the remain logfiles 

declare -a remain_files

for i in `ls -l|awk '{print $9}'`

do
   remain_files[j]=$i
   #echo $i
   j=`expr $j + 1`

done


for file in ${remain_files[@]}
do
    file_size=`ls -l $file | awk '{ print $5 }'`
    #echo 'file_size:'$file_size
    diff=`expr $file_size - $maxsize`
    if [ ${file_size} -ge ${maxsize} ];then  
      cd ${log_path} && cp $file ${log_path}/file.${hms}.log && cat /dev/null > $file 
    fi
done

#end splash the remain logfiles

echo 'logfiles rotate done' 


