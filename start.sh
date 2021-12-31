mvn package -DskipTests;

docker build -t medical-scheduler-system . ;

docker-compose up --force-recreate --build --remove-orphans