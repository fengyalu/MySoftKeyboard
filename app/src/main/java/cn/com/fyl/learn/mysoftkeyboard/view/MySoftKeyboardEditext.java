package cn.com.fyl.learn.mysoftkeyboard.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import cn.com.fyl.learn.mysoftkeyboard.R;
import cn.com.fyl.learn.mysoftkeyboard.interfaces.IKeyNumberBackListener;
import cn.com.fyl.learn.mysoftkeyboard.popwindow.PopMySoftKeyBoard;

/**
 * Created by fengyalu on 2019/9/10 0010.
 */

public class MySoftKeyboardEditext extends RelativeLayout implements IKeyNumberBackListener {


    private Context mContext;
    private TextView numberOne;
    private TextView numberTwo;
    private TextView numberThree;
    private TextView numberFour;
    private TextView numberFive;
    private TextView numberSix;
    private PopMySoftKeyBoard popSoftKeyBoard;
    private String password;

    public MySoftKeyboardEditext(Context context) {
        super(context);
        init(context);
    }

    public MySoftKeyboardEditext(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public MySoftKeyboardEditext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.editext_my_soft_keyboard, this, true);
        mContext = context;
        LinearLayout llNumberEdit = (LinearLayout) view.findViewById(R.id.ll_number_edit);
        numberOne = (TextView) llNumberEdit.findViewById(R.id.number_one);
        numberTwo = (TextView) llNumberEdit.findViewById(R.id.number_two);
        numberThree = (TextView) llNumberEdit.findViewById(R.id.number_three);
        numberFour = (TextView) llNumberEdit.findViewById(R.id.number_four);
        numberFive = (TextView) llNumberEdit.findViewById(R.id.number_five);
        numberSix = (TextView) llNumberEdit.findViewById(R.id.number_six);
        popSoftKeyBoard = new PopMySoftKeyBoard(context);
        popSoftKeyBoard.setiKeyNumberBackListener(this);
        setView(llNumberEdit);
    }



    private void setView(LinearLayout llNumberEdit) {
        llNumberEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popSoftKeyBoard.show(PopMySoftKeyBoard.Gravitys.BOTTOM);
            }
        });
    }


    @Override
    public void onListener(List<String> pressedList) {
        clear();
        if (null != pressedList && !pressedList.isEmpty()) {
            switch (pressedList.size()) {
                case 1:
                    numberOne.setText(pressedList.get(0));
                    break;
                case 2:
                    numberOne.setText(pressedList.get(0));
                    numberTwo.setText(pressedList.get(1));
                    break;
                case 3:
                    numberOne.setText(pressedList.get(0));
                    numberTwo.setText(pressedList.get(1));
                    numberThree.setText(pressedList.get(2));
                    break;
                case 4:
                    numberOne.setText(pressedList.get(0));
                    numberTwo.setText(pressedList.get(1));
                    numberThree.setText(pressedList.get(2));
                    numberFour.setText(pressedList.get(3));
                    break;
                case 5:
                    numberOne.setText(pressedList.get(0));
                    numberTwo.setText(pressedList.get(1));
                    numberThree.setText(pressedList.get(2));
                    numberFour.setText(pressedList.get(3));
                    numberFive.setText(pressedList.get(4));
                    break;
                case 6:
                    numberOne.setText(pressedList.get(0));
                    numberTwo.setText(pressedList.get(1));
                    numberThree.setText(pressedList.get(2));
                    numberFour.setText(pressedList.get(3));
                    numberFive.setText(pressedList.get(4));
                    numberSix.setText(pressedList.get(5));
                    StringBuilder builder=new StringBuilder();
                     password = builder.append(pressedList.get(0)).append(pressedList.get(1)).append(pressedList.get(2)).
                            append(pressedList.get(3)).append(pressedList.get(4)).append(pressedList.get(5)).toString();
                    break;

            }

        } else {
            clear();
        }
    }

    public String getPassword() {
        if (password.length()==6){
            return password;
        }
        return null;
    }

    private void clear() {
        numberOne.setText("");
        numberTwo.setText("");
        numberThree.setText("");
        numberFour.setText("");
        numberFive.setText("");
        numberSix.setText("");
    }
}
