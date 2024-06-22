esolang입니다.
<br>
파일에 설명서 있으니<br>
그거 보고 사용 방법 익혀도 됨<br>
<br>
<br>
변수 선언은<br>
scoreboard objectives add (변수 이름) (형식)<br>
이렇게<br>
형식으로는 int, string, input이 있고 input은 정수만 입력 받을 수 있음<br>
<br>
예시)<br>
scoreboard objectives add n1 int<br>
<br>
(변수 이름은 띄어쓰기만 없으면 다 됨. 한글도 되고, 숫자로 시작해도 되고, int 같은 다른 곳에서 쓰이는 문자도 됨.)<br>
<br>
<br>
변수에 값 대입하기<br>
int 형식은<br>
scoreboard players set (변수 이름) 100<br>
이렇게,<br>
<br>
string 형식은<br>
scoreboard players set (변수 이름) Hello, world!<br>
이렇게.<br>
<br>
<br>
연산<br>
scoreboard players operation (변수 1) (연산 기호) (변수 2)<br>
이렇게 하면 되고,<br>
연산 기호는 =, +=, -=, *=, /=, %가 있음<br>
<br>
<br>
goto<br>
setblock (숫자) redstone_block<br>
이렇게 쓰면, (숫자) 번째 줄로 이동함<br>
<br>
<br>
조건문<br>
execute if score (변수 1) ((부)등호) (변수 2) run setblock (숫자) redstone_block<br>
<br>
예시) execute if score var1 > var2 run setblock 10 redstone_block<br>
이렇게 쓰면, var1이 var2보다 클 경우에 10번째 줄로 이동.<br>
<br>
<br>
출력<br>
tellraw "Hello, world!"<br>
tellraw "당신이 입력한 숫자는 " + num + "입니다."<br>
say 숫자를 입력하세요.<br>
(say의 경우는 변수 출력 못 함. num이라는 변수가 있어도, say num은 그냥 num을 출력함.)<br>
정수인 변수 앞에 #을 붙이면 그 숫자를 유니코드로 변환해 줍니다.<br>
scoreboard objectives add num int<br>
scoreboard players set num 61<br>
tellraw #num<br>
출력 결과: a<br>
<br><br><br>
