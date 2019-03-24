with open('cipher.txt', 'r') as f:
    cipher = f.read()
    cipher = cipher.split()
    string = ''
    for number in cipher:
        string += chr(int(number))

    print(string)
    f = open('new_cipher.txt', 'w')
    f.write(string)