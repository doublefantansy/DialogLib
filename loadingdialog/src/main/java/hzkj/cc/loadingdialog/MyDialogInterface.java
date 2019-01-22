package hzkj.cc.loadingdialog;

interface MyDialogInterface {
    /**
     * 设置弹框显示文字
     *
     * @param message
     * @return 链式结构返回
     */
    MyDialog setMessage(String message);

    /**
     * Dialog消失
     */
    void dismiss();

    /**
     *
     */
    void showInCenter();

    void setDialogAlpha(float alpha);
}
