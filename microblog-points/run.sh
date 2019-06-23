
image=lgj/microblog-points:1.0.0
container=microblog-points-web

docker ps -a| grep $container

aa-remove-unknown

docker stop  $container
docker rm -f $container
docker run -d --name $container  -p 8102:8102 $image

docker ps | grep $container

echo ====================================

echo "print $container log............"
docker logs -f -t --tail 10  $container
