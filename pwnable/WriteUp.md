Write Up
========

# Toddler's Bottle

## fd
![fig1](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/fd/fig1.jpg?raw=true)  
read 함수를 통해 buf 변수에 "LETMEWIN\n"이라는 문자열을 입력해야 한다. fd 값을 0으로 만들고 read 함수가 stdin을 받도록 payload를 만들면 flag를 얻을 수 있다. 프로그램 인자 argv[1]에 4660(0x1234)를 넣고, stdin으로 "LETMEWIN\n"을 입력한다.


![fig2](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/fd/fig2.jpg?raw=true)  


**payload : (python -c "print 'LETMEWIN'") | ./fd 4660**

***flag : mommy! I think I know what a file descriptor is!!***
<br/><br/>

## collsion
![fig1](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/collision/fig1.jpg?raw=true)  
프로그램 인자 argv[1]에 20bytes 만큼을 입력 받고, 그 값을 4byte(int)씩 나눠서 나온 5개의 값을 모두 더한 값이 전역변수 hashcode(0x21DD092C)와 일치하면 flag를 얻을 수 있다. 단, NULL 문자(\x00)와 그 이후의 값은 strlen 함수가 읽어들이지 않으므로 payload에 사용할 수 없다. 0x01010101과 같은 문자열을 4번 입력하고 0x21DD09EC - (0x01010101 * 4)에 해당하는 문자열을 입력하는 형태로 payload를 생성한다. 


![fig2](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/collision/fig2.jpg?raw=true)


**payload : ./col $(python -c "print '\x01'\*16 + '\xe8\x05\xd9\x1d'")**

***flag : daddy! I just managed to create a hash collision :)***
<br/><br/>

## bof
![fig1](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/bof/fig1.jpg?raw=true)  
func 함수의 gets 함수에 BOF 취약점이 존재한다. 


![fig2](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/bof/fig2.jpg?raw=true)  
gets함수는 ebp - 0x2C 위치에서 함수 호출 인자를 받아온다. 이를 통해 overflowme 변수의 위치가 ebp - 0x2C임을 알 수 있다.


![fig3](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/bof/fig3.jpg?raw=true)  
함수 호출이 종료되기 전에 exploit하기 위해서 RET가 아닌 함수 인자 값을 변조하는 공격을 시도한다. overflowme 변수의 위치부터 52bytes(0x2C + 4bytes(SFP) + 4bytes(RET))를 더미 값으로 채우고 함수 인자인 key 값을 0xcafebabe로 변조하여 if 문의 식을 만족시킴으로서 system("/bin/sh")가 실행되도록 한다.


![fig4](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/bof/fig4.jpg?raw=true)


**payload : (python -c "print 'A'\*52 + '\xbe\xba\xfe\xca'";cat) | nc pwnable.kr 9000**

***flag : daddy, I just pwned a buFFer :)***
<br/><br/>

## flag
![fig1](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/flag/figure1.JPG?raw=true)  
문제의 바이너리 파일 flag를 HxD로 분석한다. elf파일 포맷이며, upx로 패킹되어 있음임을 확인하였다.


![fig2](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/flag/figure2.JPG?raw=true)  
Detect It Easy를 이용해 분석하면 더 자세한 정보를 얻을 수 있다.


![fig3](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/flag/figure3.JPG?raw=true)  
upx 언패킹을 시도한다.


![fig4](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/flag/figure4.JPG?raw=true)  
IDA를 이용해 .rodata section에 있는 flag값을 확인한다.

***flag : UPX...? sounds like a delivery service :)***
<br/><br/>


## passcode
![fig1](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/passcode/figure1.JPG?raw=true)  
bof 취약점이 존재하는 scanf 함수를 사용하고 있으며, scanf 함수의 인자로 변수의 주소가 아닌 변수 값을 전달하고 있다.


![fig2](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/passcode/figure2.JPG?raw=true)
![fig3](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/passcode/figure3.JPG?raw=true)  
![fig4](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/passcode/figure4.JPG?raw=true)  
welcome 함수의 name 변수는 ebp-0x70에 위치하며, login 함수의 passcode1 변수는 ebp-0x10에 위치한다. 한편 ebp-0x70과 ebp-0x10은 십진수로 96만큼 차이가 난다. 따라서 login 함수 내의 변수가 로드되는 시점에 passcode1 변수의 값은 namee 변수의 96byte 이후의 값과 같아진다. 이 점을 이용해 scanf("%d", passcode1)의 실행 결과를 조작할 수 있다.

login 함수의 scanf("%d", passcode1) 실행 이후, fflush 함수와 printf 함수를 차례로 호출한다. 해당 함수들을 대상으로 GOT overwrite 공격을 수행한다.


![fig5](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/passcode/figure5.JPG?raw=true)  
![fig6](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/passcode/figure6.JPG?raw=true)  
printf 함수의 GOT 주소에는 \x00이 포함되어 있어 payload로 전달할 수 없다. 따라서 fflush 함수의 GOT를 overwrite해야한다.


![fig7](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/passcode/figure7.JPG?raw=true)  
name 변수의 첫 96bytes를 Dummy 값으로 채우고, 마지막 4bytes에 fflush 함수의 GOT 주소를 넣는다. 그리고 scanf의 입력으로 원하는 주소값(login 함수의 if문 내부)을 int 형식으로 입력하여 exploit 한다.


**payload : (python -c "print '134514135'") | (python -c "print 'A'\*96 + '\x04\xa0\x04\x08'";cat) | ./passcode**

***flag : Sorry mom.. I got confused about scanf usage :(***
<br/><br/>


## random
![fig1](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/random/figure1.JPG?raw=true)  
random.c의 소스코드를 보면 random 함수를 이용해 난수를 생성하였으나 seed값을 명시하지 않았다. 따라서 매 실행마다 동일한 값을 생성하게 된다.


![fig2](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/random/figure2.JPG?raw=true)  
GDB를 이용해 key ^ random 연산을 수행하기 직전에 break를 걸고 random 변수에 담긴 값을 확인한다.


![fig3](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/random/figure3.JPG?raw=true)  
random 변수에는 0x6b8b4567이 기록되어있다.  
key ^ 0x6B8B4567 == 0xDEADBEEF 를 만족해야 하므로 key == 0xDEADBEEF ^ 0x6B8B4567 이다. 0xDEADBEEF ^ 0x6B8B4567의 값은 0xB526FB88(십진수 3039230856) 이다.


![fig4](https://github.com/tjrkddnr/CTF/blob/master/pwnable/Toddler's%20Bottle/random/figure4.JPG?raw=true)  


**payload : (python -c "print '3039230856'") | ./random**

***flag : Mommy, I thought libc random is unpredictable...***
<br/><br/>


## input
<br/><br/>


## leg
해당 문제 서버에 접속하는 계정은 gdb, python 등을 사용할 수 없다. 소스 코드 분석을 통해 문제를 해결해야 한다.



***flag : My daddy has a lot of ARMv5te muscle!***
<br/><br/>


## mistake
<br/><br/>
## shellshock
<br/><br/>
## coin1
<br/><br/>
## blackjack
<br/><br/>
## lotto
<br/><br/>
## cmd1
<br/><br/>
## cmd2
<br/><br/>
## uaf
<br/><br/>
## memcpy
<br/><br/>
## asm
<br/><br/>
## unlink
<br/><br/>
## blukat
<br/><br/>
## horcruxes
<br/><br/>
