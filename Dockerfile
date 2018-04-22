FROM python:3.4-alpine
ADD . /code
WORKDIR /code
RUN pip install -r requirements.txt
RUN apk add --no-cache curl
CMD ["python", "app.py"]