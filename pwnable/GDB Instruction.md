# GDB 명령어 정리
**ref : https://blog.naver.com/kth10922/221030178460**


## 실행
* gdb [바이너리]  
    * 바이너리를 gdb용으로 실행시킨다.
* gdb [바이너리] [코어파일]
     * 코어파일을 바이너리와 매치시켜 실행한다.
* run (인자)
    * 바이너리 실행
* symbol-file [symbol이 있는 바이너리]
    * 바이너리에 심볼이 없는 경우 추가
* start
    * main() 에 중단점을 설정해서 main() 까지 실행
* next (횟수)
    * 한 행씩 실행 (횟수를 지정하면 횟수만큼 명령 수행)
* step (횟수)
    * 한 행씩 실행 (횟수를 지정하면 횟수만큼 명령 수행)
    * 현재 행이 함수일경우 함수 내부로 진입 (next의 경우 함수 내부를 들어가지 않음)
    * test() 에서 실행이 정지되어있을때 step 을 실행하면 내부로 진입
* nexti (횟수)
    * 하나의 명령(어셈블리 명령)을 실행 (횟수를 지정하면 횟수만큼 명령 수행)
* stepi (횟수)
    * 하나의 명령(어셈블리 명령)을 실행 (횟수를 지정하면 횟수만큼 명령 수행)
    * 함수 내부 진입
* continue (횟수)
    * 다음 중단점 까지 실행
    * 횟수를 지정하면 횟수만큼 무시 (5일경우 6번째 중단점에서 정지)
* ignore [중단점번호] [횟수]
    *중단점 번호에 지정한 중단점, 감시점, 포착점(catchpiont)을 지정한 횟수만큼 무시한다.
* finish
    * 현재 실행되고있는 함수가 종료할 때까지 실행
* until (주소)
    * 현재 실행되고있는 함수나 Loop 등이 종료할 때까지 실행

## 프로세스
* attach [pid]
    * pid 로 프로세스에 attach 한다
    * gdb 실행상태에서 실행
* gdb [파일명] [pid]
    * pid 로 프로세스에 attach 한다


## 중단점
* break [함수명]
* break [행번호]
* break [파일명]:[행번호]
* break [+오프셋]
    * 현시점 부터 n행 앞
* break [-오프셋]
    * 현시점 부터 n행 뒤
* break [*주소]
* break [...] if [조건]
    * 해당 지점에서 특정 조건일때 정지
    * ex : break test if aaa==1
* continue [중단점번호]
    * 해당 중단점번호의 위치부터 프로그램을 시작
* continue [중단점번호] [조건]
* info break
    * break 지점 확인
* clear
    * 모든 break 삭제
* clear [함수명]
    * function 엔트리에 설정된 break를 삭제
* clear [행번호]
* clear [파일명]:[행번호]
* clear [파일명]:[함수명]
* delete [중단점번호]
* disable
    * 모든 중단점 비활성화
* disable [중단점번호]
    * 해당 중단점번호만 비활성화
disable display [디스플레이번호]
    * 정의한 자동출력 설정 비활성화
* disable mem [메모리영역]
    * 정의한 메모리영역 비활성화
* enable
    * 모든 중단점 활성화
* enable [중단점번호]
* enable once [중단점번호]
    * 지정한 중단점을 한번만 활성
* enable delete [중단점번호]
    * 실행이 정지했을때 해당 중단점을 제거
* enable display [디스플레이번호]
    * 정의한 자동출력 설정 활성화
* enable mem [메모리영역]
    * 정의한 메모리영역 활성화
* command [중단점번호]
    * 중단점에 정지했을 때 자동적으로 명령을 실행
```
***command 명령어 예제***
(gdb) break test     ->  (breakpoint 1)
(gdb) command 1
>print aaa
>end
(gdb) c
```

## 스택 프레임
* backtrace
    * 모든 백트레이스 출력
* backtrace [N]
    * 최초 N개의 프레임만 백트레이스를 출력
    * 프레임이 0~9 까지있으면 0~N-1 까지 출력
* backtrace [-N]
    * 마지막 N개의 프레임만 백트레이스를 출력
    * 프레임이 0~9 까지있으면 10-N~9 까지 출력
* backtrace full
    * 백트레이스와 로컬변수도 출력
* backtrace full [N]
    * 백트레이스와 로컬변수도 출력, N은 앞의 내용과 동일
* backtrace full [-N]
    * 백트레이스와 로컬변수도 출력, N은 앞의 내용과 동일


## 출력
* p 변수명
* p 주소
* info reg
    * 레지스터 출력
        * 각 레지스터는 레지스터 명 앞에 $를 붙여 출력할수있다
        * p $eax
        x $eax
* p/[형식] [변수 or 주소]
    * 형식
        * t : 2진수로 출력
        * o :8진수로 출력
        * d : 부호가 있는 10진수로 출력 (int)
        * u : 부호가 없는 10진수로 출력 (unsigned int)
        * x : 16진수로 출력
        * c : 최초 1바이트 값을 문자형으로 출력
        * f : 부동 소수점 값 형식으로 출력
        * a : 변수 or 주소와 가장 가까운 심볼의 오프셋을 출력
```
aaa[2] = {1, 2}; 의 메모리 주소를 16진수로 읽을경우 예시
(gdb) p/a &aaa
$3 = 0x601034 <aaa>
(gdb) x/8x 0x601034
x0x601034 <aaa>: 0x01    0x00    0x00    0x00    0x02    0x00    0x00    0x00
```
* x/[범위][형식][단위] [주소]
    * 메모리의 내용 출력
    * 범위 : 몇 회 반복 (기본 4byte 단위)
    * 형식
        * x : 16진수
        * u : 부호 없는 10진수
        * d : 10진수
        * o : 8진수
        * t  : 2진수
        * a : 어드레스
        * c : 문자
        * f : 부동 소수점
        * s : 문자열
        * i : 기계어 명령
    * 단위
        * b : 바이트
        * h : 하프 바이트 (2바이트)
        * w : 워드 (4바이트), Default 출력
        * g : 자이언트 바이트 (8바이트)


## 역어셈블
* disassemble [함수명]
* disassemble [어드레스]
* disassemble [시작어드레스] [종료어드레스]


## 감시점
* watch [변수]
    * 변수가 변경되었을때 실행 정지
* rwatch [변수]
    * 변수가 참조되었을때 실행 정지
* awatch [변수]
    * 변수가 참조/변경 되었을 때 실행 정지
* info watchpoints
    * 감시점 및 break 확인. info break와 동일
* delete [감시점번호]
    * 감시점번호에 해당하는 감시점 삭제


## 변수 설정
* set variable [변수]=[식]
    * 변수값 변경
    * 예) set variable aaa = 0
* set $[변수명]=[식]
    * 변수 정의
    * 예) set $aaa=0


## 코어파일 생성
* generate-core-file
    * 디버그하고있는 프로세스의 현재 시점의 코어파일을 생성


## 히스토리
* show value
    * 마지막 10개값 출력
* print [옵션]
    * $ : 마지막 값
    * $n : n번째 값
    * $$ : 마지막 직전 값
    * $$n : 마지막 값에서 n번째 값
    * $_ : x 명령으로 마지막에 조사한 주소
    * $__ : x 명령으로 마지막에 조사한 주소 값
    * $_exitcode : 디버그하고 있는 프로그램의 종료코드
    * $bpnum : 마지막에 설정한 중단점번호
​

## 매크로
* define
    * 사용자 정의 명령을 작성
```
(gdb) define 명령이름
>
...
> end
```
* document
    * define으로 작성한 명령에 대한 설명을 기술
```
(gdb) document 명령이름
>
...
>end
```
* help
    * 정의한 명령에 대한 설명을 출력
```
(gdb) help 명령이름
```

```
***매크로 예제***
 $pc가 나타내는 어드레스로부터 10개의 어셈블러 명령을 출력하는 li라는 명령을 정의한다. 또한 document 명령으로 li 명령에 대한 설명을 정의하고, 이를 help li로 출력한다.

(gdb) define li
> x/10i $pc
> end

(gdb) document li
> list machine instruction
> end

실행
(gdb) start
  ...
main (argc=1, argv=0xbfe23384) at main.c:28

(gdb) li
0x805bd04 <main+20>: movl $0x8179826,0x4(%esp)
  ... (8개 생략)
0x805bd2d <main+61>: call $0x812f890,0x4 <ruby_init_stack>

(gdb) help li
list machine instruction
```