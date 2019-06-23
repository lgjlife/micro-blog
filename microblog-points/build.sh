image=lgj/microblog-points:1.0.0

mvn clean

mvn package

cd  microblog-points-web

docker rmi -f $image

mvn dockerfile:build

docker images

cd -