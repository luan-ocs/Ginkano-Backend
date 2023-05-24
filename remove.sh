rm -rf ./target
# rm -rf ~/.m2
docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker rmi $(docker images -a -q)
