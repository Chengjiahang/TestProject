/**
 * @file XListView.java
 * @package me.maxwin.view
 * @create Mar 18, 2012 6:28:41 PM
 * @author Maxwin
 * @description An ListView support (a) Pull down to refresh, (b) Pull up to load more.
 * 		Implement IXListViewListener, and see stopRefresh() / stopLoadMore().
 */

package com.yuxindemo.testproject.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.yuxindemo.testproject.R;


public class XListView extends ListView implements OnScrollListener {

    private static final String TAG = "XListView";

    private float mLastY = -1; // save event y
    private Scroller mScroller; // used for scroll back

    private OnScrollListener mScrollListener; // user's scroll listener

    // the interface to trigger refresh and load more.
    private IXListViewListener mListViewListener;

    // -- header view
    private XListViewHeader mHeaderView;

    private RelativeLayout mHeaderViewContent;

//    private TextView mHeaderTimeView;

    private int mHeaderViewHeight; // header view's height

    private boolean mEnablePullRefresh = true;

    private boolean mPullRefreshing = false; // is refreashing.

    // -- footer view
    private XListViewFooter mFooterView;

    private boolean mEnablePullLoad;

    private boolean mPullLoading;

    private boolean mIsFooterReady = false;

    // 是否允许父控件及祖宗控件不要拦截当前控件的事件
    private boolean isIntercept = false;

    // total list items, used to detect is at the bottom of listview.
    private int mTotalItemCount;

    // for mScroller, scroll back from header or footer.
    private int mScrollBack;

    private final static int SCROLLBACK_HEADER = 0;//header回弹

    private final static int SCROLLBACK_FOOTER = 1;//footer回弹

    private final static int SCROLL_DURATION = 400; // scroll back duration

    private final static int PULL_LOAD_MORE_DELTA = 50; // when pull up >= 50px

    private final static float OFFSET_RADIO = 1.8f; // support iOS like pull

    /**
     * @param context
     */
    public XListView(Context context) {
        super(context);
        initWithContext(context);
    }

    public XListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWithContext(context);
    }

    public XListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initWithContext(context);
    }

    private void initWithContext(Context context) {
        mScroller = new Scroller(context, new DecelerateInterpolator());
        super.setOnScrollListener(this);

        // init header view
        mHeaderView = new XListViewHeader(context);
        mHeaderViewContent = (RelativeLayout) mHeaderView.findViewById(R.id.xlistview_header_content);
//        mHeaderTimeView = (TextView) mHeaderView.findViewById(R.id.xlistview_header_time);
        //Header和Footer通过addHeaderView和addFooterView【详情见setAdapter方法】添加上去
        addHeaderView(mHeaderView);

        // init footer view
        mFooterView = new XListViewFooter(context);
        mHeaderView.getHeight();
        mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mHeaderViewHeight = mHeaderViewContent.getHeight();
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    // 请求父控件及祖宗控件不要拦截当前控件的事件   用于解除 ScrollView嵌套ListView 事件拦截  2018.6.6
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // 请求父控件及祖宗控件不要拦截当前控件的事件
        if (isIntercept){
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(ev);
    }

    // 如果 isIntercept为true 拦截父祖宗控件的事件
    public void setDispatchTouchEvent(boolean isIntercept){
        this.isIntercept = isIntercept;
    }

    @Override
    public void setAdapter(ListAdapter adapter) {

        // make sure XListViewFooter is the last footer view, and only add once.
        if (mIsFooterReady == false) {
            mIsFooterReady = true;
            addFooterView(mFooterView);
        }
        super.setAdapter(adapter);
    }
    public void setPullRefreshEnable(boolean enable) {
        mEnablePullRefresh = enable;
        if (!mEnablePullRefresh) { // disable, hide the content
            mHeaderViewContent.setVisibility(View.INVISIBLE);
        } else {
            mHeaderViewContent.setVisibility(View.VISIBLE);
        }
    }

    /**
     * enable or disable pull up load more feature.
     *
     * @param enable
     */
    public void setPullLoadEnable(boolean enable) {
        mEnablePullLoad = enable;
        if (!mEnablePullLoad) {
            mFooterView.hide();
            mFooterView.setOnClickListener(null);
            // make sure "pull up" don't show a line in bottom when listview
            // with one page
            setFooterDividersEnabled(false);
        } else {
            mPullLoading = false;
            mFooterView.show();
            mFooterView.setState(XListViewFooter.STATE_NORMAL);
            setFooterDividersEnabled(true);
            // both "pull up" and "click" will invoke load more.
            mFooterView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    startLoadMore();
                }
            });
        }
    }

    /**
     * stop refresh, reset header view.
     */
    public void stopRefresh() {
        if (mPullRefreshing == true) {
            mPullRefreshing = false;
            resetHeaderHeight();
        }
    }

    /**
     * stop load more, reset footer view.
     */
    public void stopLoadMore() {
        if (mPullLoading == true) {
            mPullLoading = false;
            mFooterView.setState(XListViewFooter.STATE_NORMAL);
        }
    }

    /**
     * set last refresh time
     *
     * @param time
     */
    public void setRefreshTime(String time) {
//        mHeaderTimeView.setText(time);
    }

    private void invokeOnScrolling() {
        if (mScrollListener instanceof OnXScrollListener) {
            OnXScrollListener l = (OnXScrollListener) mScrollListener;
            l.onXScrolling(this);
        }
    }

    private void updateHeaderHeight(float delta) {
        mHeaderView.setVisiableHeight((int) delta + mHeaderView.getVisiableHeight());
        if (mEnablePullRefresh && !mPullRefreshing) { // 未处于刷新状态，更新箭头
            if (mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
                mHeaderView.setState(XListViewHeader.STATE_READY);
            } else {
                mHeaderView.setState(XListViewHeader.STATE_NORMAL);
            }
        }
        setSelection(0); // scroll to top each time
    }

    /**
     * reset header view's height.
     */
    private void resetHeaderHeight() {
        int height = mHeaderView.getVisiableHeight();
        if (height == 0) // not visible.
            return;
        // refreshing and header isn't shown fully. do nothing.
        if (mPullRefreshing && height <= mHeaderViewHeight) {
            return;
        }
        int finalHeight = 0; // default: scroll back to dismiss header.
        // is refreshing, just scroll back to show all the header.
        if (mPullRefreshing && height > mHeaderViewHeight) {
            finalHeight = mHeaderViewHeight;
        }
        mScrollBack = SCROLLBACK_HEADER;
        //滚动操作实现，computeScroll会被调用
        //startScroll这个方法可以这么理解 从height的间距变成height + finalHeight - height
        //官方api的参数有时挺难理解
        mScroller.startScroll(0, height, 0, finalHeight - height, SCROLL_DURATION);
        // trigger computeScroll
        invalidate();
    }

    private void updateFooterHeight(float delta) {
        int height = mFooterView.getBottomMargin() + (int) delta;
        if (mEnablePullLoad && !mPullLoading) {
            if (height > PULL_LOAD_MORE_DELTA) { // height enough to invoke load
                                                 // more.
                mFooterView.setState(XListViewFooter.STATE_READY);
            } else {
                mFooterView.setState(XListViewFooter.STATE_NORMAL);
            }
        }
        mFooterView.setBottomMargin(height);

        // setSelection(mTotalItemCount - 1); // scroll to bottom
    }

    private void resetFooterHeight() {
        int bottomMargin = mFooterView.getBottomMargin();
//        Log.d(TAG, "resetFooterHeight() bottomMargin--->" + bottomMargin);
        if (bottomMargin > 0) {
            mScrollBack = SCROLLBACK_FOOTER;
           //滚动操作实现，computeScroll会被调用
            //startScroll这个方法可以这么理解 从height的间距变成bottomMargin -bottomMargin
            mScroller.startScroll(0, bottomMargin, 0, -bottomMargin, SCROLL_DURATION);
            invalidate();
        }
    }

    private void startLoadMore() {
        mPullLoading = true;
        mFooterView.setState(XListViewFooter.STATE_LOADING);
        if (mListViewListener != null) {
            mListViewListener.onLoadMore();
        }
    }

    //通过用户的手势和距离进行判断
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //如果adapter中有10个数据，其实listView当中是有12个item的
        //mTotalItemCount = getAdapter().getCount();
        if (mLastY == -1) {
            //getRowY()是获取元Y坐标，即与Window和View坐标没有关系的坐标，代表在屏幕上的绝对位置
            mLastY = ev.getRawY();
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                //move的过程会不断调用updateHeaderHeight()和updateFooterHeight()方法
                final float deltaY = ev.getRawY() - mLastY;
                //deltaY>0即为向下滑动，反之是向上滑动
                mLastY = ev.getRawY();
                //OFFSET_RADIO为一个移动比例，控制用户滑动的体验
                //如果手指移动180px，按照这个比例，控件移动100px
                if (getFirstVisiblePosition() == 0
                        && (mHeaderView.getVisiableHeight() > 0 || deltaY > 0)) {
                    // the first item is showing, header has shown or pull down.
                    updateHeaderHeight(deltaY / OFFSET_RADIO);
                    invokeOnScrolling();
                } else if (getLastVisiblePosition() == mTotalItemCount - 1
                        && (mFooterView.getBottomMargin() > 0 || deltaY < 0)) {
                    // last item, already pulled up or want to pull up.
                    updateFooterHeight(-deltaY / OFFSET_RADIO);
                }
                break;
            default:
                //这里其实多数是指MotionEvent.ACTION_MOVE
                //手指移开会调用resetHeaderHeight()以及 resetFooterHeight()
                mLastY = -1; // reset
                if (getFirstVisiblePosition() == 0) {
                    // invoke refresh
                    if (mEnablePullRefresh && mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
                        mPullRefreshing = true;
                        mHeaderView.setState(XListViewHeader.STATE_REFRESHING);
                        if (mListViewListener != null) {
                            mListViewListener.onRefresh();
                        }
                    }
                    resetHeaderHeight();
                }
                    if (getLastVisiblePosition() == mTotalItemCount - 1) {
                    // invoke load more.
                    if (mEnablePullLoad && mFooterView.getBottomMargin() > PULL_LOAD_MORE_DELTA
                            && !mPullLoading) {
                        startLoadMore();
                    }
                    resetFooterHeight();
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            if (mScrollBack == SCROLLBACK_HEADER) {
                mHeaderView.setVisiableHeight(mScroller.getCurrY());
            } else {
                mFooterView.setBottomMargin(mScroller.getCurrY());
            }
            postInvalidate();
            invokeOnScrolling();
        }
        super.computeScroll();
    }

    //其实没用到
    @Override
    public void setOnScrollListener(OnScrollListener l) {
//        Log.d(TAG, "setOnScrollListener()--->");
        mScrollListener = l;
    }

    // 在initWithContext中调用了super.setOnScrollListener(this)，并且实现了onSrollerListener
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //可以理解成从父类的回调，但是mScrollListener其实为空，下面的onScroll类似
//        Log.d(TAG, "onScrollStateChanged()--->");
        if (mScrollListener != null) {
            mScrollListener.onScrollStateChanged(view, scrollState);
        }else{
           // LogUtil.e(TAG, "onScrollStateChanged()---> mScrollListener is null");
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        mTotalItemCount = totalItemCount;
        if (mScrollListener != null) {
            //LogUtil.e(TAG, "onScroll()---> onScroll");
            mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }else{
            //LogUtil.e(TAG, "onScroll()---> mScrollListener is null");
        }
    }

    public void setXListViewListener(IXListViewListener l) {
        mListViewListener = l;
    }

    //进入自动刷新
    public void autoRefresh() {
        mLastY = -1; // reset
        // 判断是否在第一行，如果不是第一行，则不执行
        if (getFirstVisiblePosition() == 0) {
            // 判断是否可刷新和不处于刷新状态
            if (mEnablePullRefresh && mPullRefreshing != true) {
                mPullRefreshing = true;
                mScrollBack = SCROLLBACK_HEADER;
                if (mHeaderViewHeight == 0) {
                    int width = ((WindowManager) this.getContext()
                            .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                                    .getWidth();
                    mHeaderViewContent.measure(
                            MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                            MeasureSpec.makeMeasureSpec((1 << 30) - 1, MeasureSpec.AT_MOST));
                    mScroller.startScroll(0, 0, 0, mHeaderViewContent.getMeasuredHeight(),
                            SCROLL_DURATION);
                    invalidate();
                } else {
                    mScroller.startScroll(0, 0, 0, mHeaderViewHeight, SCROLL_DURATION);
                    invalidate();
                }
                mHeaderView.setState(XListViewHeader.STATE_REFRESHING);
                if (mListViewListener != null) {
                    mListViewListener.onRefresh();
                }
            }
            resetHeaderHeight();
        }
    }

    /**
     * you can listen ListView.OnScrollListener or this one. it will invoke
     * onXScrolling when header/footer scroll back.
     */
    public interface OnXScrollListener extends OnScrollListener {
        public void onXScrolling(View view);
    }

    /**
     * implements this interface to get refresh/load more event.
     */
    public interface IXListViewListener {
        public void onRefresh();

        public void onLoadMore();
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
////        //设置为Integer.MAX_VALUE>>2 是listview全部展开
////        int measureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);
//////设置为400是设置listview的高度只能有400 不全部展开   实现可以滑动的效果
////        int measureSpec1 = MeasureSpec.makeMeasureSpec(800, MeasureSpec.AT_MOST);
////        super.onMeasure(widthMeasureSpec, measureSpec1);
//
//
////        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
////                MeasureSpec.AT_MOST);
////        super.onMeasure(widthMeasureSpec, expandSpec);
//        //重新设置高度
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);
//    }

}
