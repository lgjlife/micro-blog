clear 
basePath=~/aProject/aRealPrj/microblog/microblog-support/

jarFiles[0]=microblog-center/target/microblog-center-1.0.0.jar
jarFiles[1]=microblog-config-server/target/microblog-config-server-1.0.0.jar
jarFiles[2]=microblog-admin-server/target/microblog-admin-server-1.0.0.jar
jarFiles[3]=microblog-hystrix-dashboard/target/microblog-hystrix-dashboard-1.0.0.jar
jarFiles[4]=microblog-hystrix-turbine/target/microblog-hystrix-turbine-1.0.0.jar
jarFiles[5]=zipkin-server/zipkin-server-2.11.8-exec.jar



jarNames[0]=microblog-center-1.0.0.jar
jarNames[1]=microblog-config-server-1.0.0.jar
jarNames[2]=microblog-admin-server-1.0.0.jar
jarNames[3]=microblog-hystrix-dashboard-1.0.0.jar
jarNames[4]=microblog-hystrix-turbine-1.0.0.jar
jarNames[5]=zipkin-server-2.11.8-exec.jar


echo The cmd is [$1]

echo 
echo "Bash is for [start|stop] the application:"

for file in ${jarNames[*]}
  do
     echo "$file"
done

echo "-------------------------------"
echo 

if [[ $1 == "" ]]
 then 
    echo "The CMD is NULL ,The right CMD is : ./cloud-support.sh [start|stop]"
    exit
fi 
echo "------------------------------"
echo ++++++++++++++++++++++++++++++++
echo kill  the running application!
echo 


if [[ $1 == stop ]] || [[ $1 == start ]]
  then 
for jarName in ${jarNames[*]}
   do
     echo "Kill java Application $jarName"
     pid=$(jps | grep -E "$jarName"| awk '{print $1}')
     if [[ $pid != "" ]]
        then
       	   echo "$jarName pid  is $pid" 
	   kill -9 $pid	
     fi
     echo +
done

fi 

echo 
echo +++++++++++++++++++++++++++++++++++++++++
echo Run the Application 
echo 

if [[ $1 == start ]]
   then 
   for jarFile in ${jarFiles[*]}

     do
         jarFile=$basePath$jarFile
    
         echo Run the $jarFile ......
         nohup  java -jar $jarFile &   
         echo "===================================="
         echo    
     done

fi

sleep 5s
jps 



