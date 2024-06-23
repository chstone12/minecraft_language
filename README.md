esolang입니다.<br>
그리고 깃허브 처음 써봐서 이렇게 올리는 게 맞는지는 모르겠네요<br>
<br>
code.txt에다가 쓰면 됩니다.<br>
<br>
<br>
<br>
 1. 변수 선언하기<br>
  scoreboard objectives add (변수 이름) (int/string/input)<br>
<br>
  scoreboard objectives add num int<br>
  scoreboard objectives add str string<br>
  scoreboard objectives add inp input<br>
<br>
  변수 이름엔 띄어쓰기만 없으면 됩니다. int, 1a, 111, scoreboard, ㅇㅅㅇ <-- 이런 이름들 다 됨<br>
 		scoreboard objectives add  int <-- 이것도 됨 ㅋㅋ<br>
  input으로는 입력을 받을 수 있는데, 정수만 가능합니다. (당연히 type도 int)<br>
<br>
<br>
<br>
 2. 변수에 값 넣기<br>
  scoreboard players set (변수 이름) (숫자)<br>
<br>
  scoreboard players set num 100<br>
  scoreboard players set str Hello, world!<br>
<br>
<br>
<br>
 3. 출력<br>
  say는 뒤에 있는 모든 문자를 그대로 출력합니다.<br>
  tellraw가 print의 기능을 합니다.<br>
<br>
  say 숫자를 입력하세요.<br>
  tellraw "숫자를 입력하세요."<br>
  tellraw "입력된 숫자는" + inp + "입니다."<br>
<br>
<br>
  tellraw에서, int 변수 앞에 #을 붙이면 숫자를 유니코드로 바꿔 줍니다.<br>
  <br>
  scoreboard players set num1 61<br>
  tellraw num1 + "을 유니코드로 바꾸면" + #num1<br>
  > 61을 유니코드로 바꾸면 a<br>
<br>
<br>
<br>
 4. 연산<br>
  scoreboard players operation (변수1) (기호) (변수2)<br>
<br>
  (변수1)과 (변수2)는 모두 int여야 합니다.<br>
  (기호)에는 =, +=, -=, /=, *=, %=, ^=를 쓸 수 있습니다.<br>
  <br>
  scoreboard players operation num1 += num2<br>
  scoreboard players operation num1 *= num2<br>
<br>
<br>
<br>
  scoreboard players add (변수) (숫자)<br>
  scoreboard players remove (변수) (숫자)<br>
<br>
  (변수)의 값을 (숫자)만큼 빼거나 더합니다. (숫자) 부분에는 자연수만 쓸 수 있습니다.<br>
<br>
  scoreboard players add num1 100<br>
  scoreboard players remove num2 30<br>
<br>
<br>
  <br>
  execute store result score (변수1) run scoreboard players get (변수2)<br>
<br>
  (변수1)의 값을 (변수2)의 길이로 바꿉니다. ( Hello → 5 )<br>
  (변수2)가 int여도 그 숫자의 길이를 셉니다. ( 12345 → 5 )<br>
<br>
<br>
 4-2. 랜덤<br>
  execute store result score (변수) run random value (숫자1)..(숫자2)<br>
   이렇게 쓰면 (변수)의 값을 (숫자1)과 (숫자2) 사이에 있는 숫자 중 하나로 바꿉니다.<br>
  <br>
  execute store result score random_number run random value 1..20<br>
   이렇게 쓰면, random_number의 값은 1에서 20 중 하나가 되겠죠?<br>
<br>
  execute store result score random_number run random value num1..num2<br>
   이런 식으로 변수를 쓸 수도 있습니다.<br>
   만약 num1이 10이고 num2가 15라면, random_number는 10에서 15 중 하나가 됩니다.<br>
<br>
<br>
<br>
 5. goto<br>
  esolang에 goto가 없으면 안 되겠죠?<br>
  <br>
  setblock (숫자) redstone_block<br>
  (숫자)번째 줄로 이동합니다.<br>
  (숫자) 부분에 int 변수를 넣어도 됩니다.<br>
<br>
<br>
1 |  setblock 3 redstone_block<br>
2 |  say a<br>
3 |  say b<br>
<br>
  이렇게 써 있으면, a는 출력되지 않고 b만 출력됩니다.<br>
<br>
<br>
<br>
 6. 조건문<br>
  execute if score (변수1) ((부)등호) (변수2) run setblock (숫자) redstone_block<br>
   <br>
  ((변수1) ((부)등호) (변수2))가 참이라면, (숫자)번째 줄로 이동합니다.<br>
  마찬가지로 (숫자) 부분에 int 변수를 넣어도 됩니다.<br>
  (부)등호로는 =, <, >만 쓸 수 있습니다.<br>
<br>
<br>
  > execute if score num1 > num2 run setblock num3 redstone_block<br>
  - num1이 num2보다 크다면, num3번째 줄로<br>
<br>

<br><br>
<br>
<br>
 7. 기타<br>
  summon tnt<br>
   쓰면 프로그램을 강제종료시킵니다.<br><br>
  
  summon creeper<br>
   쓰면 while(true) continue;를 작동시킵니다.<br><br>

  h<br>
   쓰면 Hello, world!를 출력합니다.<br><br>

  주석은 #으로 쓸 수 있습니다.<br><br><br>




say 숫자를 입력하세요.<br>
(say의 경우는 변수 출력 못 함. num이라는 변수가 있어도, say num은 그냥 num을 출력함.)<br>
정수인 변수 앞에 #을 붙이면 그 숫자를 유니코드로 변환해 줍니다.<br>
scoreboard objectives add num int<br>
scoreboard players set num 61<br>
tellraw #num<br>
출력 결과: a<br>
<br><br><br>
