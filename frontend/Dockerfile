FROM webdevops/nginx

ARG BACKEND_PORT=8080
ENV WEB_DOCUMENT_ROOT /app
ENV WEB_DOCUMENT_INDEX index.html

WORKDIR /app

COPY ./index.html /app/index-original.html

RUN sed "s/8080/${BACKEND_PORT}/g" /app/index-original.html > /app/index.html