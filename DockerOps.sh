 mvn clean install 
 
 docker build --pull --rm -f "Dockerfile" -t integ-ms-akuh-parklands-edi:latest "."

 docker tag integ-ms-akuh-parklands-edi:latest 192.180.4.122:5000/integ-ms-akuh-parklands-edi:latest
 
 docker push 192.180.4.122:5000/integ-ms-akuh-parklands-edi:latest

#  http://192.180.4.22:9000/api/webhooks/d4b97603-c33c-447e-bfc1-258aa6c9883f

#  curl -X POST -H "Content-Type: application/json" -d @req.json http://192.180.4.22:9000/api/webhooks/d4b97603-c33c-447e-bfc1-258aa6c9883f
curl -X POST -H "Content-Type: application/json" http://192.180.4.22:9000/api/webhooks/d4b97603-c33c-447e-bfc1-258aa6c9883f

read message