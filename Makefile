build:
	docker-compose up --build -d

stop:
	docker-compose stop

start:
	docker-compose up -d

api-run-local:
	cd ./backend/api && ./gradlew bootRun -Plocal
