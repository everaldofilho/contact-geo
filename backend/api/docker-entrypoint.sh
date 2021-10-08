#!/bin/sh
set -e

exec java -jar -Dspring.profiles.active=prod /app.jar
