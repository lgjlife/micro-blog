docker ps -a| grep microblog-blog-web

aa-remove-unknown

docker stop microblog-blog-web
docker rm -f microblog-blog-web
docker run -d --name microblog-blog-web  -p 8703:8703 lgj/microblog-blog:1.0.0

docker ps | grep microblog-blog-web

echo ====================================

echo "print microblog-blog-web log............"
docker logs -f -t --tail 10  microblog-blog-web
