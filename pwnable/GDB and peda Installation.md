# GDB and peda Installation
**ref : http://noplanlife.com/?p=968**  
***tested on WSL ubuntu 18.04 LTS***  

## Install GDB
```bash
$ sudo apt-get install gdb
$ gdb --version
```

## Run GDB
```bash
$ gdb [file or pid]
```

## Install peda
```bash
$ git clone https://github.com/longld/peda.git ~/peda
$ echo "source ~/peda/peda.py" >> /etc/gdb/gdbinit
```

## Run peda
GDB에서 아래 명령어 입력 시 peda로 연결된다.
```bash
$ source /path/to/.../peda.py
```
<br/>

혹은 gdbinit을 수정하여 gdb 실행시 자동으로 peda와 연결되도록 할 수 있다.
```bash
$ echo "source ~/peda/peda.py" >> sudo /etc/gdb/gdbinit
```

## Further
https://github.com/tjrkddnr/CTF/blob/master/pwnable/GDB%20Instruction.md
