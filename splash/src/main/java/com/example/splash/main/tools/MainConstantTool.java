package com.example.splash.main.tools;

import androidx.annotation.IntDef;

import static com.example.splash.main.tools.MainConstantTool.SHENZHEN;
import static com.example.splash.main.tools.MainConstantTool.BEIJING;
import static com.example.splash.main.tools.MainConstantTool.HANGZHOU;
import static com.example.splash.main.tools.MainConstantTool.SHANGHAI;



@IntDef({SHANGHAI,HANGZHOU,BEIJING,SHENZHEN})
public @interface MainConstantTool {
    int SHANGHAI = 0;
    int HANGZHOU = 1;
    int BEIJING = 2;
    int SHENZHEN = 3;

}
