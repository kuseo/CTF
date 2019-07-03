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
