docker ps -a| grep microblog-user-web

aa-remove-unknown

docker stop microblog-user-web
docker rm -f microblog-user-web
docker run -d --name microblog-user-web  -p 8002:8002 lgj/microblog-user:1.0.0

docker ps | grep microblog-user-web

echo ====================================

echo "print microblog-user-web log............"
docker logs -f -t --tail 10  microblog-user-web
