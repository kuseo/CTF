# peda Installation
** ref http://noplanlife.com/?p=968  
*** tested on WSL ubuntu 18.04 LTS

## Install GDB
```bash
$ sudo apt-get install gdb
```

## Install peda
```bash
$ wget http://ropshell.com/peda/peda.tar.gz
$ tar zxvf peda.tar.gz
$ ls -l peda
```  

GDB에서 아래 명령어 입력 시 peda로 연결됨
```bash
$ source /path/to/.../peda.py
```  
  
혹은 .gdbinit을 수정하여 gdb 실행시 자동으로 peda와 연결되도록 할 수 있다.
```bash
$ echo "source /path/to/.../peda.py" >> /etc/gdb/gdbinit
```  
