import requests

login_url = 'http://suninatas.com/member/mem_action.asp'
with requests.Session() as s:
    main_login = {'Hid': '', 'Hpw': ''}

    main_login_req = s.post(login_url, data=main_login)

    if main_login_req.status_code != 200:
        raise Exception('connection error')
    
    #로그인 세션 유지
    level7 = 'http://suninatas.com/Part_one/web07/web07.asp'
    level7_1 = 'http://suninatas.com/Part_one/web07/web07_1.asp'
    data = {'web07': 'Do+U+Like+girls%3F'}
    enter = s.get(level7)
    submit = s.post(level7_1, data=data)
    if submit.status_code != 200:
        raise Exception('error')
    
    with open('level7.txt', 'w') as f:
        f.write(submit.text)