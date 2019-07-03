Write Up
========

# Toddler's Bottle

## fd
![fig1](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/fd/fig1.jpg?raw=true)
read 함수를 통해 buf 변수에 "LETMEWIN\n"이라는 문자열을 입력해야 한다. fd 값을 0으로 만들고 read 함수가 stdin을 받도록 payload를 만들면 flag를 얻을 수 있다. 프로그램 인자 argv[1]에 4660(0x1234)를 넣고, stdin으로 "LETMEWIN\n"을 입력한다.


![fig2](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/fd/fig2.jpg?raw=true)

**payload : (python -c "print 'LETMEWIN'") | ./fd 4660**

***flag : mommy! I think I know what a file descriptor is!!***


## collsion
![fig1](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/collision/fig1.jpg?raw=true)
프로그램 인자 argv[1]에 20bytes 만큼을 입력 받고, 그 값을 4byte(int)씩 나눠서 나온 5개의 값을 모두 더한 값이 전역변수 hashcode(0x21DD092C)와 일치하면 flag를 얻을 수 있다. 단, NULL 문자(\x00)와 그 이후의 값은 strlen 함수가 읽어들이지 않으므로 payload에 사용할 수 없다. 0x01010101과 같은 문자열을 4번 입력하고 0x21DD09EC - (0x01010101 * 4)에 해당하는 문자열을 입력하는 형태로 payload를 생성한다. 


![fig2](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/collision/fig2.jpg?raw=true)

**payload : ./col $(python -c "print '\x01\x01\x01\x01'*4 + '\xe8\x05\xd9\x1d'")**

***flag : daddy! I just managed to create a hash collision :)***


## bof

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
