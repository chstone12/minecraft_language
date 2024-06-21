package as.df;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {




    static String[] string_value = new String[2];
    static String[] string_name = new String[2];
    static int[] int_value = new int[2];
    static String[] int_name = new String[2];

    static int line_count = 0;
    static int string_count = 0;
    static int int_count = 0;

    public static void main(String[] args) throws Exception {

        String code = Files.readString(Paths.get(args[0])); //   C:\Users\사용자\Desktop\mc언어\code.txt
        String[] codes = code.split("\\R");

        for(int q = 0; q < codes.length; q++) {
            String code_line = codes[q];
            if(code.contains("쉙") || code.contains("쉚")) {
                System.out.println("쉙 또는 쉚이 코드 어느 부분에라도 포함되면 안 됩니다.");
                break;
            }

            line_count++;
            String[] code_index = code_line.split(" ");
            if(code_index[0].equals("scoreboard")) {
                if(code_index[1].equals("objectives")) {
                    if(code_index[2].equals("add") && code_index[4].equals("string")) {
                        string_name = Arrays.copyOf(string_name, string_count + 2);
                        string_name[string_count] = "쉚" + code_index[3] + "쉚";
                        string_count++;
                        if(hasDuplicateStrings(string_name)) {
                            System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[3] + "은(는) 중복된 변수 이름입니다.");
                            break;
                        }
                    } else if(code_index[2].equals("add") && code_index[4].equals("int")) {
                        int_name = Arrays.copyOf(int_name, int_count + 2);
                        int_name[int_count] = "쉙" + code_index[3] + "쉙";
                        int_count++;
                        if (hasDuplicateStrings(int_name)) {
                            System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[3] + "은(는) 중복된 변수 이름입니다.");
                            break;
                        }
                    } else if(code_index[2].equals("add") && code_index[4].equals("input")) {
                        try {

                            Scanner sc = new Scanner(System.in);
                            int input = Integer.parseInt(sc.next());

                            int_name = Arrays.copyOf(int_name, int_count + 2);
                            int_name[int_count] = "쉙" + code_index[3] + "쉙";
                            int_count++;
                            if (hasDuplicateStrings(int_name)) {
                                System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[3] + "은(는) 중복된 변수 이름입니다.");
                                break;
                            }

                            set_int(code_index[3], code_line, code_index, 3, input);

                        } catch (NumberFormatException e) {
                            System.out.println("정수만 입력 받을 수 있습니다.");
                            break;
                        }
                    } else {
                        if(!code_index[2].equals("add")) System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[2] + " <-- [여기]");
                        else System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[4] + " <-- [여기]");
                        break;
                    }
                } else if(code_index[1].equals("players")) {

                    if(code_index[2].equals("set")) {

                        String var_name = code_index[3];

                        // 쉙 = int, 쉚 = string

                        if(Arrays.toString(string_name).contains("쉚" + var_name + "쉚")) {
                            for(int i = 0; i < string_name.length; i++) {
                                if((string_name[i]).equals("쉚" + var_name + "쉚")) {
                                    string_value = Arrays.copyOf(string_value, string_name.length + 2);
                                    StringBuilder sb = new StringBuilder();
                                    for(int j = 4; j < code_index.length; j++) sb.append(code_index[j]).append(" ");
                                    string_value[i] = sb.toString();
                                    if(string_value[i].endsWith(" ")) string_value[i] = string_value[i].substring(0, string_value[i].length() - 1);
                                    break;
                                }
                            }
                        }
                        else if(Arrays.toString(int_name).contains("쉙" + var_name + "쉙")) {
                            for(int i = 0; i < int_name.length; i++) {
                                if((int_name[i]).equals("쉙" + var_name + "쉙")) {
                                    try {
                                        int_value = Arrays.copyOf(int_value, int_name.length + 2);
                                        int_value[i] = Integer.parseInt(code_index[4]);
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[4] + "은(는) 정수가 아닙니다.");
                                    }
                                }
                            }
                        } else {
                            System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[3] + "은(는) 존재하지 않는 변수입니다.");
                            break;
                        }

                    } else if(code_index[2].equals("add")) {
                        String var_name = code_index[3];
                        if(Arrays.toString(int_name).contains("쉙" + var_name + "쉙")) {
                            for(int i = 0; i < int_name.length; i++) {
                                if((int_name[i]).equals("쉙" + var_name + "쉙")) {
                                    try {

                                        int num = Integer.parseInt(code_index[4]);
                                        if(num < -1) {
                                            System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[4] + "이(가) 음수면 안 됩니다.");
                                            break;
                                        }

                                        int_value = Arrays.copyOf(int_value, int_name.length + 2);
                                        int temp = int_value[i];
                                        int_value[i] = num + temp;
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[4] + "은(는) 정수가 아닙니다. (여기에다가 변수 이름 써 놓은 건 아니죠? 정수만 쓸 수 있어요.)");
                                    }
                                }
                            }
                        } else if(Arrays.toString(string_name).contains("쉚" + var_name + "쉚")) {
                            System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[3] + "은(는) 문자열이라서 add를 쓸 수 없습니다.");
                            break;
                        } else {
                            System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[3] + "은(는) 존재하지 않는 변수입니다.");
                            break;
                        }
                    } else if(code_index[2].equals("remove")) {
                        String var_name = code_index[3];
                        if(Arrays.toString(int_name).contains("쉙" + var_name + "쉙")) {
                            for(int i = 0; i < int_name.length; i++) {
                                if((int_name[i]).equals("쉙" + var_name + "쉙")) {
                                    try {
                                        int num = Integer.parseInt(code_index[4]);
                                        if(num < -1) {
                                            System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[4] + "이(가) 음수면 안 됩니다.");
                                            break;
                                        }
                                        int_value = Arrays.copyOf(int_value, int_name.length + 2);
                                        int temp = int_value[i];
                                        int_value[i] = temp - num;
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[4] + "은(는) 정수가 아닙니다. (여기에다가 변수 이름 써 놓은 건 아니죠? 정수만 쓸 수 있어요.)");
                                    }
                                }
                            }
                        } else if(Arrays.toString(string_name).contains("쉚" + var_name + "쉚")) {
                            System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[3] + "은(는) 문자열이라서 add를 쓸 수 없습니다.");
                            break;
                        } else {
                            System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[3] + "은(는) 존재하지 않는 변수입니다.");
                            break;
                        }
                    }


                    else if(code_index[2].equals("operation")) {
                        String var_name = code_index[3];
                        String var_name_2 = code_index[5];

                        if(!Arrays.toString(int_name).contains(var_name)) {
                            System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[3] + "은(는) 존재하지 않는 변수입니다.");
                            break;
                        }

                        if(!Arrays.toString(int_name).contains(var_name_2)) {
                            System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[5] + "은(는) 존재하지 않는 변수입니다.");
                            break;
                        }


                        int t1 = get_int(var_name, code_line, code_index, 3);
                        int t2 = get_int(var_name_2, code_line, code_index, 5);


                        if(code_index[4].equals("*=")) set_int(var_name, code_line, code_index, 4, (t1 * t2));
                        else if(code_index[4].equals("+=")) set_int(var_name, code_line, code_index, 4, (t1 + t2));
                        else if(code_index[4].equals("-=")) set_int(var_name, code_line, code_index, 4, (t1 - t2));
                        else if(code_index[4].equals("/=")) set_int(var_name, code_line, code_index, 4, Math.round(t1 / t2));
                        else if(code_index[4].equals("%=")) set_int(var_name, code_line, code_index, 4, (t1 % t2));
                        else if(code_index[4].equals("^=")) set_int(var_name, code_line, code_index, 4, (int) Math.pow(t1, t2));
                        else if(code_index[4].equals("=")) set_int(var_name, code_line, code_index, 4, t2);
                        else {
                            System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[4] + "의 기호가 잘못됐습니다.");
                            break;
                        }

                    } else {
                        System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[2] + " <-- [여기]");
                        break;
                    }

                } else {
                    System.out.println("구문 오류! " + line_count + "번째 줄의 " + code_line + "에서, " + code_index[1] + " <-- [여기]");
                    break;
                }
            }

            else if(code_index[0].equals("execute")) {
                if(code_index[1].equals("if")) {
                    if(code_index[2].equals("score")) {
                        String var_name = code_index[3];
                        String var_name_2 = code_index[5];

                        if(!Arrays.toString(int_name).contains(var_name)) {
                            System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[3] + "은(는) 존재하지 않는 변수입니다.");
                            break;
                        }

                        if(!Arrays.toString(int_name).contains(var_name_2)) {
                            System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[5] + "은(는) 존재하지 않는 변수입니다.");
                            break;
                        }

                        int t1 = get_int(var_name, code_line, code_index, 3);
                        int t2 = get_int(var_name_2, code_line, code_index, 5);


                        if(code_index[4].equals(">")) {
                            if(t1 > t2) {
                                if(code_index[6].equals("run")) {
                                    if(code_index[7].equals("setblock")) {
                                        if(code_index[9].equals("redstone_block")) {
                                            try {
                                                q = Integer.parseInt(code_index[8]);
                                                q -= 2;
                                            } catch (NumberFormatException e) {
                                                System.out.println(line_count + "번째 줄의 " + code_index[8] + "은(는) 잘못된 정수입니다.");
                                                break;
                                            }

                                        } else {
                                            System.out.println(line_count + "번째 줄의" + code_index[9] + "은(는) 존재하지 않는 블럭입니다.");
                                            break;
                                        }
                                    } else {
                                        System.out.println(line_count + "번째 줄의 " + code_index[7] + "은(는) 알 수 없는 명령어이거나, execute에 이어서 쓸 수 없는 명령어입니다.");
                                        break;
                                    }
                                } else {
                                    System.out.println(line_count + "번째 줄의 " + code_index[6] + "은(는) 잘못된 구문입니다.");
                                    break;
                                }
                            }
                        } else if(code_index[4].equals("<")) {
                            if(t1 < t2) {
                                if(code_index[6].equals("run")) {
                                    if(code_index[7].equals("setblock")) {
                                        if(code_index[9].equals("redstone_block")) {
                                            try {
                                                q = Integer.parseInt(code_index[8]);
                                                q -= 2;
                                            } catch (NumberFormatException e) {
                                                System.out.println(line_count + "번째 줄의 " + code_index[8] + "은(는) 잘못된 정수입니다.");
                                                break;
                                            }

                                        } else {
                                            System.out.println(line_count + "번째 줄의" + code_index[9] + "은(는) 존재하지 않는 블럭입니다.");
                                            break;
                                        }
                                    } else {
                                        System.out.println(line_count + "번째 줄의 " + code_index[7] + "은(는) 알 수 없는 명령어이거나, execute에 이어서 쓸 수 없는 명령어입니다.");
                                        break;
                                    }
                                } else {
                                    System.out.println(line_count + "번째 줄의 " + code_index[6] + "은(는) 잘못된 구문입니다.");
                                    break;
                                }
                            }
                        } else if(code_index[4].equals("==")) {
                            if(t1 == t2) {
                                if(code_index[6].equals("run")) {
                                    if(code_index[7].equals("setblock")) {
                                        if(code_index[9].equals("redstone_block")) {
                                            try {
                                                q = Integer.parseInt(code_index[8]);
                                                q -= 2;
                                            } catch (NumberFormatException e) {
                                                System.out.println(line_count + "번째 줄의 " + code_index[8] + "은(는) 잘못된 정수입니다.");
                                                break;
                                            }

                                        } else {
                                            System.out.println(line_count + "번째 줄의" + code_index[9] + "은(는) 존재하지 않는 블럭입니다.");
                                            break;
                                        }
                                    } else {
                                        System.out.println(line_count + "번째 줄의 " + code_index[7] + "은(는) 알 수 없는 명령어이거나, execute에 이어서 쓸 수 없는 명령어입니다.");
                                        break;
                                    }
                                } else {
                                    System.out.println(line_count + "번째 줄의 " + code_index[6] + "은(는) 잘못된 구문입니다.");
                                    break;
                                }
                            }
                        } else {
                            System.out.println(line_count + "번째 줄의 " + code_index[4] + "은(는) 잘못된 (부)등호입니다.");
                            break;
                        }

                    } else {
                        System.out.println(line_count + "번째 줄의 " + code_index[2] + "은(는) 잘못된 구문입니다.");
                        break;
                    }
                } else {
                    System.out.println(line_count + "번째 줄의 " + code_index[1] + "은(는) 잘못된 구문입니다.");
                    break;
                }
            } else if(code_index[0].equals("setblock")) {
                if(code_index[2].equals("redstone_block")) {
                    try {
                        q = Integer.parseInt(code_index[1]);
                        q -= 2;
                    } catch (NumberFormatException e) {
                        System.out.println(line_count + "번째 줄의 " + code_index[1] + "은(는) 잘못된 정수입니다.");
                        break;
                    }
                } else {
                    System.out.println(line_count + "번째 줄의 " + code_index[2] + "은(는) 존재하지 않는 블럭입니다.");
                    break;
                }
            }


            else if(code_index[0].equals("summon")) {
                if(code_index[1].equals("tnt")) break;
                else System.out.println(line_count + "번째 줄의 " + code_index[1] + "은(는) 존재하지 않는 엔티티입니다.");
            }

            else if(code_index[0].equals("say")) {
                System.out.println(code_line.substring(4));
            }

            else if(code_index[0].startsWith("tellraw")) {
                String output = code_line.substring(8).replace("  ", " ");
                String[] ar = output.split("\\+");
                for(int w = 0; w < ar.length; w++) {

                    if(ar[w].startsWith("\"") || ar[w].startsWith(" \"")) {
                        System.out.print(ar[w].replace("\"", ""));
                    }

                    else if(ar[w].startsWith("#") || ar[w].startsWith(" #")) {
                        try {
                            String var_name = ar[w].replace(" ", "").replace("#", "");
                            int unicode = get_int(var_name, code_line, code_index, w);
                            int hex = Integer.parseInt(String.valueOf(unicode), 16);
                            char cha = (char) hex;
                            System.out.print(cha);
                        } catch (Exception e) {
                            System.out.println(++q + "번째 줄의 " + ar[w] + "에서, #은 정수인 변수에만 붙일 수 있습니다.");
                            break;
                        }
                    }

                    else {
                        ar[w] = ar[w].replace(" ", "");
                        String var_name = ar[w];
                        if(Arrays.toString(string_name).contains("쉚" + var_name + "쉚")) {
                            String out = get_string(var_name, code_line, code_index, w);
                            System.out.print(out);
                        } else if(Arrays.toString(int_name).contains("쉙" + var_name + "쉙")) {
                            int out = get_int(var_name, code_line, code_index, w);
                            System.out.print(out);
                        }
                    }

                }

                System.out.println();

            }

            else {
                if(!code_index[0].startsWith("#") && !code_index[0].equals("")) {
                    System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[0] + "은(는) 알 수 없는 구문입니다.");
                    break;
                }
            }



        }


    }


    public static boolean hasDuplicateStrings(String[] array) {
        Set<String> set = new HashSet<>();
        for (String s : array) {
            if (!set.add(s)) {
                // If add() returns false, it means the element is already in the set
                return true;
            }
        }
        return false;
    }


    public static int get_int(String var_name, String code_line, String[] code_index, int index) {
        int ret = 0;
        for(int i = 0; i < int_name.length; i++) {
            if((int_name[i]).equals("쉙" + var_name + "쉙")) {
                try {
                    int_value = Arrays.copyOf(int_value, int_name.length + 2);
                    ret = int_value[i];
                    break;
                } catch (NumberFormatException e) {
                    System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[index] + "은(는) 정수가 아니거나 존재하지 않는 변수입니다.");
                }
            }
        }
        return ret;
    }

    public static String get_string(String var_name, String code_line, String[] code_index, int index) {
        String ret = "";
        for(int i = 0; i < string_name.length; i++) {
            if((string_name[i]).equals("쉚" + var_name + "쉚")) {
                try {
                    string_value = Arrays.copyOf(string_value, string_name.length + 2);
                    ret = string_value[i];
                    break;
                } catch (NumberFormatException e) {
                    System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[index] + "은(는) 정수가 아니거나 존재하지 않는 변수입니다.");
                }
            }
        }
        return ret;
    }

    public static void set_int(String var_name, String code_line, String[] code_index, int index, int new_value) {
        for(int i = 0; i < int_name.length; i++) {
            if((int_name[i]).equals("쉙" + var_name + "쉙")) {
                try {
                    int_value = Arrays.copyOf(int_value, int_name.length + 2);
                    int_value[i] = new_value;
                    break;
                } catch (NumberFormatException e) {
                    System.out.println(line_count + "번째 줄의 " + code_line + "에서, " + code_index[index] + "은(는) 정수가 아니거나 존재하지 않는 변수입니다.");
                }
            }
        }
    }



}
