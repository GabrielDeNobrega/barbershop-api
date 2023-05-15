# enter the following folder and check if you have the files called private.pem and public.pem
C:\projects\barbershop-api\src\main\resources\certs

# in case you don't have the files mentiones above, follow the next steps

# create rsa key pair
openssl genrsa -out keypair.pem 2048

# extract public key
openssl rsa -in keypair.pem -pubout -out public.pem

# create private key in PKCS#8 format
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem