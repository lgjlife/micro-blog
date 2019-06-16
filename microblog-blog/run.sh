docker ps -a

aa-remove-unknown

docker stop microblog-blog-web
docker rm -f microblog-blog-web
docker run -d --name microblog-blog-web  -p 8703:8703 lgj/microblog-blog:1.0.0

docker ps

sleep 3s
docker logs microblog-blog-web
