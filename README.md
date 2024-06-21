esolang입니다.
[br]

예시 파일이 있으니
그거 보고 사용 방법 익혀도 됨


변수 선언은
scoreboard objectives add (변수 이름) (형식)
이렇게
형식으로는 int, string, input이 있고 input은 정수만 입력 받을 수 있음

예시)
scoreboard objectives add n1 int

(변수 이름은 띄어쓰기만 없으면 다 됨. 한글도 되고, 숫자로 시작해도 되고, int 같은 다른 곳에서 쓰이는 문자도 됨.)


변수에 값 대입하기
int 형식은
scoreboard players set (변수 이름) 100
이렇게,

string 형식은
scoreboard players set (변수 이름) Hello, world!
이렇게.


연산
scoreboard players operation (변수 1) (연산 기호) (변수 2)
이렇게 하면 되고,
연산 기호는 =, +=, -=, *=, /=, %가 있음


goto
setblock (숫자) redstone_block
이렇게 쓰면, (숫자) 번째 줄로 이동함


조건문
execute if score (변수 1) ((부)등호) (변수 2) run setblock (숫자) redstone_block

예시) execute if score var1 > var2 run setblock 10 redstone_block
이렇게 쓰면, var1이 var2보다 클 경우에 10번째 줄로 이동.


출력
tellraw "Hello, world!"
tellraw "당신이 입력한 숫자는 " + num + "입니다."
say 숫자를 입력하세요.
(say의 경우는 변수 출력 못 함. num이라는 변수가 있어도, say num은 그냥 num을 출력함.)

