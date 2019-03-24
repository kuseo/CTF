f1 = open('cipher.txt', 'r')
f2 = open('result.txt', 'r')

with f1, f2:
    s1 = f1.read()
    s2 = f2.read()
    print(len(s1))
    print(len(s2))