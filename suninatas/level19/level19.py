with open('cipher.txt', 'r') as f:
    with open('result.txt', 'w') as f2:
        temp = f.read()

        # 개행 제거
        temp = temp.split('\n')
        cipher =''
        for value in temp:
            cipher += value

        # 8bits ascii
        caesar = ''
        length = len(cipher)
        for i in range(0, length,8):
            temp = cipher[i:i+8]
            integer = int(temp, 2)
            caesar += chr(integer)

        # caesar cipher
        for key in range(1, 26):
            result = ''
            for char in caesar:
                asc = ord(char)
                if asc == 32: # space
                    result += char
                    continue
                asc += key
                if(asc > 90):
                    asc = 64 + (asc % 90)
                result += chr(asc)
            print(key, ' : ', result)
            f2.write(str(key) + ' : ' + result + '\n')