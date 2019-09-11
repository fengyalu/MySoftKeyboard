package cn.com.fyl.learn.mysoftkeyboard.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.fyl.learn.mysoftkeyboard.R;
import cn.com.fyl.learn.mysoftkeyboard.interfaces.IKeyNumberBackListener;

/**
 * Created by fengyalu on 2019/4/3 0003.
 */

public class PopMySoftKeyBoard implements View.OnClickListener {
    private Context context;
    private PopupWindow popupWindow;
    private boolean cancelable;
    private TextView keyOne;
    private TextView keyTwo;
    private TextView keyThree;
    private TextView keyFour;
    private TextView keyFive;
    private TextView keySix;
    private TextView keySeven;
    private TextView keyEight;
    private TextView keyNine;
    private TextView keyTen;

    private TextView keyDelete;
    private TextView keyClear;
    private List<Integer> integerList;
    private List<String> pressedList;
    private IKeyNumberBackListener iKeyNumberBackListener;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                keyOne.setText(String.valueOf(integerList.get(0)));
                keyTwo.setText(String.valueOf(integerList.get(1)));
                keyThree.setText(String.valueOf(integerList.get(2)));
                keyFour.setText(String.valueOf(integerList.get(3)));
                keyFive.setText(String.valueOf(integerList.get(4)));
                keySix.setText(String.valueOf(integerList.get(5)));
                keySeven.setText(String.valueOf(integerList.get(6)));
                keyEight.setText(String.valueOf(integerList.get(7)));
                keyNine.setText(String.valueOf(integerList.get(8)));
                keyTen.setText(String.valueOf(integerList.get(9)));

                keyClear.setText("清空");
                keyDelete.setText("删除");
            }
        }
    };

    /**
     * 位置
     */
    public enum Gravitys {
        TOP, LEFT, CENTER, RIGHT, BOTTOM
    }

    public PopMySoftKeyBoard(Context context) {
        this.context = context;
        integerList = new ArrayList<>();
        pressedList = new ArrayList<>();
    }

    private void createDialog(Gravitys gravity) {
        View view = View.inflate(context, R.layout.my_soft_keyboard, null);
        initView(view);
        setView();
        popupWindow = new PopupWindow(view, -1, -2);
        //先关闭
        dismiss();
        //设置一个背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //点击外边可关闭pw
        popupWindow.setOutsideTouchable(cancelable);
        //pw内可获取焦点
        popupWindow.setFocusable(true);
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = 0.7f;
        ((Activity) this.context).getWindow().setAttributes(lp);

        //退出时恢复透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
                lp.alpha = 1f;
                ((Activity) context).getWindow().setAttributes(lp);
            }
        });
        //设置popwindow显示位置及动画
        setWindow(view, gravity);
    }

    private void initView(View view) {
        keyOne = (TextView) view.findViewById(R.id.key_one);
        keyTwo = (TextView) view.findViewById(R.id.key_two);
        keyThree = (TextView) view.findViewById(R.id.key_three);
        keyFour = (TextView) view.findViewById(R.id.key_four);
        keyFive = (TextView) view.findViewById(R.id.key_five);
        keySix = (TextView) view.findViewById(R.id.key_six);
        keySeven = (TextView) view.findViewById(R.id.key_seven);
        keyEight = (TextView) view.findViewById(R.id.key_eight);
        keyNine = (TextView) view.findViewById(R.id.key_nine);
        keyTen = (TextView) view.findViewById(R.id.key_ten);

        keyDelete = (TextView) view.findViewById(R.id.key_delete);
        keyClear = (TextView) view.findViewById(R.id.key_clear);

        keyOne.setOnClickListener(this);
        keyTwo.setOnClickListener(this);
        keyThree.setOnClickListener(this);
        keyFour.setOnClickListener(this);
        keyFive.setOnClickListener(this);
        keySix.setOnClickListener(this);
        keySeven.setOnClickListener(this);
        keyEight.setOnClickListener(this);
        keyNine.setOnClickListener(this);
        keyTen.setOnClickListener(this);
        keyDelete.setOnClickListener(this);
        keyClear.setOnClickListener(this);

        //pressedList.clear();
    }


    private void setView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    int x = (int) (Math.random() * 10);
                    if (!integerList.contains(x)) {
                        integerList.add(x);
                    }
                } while (integerList.size() != 10);

                if (integerList.size() == 10) {

                    Message message = handler.obtainMessage();
                    message.what = 1;
                    handler.sendMessage(message);
                }

            }
        }).start();

    }

    private void setWindow(View view, Gravitys gravity) {
        switch (gravity) {
            case TOP:
                //显示。y轴距离底部100是因为部分手机有虚拟按键，所以实际项目中要动态设置
                popupWindow.showAtLocation(view, Gravity.TOP, 0, 0);
                break;
            case LEFT:
                popupWindow.showAtLocation(view, Gravity.LEFT, 0, 0);
                break;
            case CENTER:
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
            case RIGHT:
                popupWindow.showAtLocation(view, Gravity.RIGHT, 0, 0);
                break;
            case BOTTOM:
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.key_one:
                String oneStr = keyOne.getText().toString().trim();
                if (!TextUtils.isEmpty(oneStr)&&pressedList.size()!=6) {
                    pressedList.add(oneStr);
                }
                break;
            case R.id.key_two:
                String twoStr = keyTwo.getText().toString().trim();
                if (!TextUtils.isEmpty(twoStr)&&pressedList.size()!=6) {
                    pressedList.add(twoStr);
                }
                break;
            case R.id.key_three:
                String threeStr = keyThree.getText().toString().trim();
                if (!TextUtils.isEmpty(threeStr)&&pressedList.size()!=6) {
                    pressedList.add(threeStr);
                }
                break;
            case R.id.key_four:
                String fourStr = keyFour.getText().toString().trim();
                if (!TextUtils.isEmpty(fourStr)&&pressedList.size()!=6) {
                    pressedList.add(fourStr);
                }
                break;
            case R.id.key_five:
                String fiveStr = keyFive.getText().toString().trim();
                if (!TextUtils.isEmpty(fiveStr)&&pressedList.size()!=6) {
                    pressedList.add(fiveStr);
                }
                break;
            case R.id.key_six:
                String sixStr = keySix.getText().toString().trim();
                if (!TextUtils.isEmpty(sixStr)&&pressedList.size()!=6) {
                    pressedList.add(sixStr);
                }
                break;
            case R.id.key_seven:
                String sevenStr = keySeven.getText().toString().trim();
                if (!TextUtils.isEmpty(sevenStr)&&pressedList.size()!=6) {
                    pressedList.add(sevenStr);
                }
                break;
            case R.id.key_eight:
                String eightStr = keyEight.getText().toString().trim();
                if (!TextUtils.isEmpty(eightStr)&&pressedList.size()!=6) {
                    pressedList.add(eightStr);
                }
                break;
            case R.id.key_nine:
                String nineStr = keyNine.getText().toString().trim();
                if (!TextUtils.isEmpty(nineStr)&&pressedList.size()!=6) {
                    pressedList.add(nineStr);
                }
                break;
            case R.id.key_ten:
                String tenStr = keyTen.getText().toString().trim();
                if (!TextUtils.isEmpty(tenStr)&&pressedList.size()!=6) {
                    pressedList.add(tenStr);
                }
                break;
            case R.id.key_clear:
                if (null!=pressedList&&!pressedList.isEmpty()){
                    pressedList.clear();
                }
                break;
            case R.id.key_delete:
                if (null!=pressedList&&!pressedList.isEmpty()){
                    pressedList.remove(pressedList.size()-1);
                    if (null!=iKeyNumberBackListener){
                        iKeyNumberBackListener.onListener(pressedList);
                    }
                }
                break;

            default:
                break;
        }

        if (null!=iKeyNumberBackListener){
            iKeyNumberBackListener.onListener(pressedList);
        }
    }

    private int randomNumber() {
        int x = (int) (Math.random() * 10);
        return x;
    }

    public void setiKeyNumberBackListener(IKeyNumberBackListener iKeyNumberBackListener) {
        this.iKeyNumberBackListener = iKeyNumberBackListener;
    }

    public Context getContext() {
        return context;
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public void setPopupWindow(PopupWindow popupWindow) {
        this.popupWindow = popupWindow;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    /**
     * 设置是否点击外部使其消失
     *
     * @param cancelable
     * @return
     */
    public PopMySoftKeyBoard setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }


    /**
     * 显示弹出框
     */
    public PopMySoftKeyBoard show(Gravitys gravity) {
        createDialog(gravity);
        return this;
    }

    /**
     * 取消弹出框
     */
    public void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
