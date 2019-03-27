## Linux Bash Shell Instruction

linux에서 command로 실행되는 process는 일반적으로 표준 입력 스트림(standard input stream)과 표준 출력 스트림(standard output stream), 그리고 오류 출력 스트림(standard error stream)을 가지고 있다. 모든 스트림은 plain text 형태로 console에 출력된다.

### 1.  redirection
![fig1](https://github.com/tjrkddnr/CTF/blob/master/pwnable/etc/fig1.jpg?raw=true)

프로세스 혹은 프로그램의 stdout을 다른 파일이나 스트림으로 전달한다. 

A > B : B를 write mode로 열어 A의 stdout을 B에 write한다.

A >> B : B를 append mode로 열어 A의 stdout을 B에 append한다.

A &> B : B를 write mode로 열어 A의 stdout과 stderror를 B에 write한다.

### 2.  pipe
![fig1](https://github.com/tjrkddnr/CTF/blob/master/pwnable/etc/fig2.jpg?raw=true)

프로세스 혹은 프로그램의 stdout을 다른 프로그램의 stdin으로 넘겨준다. stdout은 console에 출력된다.

A | ./B : A의 stdout을 B의 stdin으로 사용한다. 

B에 여러 개의 표준 입력 루틴이 있을 경우 아래와 같이 여러 개의 pipe를 사용한다. 

D | (C;cat) | (B;cat) | (A;cat) | ./P : P의 stdin 루틴에 A, B, C, D 순서의 stdout 값을 사용한다. cat 명령어를 통해 stdout 스트림들을 newline이 포함된 하나의 스트림으로 만들어 P의 stdin으로 사용한다.

### 3.	grave accent( \` ) [deprecated]
command substitution. \`로 둘러 쌓인 문자열을 console command로 대체한다. 가독성을 위해 alternate syntax인 $(…)가 권장된다.

\`str\` : str을 실행한다.

$(str) : str을 실행한다.

>https://en.wikipedia.org/wiki/Standard_streams

## Exploit payload Instruction
python c옵션을 이용해 인터프리터를 실행하여 payload를 작성하고 pipe 등을 이용해 프로그램으로 전달한다. python script는 출력 과정에서 EOF를 자동으로 추가하기 때문에 쉘을 invoke 하는 payload의 경우 쉘이 실행된 후 종료되는 것을 방지하기 위해 cat을 넣어주어야 한다.

### 1.  프로그램 인자로 전달하는 경우
./program $(python -c “print argv1 argv2 …”)

### 2.  stdin으로 전달하는 경우
(python -c “print data”; cat) | ./program

(python -c “print dataN”; cat) | … | (python -c “print data2”; cat) | (python -c “print data1”; cat) | ./program

### 3.  nc로 전달하는 경우
(python -c “print data”; cat) | nc pwnable.kr port_number

(python -c “print dataN”; cat) | … | (python -c “print data2”; cat) | (python -c “print data1”; cat) | nc pwnable.kr port_number


