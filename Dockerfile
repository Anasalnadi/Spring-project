FROM ubuntu:latest
LABEL authors="nadon"

ENTRYPOINT ["top", "-b"]