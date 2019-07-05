Write Up
========

# Toddler's Bottle

## fd
![fig1](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/fd/fig1.jpg?raw=true)
read 함수를 통해 buf 변수에 "LETMEWIN\n"이라는 문자열을 입력해야 한다. fd 값을 0으로 만들고 read 함수가 stdin을 받도록 payload를 만들면 flag를 얻을 수 있다. 프로그램 인자 argv[1]에 4660(0x1234)를 넣고, stdin으로 "LETMEWIN\n"을 입력한다.


![fig2](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/fd/fig2.jpg?raw=true)

**payload : (python -c "print 'LETMEWIN'") | ./fd 4660**

***flag : mommy! I think I know what a file descriptor is!!***
<br/>

## collsion
![fig1](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/collision/fig1.jpg?raw=true)
프로그램 인자 argv[1]에 20bytes 만큼을 입력 받고, 그 값을 4byte(int)씩 나눠서 나온 5개의 값을 모두 더한 값이 전역변수 hashcode(0x21DD092C)와 일치하면 flag를 얻을 수 있다. 단, NULL 문자(\x00)와 그 이후의 값은 strlen 함수가 읽어들이지 않으므로 payload에 사용할 수 없다. 0x01010101과 같은 문자열을 4번 입력하고 0x21DD09EC - (0x01010101 * 4)에 해당하는 문자열을 입력하는 형태로 payload를 생성한다. 


![fig2](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/collision/fig2.jpg?raw=true)

**payload : ./col $(python -c "print '\x01'\*16 + '\xe8\x05\xd9\x1d'")**

***flag : daddy! I just managed to create a hash collision :)***
<br/>

## bof
![fig1](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/bof/fig1.jpg?raw=true)
func 함수의 gets 함수에 BOF 취약점이 존재한다. 


![fig2](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/bof/fig2.jpg?raw=true)
gets함수는 ebp - 0x2C 위치에서 함수 호출 인자를 받아온다. 이를 통해 overflowme 변수의 위치가 ebp - 0x2C임을 알 수 있다.

![fig3](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/bof/fig3.jpg?raw=true)<br/>

system("/bin/sh")를 수행하는 위치인 0x664로 RET값을 변조해 보았다. overflowme 변수의 위치부터 48bytes(0x2C + 4bytes(SFP))를 더미 값으로 채우고 그 다음 값으로 0x664를 입력한다.

payload : (python -c "print 'A'*48 + '\x64\x06'") | nc pwnable.kr 9000
함수 호출이 종료될 때 stack smash가 감지되어 exploit에 실패하였다.

<br/>
함수 호출이 종료되기 전에 exploit하기 위해서 RET이 아닌 함수 인자 값을 변조하는 공격을 시도한다. overflowme 변수의 위치부터 52bytes(0x2C + 4bytes(SFP) + 4bytes(RET))를 더미 값으로 채우고 함수 인자인 key 값을 0xcafebabe로 변조하여 if 문의 식을 만족시킴으로서 system("/bin/sh")가 실행되도록 한다.

![fig4](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/bof/fig4.jpg?raw=true)

**payload : (python -c "print 'A'\*52 + '\xbe\xba\xfe\xca'";cat) | nc pwnable.kr 9000**

***flag : daddy, I just pwned a buFFer :)***
<br/>

## flag

## passcode

## random

## input

## leg

## mistake

## shellshock

## coin1

## blackjack

## lotto

## cmd1

## cmd2

## uaf

## memcpy

## asm

## unlink

## blukat

## horcruxes
