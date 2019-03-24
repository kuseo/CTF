def translate(char, table):
    if char in table:
        return table[char]
    else:
        return char

with open('cipher.txt', 'r') as f:
    with open('result.txt', 'w') as f2:
        cipher = f.read()

        # substitution
        table = {}
        table['a'] = 'u'
        table['b'] = 't'
        table['c'] = 'a'
        table['d'] = 'g'
        table['e'] = 'j'
        table['f'] = 'l'
        table['g'] = 'n'
        table['h'] = 'p'
        table['i'] = 'r'
        table['j'] = 'w'
        table['k'] = 'y'
        table['l'] = 'z'
        table['m'] = 'c'
        table['n'] = 'e'
        table['o'] = 'f'
        table['p'] = 'h'
        table['q'] = 'm'
        table['r'] = 'b'
        table['s'] = 'k'
        table['t'] = 'x'
        table['u'] = 'v'
        table['v'] = 's'
        table['x'] = 'd'
        table['y'] = 'o'
        table['z'] = 'i'

        result = ''
        for value in cipher:
            result += translate(value, table)
        f2.write(result)