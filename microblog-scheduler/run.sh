docker ps -a| grep microblog-scheduler-web

aa-remove-unknown

docker stop microblog-scheduler-web
docker rm -f microblog-scheduler-web
docker run -d --name microblog-scheduler-web  -p 8703:8703 lgj/microblog-scheduler:1.0.0

docker ps | grep microblog-scheduler-web

echo ====================================

echo "print microblog-scheduler-web log............"
docker logs -f -t --tail 10  microblog-scheduler-web
