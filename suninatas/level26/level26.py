with open('cipher.txt', 'r') as f:
    with open('analysis.txt', 'w') as f2:
        cipher = f.read()

        result1 = {}
        result2 = {}
        result3 = {}

        length = len(cipher)

        # monogram
        for i in range(0, length, 1):
            value = cipher[i:i+1]
            if value in result1:
                result1[value] += 1
            else:
                result1[value] = 1

        # bigram
        for i in range(0, length, 2):
            value = cipher[i:i+2]
            if value in result2:
                result2[value] += 1
            else:
                result2[value] = 1

        # trigram
        for i in range(0, length, 3):
            value = cipher[i:i+3]
            if value in result3:
                result3[value] += 1
            else:
                result3[value] = 1

        # sorted by values
        for t in sorted(result1.items(), key = lambda t:t[1], reverse = True):
            f2.write(t[0] + ' : ' + str(t[1]) + '\n')
        f2.write('\n\n')

        for t in sorted(result2.items(), key = lambda t:t[1], reverse = True):
            f2.write(t[0] + ' : ' + str(t[1]) + '\n')
        f2.write('\n\n')

        for t in sorted(result3.items(), key = lambda t:t[1], reverse = True):
            f2.write(t[0] + ' : ' + str(t[1]) + '\n')