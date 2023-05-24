sudo systemctl start docker
sudo bash ./remove.sh
mvn clean install
sudo bash ./run.sh