import requests
import re
import pytest

def test_water_items_endpoint():
    url = 'http://localhost:8080/water/items'
    headers = {'accept': 'application/json'}
    
    response = requests.get(url, headers=headers)
    response_text = response.text
    expected = r"\[\s*(\{\s*\"id\":\s*\d+,\s*\"name\":\s*\"[A-Za-zÅÄÖÜåäöü\s]+\",\s*\"type\":\s*\"[A-Z_]+\",\s*\"water\":\s*\d+,\s*\"price\":\s*\d+\s*\}\s*,?\s*)*\]"
    
    assert re.match(expected, response_text, re.MULTILINE), "Response does not match the expected pattern"

def test_water_eater_endpoint():
    url = 'http://localhost:8080/water/eater'
    headers = {'accept': 'application/json'}
    
    response = requests.get(url, headers=headers)
    response_text = response.text
    expected = r"\{\s*\"id\":\s*\d+,\s*\"name\":\s*\"[A-Za-zÅÄÖÜåäöü\s]+\",\s*\"diet\":\s*\"[A-Z_\s]+\",\s*\"preferernces\":\s*\[\s*(\{\s*\"id\":\s*\d+,\s*\"name\":\s*\"[A-Za-zÅÄÖÜåäöü\s]+\",\s*\"type\":\s*\"[A-Z_]+\",\s*\"water\":\s*\d+,\s*\"price\":\s*\d+\s*\}\s*,?\s*)*\]\s*}"
    
    assert re.match(expected, response_text, re.MULTILINE), "Response does not match the expected pattern"

@pytest.mark.dependency()
def test_auth_register_endpoint():
    url = 'http://localhost:8080/auth/register'
    headers = {'accept': 'text/plain'}
    params = {
        'firstname': 'Joe',
        'lastname': 'Shmoe',
        'username': 'joe_shmoe',
        'email': 'joe.shmoe@example.com',
        'password': 'SecurePassword123!',
        'verifyPassword': 'SecurePassword123!'
    }
    
    response = requests.post(url, headers=headers, params=params)
    response_text = response.text
    
    assert response_text == "User Registered", "Response does not match the expected pattern"

@pytest.mark.dependency(depends=['test_auth_register_endpoint'])
def test_auth_login_endpoint():
    url = 'http://localhost:8080/auth/login'
    headers = {'accept': 'text/plain'}
    params = {
        'email': 'joe.shmoe@example.com',
        'password': 'SecurePassword123!',
    }
    
    response = requests.post(url, headers=headers, params=params)
    response_text = response.text
    expected = r"^([a-zA-Z0-9_=]+)\.([a-zA-Z0-9_=]+)\.([a-zA-Z0-9_\-\+\/=]*)"  # valid JWT
    
    assert re.match(expected, response_text), "Response does not match the expected pattern"

