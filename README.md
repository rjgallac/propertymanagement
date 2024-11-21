docker run --detach -p3306:3306 --name some-property --env MARIADB_DATABASE=property --env MARIADB_ROOT_PASSWORD=my-secret-pw mariadb:latest

docker exec -it some-property sh 

mariadb -u root -p 

use property; 

show tables; select * from estatesearch; delete from property;

