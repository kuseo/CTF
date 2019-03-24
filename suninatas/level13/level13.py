import zipfile

path = 'web13.zip'

with zipfile.ZipFile(path) as archive:
    for i in range(1000, 10000):
        pw = str(i).encode('ascii')
        try:
            archive.extractall(path = 'web13', pwd = pw)
            print('Password is ', i)
            break
        except:
            pass

            
                