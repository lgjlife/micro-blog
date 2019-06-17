mvn clean

mvn package

cd microblog-user-web

docker rmi -f lgj/microblog-user:1.0.0

mvn dockerfile:build

docker images

cd -