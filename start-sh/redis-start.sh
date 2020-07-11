#! /bin/sh
echo
echo   "#############################################"
echo  "startup the redis........................"
cd ~/java/redis-5.0.3/user
nohup ./redis-server redis.conf &
cd -


ps -ef | grep redis
