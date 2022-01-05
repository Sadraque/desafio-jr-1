mvn package;

docker build -t medical-scheduler-system . ;

docker-compose up --force-recreate --build --remove-orphans