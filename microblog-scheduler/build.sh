echo "========================"
echo "Start mvn clean ........"
mvn clean

echo "========================"
echo "Start mvn package ........"

mvn package

cd microblog-scheduler-web

echo "========================"
echo "Delete docker for scheduler ........"

docker rmi -f lgj/microblog-scheduler:1.0.0


echo "========================"
echo "Start create docker for scheduler ........"

mvn dockerfile:build

docker images

cd -