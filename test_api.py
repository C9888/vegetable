import requests

try:
    response = requests.get('http://10.242.104.11:8081/api/admin/recipes')
    print(f"Status code: {response.status_code}")
    print(f"Response: {response.json()}")
except Exception as e:
    print(f"Error: {e}")
