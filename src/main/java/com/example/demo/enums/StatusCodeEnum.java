package com.example.demo.enums;

import lombok.Getter;

import java.sql.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public enum StatusCodeEnum {
    SystemError("9999", "系統錯誤"),
    LoginError("9900", "登入失敗"),
    LoginNoUserError("9901", "此帳號不存在請先註冊"),
    RegisterError("9902", "帳號已註冊"),
    TokenError("9903", "登入逾時請重新登入"),
    BookError("9904", "取得書本清單失敗"),
    BorrowingRecordError("9905", "取得使用者借閱紀錄失敗"),
    BorrowBookError("9906", "借閱失敗");

    private final String key; // key
    private final String message; // 提示訊息
    private static Map<String, StatusCodeEnum> enum_map;

    StatusCodeEnum(String key, String message) {
        this.key = key;
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    static {
        Map<String, StatusCodeEnum> map = new ConcurrentHashMap<>();
        for (StatusCodeEnum instance : StatusCodeEnum.values()) {
            map.put(instance.getKey(), instance); // 將key和message儲存到map中
        }
        enum_map = Collections.unmodifiableMap(map); // 只可讀不可對其內容做編輯
    }

//    public static StatusCodeEnum findByCode(String code) {
//        for (StatusCodeEnum e : StatusCodeEnum.values()) {
//            if (e.name().equals(code)) {
//                return e;
//            }
//        }
//        return SystemError;
//    }

    public static StatusCodeEnum get(String code) {
        if (enum_map.get(code) == null) { // 無此error code
            return enum_map.get("9999");
        }
        return enum_map.get(code);
    }

    public static void main(String[] args) {
        // 氣泡排序法
//        Integer data[] = {5, 17, 8, 12, 0, 4, 2, 21, 18};
//        int length = data.length; // 一共有多少個值
//        for (int i = 0; i < length; i++) { // 從小排到大
//            boolean flag = false; // 是否以排序完畢
//            for (int j = 0; j < length - 1 - i; j++) {
//                if (data[j].compareTo(data[j + 1]) > 0) { // 如果後一個數值大於前一個
//                    // 兩個位置交換
//                    int tmp = data[j+1];
//                    data[j+1] = data[j];
//                    data[j] = tmp;
//                    flag = true;
//                }
//            }
//            System.out.println(Arrays.toString(data));
//            if(!flag){
//                break;
//            }
//        }

        // 字串反轉
//        String str = "abcdefghijk";
//        String result = "";
//        for (int i = str.length() - 1; i >= 0; i--) { // str.length() - 1 最後一個字
//            result += str.charAt(i);
//        }
//        System.out.println(result);
//        System.out.println(new StringBuilder(str).reverse().toString());

        // 質數判斷
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 2; i <= 100; i++) { // 1既不是質數也不是和數，所以從2開始
            boolean k = true; // 判斷條件
            for (int n = 2; n < i; n++) { // 將比被除數小的所有值作為被除數
                if (i % n == 0) { // 有除了自己和1以外的因數
                    k = false;  // 非質數傳false
                    break;
                }
            }
            if (k) {
                System.out.println(stringBuilder.append(i).append(","));
            }
        }
        String[] str = stringBuilder.substring(0, stringBuilder.length()-1).split(",");
        System.out.println(stringBuilder.substring(0, stringBuilder.length()-1).split(","));
//        System.out.println(str.length);
    }

}
