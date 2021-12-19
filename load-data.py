import requests
import json

f = open('./MOCK_DATA.json')
data = json.load(f)

url = "http://localhost:8080/order"
headers = {
   'Content-Type': 'application/json'
}

for singledata in data['users']:
    #print(singledata)
    payload = json.dumps(singledata)

    response = requests.request("POST", url, headers=headers, data=payload)

    print(response.text)
f.close()