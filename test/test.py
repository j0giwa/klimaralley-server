import requests
import re
import pytest

def test_water_items_endpoint():
    print("Testing /water/items")
    
    url = 'http://localhost:8080/water/items'
    headers = {'accept': 'application/json'}
    
    response = requests.get(url, headers=headers)
    response_text = response.text
    expected = r"\[\s*(\{\s*\"id\":\s*\d+,\s*\"name\":\s*\"[A-Za-zÅÄÖÜåäöü\s]+\",\s*\"type\":\s*\"[A-Z_]+\",\s*\"water\":\s*\d+,\s*\"price\":\s*\d+\s*,\s*\"icon\":\s*\"(?:[A-Za-z0-9+\/]{4})*(?:[A-Za-z0-9+\/]{2}==|[A-Za-z0-9+\/]{3}=)\"\s*\}\s*,?\s*)*\]"
    
    assert re.match(expected, response_text, re.MULTILINE), "Response does not match the expected pattern"

def test_water_eater_endpoint():
    print("Testing /water/eater")
    
    url = 'http://localhost:8080/water/eater'
    headers = {'accept': 'application/json'}
    
    response = requests.get(url, headers=headers)
    response_text = response.text
    expected = r"\{\s*\"id\":\s*\d+,\s*\"name\":\s*\"[A-Za-zÅÄÖÜåäöü\s]+\",\s*\"diet\":\s*\"[A-Z_\s]+\",\s*\"preferernces\":\s*\[\s*(\{\s*\"id\":\s*\d+,\s*\"name\":\s*\"[A-Za-zÅÄÖÜåäöü\s]+\",\s*\"type\":\s*\"[A-Z_]+\",\s*\"water\":\s*\d+,\s*\"price\":\s*\d+\s*,\s*\"icon\":\s*\"(?:[A-Za-z0-9+\/]{4})*(?:[A-Za-z0-9+\/]{2}==|[A-Za-z0-9+\/]{3}=)\"\s*\}\s*,?\s*)*\]\s*}"
    
    assert re.match(expected, response_text, re.MULTILINE), "Response does not match the expected pattern"

@pytest.mark.dependency()
def test_auth_register_endpoint():
    print("Testing /auth/register")
    
    url = 'http://localhost:8080/auth/register'
    headers = {'accept': 'application/json'}
    body = {
        'firstname': 'Joe',
        'lastname': 'Shmoe',
        'username': 'joe_shmoe',
        'email': 'joe.shmoe@example.com',
        'password': 'SecurePassword123!',
        'verifyPassword': 'SecurePassword123!'
    }
    
    response = requests.post(url, headers=headers, json=body)
    response_text = response.text
    expected = r"\{\s*\"message\":\s*\"User registered\"\s*\}"
    
    assert re.match(expected, response_text, re.MULTILINE), "Response does not match the expected pattern"

@pytest.mark.dependency(depends=['test_auth_register_endpoint'])
def test_auth_login_endpoint():
    print("Testing /auth/register")
    
    url = 'http://localhost:8080/auth/login'
    headers = {'accept': 'application/json'}
    body = {
        'email': 'joe.shmoe@example.com',
        'password': 'SecurePassword123!',
    }
    
    response = requests.post(url, headers=headers, json=body)
    response_text = response.text
    expected = r"\{\s*\"message\":\s*\"Authentication successful\",\s*\"token\":\s*\"(([a-zA-Z0-9_=]+)\.([a-zA-Z0-9_=]+)\.([a-zA-Z0-9_\-\+\/=]*))\"\s*\}"
    
    assert re.match(expected, response_text, re.MULTILINE), "Response does not match the expected pattern"
