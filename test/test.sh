#!/bin/bash

# Integration test script for the backend
# regex was generated via ChatGPT
# curl commands were taken from the swagger documentaion

test_water_items_endpoint(){

	RESPONSE=$(curl -X 'GET' 'http://localhost:8080/water/items' -H 'accept: application/json')
	EXPECTED_PATTERN='^\[\{"id":[0-9]+,"name":"[A-Za-z]+","type":"[A-Z_]+","water":[0-9]+,"price":[0-9]+\},.*\]$'

	if ! [[ "$RESPONSE" =~ $EXPECTED_PATTERN ]]; then
  		echo "Response does not match the expected pattern"
  		exit 1
	fi

	echo "Test /water/items passed!"
}

test_water_eater_endpoint() {

	RESPONSE=$(curl -X 'GET' 'http://localhost:8080/water/eater' \
			-H 'accept: application/json')
	EXPECTED_PATTERN='^\{"id":[0-9]+,"name":"[A-Za-z\s]+","diet":"[A-Z]+","preferences":\[(\{"id":[0-9]+,"name":"[A-Za-z]+","type":"[A-Z_]+","water":[0-9]+,"price":[0-9]+\},)*\{"id":[0-9]+,"name":"[A-Za-z]+","type":"[A-Z_]+","water":[0-9]+,"price":[0-9]+\}\]\}$'

	if ! [[ "$RESPONSE" =~ $EXPECTED_PATTERN ]]; then
  		echo "Response does not match the expected pattern"
  		exit 1
	fi

	echo "Test /water/items passed!"
}

test_auth_register_endpoint() {

	RESPONSE=$(curl -X 'POST' 'http://localhost:8080/auth/register?firstname=Joe&lastname=Shmoe&username=joe_shmoe&email=joe.shmoe%40example.com&password=SecurePassword123%21&verifyPassword=SecurePassword123%21' \
  			-H 'accept: text/plain' \
  			-d '')

	if ! [[ "$RESPONSE" == "User Registered" ]]; then
  		echo "Response does not match the expected pattern"
  		exit 1
	fi

	echo "Test /auth/register passed!"
}

test_auth_login_endpoint() {

	RESPONSE=$(curl -X 'GET' 'http://localhost:8080/water/eater' \
		   -H 'accept: application/json')
	EXPECTED_PATTERN='^([A-Za-z0-9-_]+)\.([A-Za-z0-9-_]+)\.([A-Za-z0-9-_]+)$' #valid jwt

	if ! [[ "$RESPONSE" =~ $EXPECTED_PATTERN ]]; then
  		echo "Response does not match the expected pattern"
  		exit 1
	fi

	echo "Test /water/items passed!"
}

# Function to test auth endpoints
test_auth_endpoints() {
	echo "Testing auth endpoints..."
	test_auth_register_endpoint
	test_auth_login_endpoint
	echo "auth endpoints tested successfully!"
}

test_water_endpoints() {
	echo "Testing water endpoints..."
	test_water_eater_endpoint
	test_water_eater_endpoint
	echo "water endpoints tested successfully!"
}

# Call the test functions
test_water_endpoints
test_auth_endpoints

echo "All tests passed!"