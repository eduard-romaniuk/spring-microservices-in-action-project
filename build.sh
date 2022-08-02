#!/bin/zsh
function docker-build {
  echo "Start to build $1"
  cd "$1" || exit
  docker build . -t "$1":latest || exit
  cd ..
  echo "Finished to build $1"
}

echo 'Build started'
mvn clean install -DskipTests || exit
docker-build configserver
docker-build eurekaserver
docker-build gatewayserver
docker-build licensing-service
docker-build organization-service
echo 'Build finished'
