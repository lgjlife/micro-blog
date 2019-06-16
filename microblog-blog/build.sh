mvn clean

mvn package

cd blog-web

docker rmi -f lgj/microblog-blog:1.0.0

mvn dockerfile:build

docker images

cd -