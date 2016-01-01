package testanim.user.flipkartemulate;

/**
 * Created by USER on 01-Jan-16.
 */
public enum CustomPagerEnum {
    RED(R.string.red, R.layout.view_red),
    BLUE(R.string.blue, R.layout.view_blue),
    ORANGE(R.string.orange, R.layout.view_orange),
    CUSTOM(R.string.showme,R.layout.slideshow_fragment);


    private int mTitleResId;
    private int mLayoutResId;

    CustomPagerEnum(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
